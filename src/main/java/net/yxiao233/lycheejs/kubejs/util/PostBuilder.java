package net.yxiao233.lycheejs.kubejs.util;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.Difficulty;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.phys.Vec3;
import snownee.lychee.action.*;
import snownee.lychee.action.Execute;
import snownee.lychee.action.input.PreventDefault;
import snownee.lychee.contextual.*;
import snownee.lychee.datagen.ActionBuilder;
import snownee.lychee.util.action.PostAction;
import snownee.lychee.util.action.PostActionCommonProperties;
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
public class PostBuilder {
    @HideFromJS
    private final List<ContextualConditionData> contextual = new ArrayList<>();
    @Info("create PostBuilder to get PostAction")
    public static PostBuilder create(){
        return new PostBuilder();
    }

    @Info("float chance, chance to post")
    public PostBuilder withChance(float chance){
        contextual.add(new ContextualConditionData(new Chance(chance)));
        return this;
    }

    @Info("default post for anvil crafting")
    public PostAction preventDefault(){
        return action(new PreventDefault(pack()));
    }

    @Info("add condition for Java.loadClass(xxx)")
    public PostBuilder condition(ContextualCondition condition){
        contextual.add(new ContextualConditionData(condition));
        return this;
    }
    @Info("ContextualHolder holder")
    public PostBuilder and(ContextualHolder holder){
        return condition(new And(holder));
    }

    @Info("String id, JsonObject data")
    public PostBuilder customCondition(String id, JsonObject data){
        return condition(new CustomCondition(id,data));
    }

    @Info("String name, Predicate<LycheeContext> contextPredicate")
    public PostBuilder directionCheck(String name, Predicate<LycheeContext> contextPredicate){
        DirectionCheck.createLookup(name,contextPredicate);
        return condition(DirectionCheck.LOOKUPS.get(name));
    }

    @Info("double min, double max")
    public PostBuilder entityHealth(double min, double max){
        return condition(new EntityHealth(MinMaxBounds.Doubles.between(min,max)));
    }

    @Info("String command")
    public PostBuilder execute(String command){
        return condition(new snownee.lychee.contextual.Execute(command));
    }

    @Info("double min, double max")
    public PostBuilder fallDistance(double min, double max){
        return condition(new FallDistance(MinMaxBounds.Doubles.between(min,max)));
    }

