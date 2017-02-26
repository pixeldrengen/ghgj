/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.DataWatcher;
import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHanging;
import net.minecraft.server.v1_6_R3.EntityItemFrame;
import org.apache.commons.lang.Validate;
import org.bukkit.Rotation;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHanging;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

public class CraftItemFrame
extends CraftHanging
implements ItemFrame {
    public CraftItemFrame(CraftServer server, EntityItemFrame entity) {
        super(server, entity);
    }

    @Override
    public void setItem(ItemStack item) {
        if (item == null || item.getTypeId() == 0) {
            this.getHandle().getDataWatcher().a(2, 5);
            this.getHandle().getDataWatcher().h(2);
        } else {
            this.getHandle().setItem(CraftItemStack.asNMSCopy(item));
        }
    }

    @Override
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(this.getHandle().getItem());
    }

    @Override
    public Rotation getRotation() {
        return this.toBukkitRotation(this.getHandle().getRotation());
    }

    Rotation toBukkitRotation(int value) {
        switch (value) {
            case 0: {
                return Rotation.NONE;
            }
            case 1: {
                return Rotation.CLOCKWISE;
            }
            case 2: {
                return Rotation.FLIPPED;
            }
            case 3: {
                return Rotation.COUNTER_CLOCKWISE;
            }
        }
        throw new AssertionError((Object)("Unknown rotation " + value + " for " + this.getHandle()));
    }

    @Override
    public void setRotation(Rotation rotation) {
        Validate.notNull((Object)rotation, "Rotation cannot be null");
        this.getHandle().setRotation(CraftItemFrame.toInteger(rotation));
    }

    static int toInteger(Rotation rotation) {
        switch (rotation) {
            case NONE: {
                return 0;
            }
            case CLOCKWISE: {
                return 1;
            }
            case FLIPPED: {
                return 2;
            }
            case COUNTER_CLOCKWISE: {
                return 3;
            }
        }
        throw new IllegalArgumentException((Object)((Object)rotation) + " is not applicable to an ItemFrame");
    }

    @Override
    public EntityItemFrame getHandle() {
        return (EntityItemFrame)this.entity;
    }

    @Override
    public String toString() {
        return "CraftItemFrame{item=" + this.getItem() + ", rotation=" + (Object)((Object)this.getRotation()) + "}";
    }

    @Override
    public EntityType getType() {
        return EntityType.ITEM_FRAME;
    }

}

