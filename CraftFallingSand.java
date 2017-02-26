/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityFallingBlock;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingSand;

public class CraftFallingSand
extends CraftEntity
implements FallingSand {
    public CraftFallingSand(CraftServer server, EntityFallingBlock entity) {
        super(server, entity);
    }

    @Override
    public EntityFallingBlock getHandle() {
        return (EntityFallingBlock)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFallingSand";
    }

    @Override
    public EntityType getType() {
        return EntityType.FALLING_BLOCK;
    }

    @Override
    public Material getMaterial() {
        return Material.getMaterial(this.getBlockId());
    }

    @Override
    public int getBlockId() {
        return this.getHandle().id;
    }

    @Override
    public byte getBlockData() {
        return (byte)this.getHandle().data;
    }

    @Override
    public boolean getDropItem() {
        return this.getHandle().dropItem;
    }

    @Override
    public void setDropItem(boolean drop) {
        this.getHandle().dropItem = drop;
    }
}

