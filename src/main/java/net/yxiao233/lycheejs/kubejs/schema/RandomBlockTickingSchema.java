package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface RandomBlockTickingSchema extends LycheeJSBaseRecipeSchema{
    RecipeSchema SCHEMA = new RecipeSchema(BLOCK_IN,Key.postsWithDefault(),CONTEXTUAL,HIDE_IN_VIEWER,GHOST);
}
