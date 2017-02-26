/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityAmbient;
import net.minecraft.server.v1_6_R3.EntityBat;
import net.minecraft.server.v1_6_R3.EntityLiving;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAmbient;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;

public class CraftBat
extends CraftAmbient
implements Bat {
    public CraftBat(CraftServer server, EntityBat entity) {
        super(server, entity);
    }

    @Override
    public EntityBat getHandle() {
        return (EntityBat)this.entity;
    }

    @Override
    public String toString() {
        return "CraftBat";
    }

    @Override
    public EntityType getType() {
        return EntityType.BAT;
    }
}

