package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPlayerInterface extends BlockContainer {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconTopActive;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconTopInactive;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconBottom;

	public BlockPlayerInterface() {
		super(Material.iron);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName).setHardness(4F).setResistance(8F).setStepSound(soundTypeMetal).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPlayerInterface();
	}
	
    public boolean hasComparatorInputOverride(){return true;}
    
    public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_)
    {
    	TileEntity t=world.getTileEntity(x, y, z);
        if(t!=null&&t instanceof TileEntityPlayerInterface)
        	return ((TileEntityPlayerInterface)t).hasTarget()?15:0;
        return 0;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
		return side==0?blockIconBottom:(side==1?(meta==1?blockIconTopActive:blockIconTopInactive):blockIcon);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+"_side");
        this.blockIconTopActive = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+"_top_active");
        this.blockIconTopInactive = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+"_top_inactive");
        this.blockIconBottom = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+"_bottom");
    }

}