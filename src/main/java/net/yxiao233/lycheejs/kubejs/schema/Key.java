package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.yxiao233.lycheejs.kubejs.component.BlockPredicateComponent;
import net.yxiao233.lycheejs.kubejs.component.PostActionComponent;
import net.yxiao233.lycheejs.kubejs.component.SizedIngredientComponent;
import snownee.kiwi.recipe.SizedIngredient;
import snownee.lychee.datagen.ActionBuilder;
import snownee.lychee.util.action.PostAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Key {
    @SuppressWarnings("all")
    static RecipeKey<List<PostAction>> postsWithDefault(PostAction... actions){
        List<PostAction> list = new ArrayList<>();
        Arrays.stream(actions).toList().forEach(action -> {
            list.add(new ActionBuilder(action).asAction());
        });
        return PostActionComponent.LIST.outputKey("post").optional(list);
    }
    static RecipeKey<BlockPredicate> blockPredicate(String key){
        return BlockPredicateComponent.SINGLE.inputKey(key);
    }
    static RecipeKey<List<SizedIngredient>> sizedIngredientsWithDefault(SizedIngredient... ingredients){
        return SizedIngredientComponent.LIST.inputKey("item_in").optional(Arrays.stream(ingredients).toList());
    }
}
