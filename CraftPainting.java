/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHanging;
import net.minecraft.server.v1_6_R3.EntityPainting;
import net.minecraft.server.v1_6_R3.EnumArt;
import net.minecraft.server.v1_6_R3.WorldServer;
import org.bukkit.Art;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_6_R3.CraftArt;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHanging;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Painting;

public class CraftPainting
extends CraftHanging
implements Painting {
    public CraftPainting(CraftServer server, EntityPainting entity) {
        super(server, entity);
    }

    @Override
    public Art getArt() {
        EnumArt art = this.getHandle().art;
        return CraftArt.NotchToBukkit(art);
    }

    @Override
    public boolean setArt(Art art) {
        return this.setArt(art, false);
    }

    @Override
    public boolean setArt(Art art, boolean force) {
        EntityPainting painting = this.getHandle();
        EnumArt oldArt = painting.art;
        painting.art = CraftArt.BukkitToNotch(art);
        painting.setDirection(painting.direction);
        if (!force && !painting.survives()) {
            painting.art = oldArt;
            painting.setDirection(painting.direction);
            return false;
        }
        this.update();
        return true;
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        if (super.setFacingDirection(face, force)) {
            this.update();
            return true;
        }
        return false;
    }

    private void update() {
        WorldServer world = ((CraftWorld)this.getWorld()).getHandle();
        EntityPainting painting = new EntityPainting(world);
        painting.x = this.getHandle().x;
        painting.y = this.getHandle().y;
        painting.z = this.getHandle().z;
        painting.art = this.getHandle().art;
        painting.setDirection(this.getHandle().direction);
        this.getHandle().die();
        this.getHandle().velocityChanged = true;
        world.addEntity(painting);
        this.entity = painting;
    }

    @Override
    public EntityPainting getHandle() {
        return (EntityPainting)this.entity;
    }

    @Override
    public String toString() {
        return "CraftPainting{art=" + (Object)((Object)this.getArt()) + "}";
    }

    @Override
    public EntityType getType() {
        return EntityType.PAINTING;
    }
}

