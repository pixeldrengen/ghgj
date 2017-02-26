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
import net.minecraft.server.v1_6_R3.PathEntity;
import net.minecraft.server.v1_6_R3.PathfinderGoalSit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAnimals;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

public class CraftTameableAnimal
extends CraftAnimals
implements Tameable,
Creature {
    public CraftTameableAnimal(CraftServer server, EntityTameableAnimal entity) {
        super(server, entity);
    }

    @Override
    public EntityTameableAnimal getHandle() {
        return (EntityTameableAnimal)super.getHandle();
    }

    @Override
    public AnimalTamer getOwner() {
        if ("".equals(this.getOwnerName())) {
            return null;
        }
        OfflinePlayer owner = this.getServer().getPlayerExact(this.getOwnerName());
        if (owner == null) {
            owner = this.getServer().getOfflinePlayer(this.getOwnerName());
        }
        return owner;
    }

    public String getOwnerName() {
        return this.getHandle().getOwnerName();
    }

    @Override
    public boolean isTamed() {
        return this.getHandle().isTamed();
    }

    @Override
    public void setOwner(AnimalTamer tamer) {
        if (tamer != null) {
            this.setTamed(true);
            this.getHandle().setPathEntity(null);
            this.setOwnerName(tamer.getName());
        } else {
            this.setTamed(false);
            this.setOwnerName("");
        }
    }

    public void setOwnerName(String ownerName) {
        this.getHandle().setOwnerName(ownerName == null ? "" : ownerName);
    }

    @Override
    public void setTamed(boolean tame) {
        this.getHandle().setTamed(tame);
        if (!tame) {
            this.setOwnerName("");
        }
    }

    public boolean isSitting() {
        return this.getHandle().isSitting();
    }

    public void setSitting(boolean sitting) {
        this.getHandle().getGoalSit().setSitting(sitting);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{owner=" + this.getOwner() + ",tamed=" + this.isTamed() + "}";
    }
}

