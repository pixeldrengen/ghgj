/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityEnderman;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityMonster;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMonster;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

public class CraftEnderman
extends CraftMonster
implements Enderman {
    public CraftEnderman(CraftServer server, EntityEnderman entity) {
        super(server, entity);
    }

    @Override
    public MaterialData getCarriedMaterial() {
        return Material.getMaterial(this.getHandle().getCarriedId()).getNewData((byte)this.getHandle().getCarriedData());
    }

    @Override
    public void setCarriedMaterial(MaterialData data) {
        this.getHandle().setCarriedId(data.getItemTypeId());
        this.getHandle().setCarriedData(data.getData());
    }

    @Override
    public EntityEnderman getHandle() {
        return (EntityEnderman)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderman";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDERMAN;
    }
}

