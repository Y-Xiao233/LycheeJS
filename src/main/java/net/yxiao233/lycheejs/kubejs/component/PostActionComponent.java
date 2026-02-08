package net.yxiao233.lycheejs.kubejs.component;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.type.TypeInfo;
import snownee.lychee.util.action.PostAction;

import java.util.List;

public record PostActionComponent(RecipeComponentType<?> type) implements RecipeComponent<PostAction> {
    public static final RecipeComponentType<PostAction> SINGLE = ComponentHelper.simpleType("post_action",PostActionComponent::new);
    public static final RecipeComponent<List<PostAction>> LIST = ComponentHelper.simpleList(new PostActionComponent(SINGLE));
    @Override
    public Codec<PostAction> codec() {
        return PostAction.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(PostAction.class);
    }
}
