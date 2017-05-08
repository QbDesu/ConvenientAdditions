package convenientadditions.block.machine.ironFarm;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiIronFarm extends CAGuiContainer {

    private static final ImageResourceLocation ironFarmGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/ironfarm.png",0,0,175,114);
    public TileEntityIronFarm te;

    public GuiIronFarm(ContainerIronFarm container) {
        super(container,ironFarmGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}