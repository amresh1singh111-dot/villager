package com.amresh.villagernews;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.Villager;

public final class VillagerNewsInteractions {
    private VillagerNewsInteractions() {}

    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hit) -> {
            if (world.isClientSide || hand != net.minecraft.world.InteractionHand.MAIN_HAND) return InteractionResult.PASS;
            if (!(entity instanceof Villager villager)) return InteractionResult.PASS;

            var held = player.getItemInHand(hand);
            if (held.is(VillagerNewsMod.MICROPHONE)) {
                player.sendSystemMessage(Component.literal("§6[Villager News] §f" + reporterLine(villager)));
                return InteractionResult.SUCCESS;
            }
            if (held.is(VillagerNewsMod.HANDBOOK)) {
                player.sendSystemMessage(Component.literal("§eVillager News Handbook"));
                player.sendSystemMessage(Component.literal("§7Use a microphone on a villager to interview them."));
                player.sendSystemMessage(Component.literal("§7News bulletins appear automatically from time to time."));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });
    }

    private static String reporterLine(Villager v) {
        return switch (v.getVillagerData().getProfession().name()) {
            case "FARMER" -> "Live from the farms: everything is about carrots today.";
            case "LIBRARIAN" -> "We have reports of a suspiciously large bookshelf.";
            case "CLERIC" -> "The cleric says the village is completely fine.";
            case "FLETCHER" -> "An arrow has been fired. More at eleven.";
            default -> "This villager has declined to comment. Hrrm.";
        };
    }
}
