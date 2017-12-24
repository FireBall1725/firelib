/*
 * Copyright 2017 FireBall1725
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.util.ControlState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiCheckbox extends GuiObject implements IGuiObject {
    public GuiCheckbox(int controlId) {
        super(controlId);
        this.width = 12;
        this.height = 12;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(guiContainer, partialTicks, mouseX, mouseY);

        if (this.controlState == ControlState.INVISIBLE) {
            return;
        }

        // Draw Checkbox Border
        GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.x, guiContainer.getGuiTop() + this.y, 0, 32, 12, 12, 12, 12, 1, 100);

        switch (this.controlState) {
            default:
            case NORMAL:
                GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.x, guiContainer.getGuiTop() + this.y, 16, 32, 12, 12, 12, 12, 1, 100);
                break;
            case HOVERED:
                GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.x, guiContainer.getGuiTop() + this.y, 32, 32, 12, 12, 12, 12, 1, 100);
                break;
            case SELECTED:
                GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.x, guiContainer.getGuiTop() + this.y, 48, 32, 12, 12, 12, 12, 1, 100);
                break;
            case SELECTED_HOVER:
                GuiUtils.drawContinuousTexturedBox(this.DarkSkin, guiContainer.getGuiLeft() + this.x, guiContainer.getGuiTop() + this.y, 64, 32, 12, 12, 12, 12, 1, 100);
                break;
        }
    }
}