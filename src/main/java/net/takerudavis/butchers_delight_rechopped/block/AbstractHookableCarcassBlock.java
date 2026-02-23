package net.takerudavis.butchers_delight_rechopped.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

abstract public class AbstractHookableCarcassBlock extends AbstractCarcassBlock {

    public AbstractHookableCarcassBlock(Properties properties, EntityType<? extends LivingEntity> entityType) {
        super(properties, entityType);
    }

    public static final BooleanProperty HOOKED = BooleanProperty.create("hooked");

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        BlockState belowState = level.getBlockState(pos.below());

        return aboveState.getBlock() instanceof HookBlock || belowState.isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
        if (!state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }

        if (direction != Direction.UP) return super.updateShape(state, direction, neighbourState, level, pos, neighbourPos);
        if (!(neighbourState.isAir() && state.getValue(HOOKED))) return super.updateShape(state, direction, neighbourState, level, pos, neighbourPos);

        return state.setValue(HOOKED, false).setValue(FACING, Direction.NORTH);
    }

    @Override
    protected @NotNull BlockState makeDefaultProperties() {
        return super.makeDefaultProperties().setValue(HOOKED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HOOKED);
    }

    abstract public InteractionResult processHooked(Level level, BlockPos blockPos, BlockState blockState, Player player);
}
