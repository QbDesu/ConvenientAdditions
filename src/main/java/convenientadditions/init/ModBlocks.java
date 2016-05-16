package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.block.BlockCompostSoil;
import convenientadditions.block.BlockCompostSoilTilled;
import convenientadditions.block.BlockComposter;
import convenientadditions.block.BlockPlayerInterface;
import convenientadditions.block.BlockPowderKeg;
import convenientadditions.block.BlockProximitySensor;
import convenientadditions.block.BlockSeedBox;
import convenientadditions.block.BlockTempLight;
import convenientadditions.block.BlockTitaniumOre;
import convenientadditions.block.charge.BlockChargeAccumulator;
import convenientadditions.block.charge.BlockChargeTube;
import convenientadditions.block.charge.BlockSunlightCollector;
import convenientadditions.block.item.ItemBlockCompostSoil;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModBlocks
{
    public static final BlockTitaniumOre oreTitaniumBlock = new BlockTitaniumOre();
    public static final BlockComposter composterBlock = new BlockComposter();
    public static final BlockCompostSoil compostSoilBlock = new BlockCompostSoil();
    public static final BlockCompostSoilTilled compostSoilTilledBlock = new BlockCompostSoilTilled();
    public static final BlockPowderKeg powderKegBlock = new BlockPowderKeg();
    public static final BlockPlayerInterface playerInterfaceBlock = new BlockPlayerInterface();
    public static final BlockProximitySensor proximitySensorBlock = new BlockProximitySensor();
    public static final BlockTempLight tempLightBlock = new BlockTempLight(.5F);
    public static final BlockSunlightCollector sunlightCollectorBlock = new BlockSunlightCollector();
    public static final BlockChargeAccumulator chargeAccumulatorBlock = new BlockChargeAccumulator();
    public static final BlockChargeTube chargeTubeBlock = new BlockChargeTube();
    public static final BlockSeedBox seedBoxBlock = new BlockSeedBox();

    public static void init()
    {
        GameRegistry.registerBlock(oreTitaniumBlock,Reference.oreTitaniumBlockName);
        GameRegistry.registerBlock(composterBlock,Reference.composterBlockName);
        GameRegistry.registerBlock(compostSoilTilledBlock,Reference.compostSoilTilledBlockName);
        GameRegistry.registerBlock(powderKegBlock,Reference.powderKegBlockName);
        GameRegistry.registerBlock(playerInterfaceBlock,Reference.playerInterfaceBlockName);
        GameRegistry.registerBlock(proximitySensorBlock,Reference.proximitySensorBlockName);
        GameRegistry.registerBlock(tempLightBlock,Reference.tempLightBlockName);
        GameRegistry.registerBlock(sunlightCollectorBlock,Reference.sunlightCollectorBlockName);
        GameRegistry.registerBlock(chargeAccumulatorBlock,Reference.chargeAccumulatorBlockName);
        GameRegistry.registerBlock(seedBoxBlock,Reference.seedBoxBlockName);
        GameRegistry.registerBlock(compostSoilBlock,ItemBlockCompostSoil.class,Reference.compostSoilBlockName);
    }
}
