package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.item.ItemCompost;
import convenientadditions.item.ItemFertilizer;
import convenientadditions.item.charge.ItemBlazingRock;
import convenientadditions.item.charge.ItemSunstone;
import convenientadditions.item.charge.baubles.ItemBreathAmulet;
import convenientadditions.item.charge.baubles.ItemChargingRing;
import convenientadditions.item.charge.baubles.ItemFloatingBelt;
import convenientadditions.item.charge.baubles.ItemSaturationRing;
import convenientadditions.item.charge.baubles.ItemSunlightRing;
import convenientadditions.item.tools.ItemTitaniumAxe;
import convenientadditions.item.tools.ItemTitaniumHoe;
import convenientadditions.item.tools.ItemTitaniumPickaxe;
import convenientadditions.item.tools.ItemTitaniumSpade;
import convenientadditions.item.tools.ItemTitaniumSword;
import convenientadditions.item.tools.ItemTitaniumWrench;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ConvenientAdditionsMod.MODID)
public class ModItems {
    public static final ItemFertilizer itemFertilizer = new ItemFertilizer();
    public static final ItemCompost itemCompost = new ItemCompost();
    public static final ItemSunstone itemSunstone = new ItemSunstone();
    public static final ItemBlazingRock itemBlazingRock = new ItemBlazingRock();
    //dummy
    public static final Item ingotTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.ingotTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item nuggetTitanium=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.nuggetTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item itemDirtChunk=new Item().setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.dirtChunkItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    //baubles
    public static final ItemSunlightRing itemSunlightRing = new ItemSunlightRing();
    public static final ItemSaturationRing itemSaturationRing = new ItemSaturationRing();
    public static final ItemBreathAmulet itemBreathAmulet = new ItemBreathAmulet();
    public static final ItemChargingRing itemChargingRing = new ItemChargingRing();
    public static final ItemFloatingBelt itemFloatingBelt = new ItemFloatingBelt();
    //ttools
    public static final Item itemTitaniumAxe=new ItemTitaniumAxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.axeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item itemTitaniumPickaxe=new ItemTitaniumPickaxe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.pickaxeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item itemTitaniumSpade=new ItemTitaniumSpade(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.spadeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item itemTitaniumHoe=new ItemTitaniumHoe(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.hoeTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final Item itemTitaniumSword=new ItemTitaniumSword(ConvenientAdditionsMod.TOOLMATERIAL_TITANIUM).setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.swordTitaniumItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
    public static final ItemTitaniumWrench itemTitaniumWrench=new ItemTitaniumWrench();
    
    public static void init()
    {
    	//dummy
        GameRegistry.registerItem(ingotTitanium,Reference.ingotTitaniumItemName);
        GameRegistry.registerItem(nuggetTitanium,Reference.nuggetTitaniumItemName);
        GameRegistry.registerItem(itemDirtChunk,Reference.dirtChunkItemName);
    	//ttools
        GameRegistry.registerItem(itemTitaniumPickaxe,Reference.pickaxeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumAxe,Reference.axeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumSpade,Reference.spadeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumHoe,Reference.hoeTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumSword,Reference.swordTitaniumItemName);
        GameRegistry.registerItem(itemTitaniumWrench,Reference.titaniumWrenchItemName);
        //misc
        GameRegistry.registerItem(itemFertilizer,Reference.fertilizerItemName);
        GameRegistry.registerItem(itemCompost,Reference.compostItemName);
        GameRegistry.registerItem(itemSunstone,Reference.sunstoneItemName);
        GameRegistry.registerItem(itemBlazingRock,Reference.blazingRockItemName);
        //baubles
        GameRegistry.registerItem(itemSunlightRing,Reference.sunlightRingItemName);
        GameRegistry.registerItem(itemSaturationRing,Reference.saturationRingItemName);
        GameRegistry.registerItem(itemBreathAmulet,Reference.breathAmuletItemName);
        GameRegistry.registerItem(itemChargingRing,Reference.chargingRingItemName);
        GameRegistry.registerItem(itemFloatingBelt,Reference.floatingBeltItemName);
        //goo
        //ItemGoo.init();
    }
}
