package nu.nerd.mirrormirror;

import nu.nerd.mirrormirror.Pathfinding.AbstractPathfinderGoal;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


public class ExtendedEntity {


    private LivingEntity baseEntity;


    public ExtendedEntity(LivingEntity baseEntity) {
        this.baseEntity = baseEntity;
    }


    /**
     * Make the entity walk to a specific location
     * @param loc The block for the entity to walk to
     * @param speed The speed the entity should move at
     */
    public void walkTo(Location loc, float speed) {
        try {

            Object handle = getHandle();

            // Set the onGround status, in case the entity spawned slightly above ground level.
            // Otherwise walking does not work.
            handle.getClass().getField("onGround").set(handle, true);

            // Invoke the .a(double x, double y, double z, double speed) method in NavigationAbstract.
            Object nav = handle.getClass().getMethod("getNavigation").invoke(handle);
            Method walk = nav.getClass().getMethod("a", double.class, double.class, double.class, double.class);
            walk.invoke(nav, loc.getX(), loc.getY(), loc.getZ(), speed);

        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error walking entity: " + baseEntity.getUniqueId());
            MirrorMirror.logger().warning(ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     * Remove all extant AI goals from the entity
     */
    public void clearGoals() {
        try {

            Object handle = getHandle();
            Object pfgs = createPathFinderGoalSelector(handle);
            handle.getClass().getField("goalSelector").set(handle, pfgs);

        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error clearning goals for entity: " + baseEntity.getUniqueId());
            MirrorMirror.logger().warning(ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     * Remove all extant AI targets from the entity
     */
    public void clearTargets() {
        try {

            Object handle = getHandle();
            Object pfgs = createPathFinderGoalSelector(handle);
            handle.getClass().getField("targetSelector").set(handle, pfgs);

        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error clearning targets for entity: " + baseEntity.getUniqueId());
            MirrorMirror.logger().warning(ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     * Insert a pathfinder goal
     * @param weight the weight of the goal
     * @param goal the goal to insert
     */
    public void injectGoal(int weight, AbstractPathfinderGoal goal) {
        try {

            Object handle = getHandle();

            // Obtain the addition method
            Class pfg = NMSHelper.getNMSClassByName("PathfinderGoal");
            Object goalSelector = handle.getClass().getField("goalSelector").get(handle);
            Method add = goalSelector.getClass().getMethod("a", int.class, pfg);

            // Inject the fabricated goal
            add.invoke(goalSelector, weight, pfg.cast(goal.get()));

        } catch (Exception ex) {
            MirrorMirror.logger().warning(ex.getMessage());
        }
    }


    /**
     * Replace pathfinder goals with a new set
     */
    public void injectGoals(AbstractPathfinderGoal... goals) {
        clearGoals();
        List<AbstractPathfinderGoal> goalsList = Arrays.asList(goals);
        for (AbstractPathfinderGoal goal : goalsList) {
            injectGoal(goalsList.indexOf(goal), goal);
        }
    }


    /**
     * Insert a pathfinder target
     * @param weight the weight of the goal
     * @param target the target to insert
     */
    public void injectTarget(int weight, AbstractPathfinderGoal target) {
        try {

            Object handle = getHandle();

            // Obtain the addition method
            Class pfg = NMSHelper.getNMSClassByName("PathfinderGoal");
            Object targetSelector = handle.getClass().getField("targetSelector").get(handle);
            Method add = targetSelector.getClass().getMethod("a", int.class, pfg);

            // Inject the fabricated target
            add.invoke(targetSelector, weight, pfg.cast(target.get()));

        } catch (Exception ex) {
            MirrorMirror.logger().warning(ex.getMessage());
        }
    }


    /**
     * Replace pathfinder targets with a new set
     */
    public void injectTargets(AbstractPathfinderGoal... targets) {
        clearTargets();
        List<AbstractPathfinderGoal> targetsList = Arrays.asList(targets);
        for (AbstractPathfinderGoal goal : targetsList) {
            injectTarget(targetsList.indexOf(goal), goal);
        }
    }


    /**
     * Creates an empty PathFinderGoalSelector for AI clearing methods
     * @param handle the NMS handle
     * @return NMS PathfinderGoalSelector
     */
    @SuppressWarnings("unchecked")
    private Object createPathFinderGoalSelector(Object handle) throws ClassNotFoundException {
        try {
            Object nmsWorld = handle.getClass().getField("world").get(handle);
            Object methodProfiler = nmsWorld.getClass().getField("methodProfiler").get(nmsWorld);
            Class pgsClass = NMSHelper.getNMSClassByName("PathfinderGoalSelector");
            Constructor pgsCon = pgsClass.getConstructor(NMSHelper.getNMSClassByName("MethodProfiler"));
            return pgsCon.newInstance(methodProfiler);
        } catch (Exception ex) {
            MirrorMirror.logger().warning(ex.getMessage());
            throw new ClassNotFoundException("Error constructing PathfinderGoalSelector");
        }
    }


    /**
     * Get the CraftLiving NMS handle
     * @return the reflected NSMS object
     * @throws ClassNotFoundException
     */
    private Object getHandle() throws ClassNotFoundException {
        try {
            Class<?> craft = baseEntity.getClass();
            Method getHandle = craft.getDeclaredMethod("getHandle");
            return getHandle.invoke(baseEntity);
        } catch (Exception ex) {
            throw new ClassNotFoundException("Error getting entity handle: " + ex.getMessage());
        }
    }


}
