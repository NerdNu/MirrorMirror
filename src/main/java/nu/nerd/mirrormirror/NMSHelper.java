package nu.nerd.mirrormirror;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;


public class NMSHelper {


    /**
     * Get a class in the net.minecraft.server package by name
     * @param name The short name of the class
     * @return the uninstantiated Class object
     */
    public static Class getNMSClassByName(String name) throws ClassNotFoundException{
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException ex) {
            throw ex;
        }
    }


    /**
     * Get the NMS EntityLiving object from the Bukkit entity
     * @param entity A Bukkit LivingEntity
     * @return NMS object
     * @throws ClassNotFoundException
     */
    public static Object getEntityHandle(LivingEntity entity) throws ClassNotFoundException {
        try {
            Class<?> craft = entity.getClass();
            Method getHandle = craft.getDeclaredMethod("getHandle");
            return getHandle.invoke(entity);
        } catch (Exception ex) {
            throw new ClassNotFoundException("Error getting entity handle: " + ex.getMessage());
        }
    }


    /**
     * Get the NMS ItemStack from a Bukkit ItemStack
     * @param item Bukkit ItemStack
     * @return NMS ItemSTack
     * @throws ClassNotFoundException
     */
    public static Object getNMSItemStack(ItemStack item) throws ClassNotFoundException {
        try {
            String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            Class<?> c = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
            Method method = c.getDeclaredMethod("asNMSCopy", ItemStack.class);
            return method.invoke(null, item);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ClassNotFoundException("Error getting NMS ItemStack: " + ex.getMessage());
        }
    }


}
