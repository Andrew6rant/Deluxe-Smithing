package io.github.andrew6rant.deluxesmithing.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.andrew6rant.deluxesmithing.config.Config.*;
import static net.minecraft.client.gui.screen.ingame.SmithingScreen.ARMOR_STAND_ROTATION;

@Environment(EnvType.CLIENT)
@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ForgingScreen<SmithingScreenHandler> {
    @Shadow private @Nullable ArmorStandEntity armorStand;

    public SmithingScreenMixin(SmithingScreenHandler handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title, texture);
    }

    @Inject(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V",
    at = @At(target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawEntity(Lnet/minecraft/client/gui/DrawContext;IIILorg/joml/Quaternionf;Lorg/joml/Quaternionf;Lnet/minecraft/entity/LivingEntity;)V",
    value = "INVOKE", shift = At.Shift.BEFORE))
    public void deluxesmithing$drawArmorStand(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        switch (armorStandRenderEnum) {
            case ROTATE -> InventoryScreen.drawEntity(context, this.x + armorStandRenderX, this.y + armorStandRenderY, armorStandRenderSize, ARMOR_STAND_ROTATION.rotateY(rotationSpeed), null, this.armorStand);
            case FOLLOW_MOUSE -> InventoryScreen.drawEntity(context, this.x + armorStandRenderX, this.y + armorStandRenderY, armorStandRenderSize, this.x + armorStandRenderX - mouseX, this.y + armorStandRenderY - 30 - mouseY, this.armorStand);
        }
    }

    @ModifyConstant(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", constant = @Constant(intValue = 25, ordinal = 0))
    public int deluxesmithing$removeVanillaArmorStand(int constant) {
        // modify size of the original armor stand to be 0.
        // this is more compatible than redirecting the method away
        return 0;
    }
}
