package survivalblock.componentview.common.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import survivalblock.componentview.common.ComponentView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().alwaysShowAdvancedTooltips, () -> ComponentViewYACLCompat.HANDLER.instance().alwaysShowAdvancedTooltips, newVal -> ComponentViewYACLCompat.HANDLER.instance().alwaysShowAdvancedTooltips = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.identifier"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.identifier.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().translateThroughIdentifier, () -> ComponentViewYACLCompat.HANDLER.instance().translateThroughIdentifier, newVal -> ComponentViewYACLCompat.HANDLER.instance().translateThroughIdentifier = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.color.type"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.color.type.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().componentTypeColor, () -> ComponentViewYACLCompat.HANDLER.instance().componentTypeColor, newVal -> ComponentViewYACLCompat.HANDLER.instance().componentTypeColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.color.value"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.color.value.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().componentValueColor, () -> ComponentViewYACLCompat.HANDLER.instance().componentValueColor, newVal -> ComponentViewYACLCompat.HANDLER.instance().componentValueColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.shiftopposite"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.shiftopposite.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().shiftOppositeEffect, () -> ComponentViewYACLCompat.HANDLER.instance().shiftOppositeEffect, newVal -> ComponentViewYACLCompat.HANDLER.instance().shiftOppositeEffect = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.onlyshowcomponenttypes"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.onlyshowcomponenttypes.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().onlyShowComponentTypes, () -> ComponentViewYACLCompat.HANDLER.instance().onlyShowComponentTypes, newVal -> ComponentViewYACLCompat.HANDLER.instance().onlyShowComponentTypes = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.removeunderscoresandnamespace"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.removeunderscoresandnamespace.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().removeUnderscoresAndNamespace, () -> ComponentViewYACLCompat.HANDLER.instance().removeUnderscoresAndNamespace, newVal -> ComponentViewYACLCompat.HANDLER.instance().removeUnderscoresAndNamespace = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.shownormallyhiddencomponents"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.shownormallyhiddencomponents.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().showNormallyHiddenComponents, () -> ComponentViewYACLCompat.HANDLER.instance().showNormallyHiddenComponents, newVal -> ComponentViewYACLCompat.HANDLER.instance().showNormallyHiddenComponents = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("componentview.yacl.option.boolean.formatusingnewlines"))
                                        .description(OptionDescription.of(Text.translatable("componentview.yacl.option.boolean.formatusingnewlines.desc")))
                                        .binding(ComponentViewYACLCompat.HANDLER.defaults().formatUsingNewlines, () -> ComponentViewYACLCompat.HANDLER.instance().formatUsingNewlines, newVal -> ComponentViewYACLCompat.HANDLER.instance().formatUsingNewlines = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .group((ListOption.<String>createBuilder()
                                .name(Text.translatable("componentview.yacl.group.list.hiddencomponents"))
                                .description(OptionDescription.of(Text.translatable("componentview.yacl.group.list.hiddencomponents.desc")))
                                .binding(ComponentViewConfig.DEFAULT_REMOVED_COMPONENTS, () -> ComponentViewYACLCompat.HANDLER.instance().removedComponents, newVal -> ComponentViewYACLCompat.HANDLER.instance().removedComponents = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build()))
                        .build())
                .save(() -> ComponentViewYACLCompat.HANDLER.save())
                .build()
                .generateScreen(parent);
    }

    public static ConfigClassHandler<ComponentViewYACLCompat> HANDLER = ConfigClassHandler.createBuilder(ComponentViewYACLCompat.class)
            .id(new Identifier(ComponentView.MOD_ID, "my_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("my_mod.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public boolean alwaysShowAdvancedTooltips = true;
    @SerialEntry
    public boolean translateThroughIdentifier = false;
    @SerialEntry
    public boolean removeUnderscoresAndNamespace = false;
    @SerialEntry
    public Color componentTypeColor = new Color(5635925);
    @SerialEntry
    public Color componentValueColor= new Color(11184810);
    @SerialEntry
    public List<String> removedComponents = new ArrayList<>();
    @SerialEntry
    public boolean shiftOppositeEffect = false;
    @SerialEntry
    public boolean onlyShowComponentTypes = false;
    @SerialEntry
    public boolean showNormallyHiddenComponents = true;
    @SerialEntry
    public boolean formatUsingNewlines = false;
}
