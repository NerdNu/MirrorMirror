package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class Breed extends AbstractPathfinderGoal {


    private double d0;


    public Breed(LivingEntity baseEntity, double d0) {
        super(baseEntity);
        this.d0 = d0;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class animal = NMSHelper.getNMSClassByName("EntityAnimal");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalBreed");
            Constructor constructor = c.getConstructor(animal, double.class);
            return constructor.newInstance(animal.cast(getHandle()), d0);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalBreed");
            ex.printStackTrace();
            return null;
        }
    }


}
