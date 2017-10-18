package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


/**
 * Lead an entity around with an item such as food.
 */
public class Tempt extends AbstractPathfinderGoal {


    private double speed;
    private boolean scaredByMovement;
    private Set<Object> nmsItems;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speed speed
     * @param scaredByMovement if true, the mob dislikes player movement (e.g. ocelot)
     * @param items Set of Bukkit ItemStacks representing temptable items
     */
    public Tempt(LivingEntity baseEntity, double speed, boolean scaredByMovement, Set<ItemStack> items) {
        super(baseEntity);
        this.speed = speed;
        this.scaredByMovement = scaredByMovement;
        this.nmsItems = new HashSet<Object>();
        try {
            for (ItemStack item : items) {
                Object nmsi = NMSHelper.getNMSItemStack(item);
                Method method = nmsi.getClass().getMethod("getItem");
                this.nmsItems.add(method.invoke(nmsi));
            }
        } catch (Exception ex) {
            MirrorMirror.logger().warning(ex.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalTempt");
            Constructor constructor = c.getConstructor(creature, double.class, boolean.class, Set.class);
            return constructor.newInstance(creature.cast(getHandle()), speed, scaredByMovement, nmsItems);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalTempt");
            ex.printStackTrace();
            return null;
        }
    }


}
