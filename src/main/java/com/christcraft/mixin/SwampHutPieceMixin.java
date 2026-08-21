package com.christcraft.mixin;

import net.minecraft.world.entity.Entity;
import static net.minecraft.world.entity.EntitySpawnReason.*;
import static net.minecraft.world.entity.EntityTypes.*;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.structures.SwampHutPiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SwampHutPiece.class)
public class SwampHutPieceMixin {

    @Redirect(method = "postProcess",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/WorldGenLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V"))
    private void christcraft$replaceWitchOnAdd(WorldGenLevel level, Entity entity) {
        if (entity instanceof Witch witch) {
            Entity replacement = PILLAGER.create(level.getLevel(), STRUCTURE);
            if (replacement != null) {
                replacement.snapTo(witch.getX(), witch.getY(), witch.getZ(), witch.getYRot(), witch.getXRot());
                level.addFreshEntityWithPassengers(replacement);
            }
        } else {
            level.addFreshEntityWithPassengers(entity);
        }
    }
}
