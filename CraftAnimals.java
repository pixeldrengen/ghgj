/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityAgeable;
import net.minecraft.server.v1_6_R3.EntityAnimal;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAgeable;
import org.bukkit.entity.Animals;

public class CraftAnimals
extends CraftAgeable
implements Animals {
    public CraftAnimals(CraftServer server, EntityAnimal entity) {
        super(server, entity);
    }

    @Override
    public EntityAnimal getHandle() {
        return (EntityAnimal)this.entity;
    }

    @Override
    public String toString() {
        return "CraftAnimals";
    }
}

