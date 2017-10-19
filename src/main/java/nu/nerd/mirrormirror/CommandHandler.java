package nu.nerd.mirrormirror;


import nu.nerd.mirrormirror.Pathfinding.*;
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
            if (args.length < 1) {
                sender.sendMessage("You must specify a subcommand.");
                sender.sendMessage("/mirrormirror is primarily used for development/testing.");
            }
            else if (args[0].equalsIgnoreCase("giant")) {
                // spawn a giant with regular zombie AI
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.GIANT);
                ExtendedEntity e = new ExtendedEntity(base);
                e.injectGoal(0, new FloatGoal(base));
                e.injectGoal(2, new MeleeAttack(base, 1.0d, false));
                e.injectGoal(5, new MoveTowardsRestriction(base, 1.0d));
                e.injectGoal(7, new RandomStrollLand(base, 1.0d));
                e.injectGoal(8, new LookAtPlayer(base));
                e.injectGoal(8, new RandomLookAround(base));
                e.injectGoal(6, new MoveThroughVillage(base, 1.0d, false));
                e.injectTarget(1, new HurtByTarget(base, true, "EntityPigZombie"));
                e.injectTarget(2, new NearestAttackableTarget(base, "EntityHuman"));
                e.injectTarget(3, new NearestAttackableTarget(base, "EntityVillager"));
                e.injectTarget(3, new NearestAttackableTarget(base, "EntityIronGolem"));
            }
            return true;
        }
        return false;
    }


}
