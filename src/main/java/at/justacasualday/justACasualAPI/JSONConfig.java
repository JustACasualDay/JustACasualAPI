package at.justacasualday.justACasualAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 * Creates a JSON Config using Gson
 * @param <T> ObjectType to store
 */
public class JSONConfig<T> {
    private final File file;
    private final Gson gson;
    private final Type type;
    private List<T> objectList;

    /**
     * Initializes a new JSONConfig
     * @param folder path to the folder
     * @param filename the name of the file
     * @param clazz Class of the Object that will be stored
     */
    public JSONConfig(@NotNull File folder, @NotNull String filename, @NotNull Class<T> clazz) {
        file = new File(folder, filename);
        gson = new GsonBuilder().setPrettyPrinting().create();
        type = TypeToken.getParameterized(List.class, clazz).getType();

        init();
        objectList = loadFromFile();
        if(objectList == null) objectList = new ArrayList<>();
    }

    /**
     * Initializes a new JSONConfig
     * @param filename the name of the file
     * @param plugin an instance of the plugin
     * @param clazz Class of the Object that will be stored
     */
    public JSONConfig(@NotNull String filename, Plugin plugin, Class<T> clazz) {
        file = new File(plugin.getDataFolder(), filename);
        gson = new GsonBuilder().setPrettyPrinting().create();
        type = TypeToken.getParameterized(List.class, clazz).getType();

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

        objectList = loadFromFile();
        if(objectList == null) objectList = new ArrayList<>();
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
    }

    private List<T> loadFromFile()
    {
        try {
            FileReader reader = new FileReader(file);
            List<T> list = gson.fromJson(reader, type);
            reader.close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the current list to file
     * @return returns if saving was successful
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
     * returns the stored Objects
     * @return the object list
     */
    public List<T> getObjectList() {
        return objectList;
    }
}
