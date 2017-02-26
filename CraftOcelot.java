/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityAgeable;
import net.minecraft.server.v1_6_R3.EntityAnimal;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityOcelot;
import net.minecraft.server.v1_6_R3.EntityTameableAnimal;
import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftTameableAnimal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;

public class CraftOcelot
extends CraftTameableAnimal
implements Ocelot {
    public CraftOcelot(CraftServer server, EntityOcelot wolf) {
        super(server, wolf);
    }

    @Override
    public EntityOcelot getHandle() {
        return (EntityOcelot)this.entity;
    }

    @Override
    public Ocelot.Type getCatType() {
        return Ocelot.Type.getType(this.getHandle().getCatType());
    }

    @Override
    public void setCatType(Ocelot.Type type) {
        Validate.notNull((Object)type, "Cat type cannot be null");
        this.getHandle().setCatType(type.getId());
    }

    @Override
    public EntityType getType() {
        return EntityType.OCELOT;
    }
}

