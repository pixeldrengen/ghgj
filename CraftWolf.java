/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityAgeable;
import net.minecraft.server.v1_6_R3.EntityAnimal;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityTameableAnimal;
import net.minecraft.server.v1_6_R3.EntityWolf;
import org.bukkit.DyeColor;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftTameableAnimal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;

public class CraftWolf
extends CraftTameableAnimal
implements Wolf {
    public CraftWolf(CraftServer server, EntityWolf wolf) {
        super(server, wolf);
    }

    @Override
    public boolean isAngry() {
        return this.getHandle().isAngry();
    }

    @Override
    public void setAngry(boolean angry) {
        this.getHandle().setAngry(angry);
    }

    @Override
    public EntityWolf getHandle() {
        return (EntityWolf)this.entity;
    }

    @Override
    public EntityType getType() {
        return EntityType.WOLF;
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.getByWoolData((byte)this.getHandle().getCollarColor());
    }

    @Override
    public void setCollarColor(DyeColor color) {
        this.getHandle().setCollarColor(color.getWoolData());
    }
}