    @Info("Object[] difficulty, Supported Types:[Difficulty, String:{'peaceful','easy','normal','hard'}]")
    public PostBuilder isDifficulty(Object... o){
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
    public PostBuilder isOffItemCooldown(ItemStack stack){
        return condition(new IsOffItemCooldown(Holder.direct(stack.getItem())));
    }

    public PostBuilder isSneaking(){
        return condition(new IsSneaking());
    }

    public PostBuilder isWeatherClear(){
        return condition(IsWeather.CLEAR);
    }

    public PostBuilder isWeatherRain(){
        return condition(IsWeather.RAIN);
    }

    public PostBuilder isWeatherThunder(){
        return condition(IsWeather.THUNDER);
    }

    @Info("String id, Predicate<Level> levelPredicate")
    public PostBuilder customIsWeather(String id, Predicate<Level> levelPredicate){
        IsWeather.create(id,levelPredicate);
        return condition(IsWeather.REGISTRY.get(id));
    }

    @Info("LocationCheck check")
    public PostBuilder location(LocationCheck check){
        return condition(new Location(check));
    }

    @Info("ContextualCondition condition")
    public PostBuilder not(ContextualCondition condition){
        return condition(new Not(condition));
    }

    @Info("ContextualHolder holder")
    public PostBuilder or(ContextualHolder holder){
        return condition(new Or(holder));
    }

    @Info("Holder<LycheeContextKey<?>> key, boolean create, String loot")
    public PostBuilder param(Holder<LycheeContextKey<?>> key, boolean create, String loot){
        return condition(new Param(key,create,loot));
    }

    @Info("int min, int max, boolean requireSkyLight")
    public PostBuilder skyDarken(int min, int max, boolean requireSkyLight){
        return condition(new SkyDarken(MinMaxBounds.Ints.between(min,max),requireSkyLight));
    }

    @Info("int min, int max, long period")
    public PostBuilder time(int min, int max, long period){
        return condition(new Time(MinMaxBounds.Ints.between(min,max),Optional.of(period)));
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    @Info("get action for Java.loadClass(xxx)")
    public PostAction action(PostAction action){
        PostAction postAction = new ActionBuilder(action).asAction();
        clear();
        return postAction;
    }

    @Info("ItemStack stack")
    public PostAction dropItem(ItemStack stack){
        return action(new DropItem(pack(),stack));
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    @Info("ItemStack stack, BlockPos offset, consume origin block with offset and drop item")
    public List<PostAction> consumeAndDropItem(ItemStack stack, BlockPos offset){
        PostAction consume = new ActionBuilder(new PlaceBlock(PostActionCommonProperties.EMPTY,BlockPredicateWrapper.block(Blocks.AIR),offset)).asAction();
        return List.of(consume,dropItem(stack));
    }

    @Info("ItemStack stack, consume origin block with default pos and drop item")
    public List<PostAction> consumeAndDropItem(ItemStack stack){
        return consumeAndDropItem(stack,BlockPos.ZERO);
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    @Info("Object block, BlockPos offset, consume origin block with offset and place block")
    public List<PostAction> consumeAndPlace(Object o, BlockPos offset){
        PostAction consume = new ActionBuilder(new PlaceBlock(PostActionCommonProperties.EMPTY,BlockPredicateWrapper.block(Blocks.AIR),offset)).asAction();
        return List.of(consume,place(BlockPredicateWrapper.block(o),offset));
    }

    @Info("Object block, consume origin block with default pos and place block")
    public List<PostAction> consumeAndPlace(Object o){
        return consumeAndPlace(o,BlockPos.ZERO);
    }

    @Info("consume block with default pos")
    public PostAction consumeBlock(){
        return place(BlockPredicateWrapper.block(Blocks.AIR));
    }

    @Info("Object block, BlockPos offset, place block with offset")
    public PostAction place(Object o, BlockPos offset){
        return action(new PlaceBlock(pack(), BlockPredicateWrapper.block(o),offset));
    }

    @Info("Object block, place block with default pos")
    public PostAction place(Object o){
        return place(o,BlockPos.ZERO);
    }

    @Info("float seconds, ItemStack stack")
    public PostAction addItemCooldown(float seconds, ItemStack stack){
        return action(new AddItemCooldown(pack(),seconds,Optional.of(stack.getItem())));
    }

    @Info("float chance")
    public PostAction anvilDamageChance(float chance){
        return action(new AnvilDamageChance(pack(),chance));
    }

    @Info("String id, JsonObject jsonObject, boolean repeatable, boolean preventSync")
    public PostAction customAction(String id, JsonObject jsonObject, boolean repeatable, boolean preventSync){
        return action(new CustomAction(pack(),id,jsonObject,repeatable,preventSync));
    }

    @Info("Object block, BlockPos offset, String propertyName, boolean reversed")
    public PostAction cycleState(Object o, BlockPos offset, String propertyName, boolean reversed){
        return action(new CycleStateProperty(pack(),BlockPredicateWrapper.block(o),offset,propertyName,reversed));
    }

    @Info("float seconds")
    public PostAction delay(float seconds){
        return action(new Delay(seconds));
    }

    @Info("int xp")
    public PostAction dropXp(int xp){
        return action(new DropXp(pack(),xp));
    }

    @Info("String command, boolean repeat")
    public PostAction execute(String command, boolean repeat){
        return action(new Execute(pack(),command,repeat));
    }

    public PostAction exit(){
        return action(new Exit(pack()));
    }

    @Info("Object blockInteraction, BlockPos offset, boolean fire, float radius, float step, Supported Types:[BlockInteraction, String:{'keep','destroy','destroy_with_decay','trigger_block'}]")
    public PostAction explode(Object o, BlockPos offset, boolean fire, float radius, float step){
        Explosion.BlockInteraction blockInteraction = Explosion.BlockInteraction.KEEP;
        if(o instanceof Explosion.BlockInteraction interaction){
            blockInteraction = interaction;
        }else if(o instanceof String s){
            for (Explosion.BlockInteraction value : Explosion.BlockInteraction.values()) {
                if(s.equals(value.name().toLowerCase())){
                    blockInteraction = value;
                    break;
                }
            }
        }
        return new Explode(pack(),blockInteraction,offset,fire,radius,step);
    }

    @Info("List<PostAction> successEntries, List<PostAction> failureEntries, boolean canRepeat, boolean hidden, boolean preventSync")
    public PostAction iff(List<PostAction> successEntries, List<PostAction> failureEntries, boolean canRepeat, boolean hidden, boolean preventSync){
        return action(new If(pack(),successEntries,failureEntries,canRepeat,hidden,preventSync));
    }

    @Info("Vec3 offset")
    public PostAction move(Vec3 offset){
        return action(new Move(pack(),offset,""));
    }

    @Info("Vec3 offset, String with")
    public PostAction move(Vec3 offset, String with){
        return action(new Move(pack(),offset,with));
    }

    @Info("float factor")
    public PostAction moveTowardsFace(float factor){
        return action(new MoveTowardsFace(pack(),factor));
    }

    @Info("List<RandomSelect.Entry> entries, int emptyWeight, int min, int max")
    public PostAction randomSelect(List<RandomSelect.Entry> entries, int emptyWeight, int min, int max){
        return action(new RandomSelect(pack(),entries,emptyWeight, MinMaxBounds.Ints.between(min,max)));
    }

    @Info("PostAction action, int weight")
    public RandomSelect.Entry entry(PostAction action, int weight){
        return new RandomSelect.Entry(action,weight);
    }

    @Info("Object block")
    public PostAction setBlock(Object o){
        return action(new SetBlock(pack(),BlockPredicateWrapper.block(o)));
    }

    @HideFromJS
    private void clear(){
        this.contextual.clear();
    }

    @HideFromJS
    private PostActionCommonProperties pack(){
        if(contextual.isEmpty()){
            return PostActionCommonProperties.EMPTY;
        }
        return new PostActionCommonProperties(ContextualHolder.pack(List.copyOf(contextual)), Optional.empty());
    }
}
