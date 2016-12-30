package convenientadditions.block.itemTransmitter;

import convenientadditions.api.IMatcher;
import convenientadditions.api.block.tileentity.IItemProxy;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.api.item.ItemChannelModule;
import convenientadditions.api.provider.itemnetwork.IItemProvider;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.base.TileEntityCABase;
import convenientadditions.block.inventoryProxy.BlockInventoryProxy;
import convenientadditions.block.itemReceiver.TileEntityItemReceiver;
import convenientadditions.init.ModConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

public class TileEntityItemTransmitter extends TileEntityCABase implements ITickable, IItemProvider {

    ItemStackHandlerAutoSaveRestricted channels;

    public TileEntityItemTransmitter() {
        channels = new ItemStackHandlerAutoSaveRestricted(this, 3, ItemChannelModule.class);
    }

    public EnumFacing getFacing() {
        return getWorld().getBlockState(getPos()).getValue(BlockInventoryProxy.FACING);
    }

    public BlockPos getTarget() {
        return new BlockPos(getPos().getX() + getFacing().getFrontOffsetX(), getPos().getY() + getFacing().getFrontOffsetY(), getPos().getZ() + getFacing().getFrontOffsetZ());
    }

    @Override
    public IItemHandler getItemHandler() {
        if (ModConfig.inventoryProxies_blacklist.contains(getWorld().getBlockState(getTarget()).getBlock().getRegistryName().toString()))
            return null;
        TileEntity te = getWorld().getTileEntity(getTarget());
        return te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getFacing().getOpposite());
    }

    @Override
    public boolean hasItemHandler() {
        if (ModConfig.inventoryProxies_blacklist.contains(getWorld().getBlockState(getTarget()).getBlock().getRegistryName().toString()))
            return false;
        TileEntity te = getWorld().getTileEntity(getTarget());
        return te != null && !(te instanceof TileEntityItemReceiver) && !(te instanceof IItemProxy) && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getFacing().getOpposite());
    }

    @Override
    public IMatcher[] getAccess() {
        ArrayList<IMatcher> a = new ArrayList<>();
        for (ItemStack s : channels.getStacks()) {
            if (!s.isEmpty() && ((ItemChannelModule) s.getItem()).hasMatcher(s))
                a.add(((ItemChannelModule) s.getItem()).getMatcher(s));
        }
        return a.toArray(new IMatcher[a.size()]);
    }

    @Override
    public void update() {
        ItemNetworkProvider.addEntry(getWorld(), getPos());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("CHANNELS") && nbt.getTag("CHANNELS") instanceof NBTTagCompound)
            channels.deserializeNBT((NBTTagCompound) nbt.getTag("CHANNELS"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("CHANNELS", channels.serializeNBT());
        return nbt;
    }
}
