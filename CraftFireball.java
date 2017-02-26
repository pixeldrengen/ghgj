/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityFireball;
import net.minecraft.server.v1_6_R3.EntityLiving;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class CraftFireball
extends AbstractProjectile
implements Fireball {
    public CraftFireball(CraftServer server, EntityFireball entity) {
        super(server, entity);
    }

    @Override
    public float getYield() {
        return this.getHandle().bukkitYield;
    }

    @Override
    public boolean isIncendiary() {
        return this.getHandle().isIncendiary;
    }

    @Override
    public void setIsIncendiary(boolean isIncendiary) {
        this.getHandle().isIncendiary = isIncendiary;
    }

    @Override
    public void setYield(float yield) {
        this.getHandle().bukkitYield = yield;
    }

    @Override
    public LivingEntity getShooter() {
        if (this.getHandle().shooter != null) {
            return (LivingEntity)((Object)this.getHandle().shooter.getBukkitEntity());
        }
        return null;
    }

    @Override
    public void setShooter(LivingEntity shooter) {
        if (shooter instanceof CraftLivingEntity) {
            this.getHandle().shooter = (EntityLiving)((CraftLivingEntity)shooter).entity;
        }
    }

    @Override
    public Vector getDirection() {
        return new Vector(this.getHandle().dirX, this.getHandle().dirY, this.getHandle().dirZ);
    }

    @Override
    public void setDirection(Vector direction) {
        this.getHandle().setDirection(direction.getX(), direction.getY(), direction.getZ());
    }

    @Override
    public EntityFireball getHandle() {
        return (EntityFireball)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFireball";
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }
}

