package at.justacasualday.justACasualAPI;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Creates a YAML Config for storing different kinds of data
 */
public class YAMLConfig {
    private final String filepath;
    private final File file;
    private FileConfiguration fileConfiguration;

    /**
     * Creates a new Instance of YAMLConfig
     * @param folder path to the folder
     * @param filename the name of the file
     */
    public YAMLConfig(@NotNull File folder, @NotNull String filename) {
        this.filepath = folder.getAbsolutePath() + "/" + filename;
        file = new File(folder, filename);

        init();
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
     * Stores a given Object at the filepath
     * @param path the path to store the data
     * @param value the actual data
     */
    public void setValue(@NotNull String path, Object value)
    {
        fileConfiguration.set(path, value);
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
