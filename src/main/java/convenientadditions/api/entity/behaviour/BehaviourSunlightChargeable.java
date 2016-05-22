package convenientadditions.api.entity.behaviour;

import conveniencecore.behaviours.BehaviourRegistry;
import conveniencecore.behaviours.BehaviourSensitivityWater;
import conveniencecore.behaviours.IEntitySpecialItemBehaviour;
import convenientadditions.api.item.charge.ISunlightChargeable;
import convenientadditions.api.util.Helper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class BehaviourSunlightChargeable implements IEntitySpecialItemBehaviour {

	public static long DISCRIMINATOR;
	private static boolean registered=false;
	
	@Override
	public void onCreate(EntityItem item) {}

	@Override
	public boolean onAttackItemEntityFrom(EntityItem item, DamageSource source,float damage) {
		return true;
	}

	@Override
	public void onItemEntityUpdate(EntityItem item) {
		//System.out.println("hi");
		ItemStack s=item.getEntityItem();
		if(s!=null && s.getItem() instanceof ISunlightChargeable){
			ISunlightChargeable sitem=(ISunlightChargeable)(s.getItem());
			if(sitem.isSunlightChargeable(s, -255)){
				if(item.worldObj.isDaytime() && !item.worldObj.isRaining() && Helper.canEntitySeeSky(item)){
					sitem.chargeItem(s, sitem.getSunlightChargeRate(s, -255));
				}
			}
		}
	}

	public static void init(){
		if(!registered){
			DISCRIMINATOR=BehaviourRegistry.addBehaviour(new BehaviourSunlightChargeable());
		}
	}
}
