/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityMonster;
import net.minecraft.server.v1_6_R3.EntityZombie;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMonster;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class CraftZombie
extends CraftMonster
implements Zombie {
    public CraftZombie(CraftServer server, EntityZombie entity) {
        super(server, entity);
    }

    @Override
    public EntityZombie getHandle() {
        return (EntityZombie)this.entity;
    }

    @Override
    public String toString() {
        return "CraftZombie";
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public boolean isBaby() {
        return this.getHandle().isBaby();
    }

    @Override
    public void setBaby(boolean flag) {
        this.getHandle().setBaby(flag);
    }

    @Override
    public boolean isVillager() {
        return this.getHandle().isVillager();
    }

    @Override
    public void setVillager(boolean flag) {
        this.getHandle().setVillager(flag);
    }
}

