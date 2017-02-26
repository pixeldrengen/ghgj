/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.EntityMinecartAbstract;
import net.minecraft.server.v1_6_R3.EntityMinecartChest;
import net.minecraft.server.v1_6_R3.IInventory;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecart;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftInventory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.Inventory;

public class CraftMinecartChest
extends CraftMinecart
implements StorageMinecart {
    private final CraftInventory inventory;

    public CraftMinecartChest(CraftServer server, EntityMinecartChest entity) {
        super(server, entity);
        this.inventory = new CraftInventory(entity);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public String toString() {
        return "CraftMinecartChest{inventory=" + this.inventory + '}';
    }

    @Override
    public EntityType getType() {
        return EntityType.MINECART_CHEST;
    }
}

