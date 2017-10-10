package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class NearestAttackableTarget extends AbstractPathfinderGoal {


    public NearestAttackableTarget(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalNearestAttackableTarget");
            Constructor constructor = c.getConstructor(creature, Class.class, boolean.class);
            return constructor.newInstance(creature.cast(getHandle()), NMSHelper.getNMSClassByName("EntityHuman"), true);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalNearestAttackableTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
