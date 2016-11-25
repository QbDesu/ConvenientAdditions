package convenientadditions.api.entity.specialitem.behaviours;

import convenientadditions.api.util.Helper;
import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import convenientadditions.api.item.charge.ISunlightChargeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class BehaviourSunlightChargeable implements IEntitySpecialItemBehaviour {

    public static long DISCRIMINATOR;

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) {
        return true;
    }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        ItemStack s = item.getEntityItem();
        if (s != null && s.getItem() instanceof ISunlightChargeable) {
            ISunlightChargeable sitem = (ISunlightChargeable) (s.getItem());
            if (sitem.isSunlightChargeable(s, -255)) {
                if (item.worldObj.isDaytime() && !item.worldObj.isRaining() && Helper.canEntitySeeSky(item)) {
                    sitem.chargeItem(s, sitem.getSunlightChargeRate(s, -255));
                }
            }
        }
    }
}
