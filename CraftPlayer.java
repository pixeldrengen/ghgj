/*
 * Decompiled with CFR 0_118.
 */
package org.bukkit.craftbukkit.v1_6_R3.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.MapMaker;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.v1_6_R3.AttributeBase;
import net.minecraft.server.v1_6_R3.AttributeInstance;
import net.minecraft.server.v1_6_R3.AttributeMapBase;
import net.minecraft.server.v1_6_R3.AttributeMapServer;
import net.minecraft.server.v1_6_R3.AttributeModifiable;
import net.minecraft.server.v1_6_R3.AttributeRanged;
import net.minecraft.server.v1_6_R3.BanEntry;
import net.minecraft.server.v1_6_R3.BanList;
import net.minecraft.server.v1_6_R3.ChatMessage;
import net.minecraft.server.v1_6_R3.ChunkCoordinates;
import net.minecraft.server.v1_6_R3.Container;
import net.minecraft.server.v1_6_R3.DataWatcher;
import net.minecraft.server.v1_6_R3.DedicatedPlayerList;
import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.EntityTracker;
import net.minecraft.server.v1_6_R3.EntityTrackerEntry;
import net.minecraft.server.v1_6_R3.EnumGamemode;
import net.minecraft.server.v1_6_R3.FoodMetaData;
import net.minecraft.server.v1_6_R3.IAttribute;
import net.minecraft.server.v1_6_R3.INetworkManager;
import net.minecraft.server.v1_6_R3.IPlayerFileData;
import net.minecraft.server.v1_6_R3.IntHashMap;
import net.minecraft.server.v1_6_R3.NBTTagCompound;
import net.minecraft.server.v1_6_R3.Packet;
import net.minecraft.server.v1_6_R3.Packet131ItemData;
import net.minecraft.server.v1_6_R3.Packet200Statistic;
import net.minecraft.server.v1_6_R3.Packet201PlayerInfo;
import net.minecraft.server.v1_6_R3.Packet250CustomPayload;
import net.minecraft.server.v1_6_R3.Packet3Chat;
import net.minecraft.server.v1_6_R3.Packet44UpdateAttributes;
import net.minecraft.server.v1_6_R3.Packet53BlockChange;
import net.minecraft.server.v1_6_R3.Packet54PlayNoteBlock;
import net.minecraft.server.v1_6_R3.Packet61WorldEvent;
import net.minecraft.server.v1_6_R3.Packet62NamedSoundEffect;
import net.minecraft.server.v1_6_R3.Packet6SpawnPosition;
import net.minecraft.server.v1_6_R3.Packet70Bed;
import net.minecraft.server.v1_6_R3.Packet8UpdateHealth;
import net.minecraft.server.v1_6_R3.PlayerAbilities;
import net.minecraft.server.v1_6_R3.PlayerConnection;
import net.minecraft.server.v1_6_R3.PlayerInteractManager;
import net.minecraft.server.v1_6_R3.WorldServer;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationCanceller;
import org.bukkit.conversations.ManuallyAbandonedConversationCanceller;
import org.bukkit.craftbukkit.v1_6_R3.CraftEffect;
import org.bukkit.craftbukkit.v1_6_R3.CraftOfflinePlayer;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.CraftSound;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.conversations.ConversationTracker;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_6_R3.map.CraftMapView;
import org.bukkit.craftbukkit.v1_6_R3.map.RenderData;
import org.bukkit.craftbukkit.v1_6_R3.metadata.PlayerMetadataStore;
import org.bukkit.craftbukkit.v1_6_R3.scoreboard.CraftScoreboard;
import org.bukkit.craftbukkit.v1_6_R3.scoreboard.CraftScoreboardManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.scoreboard.Scoreboard;

