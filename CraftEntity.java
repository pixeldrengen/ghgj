/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_6_R3.AxisAlignedBB;
import net.minecraft.server.v1_6_R3.EntityAmbient;
import net.minecraft.server.v1_6_R3.EntityAnimal;
import net.minecraft.server.v1_6_R3.EntityArrow;
import net.minecraft.server.v1_6_R3.EntityBat;
import net.minecraft.server.v1_6_R3.EntityBlaze;
import net.minecraft.server.v1_6_R3.EntityBoat;
import net.minecraft.server.v1_6_R3.EntityCaveSpider;
import net.minecraft.server.v1_6_R3.EntityChicken;
import net.minecraft.server.v1_6_R3.EntityComplexPart;
import net.minecraft.server.v1_6_R3.EntityCow;
import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityCreeper;
import net.minecraft.server.v1_6_R3.EntityEgg;
import net.minecraft.server.v1_6_R3.EntityEnderCrystal;
import net.minecraft.server.v1_6_R3.EntityEnderDragon;
import net.minecraft.server.v1_6_R3.EntityEnderPearl;
import net.minecraft.server.v1_6_R3.EntityEnderSignal;
import net.minecraft.server.v1_6_R3.EntityEnderman;
import net.minecraft.server.v1_6_R3.EntityExperienceOrb;
import net.minecraft.server.v1_6_R3.EntityFallingBlock;
import net.minecraft.server.v1_6_R3.EntityFireball;
import net.minecraft.server.v1_6_R3.EntityFireworks;
import net.minecraft.server.v1_6_R3.EntityFishingHook;
import net.minecraft.server.v1_6_R3.EntityFlying;
import net.minecraft.server.v1_6_R3.EntityGhast;
import net.minecraft.server.v1_6_R3.EntityGiantZombie;
import net.minecraft.server.v1_6_R3.EntityGolem;
import net.minecraft.server.v1_6_R3.EntityHanging;
import net.minecraft.server.v1_6_R3.EntityHorse;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.EntityIronGolem;
import net.minecraft.server.v1_6_R3.EntityItem;
import net.minecraft.server.v1_6_R3.EntityItemFrame;
import net.minecraft.server.v1_6_R3.EntityLargeFireball;
import net.minecraft.server.v1_6_R3.EntityLeash;
import net.minecraft.server.v1_6_R3.EntityLightning;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityMagmaCube;
import net.minecraft.server.v1_6_R3.EntityMinecartAbstract;
import net.minecraft.server.v1_6_R3.EntityMinecartChest;
import net.minecraft.server.v1_6_R3.EntityMinecartFurnace;
import net.minecraft.server.v1_6_R3.EntityMinecartHopper;
import net.minecraft.server.v1_6_R3.EntityMinecartMobSpawner;
import net.minecraft.server.v1_6_R3.EntityMinecartRideable;
import net.minecraft.server.v1_6_R3.EntityMinecartTNT;
import net.minecraft.server.v1_6_R3.EntityMonster;
import net.minecraft.server.v1_6_R3.EntityMushroomCow;
import net.minecraft.server.v1_6_R3.EntityOcelot;
import net.minecraft.server.v1_6_R3.EntityPainting;
import net.minecraft.server.v1_6_R3.EntityPig;
import net.minecraft.server.v1_6_R3.EntityPigZombie;
import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.EntityPotion;
import net.minecraft.server.v1_6_R3.EntityProjectile;
import net.minecraft.server.v1_6_R3.EntitySheep;
import net.minecraft.server.v1_6_R3.EntitySilverfish;
import net.minecraft.server.v1_6_R3.EntitySkeleton;
import net.minecraft.server.v1_6_R3.EntitySlime;
import net.minecraft.server.v1_6_R3.EntitySmallFireball;
import net.minecraft.server.v1_6_R3.EntitySnowball;
import net.minecraft.server.v1_6_R3.EntitySnowman;
import net.minecraft.server.v1_6_R3.EntitySpider;
import net.minecraft.server.v1_6_R3.EntitySquid;
import net.minecraft.server.v1_6_R3.EntityTNTPrimed;
import net.minecraft.server.v1_6_R3.EntityTameableAnimal;
import net.minecraft.server.v1_6_R3.EntityThrownExpBottle;
import net.minecraft.server.v1_6_R3.EntityVillager;
import net.minecraft.server.v1_6_R3.EntityWaterAnimal;
import net.minecraft.server.v1_6_R3.EntityWeather;
import net.minecraft.server.v1_6_R3.EntityWitch;
import net.minecraft.server.v1_6_R3.EntityWither;
import net.minecraft.server.v1_6_R3.EntityWitherSkull;
import net.minecraft.server.v1_6_R3.EntityWolf;
import net.minecraft.server.v1_6_R3.EntityZombie;
import net.minecraft.server.v1_6_R3.IComplex;
import net.minecraft.server.v1_6_R3.WorldServer;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAmbient;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftBat;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftBlaze;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftBoat;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftCaveSpider;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftChicken;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftComplexPart;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftCow;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEgg;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEnderCrystal;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEnderDragonPart;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEnderPearl;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEnderSignal;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEnderman;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftExperienceOrb;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftFallingSand;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftFireball;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftFirework;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftFish;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftFlying;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftGhast;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftGiant;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHanging;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHorse;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftIronGolem;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftItemFrame;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLargeFireball;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLeash;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLightningStrike;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMagmaCube;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecartChest;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecartFurnace;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecartHopper;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecartMobSpawner;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecartRideable;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMinecartTNT;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMonster;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftMushroomCow;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftOcelot;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPainting;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPig;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPigZombie;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSheep;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSilverfish;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSlime;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSmallFireball;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSnowball;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSnowman;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSpider;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSquid;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftTNTPrimed;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftThrownExpBottle;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftThrownPotion;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftWaterMob;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftWeather;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftWitch;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftWither;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftWitherSkull;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftWolf;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_6_R3.metadata.EntityMetadataStore;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public abstract class CraftEntity
implements Entity {
    protected final CraftServer server;
    protected net.minecraft.server.v1_6_R3.Entity entity;
    private EntityDamageEvent lastDamageEvent;

    public CraftEntity(CraftServer server, net.minecraft.server.v1_6_R3.Entity entity) {
        this.server = server;
        this.entity = entity;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static CraftEntity getEntity(CraftServer server, net.minecraft.server.v1_6_R3.Entity entity) {
        if (entity instanceof EntityLiving) {
            if (entity instanceof EntityHuman) {
                if (!(entity instanceof EntityPlayer)) return new CraftHumanEntity(server, (EntityHuman)entity);
                return new CraftPlayer(server, (EntityPlayer)entity);
            }
            if (entity instanceof EntityCreature) {
                if (entity instanceof EntityAnimal) {
                    if (entity instanceof EntityChicken) {
                        return new CraftChicken(server, (EntityChicken)entity);
                    }
                    if (entity instanceof EntityCow) {
                        if (!(entity instanceof EntityMushroomCow)) return new CraftCow(server, (EntityCow)entity);
                        return new CraftMushroomCow(server, (EntityMushroomCow)entity);
                    }
                    if (entity instanceof EntityPig) {
                        return new CraftPig(server, (EntityPig)entity);
                    }
                    if (entity instanceof EntityTameableAnimal) {
                        if (entity instanceof EntityWolf) {
                            return new CraftWolf(server, (EntityWolf)entity);
                        }
                        if (!(entity instanceof EntityOcelot)) throw new AssertionError("Unknown entity " + entity == null ? null : entity.getClass());
                        return new CraftOcelot(server, (EntityOcelot)entity);
                    }
                    if (entity instanceof EntitySheep) {
                        return new CraftSheep(server, (EntitySheep)entity);
                    }
                    if (!(entity instanceof EntityHorse)) return new CraftAnimals(server, (EntityAnimal)entity);
                    return new CraftHorse(server, (EntityHorse)entity);
                }
                if (entity instanceof EntityMonster) {
                    if (entity instanceof EntityZombie) {
                        if (!(entity instanceof EntityPigZombie)) return new CraftZombie(server, (EntityZombie)entity);
                        return new CraftPigZombie(server, (EntityPigZombie)entity);
                    }
                    if (entity instanceof EntityCreeper) {
                        return new CraftCreeper(server, (EntityCreeper)entity);
                    }
                    if (entity instanceof EntityEnderman) {
                        return new CraftEnderman(server, (EntityEnderman)entity);
                    }
                    if (entity instanceof EntitySilverfish) {
                        return new CraftSilverfish(server, (EntitySilverfish)entity);
                    }
                    if (entity instanceof EntityGiantZombie) {
                        return new CraftGiant(server, (EntityGiantZombie)entity);
                    }
                    if (entity instanceof EntitySkeleton) {
                        return new CraftSkeleton(server, (EntitySkeleton)entity);
                    }
                    if (entity instanceof EntityBlaze) {
                        return new CraftBlaze(server, (EntityBlaze)entity);
                    }
                    if (entity instanceof EntityWitch) {
                        return new CraftWitch(server, (EntityWitch)entity);
                    }
                    if (entity instanceof EntityWither) {
                        return new CraftWither(server, (EntityWither)entity);
                    }
                    if (!(entity instanceof EntitySpider)) return new CraftMonster(server, (EntityMonster)entity);
                    if (!(entity instanceof EntityCaveSpider)) return new CraftSpider(server, (EntitySpider)entity);
                    return new CraftCaveSpider(server, (EntityCaveSpider)entity);
                }
                if (entity instanceof EntityWaterAnimal) {
                    if (!(entity instanceof EntitySquid)) return new CraftWaterMob(server, (EntityWaterAnimal)entity);
                    return new CraftSquid(server, (EntitySquid)entity);
                }
                if (entity instanceof EntityGolem) {
                    if (entity instanceof EntitySnowman) {
                        return new CraftSnowman(server, (EntitySnowman)entity);
                    }
                    if (!(entity instanceof EntityIronGolem)) throw new AssertionError("Unknown entity " + entity == null ? null : entity.getClass());
                    return new CraftIronGolem(server, (EntityIronGolem)entity);
                }
                if (!(entity instanceof EntityVillager)) return new CraftCreature(server, (EntityCreature)entity);
                return new CraftVillager(server, (EntityVillager)entity);
            }
            if (entity instanceof EntitySlime) {
                if (!(entity instanceof EntityMagmaCube)) return new CraftSlime(server, (EntitySlime)entity);
                return new CraftMagmaCube(server, (EntityMagmaCube)entity);
            }
            if (entity instanceof EntityFlying) {
                if (!(entity instanceof EntityGhast)) return new CraftFlying(server, (EntityFlying)entity);
                return new CraftGhast(server, (EntityGhast)entity);
            }
            if (entity instanceof EntityEnderDragon) {
                return new CraftEnderDragon(server, (EntityEnderDragon)entity);
            }
            if (!(entity instanceof EntityAmbient)) return new CraftLivingEntity(server, (EntityLiving)entity);
            if (!(entity instanceof EntityBat)) return new CraftAmbient(server, (EntityAmbient)entity);
            return new CraftBat(server, (EntityBat)entity);
        }
        if (entity instanceof EntityComplexPart) {
            EntityComplexPart part = (EntityComplexPart)entity;
            if (!(part.owner instanceof EntityEnderDragon)) return new CraftComplexPart(server, (EntityComplexPart)entity);
            return new CraftEnderDragonPart(server, (EntityComplexPart)entity);
        }
        if (entity instanceof EntityExperienceOrb) {
            return new CraftExperienceOrb(server, (EntityExperienceOrb)entity);
        }
        if (entity instanceof EntityArrow) {
            return new CraftArrow(server, (EntityArrow)entity);
        }
        if (entity instanceof EntityBoat) {
            return new CraftBoat(server, (EntityBoat)entity);
        }
        if (entity instanceof EntityProjectile) {
            if (entity instanceof EntityEgg) {
                return new CraftEgg(server, (EntityEgg)entity);
            }
            if (entity instanceof EntitySnowball) {
                return new CraftSnowball(server, (EntitySnowball)entity);
            }
            if (entity instanceof EntityPotion) {
                return new CraftThrownPotion(server, (EntityPotion)entity);
            }
            if (entity instanceof EntityEnderPearl) {
                return new CraftEnderPearl(server, (EntityEnderPearl)entity);
            }
            if (!(entity instanceof EntityThrownExpBottle)) throw new AssertionError("Unknown entity " + entity == null ? null : entity.getClass());
            return new CraftThrownExpBottle(server, (EntityThrownExpBottle)entity);
        }
        if (entity instanceof EntityFallingBlock) {
            return new CraftFallingSand(server, (EntityFallingBlock)entity);
        }
        if (entity instanceof EntityFireball) {
            if (entity instanceof EntitySmallFireball) {
                return new CraftSmallFireball(server, (EntitySmallFireball)entity);
            }
            if (entity instanceof EntityLargeFireball) {
                return new CraftLargeFireball(server, (EntityLargeFireball)entity);
            }
            if (!(entity instanceof EntityWitherSkull)) return new CraftFireball(server, (EntityFireball)entity);
            return new CraftWitherSkull(server, (EntityWitherSkull)entity);
        }
        if (entity instanceof EntityEnderSignal) {
            return new CraftEnderSignal(server, (EntityEnderSignal)entity);
        }
        if (entity instanceof EntityEnderCrystal) {
            return new CraftEnderCrystal(server, (EntityEnderCrystal)entity);
        }
        if (entity instanceof EntityFishingHook) {
            return new CraftFish(server, (EntityFishingHook)entity);
        }
        if (entity instanceof EntityItem) {
            return new CraftItem(server, (EntityItem)entity);
        }
        if (entity instanceof EntityWeather) {
            if (!(entity instanceof EntityLightning)) return new CraftWeather(server, (EntityWeather)entity);
            return new CraftLightningStrike(server, (EntityLightning)entity);
        }
        if (entity instanceof EntityMinecartAbstract) {
            if (entity instanceof EntityMinecartFurnace) {
                return new CraftMinecartFurnace(server, (EntityMinecartFurnace)entity);
            }
            if (entity instanceof EntityMinecartChest) {
                return new CraftMinecartChest(server, (EntityMinecartChest)entity);
            }
            if (entity instanceof EntityMinecartTNT) {
                return new CraftMinecartTNT(server, (EntityMinecartTNT)entity);
            }
            if (entity instanceof EntityMinecartHopper) {
                return new CraftMinecartHopper(server, (EntityMinecartHopper)entity);
            }
            if (entity instanceof EntityMinecartMobSpawner) {
                return new CraftMinecartMobSpawner(server, (EntityMinecartMobSpawner)entity);
            }
            if (!(entity instanceof EntityMinecartRideable)) throw new AssertionError("Unknown entity " + entity == null ? null : entity.getClass());
            return new CraftMinecartRideable(server, (EntityMinecartRideable)entity);
        }
        if (entity instanceof EntityHanging) {
            if (entity instanceof EntityPainting) {
                return new CraftPainting(server, (EntityPainting)entity);
            }
            if (entity instanceof EntityItemFrame) {
                return new CraftItemFrame(server, (EntityItemFrame)entity);
            }
            if (!(entity instanceof EntityLeash)) return new CraftHanging(server, (EntityHanging)entity);
            return new CraftLeash(server, (EntityLeash)entity);
        }
        if (entity instanceof EntityTNTPrimed) {
            return new CraftTNTPrimed(server, (EntityTNTPrimed)entity);
        }
        if (!(entity instanceof EntityFireworks)) throw new AssertionError("Unknown entity " + entity == null ? null : entity.getClass());
        return new CraftFirework(server, (EntityFireworks)entity);
    }

    @Override
    public Location getLocation() {
        return new Location(this.getWorld(), this.entity.locX, this.entity.locY, this.entity.locZ, this.entity.yaw, this.entity.pitch);
    }

    @Override
    public Location getLocation(Location loc) {
        if (loc != null) {
            loc.setWorld(this.getWorld());
            loc.setX(this.entity.locX);
            loc.setY(this.entity.locY);
            loc.setZ(this.entity.locZ);
            loc.setYaw(this.entity.yaw);
            loc.setPitch(this.entity.pitch);
        }
        return loc;
    }

    @Override
    public Vector getVelocity() {
        return new Vector(this.entity.motX, this.entity.motY, this.entity.motZ);
    }

    @Override
    public void setVelocity(Vector vel) {
        this.entity.motX = vel.getX();
        this.entity.motY = vel.getY();
        this.entity.motZ = vel.getZ();
        this.entity.velocityChanged = true;
    }

    @Override
    public boolean isOnGround() {
        if (this.entity instanceof EntityArrow) {
            return ((EntityArrow)this.entity).isInGround();
        }
        return this.entity.onGround;
    }

    @Override
    public World getWorld() {
        return this.entity.world.getWorld();
    }

    @Override
    public boolean teleport(Location location) {
        return this.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        if (this.entity.vehicle != null || this.entity.passenger != null || this.entity.dead) {
            return false;
        }
        this.entity.world = ((CraftWorld)location.getWorld()).getHandle();
        this.entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        return true;
    }

    @Override
    public boolean teleport(Entity destination) {
        return this.teleport(destination.getLocation());
    }

    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        return this.teleport(destination.getLocation(), cause);
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        List notchEntityList = this.entity.world.getEntities(this.entity, this.entity.boundingBox.grow(x, y, z));
        ArrayList<Entity> bukkitEntityList = new ArrayList<Entity>(notchEntityList.size());
        for (net.minecraft.server.v1_6_R3.Entity e : notchEntityList) {
            bukkitEntityList.add(e.getBukkitEntity());
        }
        return bukkitEntityList;
    }

    @Override
    public int getEntityId() {
        return this.entity.id;
    }

    @Override
    public int getFireTicks() {
        return this.entity.fireTicks;
    }

    @Override
    public int getMaxFireTicks() {
        return this.entity.maxFireTicks;
    }

    @Override
    public void setFireTicks(int ticks) {
        this.entity.fireTicks = ticks;
    }

    @Override
    public void remove() {
        this.entity.dead = true;
    }

    @Override
    public boolean isDead() {
        return !this.entity.isAlive();
    }

    @Override
    public boolean isValid() {
        return this.entity.isAlive() && this.entity.valid;
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    public Vector getMomentum() {
        return this.getVelocity();
    }

    public void setMomentum(Vector value) {
        this.setVelocity(value);
    }

    @Override
    public Entity getPassenger() {
        return this.isEmpty() ? null : this.getHandle().passenger.getBukkitEntity();
    }

    @Override
    public boolean setPassenger(Entity passenger) {
        if (passenger instanceof CraftEntity) {
            ((CraftEntity)passenger).getHandle().setPassengerOf(this.getHandle());
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.getHandle().passenger == null;
    }

    @Override
    public boolean eject() {
        if (this.getHandle().passenger == null) {
            return false;
        }
        this.getHandle().passenger.setPassengerOf(null);
        return true;
    }

    @Override
    public float getFallDistance() {
        return this.getHandle().fallDistance;
    }

    @Override
    public void setFallDistance(float distance) {
        this.getHandle().fallDistance = distance;
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        this.lastDamageEvent = event;
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return this.lastDamageEvent;
    }

    @Override
    public UUID getUniqueId() {
        return this.getHandle().uniqueID;
    }

    @Override
    public int getTicksLived() {
        return this.getHandle().ticksLived;
    }

    @Override
    public void setTicksLived(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Age must be at least 1 tick");
        }
        this.getHandle().ticksLived = value;
    }

    public net.minecraft.server.v1_6_R3.Entity getHandle() {
        return this.entity;
    }

    @Override
    public void playEffect(EntityEffect type) {
        this.getHandle().world.broadcastEntityEffect(this.getHandle(), type.getData());
    }

    public void setHandle(net.minecraft.server.v1_6_R3.Entity entity) {
        this.entity = entity;
    }

    public String toString() {
        return "CraftEntity{id=" + this.getEntityId() + '}';
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CraftEntity other = (CraftEntity)obj;
        return this.getEntityId() == other.getEntityId();
    }

    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.getEntityId();
        return hash;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.server.getEntityMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.server.getEntityMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.server.getEntityMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.server.getEntityMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean isInsideVehicle() {
        return this.getHandle().vehicle != null;
    }

    @Override
    public boolean leaveVehicle() {
        if (this.getHandle().vehicle == null) {
            return false;
        }
        this.getHandle().setPassengerOf(null);
        return true;
    }

    @Override
    public Entity getVehicle() {
        if (this.getHandle().vehicle == null) {
            return null;
        }
        return this.getHandle().vehicle.getBukkitEntity();
    }
}

