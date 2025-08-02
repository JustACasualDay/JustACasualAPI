package at.justacasualday.justACasualAPI;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

abstract class Config {
    public static void init(File file, Plugin plugin, String filename)
    {
        if(!file.exists())
        {
            file.getParentFile().mkdirs();

            if (plugin != null) {

                if (plugin.getResource(filename) != null) {
                    plugin.saveResource(filename, false);
                    return;
                }
            }

            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
