package survivalblock.componentview.client;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.Component;
import survivalblock.componentview.common.ComponentViewUtil;
import survivalblock.componentview.common.config.ComponentViewConfig;

public class ComponentViewClient implements ClientModInitializer {


	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			if ((MinecraftClient.getInstance().options.advancedItemTooltips && ComponentViewConfig.alwaysShowAdvancedTooltips) || Screen.hasControlDown() || Screen.hasAltDown() || Screen.hasShiftDown()) {
				for (Component<?> component : stack.getComponents()) {
					// in total: comes out to O(n^2) when using indent stuff (because of my while loop)
					ComponentViewUtil.componentView(lines, component);
				}
			}
		});
	}
}