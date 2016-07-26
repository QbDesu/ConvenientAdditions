package convenientadditions.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModOredict {
	public static void registerOres(){
		OreDictionary.registerOre("oreTitanium", ModBlocks.oreTitaniumBlock);
		OreDictionary.registerOre("ingotTitanium", ModItems.ingotTitanium);
		OreDictionary.registerOre("nuggetTitanium", ModItems.nuggetTitanium);

		OreDictionary.registerOre("soil", ModBlocks.compostSoilBlock);
		OreDictionary.registerOre("soil", Blocks.DIRT);
		OreDictionary.registerOre("soilCompost", ModBlocks.compostSoilBlock);
		OreDictionary.registerOre("soilDirt", Blocks.DIRT);

		OreDictionary.registerOre("chunkCompost", new ItemStack(ModItems.itemCompost,1,0));
		OreDictionary.registerOre("chunkCompost", new ItemStack(ModItems.itemCompost,1,1));
		
		OreDictionary.registerOre("chunkDirt", new ItemStack(ModItems.itemDirtChunk,1,0));

		OreDictionary.registerOre("sugar", new ItemStack(Items.SUGAR));
		OreDictionary.registerOre("sugar", new ItemStack(ModItems.itemSapBottle,1,1));
		OreDictionary.registerOre("sugar", new ItemStack(ModItems.itemSapBottle,1,2));
		
		OreDictionary.registerOre("sap", new ItemStack(ModItems.itemSapBottle,1,1));
		OreDictionary.registerOre("sap", new ItemStack(ModItems.itemSapBottle,1,2));
		
        GameRegistry.addRecipe(new ShapelessOreRecipe(Items.PUMPKIN_PIE, Blocks.PUMPKIN, "egg", "sugar"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Items.FERMENTED_SPIDER_EYE, Blocks.BROWN_MUSHROOM, Items.SPIDER_EYE, "sugar"));
        GameRegistry.addRecipe(new ShapedOreRecipe(Items.CAKE,
				"mmm",
			    "ses",
			    "www",
			    'm', Items.MILK_BUCKET,
			    'e', "egg",
			    's', "sugar",
			    'w', "cropWheat"));
	}
}
