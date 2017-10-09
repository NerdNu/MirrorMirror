package nu.nerd.mirrormirror;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Method;


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
            MirrorMirror.instance.getLogger().warning("Error walking entity: " + baseEntity.getUniqueId());
            MirrorMirror.instance.getLogger().warning(ex.getMessage());
            ex.printStackTrace();
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
