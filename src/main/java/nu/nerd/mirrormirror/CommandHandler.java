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
                e.injectGoals(
                        new FloatGoal(base),
                        new LookAtPlayer(base)
                );
                e.injectTargets(
                        new NearestAttackableTarget(base, "EntityHuman")
                );
            }
            else if (args[0].equalsIgnoreCase("giant")) {
                // spawn a giant with regular zombie AI
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.GIANT);
                ExtendedEntity e = new ExtendedEntity(base);
                e.injectGoals(
                        new FloatGoal(base),
                        new MeleeAttack(base, 1.0d, false),
                        new MoveTowardRestriction(base, 1.0d),
                        new RandomStrollLand(base, 1.0d),
                        new LookAtPlayer(base),
                        new RandomLookAround(base)
                );
                e.injectTargets(
                        new NearestAttackableTarget(base, "EntityHuman"),
                        new NearestAttackableTarget(base, "EntityVillager"),
                        new NearestAttackableTarget(base, "EntityIronGolem"),
                        new MoveThroughVillage(base, 1.0d, false)
                );
            }
            return true;
        }
        return false;
    }


}
