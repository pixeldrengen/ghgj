/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityComplexPart;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftComplexPart;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.util.NumberConversions;

public class CraftEnderDragonPart
extends CraftComplexPart
implements EnderDragonPart {
    public CraftEnderDragonPart(CraftServer server, EntityComplexPart entity) {
        super(server, entity);
    }

    @Override
    public EnderDragon getParent() {
        return (EnderDragon)super.getParent();
    }

    @Override
    public EntityComplexPart getHandle() {
        return (EntityComplexPart)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderDragonPart";
    }

    @Override
    public void damage(double amount) {
        this.getParent().damage(amount);
    }

    @Override
    public void damage(double amount, org.bukkit.entity.Entity source) {
        this.getParent().damage(amount, source);
    }

    @Override
    public double getHealth() {
        return this.getParent().getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.getParent().setHealth(health);
    }

    @Override
    public double getMaxHealth() {
        return this.getParent().getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        this.getParent().setMaxHealth(health);
    }

    @Override
    public void resetMaxHealth() {
        this.getParent().resetMaxHealth();
    }

    @Deprecated
    @Override
    public /* synthetic */ void damage(int amount) {
        this.damage((double)amount);
    }

    @Deprecated
    @Override
    public /* synthetic */ void damage(int amount, org.bukkit.entity.Entity source) {
        this.damage((double)amount, source);
    }

    @Deprecated
    @Override
    public /* synthetic */ int getHealth() {
        return NumberConversions.ceil(this.getHealth());
    }

    @Deprecated
    @Override
    public /* synthetic */ void setHealth(int health) {
        this.setHealth((double)health);
    }

    @Deprecated
    @Override
    public /* synthetic */ int getMaxHealth() {
        return NumberConversions.ceil(this.getMaxHealth());
    }

    @Deprecated
    @Override
    public /* synthetic */ void setMaxHealth(int health) {
        this.setMaxHealth((double)health);
    }
}

