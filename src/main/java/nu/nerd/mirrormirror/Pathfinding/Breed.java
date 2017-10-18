package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class Breed extends AbstractPathfinderGoal {


    private double speed;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speed the speed the entity moves at while breeding
     */
    public Breed(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class animal = NMSHelper.getNMSClassByName("EntityAnimal");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalBreed");
            Constructor constructor = c.getConstructor(animal, double.class);
            return constructor.newInstance(animal.cast(getHandle()), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalBreed");
            ex.printStackTrace();
            return null;
        }
    }


}
