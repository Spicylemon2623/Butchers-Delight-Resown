package net.takerudavis.butchers_delight_rechopped.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.takerudavis.butchers_delight_rechopped.block.AbstractCarcassBlock;
import net.takerudavis.butchers_delight_rechopped.block.AbstractHookableCarcassBlock;

public class CleaverItem extends AxeItem {

    public CleaverItem(Tier tier, Float attackDamageBonus, Float attackSpeed, Properties properties) {
        super(tier, attackDamageBonus, attackSpeed, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        Player player = useOnContext.getPlayer();
        ItemStack stack = useOnContext.getItemInHand();

        if (!(block instanceof AbstractCarcassBlock)) return InteractionResult.PASS;
        if (level.isClientSide) {
            if (block instanceof AbstractCarcassBlock)
                return InteractionResult.SUCCESS;
            else return InteractionResult.PASS;
        };

        InteractionResult result = null;
        if (block instanceof AbstractHookableCarcassBlock hookable) {
            result = hookable.processHooked(level, blockPos, blockState, player);
        }

        if (block instanceof AbstractCarcassBlock carcass) {
            result = carcass.processBasic(level, blockPos, blockState, player);
        }

        if (result == InteractionResult.SUCCESS && player != null) {
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(useOnContext.getHand()));
            return result;
        }
        return InteractionResult.PASS;
    }

}
