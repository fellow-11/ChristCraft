package com.christcraft.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import static net.minecraft.world.entity.EntityTypes.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.math.*;


@Mixin(MonsterRoomFeature.class)
public class MonsterRoomFeatureMixin {

    private static final EntityType<?> REPLACEMENTS[][] = {
        {
            ENDERMAN, VINDICATOR, HOGLIN
            },
        {
            SPIDER, SLIME, PILLAGER
            }
    };
    double index = Math.random();

    @ModifyReturnValue(method = "randomEntityId", at = @At("RETURN"))
    private EntityType<?> christcraft$subMob (EntityType<?> spawnerDefault) {

        if (spawnerDefault == ZOMBIE) {
            int index = (int)Math.floor(this.index * REPLACEMENTS[0].length);
            return REPLACEMENTS[0][index];

        } else if (spawnerDefault == SKELETON) {
            int index = (int)Math.floor(this.index * REPLACEMENTS[1].length);
            return REPLACEMENTS[1][index];

        } else {
            return spawnerDefault;

        }
    }

}
