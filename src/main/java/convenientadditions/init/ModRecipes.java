package convenientadditions.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init(){
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composterBlock),
				"s s",
			    "s s",
			    "ppp",
			    's', "slabWood",
			    'p', "plankWood"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostSoilBlock),
				"cc",
			    "cc",
			    'c', "chunkCompost"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.dirt),
				"dd",
			    "dd",
			    'd', new ItemStack(ModItems.itemDirtChunk)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), Blocks.dirt, "chunkCompost"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.dirt), ModBlocks.compostSoilBlock));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.powderKegBlock),
				"psp",
			    "pgp",
			    "psp",
			    'p', "plankWood",
			    's', "slabWood",
			    'g', Items.gunpowder));

		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunstone.FULLY_CHARGED.copy(),
				"grg",
			    "tdt",
			    "grg",
			    't', "ingotTitanium",
			    'd', "gemDiamond",
			    'r', "dustRedstone",
			    'g', "dustGlowstone"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRedstonePulseEmitter),
				"e",
				"r",
			    "t",
			    't', "ingotTitanium",
			    'r', Blocks.redstone_torch,
			    'e', Items.ender_pearl));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
				"tpt",
			    "geg",
			    "srs",
			    't', "ingotTitanium",
			    'e', Items.ender_pearl,
			    'p', Blocks.stone_pressure_plate,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
				"sgs",
			    "tet",
			    "rgr",
			    't', "ingotTitanium",
			    'e', Items.ender_eye,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));
		
		//TITANIUM TOOLS
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumPickaxe),
				"ttt",
			    " s ",
			    " s ",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumAxe),
				"tt",
			    "ts",
			    " s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumSpade),
				"t",
			    "s",
			    "s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumHoe),
				"tt",
			    " s",
			    " s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumSword),
				"t",
			    "t",
			    "s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));
		
		GameRegistry.addSmelting(ModBlocks.oreTitaniumBlock, new ItemStack(ModItems.ingotTitanium), 1.0F);
	}
}
