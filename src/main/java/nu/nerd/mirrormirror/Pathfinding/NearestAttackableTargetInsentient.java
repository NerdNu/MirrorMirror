package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class NearestAttackableTargetInsentient extends AbstractPathfinderGoal {


    private String target;


    public NearestAttackableTargetInsentient(LivingEntity baseEntity, String target) {
        super(baseEntity);
        this.target = target;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalNearestAttackableTargetInsentient");
            Constructor constructor = c.getConstructor(insentient, Class.class);
            return constructor.newInstance(insentient.cast(getHandle()), NMSHelper.getNMSClassByName(target));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalNearestAttackableTargetInsentient");
            ex.printStackTrace();
            return null;
        }
    }


}
