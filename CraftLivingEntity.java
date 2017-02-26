/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import net.minecraft.server.v1_6_R3.AttributeInstance;
import net.minecraft.server.v1_6_R3.DamageSource;
import net.minecraft.server.v1_6_R3.EntityArrow;
import net.minecraft.server.v1_6_R3.EntityEgg;
import net.minecraft.server.v1_6_R3.EntityEnderDragon;
import net.minecraft.server.v1_6_R3.EntityEnderPearl;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.EntityInsentient;
import net.minecraft.server.v1_6_R3.EntityLargeFireball;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.EntityPotion;
import net.minecraft.server.v1_6_R3.EntitySmallFireball;
import net.minecraft.server.v1_6_R3.EntitySnowball;
import net.minecraft.server.v1_6_R3.EntityWither;
import net.minecraft.server.v1_6_R3.EntityWitherSkull;
import net.minecraft.server.v1_6_R3.GenericAttributes;
import net.minecraft.server.v1_6_R3.IAttribute;
import net.minecraft.server.v1_6_R3.MobEffect;
import net.minecraft.server.v1_6_R3.MobEffectList;
import net.minecraft.server.v1_6_R3.WorldServer;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftEntityEquipment;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

public class CraftLivingEntity
extends CraftEntity
implements LivingEntity {
    private CraftEntityEquipment equipment;

    public CraftLivingEntity(CraftServer server, EntityLiving entity) {
        super(server, entity);
        if (entity instanceof EntityInsentient) {
            this.equipment = new CraftEntityEquipment(this);
        }
    }

    @Override
    public double getHealth() {
        return Math.min((double)Math.max(0.0f, this.getHandle().getHealth()), this.getMaxHealth());
    }

    @Override
    public void setHealth(double health) {
        if (health < 0.0 || health > this.getMaxHealth()) {
            throw new IllegalArgumentException("Health must be between 0 and " + this.getMaxHealth());
        }
        if (this.entity instanceof EntityPlayer && health == 0.0) {
            ((EntityPlayer)this.entity).die(DamageSource.GENERIC);
        }
        this.getHandle().setHealth((float)health);
    }

    @Override
    public double getMaxHealth() {
        return this.getHandle().getMaxHealth();
    }

    @Override
    public void setMaxHealth(double amount) {
        Validate.isTrue(amount > 0.0, "Max health must be greater than 0");
        this.getHandle().getAttributeInstance(GenericAttributes.a).setValue(amount);
        if (this.getHealth() > amount) {
            this.setHealth(amount);
        }
    }

    @Override
    public void resetMaxHealth() {
        this.setMaxHealth(this.getHandle().getMaxHealth());
    }

    @Deprecated
    @Override
    public Egg throwEgg() {
        return this.launchProjectile(Egg.class);
    }

    @Deprecated
    @Override
    public Snowball throwSnowball() {
        return this.launchProjectile(Snowball.class);
    }

    @Override
    public double getEyeHeight() {
        return this.getHandle().getHeadHeight();
    }

    @Override
    public double getEyeHeight(boolean ignoreSneaking) {
        return this.getEyeHeight();
    }

    private List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance, int maxLength) {
        if (maxDistance > 120) {
            maxDistance = 120;
        }
        ArrayList<Block> blocks = new ArrayList<Block>();
        BlockIterator itr = new BlockIterator(this, maxDistance);
        while (itr.hasNext()) {
            Block block = (Block)itr.next();
            blocks.add(block);
            if (maxLength != 0 && blocks.size() > maxLength) {
                blocks.remove(0);
            }
            int id = block.getTypeId();
            if (!(transparent == null ? id != 0 : !transparent.contains(Byte.valueOf((byte)id)))) continue;
            break;
        }
        return blocks;
    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
        return this.getLineOfSight(transparent, maxDistance, 0);
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
        List<Block> blocks = this.getLineOfSight(transparent, maxDistance, 1);
        return blocks.get(0);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {
        return this.getLineOfSight(transparent, maxDistance, 2);
    }

    @Deprecated
    @Override
    public Arrow shootArrow() {
        return this.launchProjectile(Arrow.class);
    }

    @Override
    public int getRemainingAir() {
        return this.getHandle().getAirTicks();
    }

    @Override
    public void setRemainingAir(int ticks) {
        this.getHandle().setAirTicks(ticks);
    }

    @Override
    public int getMaximumAir() {
        return this.getHandle().maxAirTicks;
    }

    @Override
    public void setMaximumAir(int ticks) {
        this.getHandle().maxAirTicks = ticks;
    }

    @Override
    public void damage(double amount) {
        this.damage(amount, null);
    }

    @Override
    public void damage(double amount, Entity source) {
        DamageSource reason = DamageSource.GENERIC;
        if (source instanceof HumanEntity) {
            reason = DamageSource.playerAttack(((CraftHumanEntity)source).getHandle());
        } else if (source instanceof LivingEntity) {
            reason = DamageSource.mobAttack(((CraftLivingEntity)source).getHandle());
        }
        if (this.entity instanceof EntityEnderDragon) {
            ((EntityEnderDragon)this.entity).dealDamage(reason, (float)amount);
        } else {
            this.entity.damageEntity(reason, (float)amount);
        }
    }

    @Override
    public Location getEyeLocation() {
        Location loc = this.getLocation();
        loc.setY(loc.getY() + this.getEyeHeight());
        return loc;
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return this.getHandle().maxNoDamageTicks;
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        this.getHandle().maxNoDamageTicks = ticks;
    }

    @Override
    public double getLastDamage() {
        return this.getHandle().lastDamage;
    }

    @Override
    public void setLastDamage(double damage) {
        this.getHandle().lastDamage = (float)damage;
    }

    @Override
    public int getNoDamageTicks() {
        return this.getHandle().noDamageTicks;
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        this.getHandle().noDamageTicks = ticks;
    }

    @Override
    public EntityLiving getHandle() {
        return (EntityLiving)this.entity;
    }

    public void setHandle(EntityLiving entity) {
        super.setHandle(entity);
    }

    @Override
    public String toString() {
        return "CraftLivingEntity{id=" + this.getEntityId() + '}';
    }

    @Override
    public Player getKiller() {
        return this.getHandle().killer == null ? null : (Player)((Object)this.getHandle().killer.getBukkitEntity());
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return this.addPotionEffect(effect, false);
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        if (this.hasPotionEffect(effect.getType())) {
            if (!force) {
                return false;
            }
            this.removePotionEffect(effect.getType());
        }
        this.getHandle().addEffect(new MobEffect(effect.getType().getId(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient()));
        return true;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        boolean success = true;
        for (PotionEffect effect : effects) {
            success &= this.addPotionEffect(effect);
        }
        return success;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return this.getHandle().hasEffect(MobEffectList.byId[type.getId()]);
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        this.getHandle().k(type.getId());
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
        for (Object raw : this.getHandle().effects.values()) {
            if (!(raw instanceof MobEffect)) continue;
            MobEffect handle = (MobEffect)raw;
            effects.add(new PotionEffect(PotionEffectType.getById(handle.getEffectId()), handle.getDuration(), handle.getAmplifier(), handle.isAmbient()));
        }
        return effects;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        WorldServer world = ((CraftWorld)this.getWorld()).getHandle();
        net.minecraft.server.v1_6_R3.Entity launch = null;
        if (Snowball.class.isAssignableFrom(projectile)) {
            launch = new EntitySnowball(world, this.getHandle());
        } else if (Egg.class.isAssignableFrom(projectile)) {
            launch = new EntityEgg(world, this.getHandle());
        } else if (EnderPearl.class.isAssignableFrom(projectile)) {
            launch = new EntityEnderPearl(world, this.getHandle());
        } else if (Arrow.class.isAssignableFrom(projectile)) {
            launch = new EntityArrow(world, this.getHandle(), 1.0f);
        } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
            launch = new EntityPotion((net.minecraft.server.v1_6_R3.World)world, this.getHandle(), CraftItemStack.asNMSCopy(new ItemStack(Material.POTION, 1)));
        } else if (Fireball.class.isAssignableFrom(projectile)) {
            Location location = this.getEyeLocation();
            Vector direction = location.getDirection().multiply(10);
            launch = SmallFireball.class.isAssignableFrom(projectile) ? new EntitySmallFireball(world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ()) : (WitherSkull.class.isAssignableFrom(projectile) ? new EntityWitherSkull(world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ()) : new EntityLargeFireball(world, this.getHandle(), direction.getX(), direction.getY(), direction.getZ()));
            launch.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }
        Validate.notNull(launch, "Projectile not supported");
        world.addEntity(launch);
        return (T)((Projectile)((Object)launch.getBukkitEntity()));
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        return this.getHandle().o(((CraftEntity)other).getHandle());
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return this.getHandle() instanceof EntityInsentient && !((EntityInsentient)this.getHandle()).persistent;
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        if (this.getHandle() instanceof EntityInsentient) {
            ((EntityInsentient)this.getHandle()).persistent = !remove;
        }
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.equipment;
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        if (this.getHandle() instanceof EntityInsentient) {
            ((EntityInsentient)this.getHandle()).canPickUpLoot = pickup;
        }
    }

    @Override
    public boolean getCanPickupItems() {
        return this.getHandle() instanceof EntityInsentient && ((EntityInsentient)this.getHandle()).canPickUpLoot;
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        if (this.getHealth() == 0.0) {
            return false;
        }
        return super.teleport(location, cause);
    }

    @Override
    public void setCustomName(String name) {
        if (!(this.getHandle() instanceof EntityInsentient)) {
            return;
        }
        if (name == null) {
            name = "";
        }
        if (name.length() > 64) {
            name = name.substring(0, 64);
        }
        ((EntityInsentient)this.getHandle()).setCustomName(name);
    }

    @Override
    public String getCustomName() {
        if (!(this.getHandle() instanceof EntityInsentient)) {
            return null;
        }
        String name = ((EntityInsentient)this.getHandle()).getCustomName();
        if (name == null || name.length() == 0) {
            return null;
        }
        return name;
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        if (this.getHandle() instanceof EntityInsentient) {
            ((EntityInsentient)this.getHandle()).setCustomNameVisible(flag);
        }
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.getHandle() instanceof EntityInsentient && ((EntityInsentient)this.getHandle()).getCustomNameVisible();
    }

    @Override
    public boolean isLeashed() {
        if (!(this.getHandle() instanceof EntityInsentient)) {
            return false;
        }
        return ((EntityInsentient)this.getHandle()).getLeashHolder() != null;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        if (!this.isLeashed()) {
            throw new IllegalStateException("Entity not leashed");
        }
        return ((EntityInsentient)this.getHandle()).getLeashHolder().getBukkitEntity();
    }

    private boolean unleash() {
        if (!this.isLeashed()) {
            return false;
        }
        ((EntityInsentient)this.getHandle()).unleash(true, false);
        return true;
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        if (this.getHandle() instanceof EntityWither || !(this.getHandle() instanceof EntityInsentient)) {
            return false;
        }
        if (holder == null) {
            return this.unleash();
        }
        if (holder.isDead()) {
            return false;
        }
        this.unleash();
        ((EntityInsentient)this.getHandle()).setLeashHolder(((CraftEntity)holder).getHandle(), true);
        return true;
    }

    @Deprecated
    @Override
    public /* synthetic */ int getLastDamage() {
        return NumberConversions.ceil(this.getLastDamage());
    }

    @Deprecated
    @Override
    public /* synthetic */ void setLastDamage(int damage) {
        this.setLastDamage((double)damage);
    }

    @Deprecated
    @Override
    public /* synthetic */ void damage(int amount) {
        this.damage((double)amount);
    }

    @Deprecated
    @Override
    public /* synthetic */ void damage(int amount, Entity source) {
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

