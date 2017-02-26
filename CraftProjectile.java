/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityProjectile;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

public abstract class CraftProjectile
extends AbstractProjectile
implements Projectile {
    public CraftProjectile(CraftServer server, Entity entity) {
        super(server, entity);
    }

    @Override
    public LivingEntity getShooter() {
        if (this.getHandle().getShooter() != null) {
            return (LivingEntity)((Object)this.getHandle().getShooter().getBukkitEntity());
        }
        return null;
    }

    @Override
    public void setShooter(LivingEntity shooter) {
        if (shooter instanceof CraftLivingEntity) {
            this.getHandle().shooter = (EntityLiving)((CraftLivingEntity)shooter).entity;
            if (shooter instanceof CraftHumanEntity) {
                this.getHandle().shooterName = ((CraftHumanEntity)shooter).getName();
            }
        }
    }

    @Override
    public EntityProjectile getHandle() {
        return (EntityProjectile)this.entity;
    }

    @Override
    public String toString() {
        return "CraftProjectile";
    }
}

