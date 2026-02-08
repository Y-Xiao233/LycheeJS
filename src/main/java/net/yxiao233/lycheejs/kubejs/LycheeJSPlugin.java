package net.yxiao233.lycheejs.kubejs;

import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.yxiao233.lycheejs.kubejs.schema.*;
import net.yxiao233.lycheejs.kubejs.util.BlockPredicateWrapper;
import net.yxiao233.lycheejs.kubejs.util.ContextualBuilder;
import net.yxiao233.lycheejs.kubejs.util.PostBuilder;
import net.yxiao233.lycheejs.kubejs.util.SizedIngredientWrapper;

public class LycheeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        registry.namespace("lychee")
                .register("block_clicking", BlockClickingSchema.SCHEMA)
                .register("item_inside", ItemInsideSchema.SCHEMA)
                .register("anvil_crafting", AnvilCraftingSchema.SCHEMA)
                .register("block_crushing", BlockCrushingSchema.SCHEMA)
                .register("block_exploding", BlockExplodingSchema.SCHEMA)
                .register("block_interacting", BlockClickingSchema.SCHEMA)
                .register("dripstone_dripping", BlockDripStoneSchema.SCHEMA)
                .register("item_burning", ItemBurningSchema.SCHEMA)
                .register("item_exploding", ItemExplodingSchema.SCHEMA)
                .register("lightning_channeling", LightChannelingSchema.SCHEMA)
                .register("random_block_ticking", RandomBlockTickingSchema.SCHEMA);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("ContextualBuilder", ContextualBuilder.class);
        bindings.add("PostBuilder", PostBuilder.class);
        bindings.add("BlockPredicateWrapper", BlockPredicateWrapper.class);
        bindings.add("SizedIngredientWrapper", SizedIngredientWrapper.class);
    }
}
