package net.takerudavis.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.takerudavis.block.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CarcassBlockEntity extends BlockEntity {

    public CarcassBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.CARCASS_BLOCK_ENTITY, blockPos, blockState);
    }

    private CompoundTag carcassData = new CompoundTag();

    public CompoundTag getCarcassData() {return carcassData;}

    public void setCarcassData(CompoundTag carcassData) {
        this.carcassData = carcassData;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("CarcassData", carcassData);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("CarcassData")) {
            this.carcassData = tag.getCompound("CarcassData");
        }
    }
}
