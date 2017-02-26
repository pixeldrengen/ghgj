/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityAgeable;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityVillager;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAgeable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class CraftVillager
extends CraftAgeable
implements Villager {
    public CraftVillager(CraftServer server, EntityVillager entity) {
        super(server, entity);
    }

    @Override
    public EntityVillager getHandle() {
        return (EntityVillager)this.entity;
    }

    @Override
    public String toString() {
        return "CraftVillager";
    }

    @Override
    public EntityType getType() {
        return EntityType.VILLAGER;
    }

    @Override
    public Villager.Profession getProfession() {
        return Villager.Profession.getProfession(this.getHandle().getProfession());
    }

    @Override
    public void setProfession(Villager.Profession profession) {
        Validate.notNull((Object)profession);
        this.getHandle().setProfession(profession.getId());
    }
}

