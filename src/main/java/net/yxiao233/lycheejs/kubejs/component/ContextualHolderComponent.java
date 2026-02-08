package net.yxiao233.lycheejs.kubejs.component;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.yxiao233.lycheejs.LycheeJS;
import snownee.lychee.util.contextual.ContextualHolder;

public record ContextualHolderComponent(RecipeComponentType<?> type) implements RecipeComponent<ContextualHolder> {
    public static final RecipeComponentType<ContextualHolder> SINGLE = ComponentHelper.simpleType("contextual_holder",ContextualHolderComponent::new);
    @Override
    public Codec<ContextualHolder> codec() {
        return ContextualHolder.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(ContextualHolder.class);
    }
}
