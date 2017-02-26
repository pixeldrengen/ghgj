/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityProjectile;
import net.minecraft.server.v1_6_R3.EntityThrownExpBottle;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftProjectile;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownExpBottle;

public class CraftThrownExpBottle
extends CraftProjectile
implements ThrownExpBottle {
    public CraftThrownExpBottle(CraftServer server, EntityThrownExpBottle entity) {
        super(server, entity);
    }

    @Override
    public EntityThrownExpBottle getHandle() {
        return (EntityThrownExpBottle)this.entity;
    }

    @Override
    public String toString() {
        return "EntityThrownExpBottle";
    }

    @Override
    public EntityType getType() {
        return EntityType.THROWN_EXP_BOTTLE;
    }
}

