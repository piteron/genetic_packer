package genetic.web.controller

import genetic.Evolution
import genetic.api.individual.impl.RatedIndividual
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.piteron.genetic.packer.Body
import pl.piteron.genetic.packer.Box
import pl.piteron.genetic.packer.Embryo
import pl.piteron.genetic.packer.GreatestCommonDivisorCalculator
import pl.piteron.genetic.packer.PackerConfiguration
import reactor.core.publisher.Flux
import kotlin.math.sqrt
import kotlin.streams.asStream

@RestController
@SpringBootApplication
@Import(PackerConfiguration::class)
class PackerController(
    private val evolution: Evolution<Body, Embryo>
) {

  @PostMapping("pack", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
  fun fluxBoxes(@RequestBody packerRequest: PackerRequest): Flux<RatedIndividual<Body>> {
    val scale = GreatestCommonDivisorCalculator.calculate(packerRequest.boxes.flatMap { listOf(it.depth, it.width) })
    val scaledBoxes = packerRequest.boxes.map {
      val depth = it.depth / scale
      Box(
          id = it.id,
          width = it.width / scale,
          depth = depth,
          canBeRotated = packerRequest.container.width >= depth
      )
    }
    val generationSize = (sqrt(packerRequest.boxes.size.toDouble()) * 100).toInt()
    val generations = evolution(
        Evolution.Context(
            numberOfGenerations = 500,
            generationSize = generationSize,
            numberOfEliteIndividuals = generationSize / 10,
            embryo = Embryo(
                containerWidth = packerRequest.container.width,
                containerDepth = packerRequest.container.depth,
                boxes = scaledBoxes,
                minSpace = packerRequest.boxes.fold(0) { space, box -> space + box.width * box.depth }
            )
        )
    )
    return Flux.fromStream(generations.asStream())
  }
}

data class PackerRequest(
    val container: Container,
    val boxes: List<Box>
) {

  data class Box(
      val id: Int,
      val width: Int,
      val depth: Int
  )
}

data class PackerResponse(
    val container: RatedIndividual<Box>
)

interface Size {
  val width: Int
  val depth: Int
}

interface Position {
  val x: Int
  val y: Int
}

interface Rotated {
  val rotated: Boolean
}

data class Container(
    override val width: Int,
    override val depth: Int
) : Size

//data class Box(
//    override val width: Int,
//    override val depth: Int
//) : Size

data class PositionedBox(
    override val width: Int,
    override val depth: Int,
    override val x: Int,
    override val y: Int,
    override val rotated: Boolean = false
) : Size, Position, Rotated

fun main(args: Array<String>) {
  runApplication<PackerController>(*args)
}