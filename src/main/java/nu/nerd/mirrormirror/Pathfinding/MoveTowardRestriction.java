package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

public class MoveTowardRestriction extends AbstractPathfinderGoal {


    private double d0;


    public MoveTowardRestriction(LivingEntity baseEntity, double d0) {
        super(baseEntity);
        this.d0 = d0;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMoveTowardsRestriction");
            Constructor constructor = c.getConstructor(creature, double.class);
            return constructor.newInstance(getHandle(), d0);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMoveTowardsRestriction");
            ex.printStackTrace();
            return null;
        }
    }


}
