package com.christcraft.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import static net.minecraft.world.entity.EntityTypes.*;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NaturalSpawner.class)
public class NaturalSpawnerMixin {

    private static final EntityType<?> REPLACEMENTS[] = {
            ENDERMAN
    };

    @ModifyReturnValue(method = "mobsAt", at = @At("RETURN"))
    private static WeightedList<MobSpawnSettings.SpawnerData> christcraft$subFortressMob(
            WeightedList<MobSpawnSettings.SpawnerData> fortressDefault) {

        double index = Math.random();

        if (fortressDefault.contains(new MobSpawnSettings.SpawnerData(WITHER_SKELETON, 5, 5))) {
            return WeightedList.<MobSpawnSettings.SpawnerData>of(
                    new Weighted<>(new MobSpawnSettings.SpawnerData(REPLACEMENTS[(int)index], 1, 1), 1)
            );
        } else {
            return fortressDefault;
        }
    }
}
