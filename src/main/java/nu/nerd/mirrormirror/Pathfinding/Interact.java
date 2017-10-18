package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Interact with the nearest player
 */
public class Interact extends AbstractPathfinderGoal {


    private String target;
    private float maxDist;
    private float chance;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param target NMS class name of the class to interact with
     * @param maxDist maximum distance in blocks
     * @param chance percentage (0.0-1.0) representing the chance of interaction
     */
    public Interact(LivingEntity baseEntity, String target, float maxDist, float chance) {
        super(baseEntity);
        this.target = target;
        this.maxDist = maxDist;
        this.chance = chance;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalInteract");
            Constructor constructor = c.getConstructor(insentient, Class.class, float.class, float.class);
            return constructor.newInstance(insentient.cast(getHandle()), NMSHelper.getNMSClassByName(target), maxDist, chance);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalInteract");
            ex.printStackTrace();
            return null;
        }
    }


}
