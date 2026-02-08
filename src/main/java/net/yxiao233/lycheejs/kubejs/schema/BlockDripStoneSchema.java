package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface BlockDripStoneSchema extends LycheeJSBaseRecipeSchema{
    RecipeSchema SCHEMA = new RecipeSchema(Key.blockPredicate("source_block"),Key.blockPredicate("target_block"),Key.postsWithDefault(),CONTEXTUAL);
}
