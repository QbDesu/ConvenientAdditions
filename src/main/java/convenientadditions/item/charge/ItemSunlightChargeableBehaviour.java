package convenientadditions.item.charge;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import convenientadditions.api.item.ItemSunlightChargeable;
import convenientadditions.api.registry.behaviour.BehaviourRegistry;
import convenientadditions.entity.specialitem.CAEntitySpecialItem;

public abstract class ItemSunlightChargeableBehaviour extends ItemSunlightChargeable {
	public ItemSunlightChargeableBehaviour(int capacity,
			boolean showDurabilityBar, boolean showTooltips,
			int sunlightChargeRate) {
		super(capacity, showDurabilityBar, showTooltips, sunlightChargeRate);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
		CAEntitySpecialItem newE=new CAEntitySpecialItem(world, location.posX, location.posY, location.posZ, itemstack);
		newE.setVelocity(location.motionX, location.motionY, location.motionZ);
		newE.addBehaviour(BehaviourRegistry.API_DISCRIMINATORS.get("sunlightChargeable"));
		newE.delayBeforeCanPickup=20;
		newE.scheduleBehaviourUpdate();
        return newE;
    }
}