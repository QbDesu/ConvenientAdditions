package convenientadditions.item.adventurersPickaxe;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.oredict.OreDictionary;

public class OreMagnet {
	public static void attractOres(EntityPlayer p){
		List<EntityItem> l=p.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(p.posX-9, p.posY-9, p.posZ-9, p.posX+9, p.posY+9, p.posZ+9));
		for(EntityItem i:l){
			if(isOreItem(i.getEntityItem())){
				i.addVelocity((p.posX-i.posX)/48, (p.posY-i.posY)/48, (p.posZ-i.posZ)/48);
			}
		}
	}
	
	public static boolean isOreItem(ItemStack stack){
		for(String name:OreDictionary.getOreNames()){
			if(name.startsWith("ore")||name.startsWith("gem")||name.startsWith("nugget")||name.startsWith("dust")){
				return true;
			}
		}
		return false;
	}
}