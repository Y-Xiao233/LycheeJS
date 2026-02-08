package net.yxiao233.lycheejs.kubejs.component;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.yxiao233.lycheejs.kubejs.util.BlockPredicateWrapper;
import snownee.lychee.util.predicates.BlockPredicateExtensions;

import java.util.List;

public record BlockPredicateComponent(RecipeComponentType<?> type) implements RecipeComponent<BlockPredicate> {
    public static final RecipeComponentType<BlockPredicate> SINGLE = ComponentHelper.simpleType("block_predicate",BlockPredicateComponent::new);
    public static final RecipeComponent<List<BlockPredicate>> LIST = ComponentHelper.simpleList(new BlockPredicateComponent(SINGLE));
    @Override
    public Codec<BlockPredicate> codec() {
        return BlockPredicateWrapper.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(BlockPredicate.class);
    }
}
