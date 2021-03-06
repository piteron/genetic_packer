package genetic.packer.fx.translation.root;

import genetic.packer.fx.specification.BoundsGetter;
import genetic.packer.fx.specification.BoxTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("zTranslator")
public class ZTranslator extends BoxRootBasedTranslator {

    @Autowired
    public ZTranslator(@Qualifier("zBoxTranslator") BoxTranslator zTranslationSetter, BoundsGetter zSizeGetter) {
        super(zTranslationSetter, zSizeGetter);
    }
}
