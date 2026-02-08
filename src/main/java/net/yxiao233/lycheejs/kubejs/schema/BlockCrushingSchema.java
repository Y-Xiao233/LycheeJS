package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.yxiao233.lycheejs.kubejs.component.BlockPredicateComponent;
import snownee.lychee.recipes.BlockCrushingRecipe;
import snownee.lychee.util.predicates.BlockPredicateExtensions;

public interface BlockCrushingSchema extends LycheeJSBaseRecipeSchema{
    RecipeKey<BlockPredicate> FALLING = BlockPredicateComponent.SINGLE.inputKey("falling_block").optional(BlockCrushingRecipe.ANVIL);
    RecipeKey<BlockPredicate> LANDING = BlockPredicateComponent.SINGLE.inputKey("landing_block").optional(BlockPredicateExtensions.ANY);
    RecipeSchema SCHEMA = new RecipeSchema(FALLING,LANDING,Key.sizedIngredientsWithDefault(),Key.postsWithDefault(),CONTEXTUAL,HIDE_IN_VIEWER,GHOST);
}
