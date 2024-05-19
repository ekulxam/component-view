package survivalblock.componentview.common.config;

import net.minecraft.client.gui.screen.Screen;
import survivalblock.componentview.common.ComponentView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentViewConfig {

    public static boolean alwaysShowAdvancedTooltips = true;
    public static boolean translateThroughIdentifier = false;
    public static boolean removeUnderscoresAndNamespace = false;
    public static Color componentTypeColor = new Color(5635925);
    public static Color componentValueColor= new Color(11184810);
    public static List<String> removedComponents = new ArrayList<>();
    public static boolean shiftOppositeEffect = false;
    public static boolean onlyShowComponentTypes = false;
    public static boolean showNormallyHiddenComponents = true;
    public static boolean formatUsingNewlines = false;

    public static final List<String> DEFAULT_REMOVED_COMPONENTS = new ArrayList<>();


    public static Screen create(Screen parent) {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.create(parent) : parent;
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
        removedComponents.addAll(DEFAULT_REMOVED_COMPONENTS);
    }
}
