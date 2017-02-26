/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityLiving;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ComplexLivingEntity;

public abstract class CraftComplexLivingEntity
extends CraftLivingEntity
implements ComplexLivingEntity {
    public CraftComplexLivingEntity(CraftServer server, EntityLiving entity) {
        super(server, entity);
    }

    @Override
    public EntityLiving getHandle() {
        return (EntityLiving)this.entity;
    }

    @Override
    public String toString() {
        return "CraftComplexLivingEntity";
    }
}

