package net.yxiao233.lycheejs.kubejs.schema;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.yxiao233.lycheejs.kubejs.component.KeyHelper;
import snownee.lychee.action.input.PreventDefault;

import java.util.List;

public interface AnvilCraftingSchema extends LycheeJSBaseRecipeSchema{
    RecipeKey<List<Ingredient>> ITEM_IN = KeyHelper.boundsIngredientList(1,2).inputKey("item_in");
    RecipeKey<ItemStack> ITEM_OUT = ItemStackComponent.ITEM_STACK.outputKey("item_out");
    RecipeKey<Integer> LEVEL_COST = NumberComponent.INT.inputKey("level_cost");
    RecipeKey<Integer> MATERIAL_COST = NumberComponent.INT.inputKey("material_cost");
    RecipeSchema SCHEMA = new RecipeSchema(ITEM_IN,ITEM_OUT,LEVEL_COST,MATERIAL_COST,Key.postsWithDefault(new PreventDefault()),CONTEXTUAL,HIDE_IN_VIEWER,GHOST);
}
