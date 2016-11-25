package convenientadditions.api.entity.specialitem.behaviours;

import convenientadditions.api.entity.specialitem.IEntitySpecialItemBehaviour;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemDye;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BehaviourAutoBoneMeal implements IEntitySpecialItemBehaviour {

    @Override
    public void onCreate(EntityItem item) {
    }

    @Override
    public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source, float damage) { return true; }

    @Override
    public void onItemEntityUpdate(EntityItem item) {
        World w = item.worldObj;
        BlockPos pos = new BlockPos(MathHelper.floor_double(item.posX), MathHelper.floor_double(item.posY), MathHelper.floor_double(item.posZ));
        ItemDye.applyBonemeal(item.getEntityItem(),w,pos);
        if(item.getEntityItem().stackSize>0 && item.onGround){
            pos=pos.down();
            ItemDye.applyBonemeal(item.getEntityItem(),w,pos);
        }
    }

}
