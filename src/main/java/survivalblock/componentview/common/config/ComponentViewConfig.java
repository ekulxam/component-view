package survivalblock.componentview.common.config;

import net.minecraft.client.gui.screen.Screen;
import survivalblock.componentview.common.ComponentView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentViewConfig {

    private static class Defaults {
        public static boolean shouldRun = true;
        private static final boolean alwaysShowAdvancedTooltips = true;
        private static final boolean translateThroughIdentifier = false;
        private static final boolean removeUnderscoresAndNamespace = false;
        private static final Color componentTypeColor = new Color(5635925);
        private static final Color componentValueColor= new Color(11184810);
        private static final List<String> removedComponents = new ArrayList<>();
        private static final boolean shiftOppositeEffect = false;
        private static final boolean onlyShowComponentTypes = false;
        private static final boolean showNormallyHiddenComponents = true;
        private static final boolean formatUsingNewlines = false;
    }

    public static boolean shouldRun() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().shouldRun : Defaults.shouldRun;
    }

    public static boolean isAlwaysShowAdvancedTooltips() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().alwaysShowAdvancedTooltips : Defaults.alwaysShowAdvancedTooltips;
    }

    public static boolean isTranslateThroughIdentifier() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().translateThroughIdentifier : Defaults.translateThroughIdentifier;
    }

    public static boolean isRemoveUnderscoresAndNamespace() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().removeUnderscoresAndNamespace : Defaults.removeUnderscoresAndNamespace;
    }

    public static Color getComponentTypeColor() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().componentTypeColor : Defaults.componentTypeColor;
    }

    public static Color getComponentValueColor() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().componentValueColor : Defaults.componentValueColor;
    }

    public static List<String> getRemovedComponents() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().removedComponents : Defaults.removedComponents;
    }

    public static boolean isShiftOppositeEffect() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().shiftOppositeEffect : Defaults.shiftOppositeEffect;
    }

    public static boolean isOnlyShowComponentTypes() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().onlyShowComponentTypes : Defaults.onlyShowComponentTypes;
    }

    public static boolean isShowNormallyHiddenComponents() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().showNormallyHiddenComponents : Defaults.showNormallyHiddenComponents;
    }

    public static boolean isFormatUsingNewlines() {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.HANDLER.instance().formatUsingNewlines : Defaults.formatUsingNewlines;
    }

    public static final List<String> DEFAULT_REMOVED_COMPONENTS = new ArrayList<>();

    public static Screen create(Screen parent) {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.create(parent) : null;
    }

    static {
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:banner_patterns");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:base_color");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:bundle_contents");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:charged_projectiles");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:custom_name");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:damage");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:dyed_color");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:enchantment_glint_override");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:instrument");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:intangible_projectile");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:item_name");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:lore");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:map_color");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:map_decorations");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:map_id");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:max_damage");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:max_stack_size");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:note_block_sound");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:map_decorations");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:rarity");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:tool");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:writable_book_content");
        DEFAULT_REMOVED_COMPONENTS.add("minecraft:written_book_content");
        Defaults.removedComponents.addAll(DEFAULT_REMOVED_COMPONENTS);
    }
}
