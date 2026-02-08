package net.yxiao233.lycheejs.kubejs.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import snownee.lychee.recipes.BlockCrushingRecipe;
import snownee.lychee.util.predicates.BlockPredicateExtensions;

@SuppressWarnings("unused")
public interface BlockPredicateWrapper {
    @HideFromJS
    Codec<BlockPredicate> CODEC = Codec.of(new Encoder<>() {
        @Override
        public <T> DataResult<T> encode(BlockPredicate input, DynamicOps<T> ops, T prefix) {
            if (input.equals(BlockPredicateExtensions.ANY)) {
                return DataResult.success(ops.createString("*"));
            }
            return BlockPredicateExtensions.CODEC.encode(input, ops, prefix);
        }
    },BlockPredicateExtensions.CODEC);
    @Info("any match")
    BlockPredicate ANY = BlockPredicateExtensions.ANY;
    @Info("anvil")
    BlockPredicate ANVIL = BlockCrushingRecipe.ANVIL;
    @SuppressWarnings("unchecked")
    @Info("Object block, Supported Types:[Block, Block[], BlockPredicate, TagKey<Block>, String]")
    static BlockPredicate block(Object o){
        return switch (o) {
            case Block block -> BlockPredicate.Builder.block().of(block).build();
            case Block[] blocks -> BlockPredicate.Builder.block().of(blocks).build();
            case BlockPredicate blockPredicate -> blockPredicate;
            case TagKey<?> tag when tag.isFor(Registries.BLOCK.registryKey()) -> BlockPredicate.Builder.block().of((TagKey<Block>) tag).build();
            case String s -> BlockPredicateExtensions.fromString(s, true).getOrThrow();
            default -> throw new IllegalArgumentException("Invalid argument: " + String.valueOf(o));
        };
    }
}
