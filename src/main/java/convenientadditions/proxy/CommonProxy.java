package convenientadditions.proxy;

import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityComposter;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import convenientadditions.tileentity.TileEntityPowderKeg;
import convenientadditions.tileentity.TileEntityProximitySensor;
import convenientadditions.tileentity.charge.TileEntityChargeAccumulator;
import convenientadditions.tileentity.charge.TileEntityChargeTube;
import convenientadditions.tileentity.charge.TileEntitySunlightCollector;
import convenientadditions.tileentity.seedbox.TileEntitySeedBox;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	public Side getSide(){
		return Side.SERVER;
	}
	
    public void registerRenderers()
    {
    }
    
    public void registerPostSideObjects()
    {
    }

    public void registerEntities()
    {
    }

    public World getClientWorld()
    {
        return null;
    }

    public void registerActions()
    {
    }

    public void registerEvents()
    {
    }

    public void registerSoundHandler()
    {
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityComposter.class, Reference.composterBlockName);
        GameRegistry.registerTileEntity(TileEntityPowderKeg.class, Reference.powderKegBlockName);
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, Reference.playerInterfaceBlockName);
        GameRegistry.registerTileEntity(TileEntityProximitySensor.class, Reference.proximitySensorBlockName);
        GameRegistry.registerTileEntity(TileEntitySunlightCollector.class, Reference.sunlightCollectorBlockName);
        GameRegistry.registerTileEntity(TileEntityChargeAccumulator.class, Reference.chargeAccumulatorBlockName);
        GameRegistry.registerTileEntity(TileEntityChargeTube.class, Reference.chargeTubeBlockName);
        GameRegistry.registerTileEntity(TileEntitySeedBox.class, Reference.seedBoxBlockName);
    }

    public void registerEntityTrackers()
    {
    }

    public void registerTickHandlers()
    {
    }

    public void InitRendering(){}
    public void InitModels(){}
}
