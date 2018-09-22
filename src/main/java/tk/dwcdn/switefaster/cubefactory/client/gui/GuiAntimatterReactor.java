package tk.dwcdn.switefaster.cubefactory.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tk.dwcdn.switefaster.cubefactory.CubeFactory;
import tk.dwcdn.switefaster.cubefactory.inventory.ContainerAntimatterReactor;
import tk.dwcdn.switefaster.cubefactory.item.ItemLoader;
import tk.dwcdn.switefaster.cubefactory.tileentity.TileEntityAntimatterReactor;
import tk.dwcdn.switefaster.cubefactory.utils.CubeFactoryUtilities;

/**
 * @author switefaster
 */
public class GuiAntimatterReactor extends GuiContainer {

    private static final String TEXTURE_PATH = CubeFactory.MODID + ":" + "textures/gui/container/gui_antimatter_reactor.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    private final ContainerAntimatterReactor inventory;

    public GuiAntimatterReactor(ContainerAntimatterReactor inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 184;
        this.ySize = 233;
        this.inventory = inventorySlotsIn;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int matterMass = this.inventory.getMatterMass();
        int matterMassHeight = (int) Math.ceil(27.0 * matterMass / TileEntityAntimatterReactor.MAX_MASS_STORAGE);
        int antimatterMass = this.inventory.getAntimatterMass();
        int antimatterMassHeight = (int) Math.ceil(27.0 * antimatterMass / TileEntityAntimatterReactor.MAX_MASS_STORAGE);
        int powerStorage = this.inventory.getPowerStorage();
        int powerHeight = (int) Math.ceil(100.0 * powerStorage / TileEntityAntimatterReactor.MAX_ENERGY_STORAGE);

        this.drawTexturedModalRect(offsetX + 136, offsetY + 125 - powerHeight, 184, 99 - powerHeight, 18, powerHeight);
        this.drawTexturedModalRect(offsetX + 31, offsetY + 72 - matterMassHeight, 202, 26 - matterMassHeight, 18, matterMassHeight);
        this.drawTexturedModalRect(offsetX + 31, offsetY + 106 - antimatterMassHeight, 220, 26 - antimatterMassHeight, 18, antimatterMassHeight);

        int anmTick = this.inventory.getAnimationTick();
        if(anmTick > -1) {
            int mixAnimationWidth = (int) Math.ceil(33.0 * anmTick / TileEntityAntimatterReactor.MAX_ANIMATION_TICK);
            int reactAnimationFrameOffsetY = (int)(38.0 * (Math.floor(anmTick / 22.0)));
            this.drawTexturedModalRect(offsetX + 86, offsetY + 56, 184, 134 + reactAnimationFrameOffsetY, 41, 38);
            this.drawTexturedModalRect(offsetX + 51, offsetY + 59, 184, 100, mixAnimationWidth, 34);
        }

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("container.cubefactory.antimatter_reactor");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);

        this.drawHorizontalLine(5, 178, 135, 0xFF000000);

        ItemStack atmatteritem = new ItemStack(ItemLoader.ITEM_ANTIMATTER);
        this.itemRender.renderItemAndEffectIntoGUI(atmatteritem, 32, 85);
        ItemStack matteritemm = new ItemStack(Blocks.GRASS);
        this.itemRender.renderItemAndEffectIntoGUI(matteritemm, 32, 51);

        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        if (CubeFactoryUtilities.isMouseInRect(mouseX, mouseY, 136, 30, 153, 125, offsetX, offsetY) || CubeFactoryUtilities.isMouseInRect(mouseX, mouseY, 141, 26, 148, 29, offsetX, offsetY)) {
            String storage = I18n.format("tooltip.common.storage");
            this.drawHoveringText(storage + " " + Integer.toString(this.inventory.getPowerStorage()) + " RF / " + Integer.toString(TileEntityAntimatterReactor.MAX_ENERGY_STORAGE) + " RF", mouseX - offsetX, mouseY - offsetY);
        }
        if (CubeFactoryUtilities.isMouseInRect(mouseX, mouseY, 31, 46, 48, 72, offsetX, offsetY))
        {
            String matterMass = I18n.format("tooltip.common.matter") + " " + CubeFactoryUtilities.doubleToFormattenString(inventory.getMatterMass() * 1e-6) + " / " + CubeFactoryUtilities.doubleToFormattenString(TileEntityAntimatterReactor.MAX_MASS_STORAGE * 1e-6);
            this.drawHoveringText(matterMass, mouseX - offsetX, mouseY - offsetY);
        }
        if (CubeFactoryUtilities.isMouseInRect(mouseX, mouseY, 31, 80, 48, 106, offsetX, offsetY))
        {
            String antimatterMass = I18n.format("tooltip.common.antimatter") + " " + CubeFactoryUtilities.doubleToFormattenString(inventory.getAntimatterMass() * 1e-6) + " / " + CubeFactoryUtilities.doubleToFormattenString(TileEntityAntimatterReactor.MAX_MASS_STORAGE * 1e-6);
            this.drawHoveringText(antimatterMass, mouseX - offsetX, mouseY - offsetY);
        }
    }
}
