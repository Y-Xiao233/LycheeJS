package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface ItemBurningSchema extends LycheeJSBaseRecipeSchema{
    RecipeSchema SCHEMA = new RecipeSchema(SINGLE_ITEM_IN,Key.postsWithDefault(),CONTEXTUAL,HIDE_IN_VIEWER,GHOST);
}
