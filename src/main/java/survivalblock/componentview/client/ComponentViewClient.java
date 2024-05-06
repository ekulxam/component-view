package survivalblock.componentview.client;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.Component;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import survivalblock.componentview.common.config.ComponentViewConfig;

import java.util.List;

public class ComponentViewClient implements ClientModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.



	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			if ((MinecraftClient.getInstance().options.advancedItemTooltips && ComponentViewConfig.alwaysShowAdvancedTooltips) || Screen.hasControlDown() || Screen.hasAltDown() || Screen.hasShiftDown()) {
				for (Component<?> component : stack.getComponents()) {
					Identifier id = Registries.DATA_COMPONENT_TYPE.getId(component.type());
                    componentView(lines, component, id);
                }
			}
		});
	}

	private static void componentView(List<Text> lines, Component<?> component, Identifier id){
		if (ComponentViewConfig.removedComponents.contains(component.type().toString())) {
			return;
		}
		int glow = Screen.hasShiftDown() ? ComponentViewConfig.componentTypeColor.getRGB() : 5592405;
		int lighter = Screen.hasShiftDown() ? ComponentViewConfig.componentValueColor.getRGB() : 5592405;
		if (ComponentViewConfig.translateThroughIdentifier && (Screen.hasControlDown() || Screen.hasAltDown()) && id != null) {
			lines.add(Text.literal(id.toTranslationKey()).withColor(glow)
					.append(Text.literal(" : ").withColor(glow))
					.append(Text.literal(component.value().toString()).withColor(lighter)));
		} else {
			lines.add(Text.literal(component.type().toString()).withColor(glow)
					.append(Text.literal(" : ").withColor(glow))
					.append(Text.literal(component.value().toString()).withColor(lighter)));
		}
	}
}