/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHanging;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hanging;

public class CraftHanging
extends CraftEntity
implements Hanging {
    public CraftHanging(CraftServer server, EntityHanging entity) {
        super(server, entity);
    }

    @Override
    public BlockFace getAttachedFace() {
        return this.getFacing().getOppositeFace();
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        this.setFacingDirection(face, false);
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        Block block = this.getLocation().getBlock().getRelative(this.getAttachedFace()).getRelative(face.getOppositeFace()).getRelative(this.getFacing());
        EntityHanging hanging = this.getHandle();
        int x = hanging.x;
        int y = hanging.y;
        int z = hanging.z;
        int dir = hanging.direction;
        hanging.x = block.getX();
        hanging.y = block.getY();
        hanging.z = block.getZ();
        switch (face) {
            default: {
                this.getHandle().setDirection(0);
                break;
            }
            case WEST: {
                this.getHandle().setDirection(1);
                break;
            }
            case NORTH: {
                this.getHandle().setDirection(2);
                break;
            }
            case EAST: {
                this.getHandle().setDirection(3);
            }
        }
        if (!force && !hanging.survives()) {
            hanging.x = x;
            hanging.y = y;
            hanging.z = z;
            hanging.setDirection(dir);
            return false;
        }
        return true;
    }

    @Override
    public BlockFace getFacing() {
        switch (this.getHandle().direction) {
            default: {
                return BlockFace.SOUTH;
            }
            case 1: {
                return BlockFace.WEST;
            }
            case 2: {
                return BlockFace.NORTH;
            }
            case 3: 
        }
        return BlockFace.EAST;
    }

    @Override
    public EntityHanging getHandle() {
        return (EntityHanging)this.entity;
    }

    @Override
    public String toString() {
        return "CraftHanging";
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }

}

