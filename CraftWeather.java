/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityWeather;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Weather;

public class CraftWeather
extends CraftEntity
implements Weather {
    public CraftWeather(CraftServer server, EntityWeather entity) {
        super(server, entity);
    }

    @Override
    public EntityWeather getHandle() {
        return (EntityWeather)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWeather";
    }

    @Override
    public EntityType getType() {
        return EntityType.WEATHER;
    }
}

