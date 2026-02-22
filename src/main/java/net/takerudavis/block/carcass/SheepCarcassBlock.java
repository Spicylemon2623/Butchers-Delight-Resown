package net.takerudavis.block.carcass;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.takerudavis.block.AbstractHookableCarcassBlock;
import net.takerudavis.block.IRoastableCarcassBlock;
import net.takerudavis.block.ProcessingStage;
import net.takerudavis.block.entity.CarcassBlockEntity;
import org.jetbrains.annotations.NotNull;

public class SheepCarcassBlock extends AbstractHookableCarcassBlock implements IRoastableCarcassBlock {


    public SheepCarcassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ProcessingStage getInitialStage(LivingEntity entity) {
        return entity instanceof Sheep sheep && sheep.isSheared() ?
                ProcessingStage.SKINNED :
                ProcessingStage.INTACT;
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
    public void processBasic(Level level, BlockPos pos, BlockState state, Player player) {

    }

    @Override
    public void processHooked(Level level, BlockPos blockPos, BlockState blockState, Player player) {

    }

    @Override
    public void processRoasted(Level level, BlockPos pos, BlockState state, Player player) {

    }
}
