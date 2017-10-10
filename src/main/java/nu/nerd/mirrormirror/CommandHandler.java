package nu.nerd.mirrormirror;

import nu.nerd.mirrormirror.Pathfinding.AbstractPathfinderGoal;
import nu.nerd.mirrormirror.Pathfinding.LookAtPlayer;
import nu.nerd.mirrormirror.Pathfinding.NearestAttackableTarget;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class CommandHandler implements CommandExecutor {


    private MirrorMirror plugin;


    public CommandHandler(MirrorMirror plugin) {
        this.plugin = plugin;
        plugin.getCommand("mirrormirror").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mirrormirror")) {
            // dev test commands
            if (args.length < 1) {
                sender.sendMessage("You must specify a subcommand.");
            }
            else if (args[0].equalsIgnoreCase("walkten")) {
                // spawn a chicken that walks ten blocks forward
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.CHICKEN);
                ExtendedEntity e = new ExtendedEntity(base);
                e.walkTo(p.getLocation().add(10, 0, 0), 1.0f);
            }
            else if (args[0].equalsIgnoreCase("nopath")) {
                // spawn a zombie with no AI behaviors
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                ExtendedEntity e = new ExtendedEntity(base);
                e.clearGoals();
                e.clearTargets();
            }
            else if (args[0].equalsIgnoreCase("target")) {
                // spawn a zombie with some custom AI
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                ExtendedEntity e = new ExtendedEntity(base);
                e.injectGoals(new LookAtPlayer(base));
                e.injectTargets(new NearestAttackableTarget(base));
            }
            return true;
        }
        return false;
    }


}
