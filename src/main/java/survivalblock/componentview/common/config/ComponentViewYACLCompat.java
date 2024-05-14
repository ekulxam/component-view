package survivalblock.componentview.common.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import survivalblock.componentview.common.ComponentView;

import java.awt.*;

public class ComponentViewYACLCompat {

    public static Screen create(Screen parent){
        if (!ComponentView.shouldDoConfig) {
            throw new UnsupportedOperationException();
        }
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("componentview.yacl.category.main"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("componentview.yacl.category.main"))
                        .tooltip(Text.translatable("componentview.yacl.category.main.tooltip"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("componentview.yacl.group.client"))
                                .description(OptionDescription.of(Text.translatable("componentview.yacl.option.color.type.desc")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.advancedtooltips"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.advancedtooltips.desc")))
                                        .binding(true, () -> ComponentViewConfig.alwaysShowAdvancedTooltips, newVal -> ComponentViewConfig.alwaysShowAdvancedTooltips = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.identifier"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.identifier.desc")))
                                        .binding(true, () -> ComponentViewConfig.translateThroughIdentifier, newVal -> ComponentViewConfig.translateThroughIdentifier = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.color.type"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.color.type.desc")))
                                        .binding(new Color(5635925), () -> ComponentViewConfig.componentTypeColor, newVal -> ComponentViewConfig.componentTypeColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.color.value"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.color.value.desc")))
                                        .binding(new Color(11184810), () -> ComponentViewConfig.componentValueColor, newVal -> ComponentViewConfig.componentValueColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.shiftopposite"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.shiftopposite.desc")))
                                        .binding(false, () -> ComponentViewConfig.shiftOppositeEffect, newVal -> ComponentViewConfig.shiftOppositeEffect = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.onlyshowcomponenttypes"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.onlyshowcomponenttypes.desc")))
                                        .binding(false, () -> ComponentViewConfig.onlyShowComponentTypes, newVal -> ComponentViewConfig.onlyShowComponentTypes = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .group((ListOption.<String>createBuilder()
                                .name(Text.translatable("componentview.yacl.group.list.hiddencomponents"))
                                .description(OptionDescription.of(Text.translatable("componentview.yacl.group.list.hiddencomponents.desc")))
                                .binding(ComponentViewConfig.DEFAULT_REMOVED_COMPONENTS, () -> ComponentViewConfig.removedComponents, newVal -> ComponentViewConfig.removedComponents = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build()))
                        .build())
                .build()
                .generateScreen(parent);
    }
}
