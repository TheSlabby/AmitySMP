package codes.slab.amitysmp;

import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.File;

//ideally i would use mongodb but this is a simple solution for now
public class Data implements Serializable {
    private static transient final long serialVersionUID = -1681012206529286330L;

    //save vars
    public String motd;
    public HashMap<UUID, Location> homes; 

    public static final String path = "plugins/AmitySMP.data";

    //setup save object
    public Data(String motd, HashMap<UUID, Location> homes) {
        this.motd = motd;
        this.homes = homes;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Data loadData(String filePath) {
        File file = new File(filePath);
        if(!file.exists() || file.length() == 0) {
            return new Data("Default MOTD", new HashMap<UUID, Location>());
        }

        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setHome(UUID player, Location location) {
        Data data = loadData(path);
        if (data.homes == null) {
            data.homes = new HashMap<>();
        }
        data.homes.put(player, location);
        data.saveData(path);
    }

    public static void modifyMOTD(String motd) {
        Data data = loadData(path);
        data.motd = motd;
        data.saveData(path);
    }

}
