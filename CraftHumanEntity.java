/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import java.util.Set;
import net.minecraft.server.v1_6_R3.Container;
import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityMinecartHopper;
import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.ICrafting;
import net.minecraft.server.v1_6_R3.IInventory;
import net.minecraft.server.v1_6_R3.InventoryEnderChest;
import net.minecraft.server.v1_6_R3.Packet;
import net.minecraft.server.v1_6_R3.Packet100OpenWindow;
import net.minecraft.server.v1_6_R3.Packet101CloseWindow;
import net.minecraft.server.v1_6_R3.PlayerConnection;
import net.minecraft.server.v1_6_R3.PlayerInventory;
import net.minecraft.server.v1_6_R3.TileEntityBrewingStand;
import net.minecraft.server.v1_6_R3.TileEntityDispenser;
import net.minecraft.server.v1_6_R3.TileEntityFurnace;
import net.minecraft.server.v1_6_R3.TileEntityHopper;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_6_R3.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftContainer;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.Plugin;

public class CraftHumanEntity
extends CraftLivingEntity
implements HumanEntity {
    private CraftInventoryPlayer inventory;
    private final CraftInventory enderChest;
    protected final PermissibleBase perm;
    private boolean op;
    private GameMode mode;

    public CraftHumanEntity(CraftServer server, EntityHuman entity) {
        super(server, entity);
        this.perm = new PermissibleBase(this);
        this.mode = server.getDefaultGameMode();
        this.inventory = new CraftInventoryPlayer(entity.inventory);
        this.enderChest = new CraftInventory(entity.getEnderChest());
    }

    @Override
    public String getName() {
        return this.getHandle().getName();
    }

    @Override
    public org.bukkit.inventory.PlayerInventory getInventory() {
        return this.inventory;
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.inventory;
    }

    @Override
    public Inventory getEnderChest() {
        return this.enderChest;
    }

    @Override
    public ItemStack getItemInHand() {
        return this.getInventory().getItemInHand();
    }

    @Override
    public void setItemInHand(ItemStack item) {
        this.getInventory().setItemInHand(item);
    }

    @Override
    public ItemStack getItemOnCursor() {
        return CraftItemStack.asCraftMirror(this.getHandle().inventory.getCarried());
    }

    @Override
    public void setItemOnCursor(ItemStack item) {
        net.minecraft.server.v1_6_R3.ItemStack stack = CraftItemStack.asNMSCopy(item);
        this.getHandle().inventory.setCarried(stack);
        if (this instanceof CraftPlayer) {
            ((EntityPlayer)this.getHandle()).broadcastCarriedItem();
        }
    }

    @Override
    public boolean isSleeping() {
        return this.getHandle().sleeping;
    }

    @Override
    public int getSleepTicks() {
        return this.getHandle().sleepTicks;
    }

    @Override
    public boolean isOp() {
        return this.op;
    }

    @Override
    public boolean isPermissionSet(String name) {
        return this.perm.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return this.perm.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return this.perm.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return this.perm.hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return this.perm.addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return this.perm.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return this.perm.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return this.perm.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        this.perm.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        this.perm.recalculatePermissions();
    }

    @Override
    public void setOp(boolean value) {
        this.op = value;
        this.perm.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return this.perm.getEffectivePermissions();
    }

    @Override
    public GameMode getGameMode() {
        return this.mode;
    }

    @Override
    public void setGameMode(GameMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        this.mode = mode;
    }

    @Override
    public EntityHuman getHandle() {
        return (EntityHuman)this.entity;
    }

    public void setHandle(EntityHuman entity) {
        super.setHandle(entity);
        this.inventory = new CraftInventoryPlayer(entity.inventory);
    }

    @Override
    public String toString() {
        return "CraftHumanEntity{id=" + this.getEntityId() + "name=" + this.getName() + '}';
    }

    @Override
    public InventoryView getOpenInventory() {
        return this.getHandle().activeContainer.getBukkitView();
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        if (!(this.getHandle() instanceof EntityPlayer)) {
            return null;
        }
        EntityPlayer player = (EntityPlayer)this.getHandle();
        InventoryType type = inventory.getType();
        Container formerContainer = this.getHandle().activeContainer;
        CraftInventory craftinv = (CraftInventory)inventory;
        switch (type) {
            case PLAYER: 
            case CHEST: 
            case ENDER_CHEST: {
                this.getHandle().openContainer(craftinv.getInventory());
                break;
            }
            case DISPENSER: {
                if (craftinv.getInventory() instanceof TileEntityDispenser) {
                    this.getHandle().openDispenser((TileEntityDispenser)craftinv.getInventory());
                    break;
                }
                this.openCustomInventory(inventory, player, 3);
                break;
            }
            case FURNACE: {
                if (craftinv.getInventory() instanceof TileEntityFurnace) {
                    this.getHandle().openFurnace((TileEntityFurnace)craftinv.getInventory());
                    break;
                }
                this.openCustomInventory(inventory, player, 2);
                break;
            }
            case WORKBENCH: {
                this.openCustomInventory(inventory, player, 1);
                break;
            }
            case BREWING: {
                if (craftinv.getInventory() instanceof TileEntityBrewingStand) {
                    this.getHandle().openBrewingStand((TileEntityBrewingStand)craftinv.getInventory());
                    break;
                }
                this.openCustomInventory(inventory, player, 5);
                break;
            }
            case ENCHANTING: {
                this.openCustomInventory(inventory, player, 4);
                break;
            }
            case HOPPER: {
                if (craftinv.getInventory() instanceof TileEntityHopper) {
                    this.getHandle().openHopper((TileEntityHopper)craftinv.getInventory());
                    break;
                }
                if (craftinv.getInventory() instanceof EntityMinecartHopper) {
                    this.getHandle().openMinecartHopper((EntityMinecartHopper)craftinv.getInventory());
                    break;
                }
                this.openCustomInventory(inventory, player, 9);
                break;
            }
            case CREATIVE: 
            case CRAFTING: {
                throw new IllegalArgumentException("Can't open a " + (Object)((Object)type) + " inventory!");
            }
        }
        if (this.getHandle().activeContainer == formerContainer) {
            return null;
        }
        this.getHandle().activeContainer.checkReachable = false;
        return this.getHandle().activeContainer.getBukkitView();
    }

    private void openCustomInventory(Inventory inventory, EntityPlayer player, int windowType) {
        if (player.playerConnection == null) {
            return;
        }
        Container container = new CraftContainer(inventory, this, player.nextContainerCounter());
        if ((container = CraftEventFactory.callInventoryOpenEvent(player, container)) == null) {
            return;
        }
        String title = container.getBukkitView().getTitle();
        int size = container.getBukkitView().getTopInventory().getSize();
        player.playerConnection.sendPacket(new Packet100OpenWindow(container.windowId, windowType, title, size, true));
        this.getHandle().activeContainer = container;
        this.getHandle().activeContainer.addSlotListener(player);
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        Block block;
        if (!force && (block = location.getBlock()).getType() != Material.WORKBENCH) {
            return null;
        }
        if (location == null) {
            location = this.getLocation();
        }
        this.getHandle().startCrafting(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        if (force) {
            this.getHandle().activeContainer.checkReachable = false;
        }
        return this.getHandle().activeContainer.getBukkitView();
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        Block block;
        if (!force && (block = location.getBlock()).getType() != Material.ENCHANTMENT_TABLE) {
            return null;
        }
        if (location == null) {
            location = this.getLocation();
        }
        this.getHandle().startEnchanting(location.getBlockX(), location.getBlockY(), location.getBlockZ(), null);
        if (force) {
            this.getHandle().activeContainer.checkReachable = false;
        }
        return this.getHandle().activeContainer.getBukkitView();
    }

    @Override
    public void openInventory(InventoryView inventory) {
        if (!(this.getHandle() instanceof EntityPlayer)) {
            return;
        }
        if (((EntityPlayer)this.getHandle()).playerConnection == null) {
            return;
        }
        if (this.getHandle().activeContainer != this.getHandle().defaultContainer) {
            ((EntityPlayer)this.getHandle()).playerConnection.handleContainerClose(new Packet101CloseWindow(this.getHandle().activeContainer.windowId));
        }
        EntityPlayer player = (EntityPlayer)this.getHandle();
        Container container = inventory instanceof CraftInventoryView ? ((CraftInventoryView)inventory).getHandle() : new CraftContainer(inventory, player.nextContainerCounter());
        if ((container = CraftEventFactory.callInventoryOpenEvent(player, container)) == null) {
            return;
        }
        InventoryType type = inventory.getType();
        int windowType = CraftContainer.getNotchInventoryType(type);
        String title = inventory.getTitle();
        int size = inventory.getTopInventory().getSize();
        player.playerConnection.sendPacket(new Packet100OpenWindow(container.windowId, windowType, title, size, false));
        player.activeContainer = container;
        player.activeContainer.addSlotListener(player);
    }

    @Override
    public void closeInventory() {
        this.getHandle().closeInventory();
    }

    @Override
    public boolean isBlocking() {
        return this.getHandle().isBlocking();
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        return false;
    }

    @Override
    public int getExpToLevel() {
        return this.getHandle().getExpToLevel();
    }

}

