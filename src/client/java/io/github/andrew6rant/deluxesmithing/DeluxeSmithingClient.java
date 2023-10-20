package io.github.andrew6rant.deluxesmithing;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.andrew6rant.deluxesmithing.config.Config;
import net.fabricmc.api.ClientModInitializer;

public class DeluxeSmithingClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MidnightConfig.init("deluxesmithing", Config.class);
	}
}