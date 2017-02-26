/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import java.util.Collection;
import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityPotion;
import net.minecraft.server.v1_6_R3.EntityProjectile;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftProjectile;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

public class CraftThrownPotion
extends CraftProjectile
implements ThrownPotion {
    public CraftThrownPotion(CraftServer server, EntityPotion entity) {
        super(server, entity);
    }

    @Override
    public Collection<PotionEffect> getEffects() {
        return Potion.getBrewer().getEffectsFromDamage(this.getHandle().getPotionValue());
    }

    @Override
    public ItemStack getItem() {
        this.getHandle().getPotionValue();
        return CraftItemStack.asBukkitCopy(this.getHandle().item);
    }

    @Override
    public void setItem(ItemStack item) {
        Validate.notNull(item, "ItemStack cannot be null.");
        Validate.isTrue(item.getType() == Material.POTION, "ItemStack must be a potion. This item stack was " + (Object)((Object)item.getType()) + ".");
        this.getHandle().item = CraftItemStack.asNMSCopy(item);
    }

    @Override
    public EntityPotion getHandle() {
        return (EntityPotion)this.entity;
    }

    @Override
    public String toString() {
        return "CraftThrownPotion";
    }

    @Override
    public EntityType getType() {
        return EntityType.SPLASH_POTION;
    }
}

