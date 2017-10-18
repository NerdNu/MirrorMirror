package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Entity will open doors
 */
public class OpenDoor extends AbstractPathfinderGoal {


    private boolean shouldClose;


    public OpenDoor(LivingEntity baseEntity, boolean shouldClose) {
        super(baseEntity);
        this.shouldClose = shouldClose;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalOpenDoor");
            Constructor constructor = c.getConstructor(insentient, boolean.class);
            return constructor.newInstance(insentient.cast(getHandle()), shouldClose);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalOpenDoor");
            ex.printStackTrace();
            return null;
        }
    }


}
