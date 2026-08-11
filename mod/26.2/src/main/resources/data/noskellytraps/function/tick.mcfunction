execute as @e[type=minecraft:skeleton_horse,nbt={Passengers:[{id:"minecraft:skeleton"}]}] run execute as @e[type=minecraft:skeleton_horse,nbt={SkeletonTrap:0b}] run kill @e[type=skeleton]
execute as @e[type=minecraft:skeleton_horse] run kill @s
