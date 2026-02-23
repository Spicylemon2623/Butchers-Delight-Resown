package net.takerudavis.butchers_delight_rechopped.block.carcass;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.takerudavis.butchers_delight_rechopped.block.AbstractCarcassBlock;
import net.takerudavis.butchers_delight_rechopped.block.ProcessingStage;

public class ChickenCarcassBlock extends AbstractCarcassBlock {
    public ChickenCarcassBlock(Properties properties) {
        super(properties, EntityType.CHICKEN);
    }

    @Override
    public CompoundTag extractAnimalData(LivingEntity entity) {
        if (!(entity instanceof net.minecraft.world.entity.animal.Chicken chicken)) return null;
        CompoundTag nbt = new CompoundTag(); // Extract normal/cold/warm variant
        nbt.putBoolean("Variant", chicken.isBaby());
        return nbt;
    }

    private static final VoxelShape SHAPE = Shapes.or(
            Shapes.box(0.260953125, -0.010493125, 0.333946875, 0.635953125, 0.364506875, 0.833946875),
            Shapes.box(0.364506875, -0.072993125, 0.375, 0.614506875, -0.010493125, 0.75),
            Shapes.box(0.364506875, 0.364506875, 0.375, 0.614506875, 0.427006875, 0.75),
            Shapes.box(0.614506875, -0.010493125, 0.625, 0.927006875, 0.177006875, 0.625),
            Shapes.box(0.927006875, -0.010493125, 0.4375, 0.927006875, 0.177006875, 0.625),
            Shapes.box(0.927006875, 0.177006875, 0.4375, 0.927006875, 0.364506875, 0.625),
            Shapes.box(0.614506875, 0.177006875, 0.625, 0.927006875, 0.364506875, 0.625),
            Shapes.box(0.114506875, 0.052006875, 0.1875, 0.489506875, 0.302006875, 0.375),
            Shapes.box(0.364506875, 0.114506875, 0.125, 0.489506875, 0.239506875, 0.25),
            Shapes.box(0.239506875, 0.052006875, 0.0625, 0.364506875, 0.302006875, 0.1875)
    );

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult processBasic(Level level, BlockPos pos, BlockState state, Player player) {
        switch (state.getValue(STAGE)) {
            case INTACT -> level.setBlockAndUpdate(pos, state.setValue(STAGE, ProcessingStage.SHEARED));
            default -> {
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.SUCCESS;
    }
}
