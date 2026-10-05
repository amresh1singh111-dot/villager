package com.amresh.villagernews;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.world.entity.npc.Villager;

import java.util.List;
import java.util.Random;

public class VillagerNewsMod implements ModInitializer {
    public static final String MOD_ID = "villagernews";
    private static final Random RANDOM = new Random();
    private static long newsTimer = 0;

    public static final Item HANDBOOK = new Item(new Item.Properties().stacksTo(1));
    public static final Item MAYOR_HAT = new Item(new Item.Properties().stacksTo(1));
    public static final Item MICROPHONE = new Item(new Item.Properties().stacksTo(1));
    public static final Item MOUSTACHE = new Item(new Item.Properties().stacksTo(1));
    public static final Item TESTIFICATE_HELMET = new Item(new Item.Properties().stacksTo(1));
    public static final Item VILLAGER_NOSE = new Item(new Item.Properties().stacksTo(1));

    private static final String[] NEWS = {
        "BREAKING NEWS! A villager has discovered a potato!",
        "Villager News: The village is having a very normal day.",
        "BREAKING NEWS! Somebody has rung a bell.",
        "Villager News: A wandering trader has been spotted nearby.",
        "IMPORTANT REPORT: A farmer is looking for carrots.",
        "BREAKING NEWS! Someone built something suspiciously large.",
        "Villager News: The weather remains completely unpredictable.",
        "LIVE REPORT: A villager has misplaced their workstation."
    };

    @Override
    public void onInitialize() {
        ModItems.register();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.accept(HANDBOOK);
            entries.accept(MICROPHONE);
            entries.accept(MAYOR_HAT);
            entries.accept(MOUSTACHE);
            entries.accept(TESTIFICATE_HELMET);
            entries.accept(VILLAGER_NOSE);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            newsTimer++;
            // Every ~60 seconds, publish a news bulletin when players are online.
            if (newsTimer >= 1200) {
                newsTimer = 0;
                if (!server.getPlayerList().getPlayers().isEmpty()) {
                    String bulletin = NEWS[RANDOM.nextInt(NEWS.length)];
                    for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                        player.sendSystemMessage(Component.literal("§6[Villager News] §f" + bulletin));
                    }
                }
            }
        });

        VillagerNewsInteractions.register();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static class ModItems {
        public static void register() {
            net.minecraft.core.registries.BuiltInRegistries.ITEM.register(id("handbook"), HANDBOOK);
            net.minecraft.core.registries.BuiltInRegistries.ITEM.register(id("mayor_hat"), MAYOR_HAT);
            net.minecraft.core.registries.BuiltInRegistries.ITEM.register(id("microphone"), MICROPHONE);
            net.minecraft.core.registries.BuiltInRegistries.ITEM.register(id("moustache"), MOUSTACHE);
            net.minecraft.core.registries.BuiltInRegistries.ITEM.register(id("testificate_helmet"), TESTIFICATE_HELMET);
            net.minecraft.core.registries.BuiltInRegistries.ITEM.register(id("villager_nose"), VILLAGER_NOSE);
        }
    }
}
