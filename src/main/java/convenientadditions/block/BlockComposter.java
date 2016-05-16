package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityComposter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockComposter extends BlockContainer {

	public BlockComposter() {
		super(Material.wood);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.composterBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeWood).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.getTileEntity(pos) instanceof TileEntityComposter && !player.isSneaking())
        {
        	TileEntityComposter t=(TileEntityComposter)world.getTileEntity(pos);
        	ItemStack c=player.inventory.getStackInSlot(player.inventory.currentItem);
        	if(c!=null){
        		if(t.getContentValue(c)>0)
	        		if(t.isItemValidForSlot(0, c)&&!world.isRemote)
	        			t.setInventorySlotContents(0, c.splitStack(1));
        	}
        	return true;
        }
        return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityComposter();
	}

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}
