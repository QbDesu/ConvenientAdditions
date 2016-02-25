package convenientadditions.item.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.block.IDismantleable;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTitaniumWrench extends Item {
	public ItemTitaniumWrench(){
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.titaniumWrenchItemName).setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.titaniumWrenchItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
	public boolean onItemUseFirst( ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ )
	{
		boolean ret=false;
		Block b = world.getBlock( x, y, z );
		if( b != null )
		{
			if(!player.isSneaking()){
				if(!world.isRemote)
					b.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side));
				player.swingItem();
			}else{
				if(b instanceof IDismantleable){
					IDismantleable d=(IDismantleable)b;
					if(d.canDismantle(player, world, x, y, z)&&!world.isRemote){
						d.dismantleBlock(player, world, x, y, z, false);
					}
					player.swingItem();
				}
			}
		}
		return ret;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
