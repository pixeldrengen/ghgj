/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.PathEntity;
import net.minecraft.server.v1_6_R3.World;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;

public class CraftCreature
extends CraftLivingEntity
implements Creature {
    public CraftCreature(CraftServer server, EntityCreature entity) {
        super(server, entity);
    }

    @Override
    public void setTarget(LivingEntity target) {
        EntityCreature entity = this.getHandle();
        if (target == null) {
            entity.target = null;
        } else if (target instanceof CraftLivingEntity) {
            entity.target = ((CraftLivingEntity)target).getHandle();
            entity.pathEntity = entity.world.findPath(entity, entity.target, 16.0f, true, false, false, true);
        }
    }

    @Override
    public CraftLivingEntity getTarget() {
        if (this.getHandle().target == null) {
            return null;
        }
        if (!(this.getHandle().target instanceof EntityLiving)) {
            return null;
        }
        return (CraftLivingEntity)this.getHandle().target.getBukkitEntity();
    }

    @Override
    public EntityCreature getHandle() {
        return (EntityCreature)this.entity;
    }

    @Override
    public String toString() {
        return "CraftCreature";
    }
}

