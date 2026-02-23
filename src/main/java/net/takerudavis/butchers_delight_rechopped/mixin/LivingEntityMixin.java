package net.takerudavis.butchers_delight_rechopped.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.takerudavis.butchers_delight_rechopped.CarcassRegistry;
import net.takerudavis.butchers_delight_rechopped.block.AbstractCarcassBlock;
import net.takerudavis.butchers_delight_rechopped.block.ProcessingStage;
import net.takerudavis.butchers_delight_rechopped.item.CleaverItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "dropFromLootTable", at = @At("HEAD"), cancellable = true)
    protected void dropFromLootTable(DamageSource source, boolean allowDrops, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!(source.getEntity() instanceof LivingEntity attacker)) return;

        ItemStack weapon = attacker.getMainHandItem();
        if (!(weapon.getItem() instanceof CleaverItem)) return;

        AbstractCarcassBlock carcassBlock = CarcassRegistry.getCarcassFor(entity.getType());
        if (carcassBlock == null) return;

        ItemStack carcassStack = new ItemStack(carcassBlock.asItem());
        CompoundTag carcassData = carcassBlock.extractAnimalData(entity);

        if (carcassData != null) {
            CompoundTag blockEntityTag = new CompoundTag();
            blockEntityTag.put("CarcassData", carcassData);
            carcassStack.getOrCreateTag().put("BlockEntityTag", blockEntityTag);
        }

        ProcessingStage initialStage = AbstractCarcassBlock.getInitialStage(entity);
        CompoundTag rootTag = carcassStack.getOrCreateTag();
        CompoundTag blockStateTag = new CompoundTag();
        blockStateTag.putString(AbstractCarcassBlock.STAGE.getName(), initialStage.getSerializedName());
        rootTag.put("BlockStateTag", blockStateTag);

        entity.spawnAtLocation(carcassStack);
        ci.cancel();
    }

}
