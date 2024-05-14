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

	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			if ((MinecraftClient.getInstance().options.advancedItemTooltips && ComponentViewConfig.alwaysShowAdvancedTooltips) || Screen.hasControlDown() || Screen.hasAltDown() || Screen.hasShiftDown()) {
				for (Component<?> component : stack.getComponents()) {
                    componentView(lines, component);
                }
			}
		});
	}

	private static void componentView(List<Text> lines, Component<?> component){
		if (ComponentViewConfig.removedComponents.contains(component.type().toString())) {
			return;
		}
		boolean vibrant = (Screen.hasShiftDown() && !ComponentViewConfig.shiftOppositeEffect) || (!Screen.hasShiftDown() && ComponentViewConfig.shiftOppositeEffect); // XOR
		int glow = vibrant ? ComponentViewConfig.componentTypeColor.getRGB() : 5592405;
		int lighter = vibrant ? ComponentViewConfig.componentValueColor.getRGB() : 5592405;
		Identifier id = Registries.DATA_COMPONENT_TYPE.getId(component.type());
		if (ComponentViewConfig.onlyShowComponentTypes) {
			if (ComponentViewConfig.translateThroughIdentifier && (Screen.hasControlDown() || Screen.hasAltDown()) && id != null) {
				lines.add(Text.literal(id.toTranslationKey()).withColor(glow));
			} else {
				lines.add(Text.literal(component.type().toString()).withColor(glow));
			}
		} else {
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
}