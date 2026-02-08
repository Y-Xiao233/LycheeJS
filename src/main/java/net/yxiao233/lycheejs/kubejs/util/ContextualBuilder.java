package net.yxiao233.lycheejs.kubejs.util;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.world.Difficulty;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import snownee.lychee.contextual.*;
import snownee.lychee.util.context.LycheeContext;
import snownee.lychee.util.context.LycheeContextKey;
import snownee.lychee.util.contextual.ContextualCondition;
import snownee.lychee.util.contextual.ContextualConditionData;
import snownee.lychee.util.contextual.ContextualHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class ContextualBuilder {
    @HideFromJS
    private final List<ContextualConditionData> contextual = new ArrayList<>();
    @Info("create ContextualBuilder for contextual")
    public static ContextualBuilder create(){
        return new ContextualBuilder();
    }

    @Info("float chance")
    public ContextualBuilder chance(float chance){
        this.contextual.add(new ContextualConditionData(new Chance(chance)));
        return this;
    }

    @Info("add condition for Java.loadClass(xxx)")
    public ContextualBuilder condition(ContextualCondition condition){
        this.contextual.add(new ContextualConditionData(condition));
        return this;
    }

    @Info("ContextualHolder holder")
    public ContextualBuilder and(ContextualHolder holder){
        return condition(new And(holder));
    }

    @Info("String id, JsonObject data")
    public ContextualBuilder customCondition(String id, JsonObject data){
        return condition(new CustomCondition(id,data));
    }

    @Info("String name, Predicate<LycheeContext> contextPredicate")
    public ContextualBuilder directionCheck(String name, Predicate<LycheeContext> contextPredicate){
        DirectionCheck.createLookup(name,contextPredicate);
        return condition(DirectionCheck.LOOKUPS.get(name));
    }

    @Info("double min, double max")
    public ContextualBuilder entityHealth(double min, double max){
        return condition(new EntityHealth(MinMaxBounds.Doubles.between(min,max)));
    }

    @Info("String command")
    public ContextualBuilder execute(String command){
        return condition(new Execute(command));
    }

    @Info("double min, double max")
    public ContextualBuilder fallDistance(double min, double max){
        return condition(new FallDistance(MinMaxBounds.Doubles.between(min,max)));
    }

    @Info("Object[] difficulty, Supported Types:[Difficulty, String:{'peaceful','easy','normal','hard'}]")
    public ContextualBuilder isDifficulty(Object... o){
        List<Difficulty> list = new ArrayList<>();
        for (Object object : o) {
            if(object instanceof Difficulty difficulty){
                list.add(difficulty);
            }else if(object instanceof String s){
                for (Difficulty value : Difficulty.values()) {
                    if(s.equals(value.name().toLowerCase())){
                        list.add(value);
                        break;
                    }
                }
            }
        }
        return condition(new IsDifficulty(list));
    }

    @Info("ItemStack stack")
    public ContextualBuilder isOffItemCooldown(ItemStack stack){
        return condition(new IsOffItemCooldown(Holder.direct(stack.getItem())));
    }

    public ContextualBuilder isSneaking(){
        return condition(new IsSneaking());
    }

    public ContextualBuilder isWeatherClear(){
        return condition(IsWeather.CLEAR);
    }

    public ContextualBuilder isWeatherRain(){
        return condition(IsWeather.RAIN);
    }

    public ContextualBuilder isWeatherThunder(){
        return condition(IsWeather.THUNDER);
    }

    @Info("String id, Predicate<Level> levelPredicate")
    public ContextualBuilder customIsWeather(String id, Predicate<Level> levelPredicate){
        IsWeather.create(id,levelPredicate);
        return condition(IsWeather.REGISTRY.get(id));
    }

    @Info("LocationCheck check")
    public ContextualBuilder location(LocationCheck check){
        return condition(new Location(check));
    }

    @Info("ContextualCondition condition")
    public ContextualBuilder not(ContextualCondition condition){
        return condition(new Not(condition));
    }

    @Info("ContextualHolder holder")
    public ContextualBuilder or(ContextualHolder holder){
        return condition(new Or(holder));
    }

    @Info("Holder<LycheeContextKey<?>> key, boolean create, String loot")
    public ContextualBuilder param(Holder<LycheeContextKey<?>> key, boolean create, String loot){
        return condition(new Param(key,create,loot));
    }

    @Info("int min, int max, boolean requireSkyLight")
    public ContextualBuilder skyDarken(int min, int max, boolean requireSkyLight){
        return condition(new SkyDarken(MinMaxBounds.Ints.between(min,max),requireSkyLight));
    }

    @Info("int min, int max, long period")
    public ContextualBuilder time(int min, int max, long period){
        return condition(new Time(MinMaxBounds.Ints.between(min,max),Optional.of(period)));
    }


    @HideFromJS
    private void clear(){
        this.contextual.clear();
    }
    @Info("build to contextual")
    public ContextualHolder build(){
        ContextualHolder holder = ContextualHolder.pack(List.copyOf(contextual));
        clear();
        return holder;
    }
}
