package com.christcraft.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import static net.minecraft.world.entity.EntityTypes.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.lang.Math;


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
    
    @ModifyReturnValue(method = "randomEntityId", at = @At("RETURN"))
    private EntityType<?> christcraft$subMob (EntityType<?> spawnerDefault) {
        
        double index = Math.random();
        
        if (spawnerDefault == ZOMBIE) {
            index = Math.floor(index * REPLACEMENTS[0].length);
            return REPLACEMENTS[0][(int)index];

        } else if (spawnerDefault == SKELETON) {
            index = Math.floor(index * REPLACEMENTS[1].length);
            return REPLACEMENTS[1][(int)index];

        } else {
            return spawnerDefault;

        }
    }
}
