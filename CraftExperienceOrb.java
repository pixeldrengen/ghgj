/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityExperienceOrb;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;

public class CraftExperienceOrb
extends CraftEntity
implements ExperienceOrb {
    public CraftExperienceOrb(CraftServer server, EntityExperienceOrb entity) {
        super(server, entity);
    }

    @Override
    public int getExperience() {
        return this.getHandle().value;
    }

    @Override
    public void setExperience(int value) {
        this.getHandle().value = value;
    }

    @Override
    public EntityExperienceOrb getHandle() {
        return (EntityExperienceOrb)this.entity;
    }

    @Override
    public String toString() {
        return "CraftExperienceOrb";
    }

    @Override
    public EntityType getType() {
        return EntityType.EXPERIENCE_ORB;
    }
}

