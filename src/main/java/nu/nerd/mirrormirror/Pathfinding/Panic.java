package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class Panic extends AbstractPathfinderGoal {


    private double d0;


    public Panic(LivingEntity baseEntity, double d0) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalPanic");
            Constructor constructor = c.getConstructor(creature, double.class);
            return constructor.newInstance(creature.cast(getHandle()), d0);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalPanic");
            ex.printStackTrace();
            return null;
        }
    }


}
