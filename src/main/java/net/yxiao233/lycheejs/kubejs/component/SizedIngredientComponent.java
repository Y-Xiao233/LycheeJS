package net.yxiao233.lycheejs.kubejs.component;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.type.TypeInfo;
import snownee.kiwi.recipe.SizedIngredient;
import snownee.lychee.util.codec.LycheeCodecs;

import java.util.List;

public record SizedIngredientComponent(RecipeComponentType<?> type) implements RecipeComponent<SizedIngredient> {
    public static final RecipeComponentType<SizedIngredient> SINGLE = ComponentHelper.simpleType("sized_ingredient",SizedIngredientComponent::new);
    public static final RecipeComponent<List<SizedIngredient>> LIST = ComponentHelper.simpleList(new SizedIngredientComponent(SINGLE));
    public static final RecipeComponent<List<SizedIngredient>> LIST_2 = ComponentHelper.boundsList(new SizedIngredientComponent(SINGLE),1,2);
    @Override
    public Codec<SizedIngredient> codec() {
        return LycheeCodecs.SIZED_INGREDIENT;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(SizedIngredient.class);
    }
}