@DelegateDeserialization(value=CraftOfflinePlayer.class)
public class CraftPlayer
extends CraftHumanEntity
implements Player {
    private long firstPlayed = System.currentTimeMillis();
    private long lastPlayed = 0;
    private boolean hasPlayedBefore = false;
    private final ConversationTracker conversationTracker = new ConversationTracker();
    private final Set<String> channels = new HashSet<String>();
    private final Map<String, Player> hiddenPlayers = new MapMaker().softValues().makeMap();
    private int hash = 0;
    private double health = 20.0;
    private boolean scaledHealth = false;
    private double healthScale = 20.0;

    public CraftPlayer(CraftServer server, EntityPlayer entity) {
        super(server, entity);
    }

    @Override
    public boolean isOp() {
        return this.server.getHandle().isOp(this.getName());
    }

    @Override
    public void setOp(boolean value) {
        if (value == this.isOp()) {
            return;
        }
        if (value) {
            this.server.getHandle().addOp(this.getName());
        } else {
            this.server.getHandle().removeOp(this.getName());
        }
        this.perm.recalculatePermissions();
    }

    @Override
    public boolean isOnline() {
        for (Object obj : this.server.getHandle().players) {
            EntityPlayer player = (EntityPlayer)obj;
            if (!player.getName().equalsIgnoreCase(this.getName())) continue;
            return true;
        }
        return false;
    }

    @Override
    public InetSocketAddress getAddress() {
        if (this.getHandle().playerConnection == null) {
            return null;
        }
        SocketAddress addr = this.getHandle().playerConnection.networkManager.getSocketAddress();
        if (addr instanceof InetSocketAddress) {
            return (InetSocketAddress)addr;
        }
        return null;
    }

    @Override
    public double getEyeHeight() {
        return this.getEyeHeight(false);
    }

    @Override
    public double getEyeHeight(boolean ignoreSneaking) {
        if (ignoreSneaking) {
            return 1.62;
        }
        if (this.isSneaking()) {
            return 1.54;
        }
        return 1.62;
    }

    @Override
    public void sendRawMessage(String message) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        this.getHandle().playerConnection.sendPacket(new Packet3Chat(ChatMessage.d(message)));
    }

    @Override
    public void sendMessage(String message) {
        if (!this.conversationTracker.isConversingModaly()) {
            this.sendRawMessage(message);
        }
    }

    @Override
    public void sendMessage(String[] messages) {
        for (String message : messages) {
            this.sendMessage(message);
        }
    }

    @Override
    public String getDisplayName() {
        return this.getHandle().displayName;
    }

    @Override
    public void setDisplayName(String name) {
        this.getHandle().displayName = name;
    }

    @Override
    public String getPlayerListName() {
        return this.getHandle().listName;
    }

    @Override
    public void setPlayerListName(String name) {
        String oldName = this.getHandle().listName;
        if (name == null) {
            name = this.getName();
        }
        if (oldName.equals(name)) {
            return;
        }
        if (name.length() > 16) {
            throw new IllegalArgumentException("Player list names can only be a maximum of 16 characters long");
        }
        for (int i = 0; i < this.server.getHandle().players.size(); ++i) {
            if (!((EntityPlayer)this.server.getHandle().players.get((int)i)).listName.equals(name)) continue;
            throw new IllegalArgumentException(name + " is already assigned as a player list name for someone");
        }
        this.getHandle().listName = name;
        Packet201PlayerInfo oldpacket = new Packet201PlayerInfo(oldName, false, 9999);
        Packet201PlayerInfo packet = new Packet201PlayerInfo(name, true, this.getHandle().ping);
        for (int i2 = 0; i2 < this.server.getHandle().players.size(); ++i2) {
            EntityPlayer entityplayer = (EntityPlayer)this.server.getHandle().players.get(i2);
            if (entityplayer.playerConnection == null || !entityplayer.getBukkitEntity().canSee(this)) continue;
            entityplayer.playerConnection.sendPacket(oldpacket);
            entityplayer.playerConnection.sendPacket(packet);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OfflinePlayer)) {
            return false;
        }
        OfflinePlayer other = (OfflinePlayer)obj;
        if (this.getName() == null || other.getName() == null) {
            return false;
        }
        boolean nameEquals = this.getName().equalsIgnoreCase(other.getName());
        boolean idEquals = true;
        if (other instanceof CraftPlayer) {
            idEquals = this.getEntityId() == ((CraftPlayer)other).getEntityId();
        }
        return nameEquals && idEquals;
    }

    @Override
    public void kickPlayer(String message) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        this.getHandle().playerConnection.disconnect(message == null ? "" : message);
    }

    @Override
    public void setCompassTarget(Location loc) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        this.getHandle().playerConnection.sendPacket(new Packet6SpawnPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
    }

    @Override
    public Location getCompassTarget() {
        return this.getHandle().compassTarget;
    }

    @Override
    public void chat(String msg) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        this.getHandle().playerConnection.chat(msg, false);
    }

    @Override
    public boolean performCommand(String command) {
        return this.server.dispatchCommand(this, command);
    }

    @Override
    public void playNote(Location loc, byte instrument, byte note) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        int id = this.getHandle().world.getTypeId(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        this.getHandle().playerConnection.sendPacket(new Packet54PlayNoteBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), id, instrument, note));
    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        int id = this.getHandle().world.getTypeId(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        this.getHandle().playerConnection.sendPacket(new Packet54PlayNoteBlock(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), id, instrument.getType(), note.getId()));
    }

    @Override
    public void playSound(Location loc, Sound sound, float volume, float pitch) {
        if (sound == null) {
            return;
        }
        this.playSound(loc, CraftSound.getSound(sound), volume, pitch);
    }

    @Override
    public void playSound(Location loc, String sound, float volume, float pitch) {
        if (loc == null || sound == null || this.getHandle().playerConnection == null) {
            return;
        }
        double x = (double)loc.getBlockX() + 0.5;
        double y = (double)loc.getBlockY() + 0.5;
        double z = (double)loc.getBlockZ() + 0.5;
        Packet62NamedSoundEffect packet = new Packet62NamedSoundEffect(sound, x, y, z, volume, pitch);
        this.getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void playEffect(Location loc, Effect effect, int data) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        int packetData = effect.getId();
        Packet61WorldEvent packet = new Packet61WorldEvent(packetData, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), data, false);
        this.getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        if (data != null) {
            Validate.isTrue(data.getClass().equals(effect.getData()), "Wrong kind of data for this effect!");
        } else {
            Validate.isTrue(effect.getData() == null, "Wrong kind of data for this effect!");
        }
        int datavalue = data == null ? 0 : CraftEffect.getDataValue(effect, data);
        this.playEffect(loc, effect, datavalue);
    }

    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {
        this.sendBlockChange(loc, material.getId(), data);
    }

    @Override
    public void sendBlockChange(Location loc, int material, byte data) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        Packet53BlockChange packet = new Packet53BlockChange(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), ((CraftWorld)loc.getWorld()).getHandle());
        packet.material = material;
        packet.data = data;
        this.getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
        if (this.getHandle().playerConnection == null) {
            return false;
        }
        throw new NotImplementedException("Chunk changes do not yet work");
    }

    @Override
    public void sendMap(MapView map) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        RenderData data = ((CraftMapView)map).render(this);
        for (int x = 0; x < 128; ++x) {
            byte[] bytes = new byte[131];
            bytes[1] = (byte)x;
            for (int y = 0; y < 128; ++y) {
                bytes[y + 3] = data.buffer[y * 128 + x];
            }
            Packet131ItemData packet = new Packet131ItemData((short)Material.MAP.getId(), map.getId(), bytes);
            this.getHandle().playerConnection.sendPacket(packet);
        }
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        EntityPlayer entity = this.getHandle();
        if (this.getHealth() == 0.0 || entity.dead) {
            return false;
        }
        if (entity.playerConnection == null || entity.playerConnection.disconnected) {
            return false;
        }
        if (entity.vehicle != null || entity.passenger != null) {
            return false;
        }
        Location from = this.getLocation();
        Location to = location;
        PlayerTeleportEvent event = new PlayerTeleportEvent(this, from, to, cause);
        this.server.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return false;
        }
        from = event.getFrom();
        to = event.getTo();
        WorldServer fromWorld = ((CraftWorld)from.getWorld()).getHandle();
        WorldServer toWorld = ((CraftWorld)to.getWorld()).getHandle();
        if (this.getHandle().activeContainer != this.getHandle().defaultContainer) {
            this.getHandle().closeInventory();
        }
        if (fromWorld == toWorld) {
            entity.playerConnection.teleport(to);
        } else {
            this.server.getHandle().moveToWorld(entity, toWorld.dimension, true, to, true);
        }
        return true;
    }

    @Override
    public void setSneaking(boolean sneak) {
        this.getHandle().setSneaking(sneak);
    }

    @Override
    public boolean isSneaking() {
        return this.getHandle().isSneaking();
    }

    @Override
    public boolean isSprinting() {
        return this.getHandle().isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.getHandle().setSprinting(sprinting);
    }

    @Override
    public void loadData() {
        this.server.getHandle().playerFileData.load(this.getHandle());
    }

    @Override
    public void saveData() {
        this.server.getHandle().playerFileData.save(this.getHandle());
    }

    @Deprecated
    @Override
    public void updateInventory() {
        this.getHandle().updateInventory(this.getHandle().activeContainer);
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        this.getHandle().fauxSleeping = isSleeping;
        ((CraftWorld)this.getWorld()).getHandle().checkSleepStatus();
    }

    @Override
    public boolean isSleepingIgnored() {
        return this.getHandle().fauxSleeping;
    }

    @Override
    public void awardAchievement(Achievement achievement) {
        this.sendStatistic(achievement.getId(), 1);
    }

    @Override
    public void incrementStatistic(Statistic statistic) {
        this.incrementStatistic(statistic, 1);
    }

    @Override
    public void incrementStatistic(Statistic statistic, int amount) {
        this.sendStatistic(statistic.getId(), amount);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) {
        this.incrementStatistic(statistic, material, 1);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount) {
        if (!statistic.isSubstatistic()) {
            throw new IllegalArgumentException("Given statistic is not a substatistic");
        }
        if (statistic.isBlock() != material.isBlock()) {
            throw new IllegalArgumentException("Given material is not valid for this substatistic");
        }
        int mat = material.getId();
        if (!material.isBlock()) {
            mat -= 255;
        }
        this.sendStatistic(statistic.getId() + mat, amount);
    }

    private void sendStatistic(int id, int amount) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        while (amount > 127) {
            this.sendStatistic(id, 127);
            amount -= 127;
        }
        this.getHandle().playerConnection.sendPacket(new Packet200Statistic(id, amount));
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        this.getHandle().timeOffset = time;
        this.getHandle().relativeTime = relative;
    }

    @Override
    public long getPlayerTimeOffset() {
        return this.getHandle().timeOffset;
    }

    @Override
    public long getPlayerTime() {
        return this.getHandle().getPlayerTime();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return this.getHandle().relativeTime;
    }

    @Override
    public void resetPlayerTime() {
        this.setPlayerTime(0, true);
    }

    @Override
    public void setPlayerWeather(WeatherType type) {
        this.getHandle().setPlayerWeather(type, true);
    }

    @Override
    public WeatherType getPlayerWeather() {
        return this.getHandle().getPlayerWeather();
    }

    @Override
    public void resetPlayerWeather() {
        this.getHandle().resetPlayerWeather();
    }

    @Override
    public boolean isBanned() {
        return this.server.getHandle().getNameBans().isBanned(this.getName().toLowerCase());
    }

    @Override
    public void setBanned(boolean value) {
        if (value) {
            BanEntry entry = new BanEntry(this.getName().toLowerCase());
            this.server.getHandle().getNameBans().add(entry);
        } else {
            this.server.getHandle().getNameBans().remove(this.getName().toLowerCase());
        }
        this.server.getHandle().getNameBans().save();
    }

    @Override
    public boolean isWhitelisted() {
        return this.server.getHandle().getWhitelisted().contains(this.getName().toLowerCase());
    }

    @Override
    public void setWhitelisted(boolean value) {
        if (value) {
            this.server.getHandle().addWhitelist(this.getName().toLowerCase());
        } else {
            this.server.getHandle().removeWhitelist(this.getName().toLowerCase());
        }
    }

    @Override
    public void setGameMode(GameMode mode) {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        if (mode != this.getGameMode()) {
            PlayerGameModeChangeEvent event = new PlayerGameModeChangeEvent(this, mode);
            this.server.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            this.getHandle().playerInteractManager.setGameMode(EnumGamemode.a(mode.getValue()));
            this.getHandle().playerConnection.sendPacket(new Packet70Bed(3, mode.getValue()));
        }
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.getByValue(this.getHandle().playerInteractManager.getGameMode().a());
    }

    @Override
    public void giveExp(int exp) {
        this.getHandle().giveExp(exp);
    }

    @Override
    public void giveExpLevels(int levels) {
        this.getHandle().levelDown(levels);
    }

    @Override
    public float getExp() {
        return this.getHandle().exp;
    }

    @Override
    public void setExp(float exp) {
        this.getHandle().exp = exp;
        this.getHandle().lastSentExp = -1;
    }

    @Override
    public int getLevel() {
        return this.getHandle().expLevel;
    }

    @Override
    public void setLevel(int level) {
        this.getHandle().expLevel = level;
        this.getHandle().lastSentExp = -1;
    }

    @Override
    public int getTotalExperience() {
        return this.getHandle().expTotal;
    }

    @Override
    public void setTotalExperience(int exp) {
        this.getHandle().expTotal = exp;
    }

    @Override
    public float getExhaustion() {
        return this.getHandle().getFoodData().exhaustionLevel;
    }

    @Override
    public void setExhaustion(float value) {
        this.getHandle().getFoodData().exhaustionLevel = value;
    }

    @Override
    public float getSaturation() {
        return this.getHandle().getFoodData().saturationLevel;
    }

    @Override
    public void setSaturation(float value) {
        this.getHandle().getFoodData().saturationLevel = value;
    }

    @Override
    public int getFoodLevel() {
        return this.getHandle().getFoodData().foodLevel;
    }

    @Override
    public void setFoodLevel(int value) {
        this.getHandle().getFoodData().foodLevel = value;
    }

    @Override
    public Location getBedSpawnLocation() {
        World world = this.getServer().getWorld(this.getHandle().spawnWorld);
        ChunkCoordinates bed = this.getHandle().getBed();
        if (world != null && bed != null && (bed = EntityHuman.getBed(((CraftWorld)world).getHandle(), bed, this.getHandle().isRespawnForced())) != null) {
            return new Location(world, bed.x, bed.y, bed.z);
        }
        return null;
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        this.setBedSpawnLocation(location, false);
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean override) {
        if (location == null) {
            this.getHandle().setRespawnPosition(null, override);
        } else {
            this.getHandle().setRespawnPosition(new ChunkCoordinates(location.getBlockX(), location.getBlockY(), location.getBlockZ()), override);
            this.getHandle().spawnWorld = location.getWorld().getName();
        }
    }

    @Override
    public void hidePlayer(Player player) {
        Validate.notNull(player, "hidden player cannot be null");
        if (this.getHandle().playerConnection == null) {
            return;
        }
        if (this.equals(player)) {
            return;
        }
        if (this.hiddenPlayers.containsKey(player.getName())) {
            return;
        }
        this.hiddenPlayers.put(player.getName(), player);
        EntityTracker tracker = ((WorldServer)this.entity.world).tracker;
        EntityPlayer other = ((CraftPlayer)player).getHandle();
        EntityTrackerEntry entry = (EntityTrackerEntry)tracker.trackedEntities.get(other.id);
        if (entry != null) {
            entry.clear(this.getHandle());
        }
        this.getHandle().playerConnection.sendPacket(new Packet201PlayerInfo(player.getPlayerListName(), false, 9999));
    }

    @Override
    public void showPlayer(Player player) {
        Validate.notNull(player, "shown player cannot be null");
        if (this.getHandle().playerConnection == null) {
            return;
        }
        if (this.equals(player)) {
            return;
        }
        if (!this.hiddenPlayers.containsKey(player.getName())) {
            return;
        }
        this.hiddenPlayers.remove(player.getName());
        EntityTracker tracker = ((WorldServer)this.entity.world).tracker;
        EntityPlayer other = ((CraftPlayer)player).getHandle();
        EntityTrackerEntry entry = (EntityTrackerEntry)tracker.trackedEntities.get(other.id);
        if (entry != null && !entry.trackedPlayers.contains(this.getHandle())) {
            entry.updatePlayer(this.getHandle());
        }
        this.getHandle().playerConnection.sendPacket(new Packet201PlayerInfo(player.getPlayerListName(), true, this.getHandle().ping));
    }

    @Override
    public boolean canSee(Player player) {
        return !this.hiddenPlayers.containsKey(player.getName());
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("name", this.getName());
        return result;
    }

    @Override
    public Player getPlayer() {
        return this;
    }

    @Override
    public EntityPlayer getHandle() {
        return (EntityPlayer)this.entity;
    }

    public void setHandle(EntityPlayer entity) {
        super.setHandle(entity);
    }

    @Override
    public String toString() {
        return "CraftPlayer{name=" + this.getName() + '}';
    }

    @Override
    public int hashCode() {
        if (this.hash == 0 || this.hash == 485) {
            this.hash = 485 + (this.getName() != null ? this.getName().toLowerCase().hashCode() : 0);
        }
        return this.hash;
    }

    @Override
    public long getFirstPlayed() {
        return this.firstPlayed;
    }

    @Override
    public long getLastPlayed() {
        return this.lastPlayed;
    }

    @Override
    public boolean hasPlayedBefore() {
        return this.hasPlayedBefore;
    }

    public void setFirstPlayed(long firstPlayed) {
        this.firstPlayed = firstPlayed;
    }

    public void readExtraData(NBTTagCompound nbttagcompound) {
        this.hasPlayedBefore = true;
        if (nbttagcompound.hasKey("bukkit")) {
            NBTTagCompound data = nbttagcompound.getCompound("bukkit");
            if (data.hasKey("firstPlayed")) {
                this.firstPlayed = data.getLong("firstPlayed");
                this.lastPlayed = data.getLong("lastPlayed");
            }
            if (data.hasKey("newExp")) {
                EntityPlayer handle = this.getHandle();
                handle.newExp = data.getInt("newExp");
                handle.newTotalExp = data.getInt("newTotalExp");
                handle.newLevel = data.getInt("newLevel");
                handle.expToDrop = data.getInt("expToDrop");
                handle.keepLevel = data.getBoolean("keepLevel");
            }
        }
    }

    public void setExtraData(NBTTagCompound nbttagcompound) {
        if (!nbttagcompound.hasKey("bukkit")) {
            nbttagcompound.setCompound("bukkit", new NBTTagCompound());
        }
        NBTTagCompound data = nbttagcompound.getCompound("bukkit");
        EntityPlayer handle = this.getHandle();
        data.setInt("newExp", handle.newExp);
        data.setInt("newTotalExp", handle.newTotalExp);
        data.setInt("newLevel", handle.newLevel);
        data.setInt("expToDrop", handle.expToDrop);
        data.setBoolean("keepLevel", handle.keepLevel);
        data.setLong("firstPlayed", this.getFirstPlayed());
        data.setLong("lastPlayed", System.currentTimeMillis());
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return this.conversationTracker.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        this.conversationTracker.abandonConversation(conversation, new ConversationAbandonedEvent(conversation, new ManuallyAbandonedConversationCanceller()));
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        this.conversationTracker.abandonConversation(conversation, details);
    }

    @Override
    public void acceptConversationInput(String input) {
        this.conversationTracker.acceptConversationInput(input);
    }

    @Override
    public boolean isConversing() {
        return this.conversationTracker.isConversing();
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        StandardMessenger.validatePluginMessage(this.server.getMessenger(), source, channel, message);
        if (this.getHandle().playerConnection == null) {
            return;
        }
        if (this.channels.contains(channel)) {
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.tag = channel;
            packet.length = message.length;
            packet.data = message;
            this.getHandle().playerConnection.sendPacket(packet);
        }
    }

    @Override
    public void setTexturePack(String url) {
        Validate.notNull(url, "Texture pack URL cannot be null");
        byte[] message = (url + "\u0000" + "16").getBytes();
        Validate.isTrue(message.length <= 32766, "Texture pack URL is too long");
        this.getHandle().playerConnection.sendPacket(new Packet250CustomPayload("MC|TPack", message));
    }

    public void addChannel(String channel) {
        if (this.channels.add(channel)) {
            this.server.getPluginManager().callEvent(new PlayerRegisterChannelEvent(this, channel));
        }
    }

    public void removeChannel(String channel) {
        if (this.channels.remove(channel)) {
            this.server.getPluginManager().callEvent(new PlayerUnregisterChannelEvent(this, channel));
        }
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return ImmutableSet.copyOf(this.channels);
    }

    public void sendSupportedChannels() {
        if (this.getHandle().playerConnection == null) {
            return;
        }
        Set<String> listening = this.server.getMessenger().getIncomingChannels();
        if (!listening.isEmpty()) {
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.tag = "REGISTER";
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            for (String channel : listening) {
                try {
                    stream.write(channel.getBytes("UTF8"));
                    stream.write(0);
                }
                catch (IOException ex) {
                    Logger.getLogger(CraftPlayer.class.getName()).log(Level.SEVERE, "Could not send Plugin Channel REGISTER to " + this.getName(), ex);
                }
            }
            packet.data = stream.toByteArray();
            packet.length = packet.data.length;
            this.getHandle().playerConnection.sendPacket(packet);
        }
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.server.getPlayerMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.server.getPlayerMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.server.getPlayerMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.server.getPlayerMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        Container container = this.getHandle().activeContainer;
        if (container.getBukkitView().getType() != prop.getType()) {
            return false;
        }
        this.getHandle().setContainerData(container, prop.getId(), value);
        return true;
    }

    public void disconnect(String reason) {
        this.conversationTracker.abandonAllConversations();
        this.perm.clearPermissions();
    }

    @Override
    public boolean isFlying() {
        return this.getHandle().abilities.isFlying;
    }

    @Override
    public void setFlying(boolean value) {
        if (!this.getAllowFlight() && value) {
            throw new IllegalArgumentException("Cannot make player fly if getAllowFlight() is false");
        }
        this.getHandle().abilities.isFlying = value;
        this.getHandle().updateAbilities();
    }

    @Override
    public boolean getAllowFlight() {
        return this.getHandle().abilities.canFly;
    }

    @Override
    public void setAllowFlight(boolean value) {
        if (this.isFlying() && !value) {
            this.getHandle().abilities.isFlying = false;
        }
        this.getHandle().abilities.canFly = value;
        this.getHandle().updateAbilities();
    }

    @Override
    public int getNoDamageTicks() {
        if (this.getHandle().invulnerableTicks > 0) {
            return Math.max(this.getHandle().invulnerableTicks, this.getHandle().noDamageTicks);
        }
        return this.getHandle().noDamageTicks;
    }

    @Override
    public void setFlySpeed(float value) {
        this.validateSpeed(value);
        EntityPlayer player = this.getHandle();
        player.abilities.flySpeed = value / 2.0f;
        player.updateAbilities();
    }

    @Override
    public void setWalkSpeed(float value) {
        this.validateSpeed(value);
        EntityPlayer player = this.getHandle();
        player.abilities.walkSpeed = value / 2.0f;
        player.updateAbilities();
    }

    @Override
    public float getFlySpeed() {
        return this.getHandle().abilities.flySpeed * 2.0f;
    }

    @Override
    public float getWalkSpeed() {
        return this.getHandle().abilities.walkSpeed * 2.0f;
    }

    private void validateSpeed(float value) {
        if (value < 0.0f) {
            if (value < -1.0f) {
                throw new IllegalArgumentException("" + value + " is too low");
            }
        } else if (value > 1.0f) {
            throw new IllegalArgumentException("" + value + " is too high");
        }
    }

    @Override
    public void setMaxHealth(double amount) {
        super.setMaxHealth(amount);
        this.health = Math.min(this.health, this.health);
        this.getHandle().triggerHealthUpdate();
    }

    @Override
    public void resetMaxHealth() {
        super.resetMaxHealth();
        this.getHandle().triggerHealthUpdate();
    }

    @Override
    public CraftScoreboard getScoreboard() {
        return this.server.getScoreboardManager().getPlayerBoard(this);
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        Validate.notNull(scoreboard, "Scoreboard cannot be null");
        PlayerConnection playerConnection = this.getHandle().playerConnection;
        if (playerConnection == null) {
            throw new IllegalStateException("Cannot set scoreboard yet");
        }
        if (playerConnection.disconnected) {
            throw new IllegalStateException("Cannot set scoreboard for invalid CraftPlayer");
        }
        this.server.getScoreboardManager().setPlayerBoard(this, scoreboard);
    }

    @Override
    public void setHealthScale(double value) {
        Validate.isTrue((float)value > 0.0f, "Must be greater than 0");
        this.healthScale = value;
        this.scaledHealth = true;
        this.updateScaledHealth();
    }

    @Override
    public double getHealthScale() {
        return this.healthScale;
    }

    @Override
    public void setHealthScaled(boolean scale) {
        this.scaledHealth = scale;
        if (this.scaledHealth != this.scaledHealth) {
            this.updateScaledHealth();
        }
    }

    @Override
    public boolean isHealthScaled() {
        return this.scaledHealth;
    }

    public float getScaledHealth() {
        return (float)(this.isHealthScaled() ? this.getHealth() * this.getHealthScale() / this.getMaxHealth() : this.getHealth());
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    public void setRealHealth(double health) {
        this.health = health;
    }

    public void updateScaledHealth() {
        AttributeMapServer attributemapserver = (AttributeMapServer)this.getHandle().aX();
        Set set = attributemapserver.b();
        this.injectScaledMaxHealth(set, true);
        this.getHandle().getDataWatcher().watch(6, Float.valueOf(this.getScaledHealth()));
        this.getHandle().playerConnection.sendPacket(new Packet8UpdateHealth(this.getScaledHealth(), this.getHandle().getFoodData().a(), this.getHandle().getFoodData().e()));
        this.getHandle().playerConnection.sendPacket(new Packet44UpdateAttributes(this.getHandle().id, set));
        set.clear();
        this.getHandle().maxHealthCache = this.getMaxHealth();
    }

    public void injectScaledMaxHealth(Collection collection, boolean force) {
        if (!this.scaledHealth && !force) {
            return;
        }
        for (Object genericInstance : collection) {
            IAttribute attribute = ((AttributeInstance)genericInstance).a();
            if (!attribute.a().equals("generic.maxHealth")) continue;
            collection.remove(genericInstance);
            break;
        }
        collection.add(new AttributeModifiable(this.getHandle().aX(), new AttributeRanged("generic.maxHealth", this.scaledHealth ? this.healthScale : this.getMaxHealth(), 0.0, 3.4028234663852886E38).a("Max Health").a(true)));
    }
}

