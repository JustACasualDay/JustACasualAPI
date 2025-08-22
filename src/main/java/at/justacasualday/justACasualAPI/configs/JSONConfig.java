package at.justacasualday.justACasualAPI.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 * Creates a JSON Config using Gson
 * @param <T> ObjectType to store
 */
public class JSONConfig<T> {
    private final String filename;
    private final File file;
    private final Gson gson;
    private final Type type;
    private final Plugin plugin;
    private List<T> objectList;

    /**
     * Initializes a new JSONConfig
     * @param folder path to the folder
     * @param filename name of the file
     * @param clazz Class of the Object that will be stored
     */
    public JSONConfig(@NotNull File folder, @NotNull String filename, @NotNull Class<T> clazz) {
        this.filename = filename;
        file = new File(folder, filename);
        gson = new GsonBuilder().setPrettyPrinting().create();
        type = TypeToken.getParameterized(List.class, clazz).getType();
        plugin = null;

        ConfigUtils.init(file, null, filename);
        loadFromFile();
        if(objectList == null) objectList = new ArrayList<>();
    }

    /**
     * Initializes a new JSONConfig
     * @param filename name of the file
     * @param plugin an instance of the plugin
     * @param clazz Class of the Object that will be stored
     */
    public JSONConfig(@NotNull String filename, Plugin plugin, Class<T> clazz) {
        this.filename = filename;
        file = new File(plugin.getDataFolder(), filename);
        gson = new GsonBuilder().setPrettyPrinting().create();
        type = TypeToken.getParameterized(List.class, clazz).getType();
        this.plugin = plugin;

        ConfigUtils.init(file, plugin, filename);
        loadFromFile();

        if(objectList == null) objectList = new ArrayList<>();
    }

    public void loadFromFile()
    {
        try {
            FileReader reader = new FileReader(file);
            List<T> list = gson.fromJson(reader, type);
            reader.close();
            objectList = list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the current list to file
     * @return saving successful
     */
    public boolean saveToFile()
    {
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write(gson.toJson(objectList));
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Returns the stored Objects
     * @return Object List
     */
    public List<T> getObjectList() {
        return objectList;
    }
}
