/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityFireball;
import net.minecraft.server.v1_6_R3.EntityWitherSkull;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftFireball;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;

public class CraftWitherSkull
extends CraftFireball
implements WitherSkull {
    public CraftWitherSkull(CraftServer server, EntityWitherSkull entity) {
        super(server, entity);
    }

    @Override
    public EntityWitherSkull getHandle() {
        return (EntityWitherSkull)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWitherSkull";
    }

    @Override
    public EntityType getType() {
        return EntityType.WITHER_SKULL;
    }
}

