package convenientadditions.init;

import convenientadditions.block.itemReceiver.ContainerItemReceiver;
import convenientadditions.block.itemReceiver.GuiItemReceiver;
import convenientadditions.block.itemReceiver.TileEntityItemReceiver;
import convenientadditions.block.itemTransmitter.ContainerItemTransmitter;
import convenientadditions.block.itemTransmitter.GuiItemTransmitter;
import convenientadditions.block.itemTransmitter.TileEntityItemTransmitter;
import convenientadditions.block.setProvider.ContainerSetProvider;
import convenientadditions.block.setProvider.GuiSetProvider;
import convenientadditions.block.setProvider.TileEntitySetProvider;
import convenientadditions.item.channelModule.color.GuiColorChannelModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	public static final int GUI_SET_PROVIDER_ID = 0;
	public static final int GUI_COLOR_MODULE_ID = 1;
	public static final int GUI_ITEM_TRANSMITTER_ID = 2;
	public static final int GUI_ITEM_RECEIVER_ID = 3;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GUI_SET_PROVIDER_ID:
			return new ContainerSetProvider((TileEntitySetProvider)world.getTileEntity(new BlockPos(x, y, z)),player);
		case GUI_ITEM_TRANSMITTER_ID:
			return new ContainerItemTransmitter((TileEntityItemTransmitter)world.getTileEntity(new BlockPos(x, y, z)),player);
		case GUI_ITEM_RECEIVER_ID:
			return new ContainerItemReceiver((TileEntityItemReceiver)world.getTileEntity(new BlockPos(x, y, z)),player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
			case GUI_SET_PROVIDER_ID:
				return new GuiSetProvider(new ContainerSetProvider((TileEntitySetProvider)world.getTileEntity(new BlockPos(x, y, z)),player));
			case GUI_COLOR_MODULE_ID:
				return new GuiColorChannelModule(EnumHand.values()[x]==EnumHand.MAIN_HAND);
			case GUI_ITEM_TRANSMITTER_ID:
				return new GuiItemTransmitter(new ContainerItemTransmitter((TileEntityItemTransmitter)world.getTileEntity(new BlockPos(x, y, z)),player));
			case GUI_ITEM_RECEIVER_ID:
				return new GuiItemReceiver(new ContainerItemReceiver((TileEntityItemReceiver)world.getTileEntity(new BlockPos(x, y, z)),player));
			default:
				return null;
		}
	}

}
