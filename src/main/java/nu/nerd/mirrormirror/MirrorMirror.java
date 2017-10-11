package nu.nerd.mirrormirror;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class MirrorMirror extends JavaPlugin {


    public static MirrorMirror instance;


    public void onEnable() {
        MirrorMirror.instance = this;
        new CommandHandler(this);
    }


    public static Logger logger() {
        return MirrorMirror.instance.getLogger();
    }


}
