/*
 * Copyright 2017 FireBall1725
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.fireball1725.firelib.guimaker.controls;

import com.fireball1725.firelib.guimaker.base.GuiBaseControl;
import com.fireball1725.firelib.guimaker.base.IGuiObject;
import com.fireball1725.firelib.guimaker.util.GuiControlState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class GuiCheckbox extends GuiBaseControl implements IGuiObject {
    GuiLabel guiCheckBoxLabel = null;

    public GuiCheckbox(String controlName) {
        super(controlName);
        this.width = 12;
        this.height = 12;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Rectangle getClickableArea() {
        return new Rectangle(guiContainer.getGuiLeft() + this.left, guiContainer.getGuiTop() + top, this.width, this.height);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        //Draw Checkbox Border
        GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.left, guiContainer.getGuiTop() + this.top, 0, 32, 12, 12, 12, 12, 1, 100);

        if (this.hasGuiControlState(GuiControlState.HOVERED)) {
            if (this.hasGuiControlState(GuiControlState.SELECTED)) {
                // Hovered Checked
                GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.left, guiContainer.getGuiTop() + this.top, 64, 32, 12, 12, 12, 12, 1, 100);
            } else {
                // Hovered
                GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.left, guiContainer.getGuiTop() + this.top, 32, 32, 12, 12, 12, 12, 1, 100);
            }
        } else if (this.hasGuiControlState(GuiControlState.SELECTED)) {
            // Checked
            GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.left, guiContainer.getGuiTop() + this.top, 48, 32, 12, 12, 12, 12, 1, 100);
        } else {
            // Normal
            GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.left, guiContainer.getGuiTop() + this.top, 16, 32, 12, 12, 12, 12, 1, 100);
        }
    }

    @Override
    public NBTTagCompound writeNBT() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setBoolean("controlSelected", this.hasGuiControlState(GuiControlState.SELECTED));

        return nbtTagCompound;
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("controlSelected")) {
            if (nbt.getBoolean("controlSelected")) {
                this.addGuiControlState(GuiControlState.SELECTED);
            } else {
                this.removeGuiControlState(GuiControlState.SELECTED);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        if (this.guiCheckBoxLabel != null) {
            guiCheckBoxLabel.drawGuiContainerForegroundLayer(mouseX, mouseY);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initGui() {
        super.initGui();

        if (guiCheckBoxLabel != null) {
            guiCheckBoxLabel.setGuiMaker(this.guiMaker);
            guiCheckBoxLabel.setGuiContainer(this.guiContainer);

            guiCheckBoxLabel.initGui();
        }
    }

    public void setLabel(String labelText, int labelColor) {
        if (this.guiCheckBoxLabel == null) {
            this.guiCheckBoxLabel = new GuiLabel(this.controlName + "-label");

            this.guiCheckBoxLabel.setControlPosition(this.left + 16, this.top + 2);
        }

        this.guiCheckBoxLabel.label = labelText;
        this.guiCheckBoxLabel.color = labelColor;
    }
}