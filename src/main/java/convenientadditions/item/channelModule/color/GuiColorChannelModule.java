package convenientadditions.item.channelModule.color;

import java.awt.Rectangle;
import java.io.IOException;

import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class GuiColorChannelModule extends GuiScreen {
    protected int xSize = 128;
    protected int ySize = 83;
    boolean mainhand=true;
	
    private static final ResourceLocation colorChannelModuleGuiTextures = new ResourceLocation(ConvenientAdditions.MODID+":textures/gui/colorModule.png");
	
	public GuiColorChannelModule(boolean mainhand){
		this.mainhand=mainhand;
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawBackgroundTexture();
        this.drawForegroundPanels();
    }
	
	public void drawBackgroundTexture(){
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(colorChannelModuleGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
	
	public void drawForegroundPanels(){
        int i = ((this.width - this.xSize) / 2);
        int j = ((this.height - this.ySize) / 2);
        if(getItem()!=null&&getItem().getItem()==ModItems.itemModuleColor){
			this.drawColoredRect(i+13, j+13, i+35, j+68, ModItems.itemModuleColor.getDyeColors(getItem())[0].getMapColor().colorValue);
			this.drawColoredRect(i+13+40, j+13, i+35+40, j+68, ModItems.itemModuleColor.getDyeColors(getItem())[1].getMapColor().colorValue);
			this.drawColoredRect(i+13+(40*2), j+13, i+35+(40*2), j+68, ModItems.itemModuleColor.getDyeColors(getItem())[2].getMapColor().colorValue);
		}
        this.mc.getTextureManager().bindTexture(colorChannelModuleGuiTextures);
		this.drawTexturedModalRect(i+12, j+12, 128, 0, 24, 57);
		this.drawTexturedModalRect(i+12+40, j+12, 128, 0, 24, 57);
		this.drawTexturedModalRect(i+12+(40*2), j+12, 128, 0, 24, 57);
	}
	
	public void drawColoredRect(int startX,int startY,int endX,int endY,int color){
		this.drawGradientRect(startX, startY, endX, endY, 0xFF000000+color, 0xFF000000+color);
	}
	
	@Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        int i = ((this.width - this.xSize) / 2);
        int j = ((this.height - this.ySize) / 2);
        super.mouseClicked(mouseX, mouseY, mouseButton);
        int adder=0;
        if(mouseButton==0)
        	adder=1;
        else if(mouseButton==1)
        	adder=-1;
        else if(mouseButton==2)
        	adder=Helper.getClientWorld().rand.nextInt(15)+1;
        else
        	return;
        if(getItem()!=null&&getItem().getItem()==ModItems.itemModuleColor){
	        if(new Rectangle(i+12, j+12, 25, 57).contains(mouseX, mouseY)){
				ModNetworking.INSTANCE.sendToServer(new MessageColorChannelModule(mainhand,(byte)0,(byte)((ModItems.itemModuleColor.getColorInts(getItem())[0]+adder)%15)));
				getItem().getTagCompound().setInteger("MATCHER_DYE_0",(ModItems.itemModuleColor.getColorInts(getItem())[0]+adder)%15);
				playButtonPressSound(this.mc.getSoundHandler());
	        }else if(new Rectangle(i+12+40, j+12, 25, 57).contains(mouseX, mouseY)){
				ModNetworking.INSTANCE.sendToServer(new MessageColorChannelModule(mainhand,(byte)1,(byte)((ModItems.itemModuleColor.getColorInts(getItem())[1]+adder)%15)));
				getItem().getTagCompound().setInteger("MATCHER_DYE_1",(ModItems.itemModuleColor.getColorInts(getItem())[1]+adder)%15);
				playButtonPressSound(this.mc.getSoundHandler());
	        }else if(new Rectangle(i+12+(40*2), j+12, 25, 57).contains(mouseX, mouseY)){
				ModNetworking.INSTANCE.sendToServer(new MessageColorChannelModule(mainhand,(byte)2,(byte)((ModItems.itemModuleColor.getColorInts(getItem())[2]+adder)%15)));
				getItem().getTagCompound().setInteger("MATCHER_DYE_2",(ModItems.itemModuleColor.getColorInts(getItem())[2]+adder)%15);
				playButtonPressSound(this.mc.getSoundHandler());
	        }
	    }
    }
	
	public ItemStack getItem(){
		return Helper.getClientPlayer().getHeldItem(mainhand?EnumHand.MAIN_HAND:EnumHand.OFF_HAND);
	}

    public void playButtonPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}