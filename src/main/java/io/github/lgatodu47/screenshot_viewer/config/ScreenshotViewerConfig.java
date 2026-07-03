package io.github.lgatodu47.screenshot_viewer.config;

import io.github.lgatodu47.catconfig.CatConfig;
import io.github.lgatodu47.catconfig.CatConfigLogger;
import io.github.lgatodu47.catconfig.ConfigOption;
import io.github.lgatodu47.catconfig.ConfigOptionAccess;
import io.github.lgatodu47.catconfigmc.MinecraftConfigSides;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class ScreenshotViewerConfig extends CatConfig {
    public ScreenshotViewerConfig() {
        super(MinecraftConfigSides.CLIENT, "screenshot_viewer", CatConfigLogger.delegate(LoggerFactory.getLogger("Screenshot Viewer Config")));
    }

    @Override
    protected @NotNull ConfigOptionAccess getConfigOptions() {
        return ScreenshotViewerOptions.OPTIONS;
    }

    @Override
    protected @NotNull Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    protected @Nullable ConfigWatcher makeAndStartConfigWatcherThread() {
        ConfigWatcher watcher = new ConfigWatcher("Screenshot Viewer's Config Watcher");
        watcher.start();
        return watcher;
    }

    public boolean getBool(ConfigOption<Boolean> option) {
        return get(option).filter(Boolean::booleanValue).isPresent();
    }

    public boolean getBoolOrTrue(ConfigOption<Boolean> option) {
        return get(option).filter(b -> !b).isEmpty();
    }

    public <E extends Enum<E>> boolean enumEquals(ConfigOption<E> option, E value) {
        return get(option).filter(value::equals).isPresent();
    }
}
