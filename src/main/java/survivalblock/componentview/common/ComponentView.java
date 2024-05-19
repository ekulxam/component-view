package survivalblock.componentview.common;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import survivalblock.componentview.common.config.ComponentViewYACLCompat;

public class ComponentView implements ModInitializer {

	public static final String MOD_ID = "component_view";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean shouldDoConfig = false;

	@Override
	public void onInitialize() {
		shouldDoConfig = FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");
		if (!shouldDoConfig) {
			LOGGER.warn("YACL is not installed, so Component View's Config will not be accessible!");
		} else {
			ComponentViewYACLCompat.HANDLER.load();
		}
	}
}