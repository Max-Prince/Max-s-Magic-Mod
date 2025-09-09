package com.everrell.magicmod.gui.overlays;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.player.client.ClientConfigs;
import com.everrell.magicmod.player.client.ClientMagicData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import java.lang.Math;
import java.util.function.Function;


import static com.everrell.magicmod.api.attribute.AttributeRegistry.MAX_MANA;

public class ManaBarOverlay implements LayeredDraw.Layer {
    public static final ManaBarOverlay instance = new ManaBarOverlay();

    public final static ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MaxsMagicMod.MODID, "gui/textures/icons.png");



    public enum Anchor {
        Hunger,
        XP,
        Center,
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
    }

    public enum Display {
        Never,
        Always,
        Contextual
    }

    static final int DEFAULT_IMAGE_WIDTH = 98;
    static final int XP_IMAGE_WIDTH = 188;
    static final int IMAGE_HEIGHT = 21;
    static final int HOTBAR_HEIGHT = 25;
    static final int ICON_ROW_HEIGHT = 11;
    static final int CHAR_WIDTH = 6;
    static final int HUNGER_BAR_OFFSET = 50;
    static final int SCREEN_BORDER_MARGIN = 20;
    static final int TEXT_COLOR = ChatFormatting.AQUA.getColor();

    public void render(GuiGraphics guiHelper, DeltaTracker deltaTracker) {

        var player = Minecraft.getInstance().player;
        var screenWidth = guiHelper.guiWidth();
        var screenHeight = guiHelper.guiHeight();

        int maxMana = (int) player.getAttributeValue(MAX_MANA);
        int mana = ClientMagicData.getPlayerMana();
        int barX, barY;
        //TODO: cache these?
        int configOffsetY = ClientConfigs.MANA_BAR_Y_OFFSET.get();
        int configOffsetX = ClientConfigs.MANA_BAR_X_OFFSET.get();
        Anchor anchor = ClientConfigs.MANA_BAR_ANCHOR.get();
        barX = getBarX(anchor, screenWidth) + configOffsetX;
        barY = getBarY(anchor, screenHeight, Minecraft.getInstance().gui) - configOffsetY;

        int imageWidth = anchor == Anchor.XP ? XP_IMAGE_WIDTH : DEFAULT_IMAGE_WIDTH;
        int spriteX = anchor == Anchor.XP ? 68 : 0;
        int spriteY = anchor == Anchor.XP ? 40 : 0;

        //Originals: guiHelper.blit(TEXTURE, barX, barY, spriteX, spriteY, imageWidth, IMAGE_HEIGHT, 256, 256);
        //guiHelper.blit(TEXTURE, barX, barY, spriteX, spriteY + IMAGE_HEIGHT, (int) (imageWidth * Math.min((mana / (double) maxMana), 1)), IMAGE_HEIGHT);

        //Attempted changes: guiHelper.blit((TEXTURE, RenderType.cutout()) ,TEXTURE, barX, barY, spriteX, spriteY, imageWidth, IMAGE_HEIGHT, 256, 256);
        //guiHelper.blit(TEXTURE ,TEXTURE, barX, barY, spriteX, spriteY, imageWidth, IMAGE_HEIGHT, 256, 256);


        int textX, textY;
        String manaFraction = (mana) + "/" + maxMana;
        //different attempts to make the mana bar
        guiHelper.blitInscribed(TEXTURE, barX, barY, spriteX, spriteY, imageWidth, IMAGE_HEIGHT);
        guiHelper.hLine(RenderType.solid(), barX+1, barX+mana, barY+1, 100);
        guiHelper.fill(barX, barY, barX+mana, barY+50, 100);

        textX = ClientConfigs.MANA_TEXT_X_OFFSET.get() + barX + imageWidth / 2 - (int) ((("" + mana).length() + 0.5) * CHAR_WIDTH);
        textY = ClientConfigs.MANA_TEXT_Y_OFFSET.get() + barY + (anchor == Anchor.XP ? ICON_ROW_HEIGHT / 3 : ICON_ROW_HEIGHT);

        if (ClientConfigs.MANA_BAR_TEXT_VISIBLE.get()) {
            guiHelper.drawString(Minecraft.getInstance().font, manaFraction, textX, textY, TEXT_COLOR);
            //gui.getFont().draw(poseStack, manaFraction, textX, textY, TEXT_COLOR);
        }
    }




    public static boolean shouldShowManaBar(Player player) {
        return (true);

    }

    private static int getBarX(Anchor anchor, int screenWidth) {
        return screenWidth / 2 - 91 - 3; //Vanilla's Pos - 3


    }

    private static int getBarY(Anchor anchor, int screenHeight, Gui gui) {
        return screenHeight - 32 + 3 - 7; //Vanilla's Pos - 7


    }

    private static int getAndIncrementRightHeight(Gui gui) {
        int x = gui.rightHeight;
        gui.rightHeight += 10;
        return x;
    }
}
