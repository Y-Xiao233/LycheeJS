package net.yxiao233.lycheejs;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(LycheeJS.MODID)
public class LycheeJS {
    public static final String MODID = "lycheejs";
    public LycheeJS(IEventBus modEventBus, ModContainer modContainer) {

    }

    public static ResourceLocation makeId(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID,path);
    }
}
