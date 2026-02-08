package net.yxiao233.lycheejs.kubejs.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import snownee.kiwi.recipe.SizedIngredient;
import snownee.lychee.util.codec.ParsedItem;

public interface SizedIngredientWrapper {
    @SuppressWarnings("unchecked")
    @Info("Object item, int count, Supported Types:[ItemStack, Item, SizedIngredient, TagKey<Item>, ItemLike, String]")
    static SizedIngredient of(Object o, int count){
        return switch (o) {
            case ItemStack stack -> SizedIngredient.of(stack.getItem(), stack.getCount());
            case Item item -> SizedIngredient.of(item, count);
            case Ingredient ingredient -> new SizedIngredient(ingredient, count);
            case SizedIngredient sizedIngredient -> sizedIngredient;
            case TagKey<?> tag when tag.isFor(Registries.ITEM.registryKey()) -> SizedIngredient.of((TagKey<Item>) tag, count);
            case ItemLike itemLike -> SizedIngredient.of(itemLike, count);
            case String s -> parse(s).sizedIngredient();
            default -> throw new IllegalArgumentException("Invalid argument: " + String.valueOf(o));
        };
    }

    @Info("Object item, default count = 1, Supported Types:[ItemStack, Item, SizedIngredient, TagKey<Item>, ItemLike, String]")
    static SizedIngredient of(Object o){
        return of(o,1);
    }

    @HideFromJS
    static ParsedItem parse(String s, boolean single) {
        try {
            return ParsedItem.read(new StringReader(s), single);
        } catch (CommandSyntaxException var3) {
            throw new RuntimeException(var3);
        }
    }

    @HideFromJS
    static ParsedItem parse(String s) {
        return parse(s, false);
    }
}
