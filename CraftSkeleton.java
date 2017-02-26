/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityMonster;
import net.minecraft.server.v1_6_R3.EntitySkeleton;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMonster;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;

public class CraftSkeleton
extends CraftMonster
implements Skeleton {
    public CraftSkeleton(CraftServer server, EntitySkeleton entity) {
        super(server, entity);
    }

    @Override
    public EntitySkeleton getHandle() {
        return (EntitySkeleton)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSkeleton";
    }

    @Override
    public EntityType getType() {
        return EntityType.SKELETON;
    }

    @Override
    public Skeleton.SkeletonType getSkeletonType() {
        return Skeleton.SkeletonType.getType(this.getHandle().getSkeletonType());
    }

    @Override
    public void setSkeletonType(Skeleton.SkeletonType type) {
        Validate.notNull((Object)type);
        this.getHandle().setSkeletonType(type.getId());
    }
}

