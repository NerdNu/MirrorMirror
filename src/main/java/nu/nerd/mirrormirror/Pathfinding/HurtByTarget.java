package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

public class HurtByTarget extends AbstractPathfinderGoal {


    private boolean flag;
    private String[] classes;


    public HurtByTarget(LivingEntity baseEntity, boolean flag, String... classes) {
        super(baseEntity);
        this.flag = flag;
        this.classes = classes;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Set<Class> classSet = new HashSet<Class>();
            for (String name : classes) {
                classSet.add(NMSHelper.getNMSClassByName(name));
            }
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalHurtByTarget");
            Constructor constructor = c.getConstructor(creature, boolean.class, Class[].class);
            return constructor.newInstance(creature.cast(getHandle()), flag, classSet.toArray(new Class[classSet.size()]));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalHurtByTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
