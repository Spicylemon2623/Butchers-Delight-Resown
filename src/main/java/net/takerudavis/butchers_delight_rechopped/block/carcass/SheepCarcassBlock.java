package net.takerudavis.butchers_delight_rechopped.block.carcass;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;
import net.takerudavis.butchers_delight_rechopped.block.*;
import net.takerudavis.butchers_delight_rechopped.block.entity.CarcassBlockEntity;
import org.jetbrains.annotations.NotNull;

public class SheepCarcassBlock extends AbstractHookableCarcassBlock implements IRoastableCarcassBlock {

    public SheepCarcassBlock(Properties properties) {
        super(properties, EntityType.SHEEP);
    }

    public static ProcessingStage getInitialStage(LivingEntity entity) {
        return entity instanceof Sheep sheep && sheep.isSheared() ?
                ProcessingStage.SHEARED :
                ProcessingStage.INTACT;
    }

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(5.66667, 5.66667, 0, 17.66667, 9.66667, 4),
            Block.box(5.66667, -0.33333, 0, 17.66667, 3.66667, 4),
            Block.box(5.66667, 5.66667, 12, 17.66667, 9.66667, 16),
            Block.box(5.66667, -0.33333, 12, 17.66667, 3.66667, 16),
            Block.box(-4.33333, 1.66667, -7, 1.66667, 7.66667, 1),
            Block.box(1.16667, 0.33334, -0.83333, 7.16667, 8.33334, 15.16667)
    );

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (!state.getValue(HOOKED)) return SHAPE;
        return Shapes.empty();
    }

    @Override
    public CompoundTag extractAnimalData(LivingEntity entity) {
        if (!(entity instanceof Sheep sheep)) return null;
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("WoolColor", sheep.getColor().getId());
        return nbt;
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);

        if (!(stack.getItem() instanceof DyeItem dyeItem && state.getValue(STAGE) == ProcessingStage.INTACT && level.getBlockEntity(pos) instanceof CarcassBlockEntity be)) return super.use(state, level, pos, player, hand, hitResult);
        CompoundTag data = be.getCarcassData();
        data.putInt("WoolColor", dyeItem.getDyeColor().getId());
        be.setChanged();
        level.sendBlockUpdated(pos, state, state, SheepCarcassBlock.UPDATE_CLIENTS);
        stack.shrink(1);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult processBasic(Level level, BlockPos pos, BlockState state, Player player) {
        switch (state.getValue(STAGE)) {
            case INTACT -> level.setBlockAndUpdate(pos, state.setValue(STAGE, ProcessingStage.SHEARED));
            case SHEARED -> level.setBlockAndUpdate(pos, state.setValue(STAGE, ProcessingStage.SKINNED));
            default -> {
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult processHooked(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        return processBasic(level, blockPos, blockState, player);
    }

    @Override
    public void processRoasted(Level level, BlockPos pos, BlockState state, Player player) {

    }
}
