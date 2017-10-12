package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * The Entity will target an follow its parent
 */
public class FollowParent extends AbstractPathfinderGoal {


    private double speed;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speed the speed the entity will move at
     */
    public FollowParent(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class animal = NMSHelper.getNMSClassByName("EntityAnimal");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalFollowParent");
            Constructor constructor = c.getConstructor(animal, double.class);
            return constructor.newInstance(animal.cast(getHandle()), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalFollowParent");
            ex.printStackTrace();
            return null;
        }
    }


}
