package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;


public abstract class AbstractPathfinderGoal {


    protected LivingEntity baseEntity;


    public AbstractPathfinderGoal(LivingEntity baseEntity) {
        this.baseEntity = baseEntity;
    }


    /**
     * Return the reflected NMS PathfinderGoal object to be injected
     */
    public abstract Object get();


    /**
     * Get the CraftLiving NMS handle
     * @return the reflected NSMS object
     * @throws ClassNotFoundException
     */
    protected Object getHandle() throws ClassNotFoundException {
        return NMSHelper.getEntityHandle(baseEntity);
    }


}
