/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.AttributeInstance;
import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityAgeable;
import net.minecraft.server.v1_6_R3.EntityAnimal;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityHorse;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.IAttribute;
import net.minecraft.server.v1_6_R3.IInventory;
import net.minecraft.server.v1_6_R3.InventoryHorseChest;
import net.minecraft.server.v1_6_R3.PathEntity;
import org.apache.commons.lang.Validate;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftInventoryHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.Inventory;

public class CraftHorse
extends CraftAnimals
implements Horse {
    public CraftHorse(CraftServer server, EntityHorse entity) {
        super(server, entity);
    }

    @Override
    public EntityHorse getHandle() {
        return (EntityHorse)this.entity;
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.values()[this.getHandle().getType()];
    }

    @Override
    public void setVariant(Horse.Variant variant) {
        Validate.notNull((Object)variant, "Variant cannot be null");
        this.getHandle().setType(variant.ordinal());
    }

    @Override
    public Horse.Color getColor() {
        return Horse.Color.values()[this.getHandle().getVariant() & 255];
    }

    @Override
    public void setColor(Horse.Color color) {
        Validate.notNull((Object)color, "Color cannot be null");
        this.getHandle().setVariant(color.ordinal() & 255 | this.getStyle().ordinal() << 8);
    }

    @Override
    public Horse.Style getStyle() {
        return Horse.Style.values()[this.getHandle().getVariant() >>> 8];
    }

    @Override
    public void setStyle(Horse.Style style) {
        Validate.notNull((Object)style, "Style cannot be null");
        this.getHandle().setVariant(this.getColor().ordinal() & 255 | style.ordinal() << 8);
    }

    @Override
    public boolean isCarryingChest() {
        return this.getHandle().hasChest();
    }

    @Override
    public void setCarryingChest(boolean chest) {
        if (chest == this.isCarryingChest()) {
            return;
        }
        this.getHandle().setHasChest(chest);
        this.getHandle().loadChest();
    }

    @Override
    public int getDomestication() {
        return this.getHandle().getTemper();
    }

    @Override
    public void setDomestication(int value) {
        Validate.isTrue(value >= 0, "Domestication cannot be less than zero");
        Validate.isTrue(value <= this.getMaxDomestication(), "Domestication cannot be greater than the max domestication");
        this.getHandle().setTemper(value);
    }

    @Override
    public int getMaxDomestication() {
        return this.getHandle().getMaxDomestication();
    }

    @Override
    public void setMaxDomestication(int value) {
        Validate.isTrue(value > 0, "Max domestication cannot be zero or less");
        this.getHandle().maxDomestication = value;
    }

    @Override
    public double getJumpStrength() {
        return this.getHandle().getJumpStrength();
    }

    @Override
    public void setJumpStrength(double strength) {
        Validate.isTrue(strength >= 0.0, "Jump strength cannot be less than zero");
        this.getHandle().getAttributeInstance(EntityHorse.attributeJumpStrength).setValue(strength);
    }

    @Override
    public boolean isTamed() {
        return this.getHandle().isTame();
    }

    @Override
    public void setTamed(boolean tamed) {
        this.getHandle().setTame(tamed);
    }

    @Override
    public AnimalTamer getOwner() {
        if (this.getOwnerName() == null || "".equals(this.getOwnerName())) {
            return null;
        }
        return this.getServer().getOfflinePlayer(this.getOwnerName());
    }

    @Override
    public void setOwner(AnimalTamer owner) {
        if (owner != null && !"".equals(owner.getName())) {
            this.setTamed(true);
            this.getHandle().setPathEntity(null);
            this.setOwnerName(owner.getName());
        } else {
            this.setTamed(false);
            this.setOwnerName("");
        }
    }

    public String getOwnerName() {
        return this.getHandle().getOwnerName();
    }

    public void setOwnerName(String name) {
        this.getHandle().setOwnerName(name);
    }

    @Override
    public HorseInventory getInventory() {
        return new CraftInventoryHorse(this.getHandle().inventoryChest);
    }

    @Override
    public String toString() {
        return "CraftHorse{variant=" + (Object)((Object)this.getVariant()) + ", owner=" + this.getOwner() + '}';
    }

    @Override
    public EntityType getType() {
        return EntityType.HORSE;
    }
}

