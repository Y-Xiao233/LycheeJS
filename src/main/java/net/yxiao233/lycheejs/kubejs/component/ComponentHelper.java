package net.yxiao233.lycheejs.kubejs.component;

import dev.latvian.mods.kubejs.recipe.component.ListRecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.kubejs.util.IntBounds;
import net.yxiao233.lycheejs.LycheeJS;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ComponentHelper {
    public static <T> RecipeComponentType<T> simpleType(String id, Function<RecipeComponentType<T>, RecipeComponent<T>> instanceGetter){
        return RecipeComponentType.unit(LycheeJS.makeId(id),instanceGetter);
    }

    public static <T> RecipeComponent<List<T>> simpleList(RecipeComponent<T> component){
        return ListRecipeComponent.create(component,true,true);
    }

    public static <T> RecipeComponent<List<T>> boundsList(RecipeComponent<T> component, int min, int max){
        return ListRecipeComponent.create(component,true,true, IntBounds.of(min,max), Optional.empty());
    }
}
