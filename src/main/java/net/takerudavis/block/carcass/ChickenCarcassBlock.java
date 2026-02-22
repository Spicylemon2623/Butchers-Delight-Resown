package net.takerudavis.block.carcass;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.takerudavis.block.AbstractCarcassBlock;

public class ChickenCarcassBlock extends AbstractCarcassBlock {
    public ChickenCarcassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public CompoundTag extractAnimalData(LivingEntity entity) {
        if (!(entity instanceof net.minecraft.world.entity.animal.Chicken chicken)) return null;
        CompoundTag nbt = new CompoundTag(); // Extract normal/cold/warm variant
        nbt.putBoolean("Variant", chicken.isBaby());
        return nbt;
    }

    @Override
    public void processBasic(Level level, BlockPos pos, BlockState state, Player player) {

    }
}
