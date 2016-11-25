package convenientadditions.item.charge;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import convenientadditions.api.item.charge.ISunlightChargeable;
import convenientadditions.api.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ChargeTickHandler {
    public static final int[] OFFHAND_SLOTS = new int[]{255};
    public static final int[] HOTBAR_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    public static final int[] ARMOR_SLOTS = new int[]{155, 156, 157, 158};
    public static final int[] BAUBLES_SLOTS = new int[]{-1, -2, -3, -4, -5, -6, -7};
    private byte time = 0;

    @SubscribeEvent
    public void onPlayerSunlightChargeTick(TickEvent.PlayerTickEvent e) {
        time++;
        if (time < 20 || e.side != Side.SERVER)
            return;
        time = 0;

        EntityPlayer player = e.player;
        InventoryPlayer playerInv = player.inventory;

        if (player.worldObj.isDaytime() && !player.worldObj.isRaining() && Helper.canEntitySeeSky(player)) {
            //BAUBLES SUNLIGHT
            IBaublesItemHandler baublesInv = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null);
            for (int i = 0; i < baublesInv.getSlots(); i++) {
                ItemStack stack = baublesInv.getStackInSlot(i);
                if (stack != null && stack.getItem() instanceof ISunlightChargeable) {
                    ISunlightChargeable sitem = (ISunlightChargeable) (stack.getItem());
                    if (sitem.isSunlightChargeable(stack, BAUBLES_SLOTS[i])) {
                        sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, BAUBLES_SLOTS[i]) * 20);
                    }
                }
            }
            //VANILLA SUNLIGHT
            //MAIN
            for (int i = 0; i < playerInv.mainInventory.length; i++) {
                ItemStack stack = playerInv.mainInventory[i];
                if (stack != null && stack.getItem() instanceof ISunlightChargeable) {
                    ISunlightChargeable sitem = (ISunlightChargeable) (stack.getItem());
                    if (sitem.isSunlightChargeable(stack, i)) {
                        sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, i) * 20);
                    }
                }
            }
            //ARMOR
            for (int i = 0; i < playerInv.armorInventory.length; i++) {
                ItemStack stack = playerInv.armorInventory[i];
                if (stack != null && stack.getItem() instanceof ISunlightChargeable) {
                    ISunlightChargeable sitem = (ISunlightChargeable) (stack.getItem());
                    if (sitem.isSunlightChargeable(stack, ARMOR_SLOTS[i])) {
                        sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, ARMOR_SLOTS[i]) * 20);
                    }
                }
            }
            //OFFHAND
            for (ItemStack stack : playerInv.offHandInventory) {
                if (stack != null && stack.getItem() instanceof ISunlightChargeable) {
                    ISunlightChargeable sitem = (ISunlightChargeable) (stack.getItem());
                    if (sitem.isSunlightChargeable(stack, OFFHAND_SLOTS[0])) {
                        sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, OFFHAND_SLOTS[0]) * 20);
                    }
                }
            }
        }
    }

}
