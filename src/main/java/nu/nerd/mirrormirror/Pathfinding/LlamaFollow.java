package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Llama caravan
 */
public class LlamaFollow extends AbstractPathfinderGoal {


    private double speed;


    public LlamaFollow(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!baseEntity.getType().equals(EntityType.LLAMA)) {
                return null;
            }
            Class llama = NMSHelper.getNMSClassByName("EntityLlama");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalLlamaFollow");
            Constructor constructor = c.getConstructor(llama, double.class);
            return constructor.newInstance(llama.cast(getHandle()), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalLlamaFollow");
            ex.printStackTrace();
            return null;
        }
    }


}
