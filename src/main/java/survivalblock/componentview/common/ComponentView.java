package survivalblock.componentview.common;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComponentView implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("component_view");
	public static boolean shouldDoConfig = false;

	@Override
	public void onInitialize() {
		shouldDoConfig = FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");
	}
}