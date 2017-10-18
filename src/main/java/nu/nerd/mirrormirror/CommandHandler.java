package nu.nerd.mirrormirror;


import com.google.common.collect.Sets;
import nu.nerd.mirrormirror.Pathfinding.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


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
            else if (args[0].equalsIgnoreCase("creeper")) {
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.CREEPER);
                ExtendedEntity e = new ExtendedEntity(base);
                e.clearGoals();
                e.injectGoal(2, new MeleeAttack(base, 1.0d, false));
                e.injectGoal(7, new RandomStrollLand(base, 0.8d));
                e.injectGoal(8, new RandomLookAround(base));
                e.injectGoal(1, new Panic(base, 1.4d));
                e.clearTargets();
                e.injectTarget(2, new NearestAttackableTarget(base, "EntityVillager"));
            }
            else if (args[0].equalsIgnoreCase("skeleton")) {
                // spawn a skeleton with custom AI
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                ExtendedEntity e = new ExtendedEntity(base);
                e.clearGoals();
                e.injectGoal(1, new BowShoot(base, 1.0d, 20, 15.0f));
                e.injectGoal(3, new FleeSun(base, 1.5d));
                e.injectGoal(3, new AvoidTarget(base, "EntityChicken", 2.0F, 1.0D, 1.2D));
                e.clearTargets();
                e.injectTarget(1, new HurtByTarget(base, true, "EntityHuman"));
                e.injectTarget(2, new NearestAttackableTarget(base, "EntityVillager"));
            }
            else if (args[0].equalsIgnoreCase("chicken")) {
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.CHICKEN);
                ExtendedEntity e = new ExtendedEntity(base);
                e.clearGoals();
                e.injectGoal(2, new Tempt(base, 1.0d, false, Sets.newHashSet(new ItemStack(Material.ANVIL))));
                e.injectGoal(3, new LookAtPlayer(base, "EntityVillager"));
                e.clearTargets();
            }
            else if (args[0].equalsIgnoreCase("llama")) {
                Player p = (Player) sender;
                LivingEntity base = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.LLAMA);
                ExtendedEntity e = new ExtendedEntity(base);
                e.clearGoals();
                e.injectGoal(1, new LlamaFollow(base, 2.0d));
                e.injectGoal(2, new ArrowAttack(base, 1.25d, 40, 20.0f));
                e.clearTargets();
                e.injectTarget(1, new NearestAttackableTarget(base, "EntityHuman"));
            }
            return true;
        }
        return false;
    }


}
