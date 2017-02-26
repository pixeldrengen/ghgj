/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityFishingHook;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.MathHelper;
import net.minecraft.server.v1_6_R3.World;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHumanEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.LivingEntity;

public class CraftFish
extends AbstractProjectile
implements Fish {
    private double biteChance = -1.0;

    public CraftFish(CraftServer server, EntityFishingHook entity) {
        super(server, entity);
    }

    @Override
    public LivingEntity getShooter() {
        if (this.getHandle().owner != null) {
            return this.getHandle().owner.getBukkitEntity();
        }
        return null;
    }

    @Override
    public void setShooter(LivingEntity shooter) {
        if (shooter instanceof CraftHumanEntity) {
            this.getHandle().owner = (EntityHuman)((CraftHumanEntity)shooter).entity;
        }
    }

    @Override
    public EntityFishingHook getHandle() {
        return (EntityFishingHook)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFish";
    }

    @Override
    public EntityType getType() {
        return EntityType.FISHING_HOOK;
    }

    @Override
    public double getBiteChance() {
        EntityFishingHook hook = this.getHandle();
        if (this.biteChance == -1.0) {
            if (hook.world.isRainingAt(MathHelper.floor(hook.locX), MathHelper.floor(hook.locY) + 1, MathHelper.floor(hook.locZ))) {
                return 0.0033333333333333335;
            }
            return 0.002;
        }
        return this.biteChance;
    }

    @Override
    public void setBiteChance(double chance) {
        Validate.isTrue(chance >= 0.0 && chance <= 1.0, "The bite chance must be between 0 and 1.");
        this.biteChance = chance;
    }
}

