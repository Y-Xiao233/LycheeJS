package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BooleanComponent;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.yxiao233.lycheejs.kubejs.component.BlockPredicateComponent;
import net.yxiao233.lycheejs.kubejs.component.ContextualHolderComponent;
import net.yxiao233.lycheejs.kubejs.component.PostActionComponent;
import net.yxiao233.lycheejs.kubejs.component.SizedIngredientComponent;
import snownee.kiwi.recipe.SizedIngredient;
import snownee.lychee.datagen.ActionBuilder;
import snownee.lychee.util.action.PostAction;
import snownee.lychee.util.contextual.ContextualHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface LycheeJSBaseRecipeSchema {
    RecipeKey<Boolean> HIDE_IN_VIEWER = BooleanComponent.BOOLEAN.otherKey("hide_in_viewer").optional(false);
    RecipeKey<Boolean> GHOST = BooleanComponent.BOOLEAN.otherKey("ghost").optional(false);
    RecipeKey<List<SizedIngredient>> ITEM_IN_2 = SizedIngredientComponent.LIST_2.inputKey("item_in");
    RecipeKey<List<SizedIngredient>> ITEM_IN = SizedIngredientComponent.LIST.inputKey("item_in");
    RecipeKey<SizedIngredient> SINGLE_ITEM_IN = SizedIngredientComponent.SINGLE.inputKey("item_in");
    RecipeKey<BlockPredicate> BLOCK_IN = BlockPredicateComponent.SINGLE.inputKey("block_in");
    RecipeKey<List<PostAction>> POSTS = PostActionComponent.LIST.outputKey("post");
    RecipeKey<ContextualHolder> CONTEXTUAL = ContextualHolderComponent.SINGLE.outputKey("contextual").optional(ContextualHolder.EMPTY);
}
