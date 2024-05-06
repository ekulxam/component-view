package survivalblock.componentview.common.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.CyclingListControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
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

    public static Screen create(Screen parent) {
        return ComponentView.shouldDoConfig ? ComponentViewYACLCompat.create(parent) : parent;
    }
}
