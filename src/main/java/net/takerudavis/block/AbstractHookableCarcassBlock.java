package net.takerudavis.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

abstract public class AbstractHookableCarcassBlock extends AbstractCarcassBlock {

    public AbstractHookableCarcassBlock(Properties properties) {
        super(properties);
    }

    public static final BooleanProperty HOOKED = BooleanProperty.create("hooked");

    @Override
    protected @NotNull BlockState makeDefaultProperties() {
        return super.makeDefaultProperties().setValue(HOOKED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HOOKED);
    }

    abstract public void processHooked(Level level, BlockPos blockPos, BlockState blockState, Player player);
}
