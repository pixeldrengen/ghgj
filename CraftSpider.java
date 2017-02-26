/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityMonster;
import net.minecraft.server.v1_6_R3.EntitySpider;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMonster;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;

public class CraftSpider
extends CraftMonster
implements Spider {
    public CraftSpider(CraftServer server, EntitySpider entity) {
        super(server, entity);
    }

    @Override
    public EntitySpider getHandle() {
        return (EntitySpider)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSpider";
    }

    @Override
    public EntityType getType() {
        return EntityType.SPIDER;
    }
}

