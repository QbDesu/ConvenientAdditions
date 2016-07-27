package convenientadditions.item.charge.enderPlate;

import convenientadditions.init.ModConfig;
import convenientadditions.init.ModItems;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EnderPlateInventoryTickHandler {
	private byte time=0;
	
	@SubscribeEvent
    public void onPlayerChargeTick(TickEvent.PlayerTickEvent e)
    {
		time++;
		if(time<20 || e.side!=Side.SERVER)
			return;
		time=0;
		
		EntityPlayer player = e.player;
		InventoryPlayer playerInv=player.inventory;
		
		int crystals=player.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, 
				new AxisAlignedBB(player.posX-4.5, player.posY-4.5, player.posZ-4.5,
						player.posX+4.5, player.posY+4.5, player.posZ+4.5)).size();
		
		if(crystals==0)
			return;
		
		int charge=(int)(ModConfig.enderPlate_crystalChargeRate*Math.log(crystals+1)/Math.log(2));
		
		for(ItemStack i:playerInv.mainInventory){
			if(i!=null&&i.getItem()==ModItems.itemEnderPlate){
				if(!ModItems.itemEnderPlate.isActive(i)){
						ModItems.itemEnderPlate.chargeItem(i, charge);
				}
			}
		}
		
		for(ItemStack i:playerInv.offHandInventory){
			if(i!=null&&i.getItem()==ModItems.itemEnderPlate){
				if(!ModItems.itemEnderPlate.isActive(i)){
						ModItems.itemEnderPlate.chargeItem(i, charge);
				}
			}
		}
    }

	public static void init(){
		if(ModConfig.enderPlate_crystalCharge)
			MinecraftForge.EVENT_BUS.register(new EnderPlateInventoryTickHandler());
	}
}