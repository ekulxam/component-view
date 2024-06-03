package survivalblock.componentview.client;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.Component;
import survivalblock.componentview.common.ComponentViewUtil;
import survivalblock.componentview.common.config.ComponentViewConfig;

public class ComponentViewClient implements ClientModInitializer {


	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			if (!ComponentViewConfig.shouldRun()) {
				return;
			}
			if (ComponentViewUtil.shouldShowInAdvancedTooltips() || Screen.hasControlDown() || Screen.hasAltDown() || Screen.hasShiftDown()) {
				for (Component<?> component : stack.getComponents()) {
					ComponentViewUtil.componentView(lines, component);
				}
			}
		});
	}
}