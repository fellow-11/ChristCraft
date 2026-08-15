package com.christcraft.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Random;

@Mixin(MonsterRoomFeature.class)
public class MonsterRoomFeatureMixin {
    private static final Random RANDOM = new Random();
    private static final List<EntityType<?>> ZOMBIE_SUBS = List.of(
            EntityTypes.ENDERMAN, EntityTypes.VINDICATOR, EntityTypes.HOGLIN);
    private static final List<EntityType<?>> SKELETON_SUBS = List.of(
            EntityTypes.PILLAGER, EntityTypes.BLAZE);

    @ModifyReturnValue(method = "randomEntityId", at = @At("RETURN"))
    private EntityType<?> christcraft$swapDungeonMob(EntityType<?> original) {
        if (original == EntityTypes.ZOMBIE) {
            return ZOMBIE_SUBS.get(RANDOM.nextInt(ZOMBIE_SUBS.size()));
        }
        if (original == EntityTypes.SKELETON) {
            return SKELETON_SUBS.get(RANDOM.nextInt(SKELETON_SUBS.size()));
        }
        return original;
    }
}