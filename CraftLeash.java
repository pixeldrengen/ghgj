/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHanging;
import net.minecraft.server.v1_6_R3.EntityLeash;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHanging;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LeashHitch;

public class CraftLeash
extends CraftHanging
implements LeashHitch {
    public CraftLeash(CraftServer server, EntityLeash entity) {
        super(server, entity);
    }

    @Override
    public EntityLeash getHandle() {
        return (EntityLeash)this.entity;
    }

    @Override
    public String toString() {
        return "CraftLeash";
    }

    @Override
    public EntityType getType() {
        return EntityType.LEASH_HITCH;
    }
}

