package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface BlockClickingSchema extends LycheeJSBaseRecipeSchema{
    RecipeSchema SCHEMA = new RecipeSchema(ITEM_IN_2,BLOCK_IN,Key.postsWithDefault(),CONTEXTUAL,HIDE_IN_VIEWER,GHOST);
}
