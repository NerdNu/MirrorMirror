package nu.nerd.mirrormirror;

import org.bukkit.plugin.java.JavaPlugin;


public class MirrorMirror extends JavaPlugin {


    public static MirrorMirror instance;


    public void onEnable() {
        MirrorMirror.instance = this;
        new CommandHandler(this);
    }


}
