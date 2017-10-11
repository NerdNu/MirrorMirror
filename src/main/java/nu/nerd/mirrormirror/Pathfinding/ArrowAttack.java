package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class ArrowAttack extends AbstractPathfinderGoal {


    private double d0;
    private int i;
    private int j;
    private float f;


    public ArrowAttack(LivingEntity baseEntity, double d0, int i, int j, float f) {
        super(baseEntity);
        this.d0 = d0;
        this.i = i;
        this.j = j;
        this.f = f;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class ranged = NMSHelper.getNMSClassByName("IRangedEntity");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalArrowAttack");
            Constructor constructor = c.getConstructor(ranged, double.class, int.class, int.class, float.class);
            return constructor.newInstance(getHandle(), d0, i, j, f);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalArrowAttack");
            ex.printStackTrace();
            return null;
        }
    }


}
