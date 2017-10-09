package nu.nerd.mirrormirror;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public class CommandHandler implements CommandExecutor {


    private MirrorMirror plugin;


    public CommandHandler(MirrorMirror plugin) {
        this.plugin = plugin;
        plugin.getCommand("mirrormirror").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mirrormirror")) {
            //dev test
            Player p = (Player) sender;
            LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.CHICKEN);
            ExtendedEntity e = new ExtendedEntity(base);
            e.walkTo(p.getLocation().add(10, 0, 0), 1.0f);
            return true;
        }
        return false;
    }


}
