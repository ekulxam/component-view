package survivalblock.componentview.common.config;

import net.minecraft.client.gui.screen.Screen;
import survivalblock.componentview.common.ComponentView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentViewConfig {

    public static boolean alwaysShowAdvancedTooltips = true;
    public static boolean translateThroughIdentifier = true;
    public static Color componentTypeColor = new Color(5635925);
    public static Color componentValueColor= new Color(11184810);
    public static List<String> removedComponents = new ArrayList<>();
    public static boolean shiftOppositeEffect = false;

    public static Screen create(Screen parent) {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.create(parent) : parent;
    }
}
