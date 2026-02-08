package net.yxiao233.lycheejs.kubejs.component;

import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ListRecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.util.IntBounds;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Optional;

public interface KeyHelper {
    static <T> RecipeComponent<List<T>> listKey(RecipeComponent<T> component){
        return ListRecipeComponent.create(component,true,true);
    }

    static RecipeComponent<List<Ingredient>> listIngredient(){
        return ListRecipeComponent.create(new IngredientComponent(IngredientComponent.OPTIONAL_INGREDIENT,Ingredient.CODEC,true),true,true);
    }

    static RecipeComponent<List<Ingredient>> boundsIngredientList(int min, int max){
        return ListRecipeComponent.create(new IngredientComponent(IngredientComponent.OPTIONAL_INGREDIENT,Ingredient.CODEC,true),true,true, IntBounds.of(min,max), Optional.empty());
    }
}
