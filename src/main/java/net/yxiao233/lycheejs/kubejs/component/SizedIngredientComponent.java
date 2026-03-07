package net.yxiao233.lycheejs.kubejs.component;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.world.item.crafting.Ingredient;
import snownee.kiwi.recipe.SizedIngredient;
import snownee.lychee.util.codec.LycheeCodecs;

import java.util.List;

public record SizedIngredientComponent(RecipeComponentType<?> type) implements RecipeComponent<SizedIngredient> {
    public static final RecipeComponentType<SizedIngredient> SINGLE = ComponentHelper.simpleType("sized_ingredient",SizedIngredientComponent::new);
    public static final RecipeComponent<List<SizedIngredient>> LIST = ComponentHelper.simpleList(new SizedIngredientComponent(SINGLE));
    public static final RecipeComponent<List<SizedIngredient>> LIST_2 = ComponentHelper.boundsList(new SizedIngredientComponent(SINGLE),1,2);

    @Override
    public Codec<SizedIngredient> codec() {
        return new Codec<>() {
            @Override
            public <T> DataResult<Pair<SizedIngredient, T>> decode(DynamicOps<T> ops, T input) {
                // Handle null, empty, or missing input
                if (input == null || ops.empty() == input) {
                    return DataResult.success(new Pair<>(new SizedIngredient(Ingredient.EMPTY, 1), input));
                }
                // Otherwise use the normal codec
                return LycheeCodecs.SIZED_INGREDIENT.decode(ops, input);
            }

            @Override
            public <T> DataResult<T> encode(SizedIngredient input, DynamicOps<T> ops, T prefix) {
                // If ingredient is empty, return empty JSON object {}
                if (input.ingredient().isEmpty()) {
                    // Create an empty JSON object instead of null
                    return DataResult.success(ops.createMap(java.util.Map.of()));
                }
                // Otherwise use the normal codec
                return LycheeCodecs.SIZED_INGREDIENT.encode(input, ops, prefix);
            }
        };
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(SizedIngredient.class);
    }
}
