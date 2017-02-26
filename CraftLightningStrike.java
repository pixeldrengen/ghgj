/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityLightning;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;

public class CraftLightningStrike
extends CraftEntity
implements LightningStrike {
    public CraftLightningStrike(CraftServer server, EntityLightning entity) {
        super(server, entity);
    }

    @Override
    public boolean isEffect() {
        return ((EntityLightning)super.getHandle()).isEffect;
    }

    @Override
    public EntityLightning getHandle() {
        return (EntityLightning)this.entity;
    }

    @Override
    public String toString() {
        return "CraftLightningStrike";
    }

    @Override
    public EntityType getType() {
        return EntityType.LIGHTNING;
    }
}

