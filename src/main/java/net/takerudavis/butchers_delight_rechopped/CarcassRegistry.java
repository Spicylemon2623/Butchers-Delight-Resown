package net.takerudavis.butchers_delight_rechopped;

import net.minecraft.world.entity.EntityType;
import net.takerudavis.butchers_delight_rechopped.block.AbstractCarcassBlock;

import java.util.HashMap;
import java.util.Map;

import static net.takerudavis.butchers_delight_rechopped.block.ModBlocks.CARCASS_BLOCKS;

public class CarcassRegistry {
    private static final Map<EntityType<?>, AbstractCarcassBlock> ANIMAL_TO_CARCASS = new HashMap<>();

    public static AbstractCarcassBlock register(AbstractCarcassBlock block) {
        ANIMAL_TO_CARCASS.put(block.getEntityType(), block);
        CARCASS_BLOCKS.add(block);
        return block;
    }

    public static AbstractCarcassBlock getCarcassFor(EntityType<?> type) {
        return ANIMAL_TO_CARCASS.get(type);
    }

}
