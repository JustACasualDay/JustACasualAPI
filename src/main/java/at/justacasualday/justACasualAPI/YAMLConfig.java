package at.justacasualday.justACasualAPI;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Creates a YAML Config for storing different kinds of data
 */
public class YAMLConfig {
    private final String filepath;
    private final String filename;
    private final File file;
    private FileConfiguration fileConfiguration;
    private final Plugin plugin;

    /**
     * Creates a new Instance of YAMLConfig
     * @param folder path to the folder
     * @param filename the name of the file
     */
    public YAMLConfig(@NotNull File folder, @NotNull String filename) {
        this.filepath = folder.getAbsolutePath() + "/" + filename;
        file = new File(folder, filename);
        this.filename = filename;
        plugin = null;

        init();
    }

    /**
     * Constructor for new Instance using a Plugin resource
     * @param filename name of the Resource
     * @param plugin an Instance of the Plugin
     */
    public YAMLConfig(@NotNull String filename, Plugin plugin) {
        this.filepath = plugin.getDataFolder() + "/" + filename;
        file = new File(plugin.getDataFolder(), filename);
        this.filename = filename;
        this.plugin = plugin;

        if(!file.exists())
        {
            file.getParentFile().mkdirs();

            if(plugin.getResource(filename) != null)
            {
                plugin.saveResource(filename, false);
            } else {
                init();
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    private void init()
    {
        if(!file.exists())
        {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * saves the fileConfiguration to file
     */
    public void saveConfig()
    {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears the current Config
     */
    public void clearConfig()
    {
        if(plugin == null)
        {
            if(file.delete())
            {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            plugin.saveResource(filename, true);
        }
    }

    /**
     * @param path path to the Location
     * @return the requested Location
     */
    public Location getLocation(String path)
    {
        return fileConfiguration.getLocation(path);
    }

    /**
     * Stores a given Object at the filepath
     * @param path path to store the data
     * @param value the data
     */
    public void setValue(@NotNull String path, Object value)
    {
        fileConfiguration.set(path, value);
    }

    /**
     * Gets an  Object from the fileConfiguration
     * ONLY USE WHEN ABSOLUTELY NECESSARY
     * @param path path to the data
     * @return the requested Object
     */
    public Object getValue(@NotNull String path)
    {
        return fileConfiguration.get(path);
    }

    public int getInt(String path)
    {
        return fileConfiguration.getInt(path);
    }

    public double getDouble(String path)
    {
        return fileConfiguration.getDouble(path);
    }

    public String getString(String path)
    {
        return fileConfiguration.getString(path);
    }

    public Object getObject(String path)
    {
        return fileConfiguration.get(path);
    }

    public boolean getBoolean(String path)
    {
        return fileConfiguration.getBoolean(path);
    }

    public ConfigurationSection getConfigSection(String path)
    {
        return fileConfiguration.getConfigurationSection(path);
    }

    public Collection<String> getSection(String path, @NotNull boolean deep)
    {
        return fileConfiguration.getConfigurationSection(path).getKeys(deep);
    }

}
