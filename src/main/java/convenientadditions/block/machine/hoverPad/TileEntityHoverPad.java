package convenientadditions.block.machine.hoverPad;

import convenientadditions.base.block.CATileEntity;
import convenientadditions.init.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class TileEntityHoverPad extends CATileEntity implements ITickable {
    public static final int RANGE = 15;

    @Override
    public void update() {
        double mult = getWorld().isBlockIndirectlyGettingPowered(pos) / 15d;
        if (mult == 0d)
            return;
        List<EntityLivingBase> l = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + checkForObstuctions(), pos.getZ() + 1));
        for (EntityLivingBase e : l) {
            double acc = mult*.3d;
            if (e instanceof EntityPlayer && e.isSneaking())
                e.setVelocity(e.motionX, Math.max(e.motionY,Math.min(e.motionY+acc/4,acc/4)), e.motionZ);
            else
                e.setVelocity(e.motionX, Math.max(e.motionY,Math.min(e.motionY+acc,acc*2)), e.motionZ);
            if (e.motionY > -.666)
                e.fallDistance = 0;
        }
    }

    public int checkForObstuctions() {
        int i;
        for(i=0; i< ModConfig.hoverPad_range; i++){
            IBlockState s=getWorld().getBlockState(getPos().up(i+1));
            if(s.isSideSolid(getWorld(),getPos(), EnumFacing.DOWN)||s.isSideSolid(getWorld(),getPos(), EnumFacing.UP))
                break;
        }
        return i+1;
    }
}