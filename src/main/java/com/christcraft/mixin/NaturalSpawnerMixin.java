package com.christcraft.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import static net.minecraft.world.entity.EntityTypes.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.lang.Math;

@Mixin(NaturalSpawner.class)
public class NaturalSpawnerMixin {

    private static final EntityType<?> REPLACEMENTS[] = {
                    ENDERMAN
    };

    @ModifyReturnValue(method = "mobsAt", at = @At("RETURN"))
    private static EntityType<?> christcraft$subMob (EntityType<?> fortressDefault) {

        double index = Math.random();

        if (fortressDefault == WITHER_SKELETON) {
            index = Math.floor(index * REPLACEMENTS.length);
            return REPLACEMENTS[(int)index];
        } else {
            return fortressDefault;

        }
    }
}