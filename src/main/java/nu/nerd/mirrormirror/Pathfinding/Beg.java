package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

public class Beg extends AbstractPathfinderGoal {


    private float minDist;


    public Beg(LivingEntity baseEntity, float minDist) {
        super(baseEntity);
        this.minDist = minDist;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class wolf = NMSHelper.getNMSClassByName("EntityWolf");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalBeg");
            Constructor constructor = c.getConstructor(wolf, float.class);
            return constructor.newInstance(wolf.cast(getHandle()), minDist);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalBeg");
            ex.printStackTrace();
            return null;
        }
    }


}
