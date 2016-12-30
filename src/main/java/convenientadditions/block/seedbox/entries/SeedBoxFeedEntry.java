package convenientadditions.block.seedbox.entries;

import convenientadditions.api.entity.specialitem.BehaviourRegistry;
import convenientadditions.api.registry.seedbox.ISeedBoxItemBehaviourRegistryEntry;
import convenientadditions.init.ModConfig;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SeedBoxFeedEntry implements ISeedBoxItemBehaviourRegistryEntry {

    @Override
    public boolean hasSpecialBehaviour(ItemStack stack) {
        return ModConfig.seedBox_autoFeedItems.contains(stack.getItem().getRegistryName().toString());
    }

    @Override
    public void getDiscriminators(ItemStack stack, List<Long> behaviours) {
        behaviours.add(BehaviourRegistry.API_DISCRIMINATORS.get("autoFeed"));
    }

}
