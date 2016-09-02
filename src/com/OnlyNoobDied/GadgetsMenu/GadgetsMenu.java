package com.OnlyNoobDied.GadgetsMenu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.OnlyNoobDied.GadgetsMenu.Updater.UpdaterManager;

import depend.xxmicloxx.NoteBlockAPI.SongPlayer;

import com.OnlyNoobDied.GadgetsMenu.API.*;
import com.OnlyNoobDied.GadgetsMenu.Commands.*;
import com.OnlyNoobDied.GadgetsMenu.Commands.SubCommands.*;
import com.OnlyNoobDied.GadgetsMenu.Configuration.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Banners.BannerManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Banners.BannerType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.DiscoArmor.DiscoArmorManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Emotes.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Gadgets.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Hats.HatManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Hats.HatType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.MainMenu.MainMenuManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.MainMenu.MainMenuType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Morphs.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Morphs.Types.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.ParticleManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.ParticleType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.Types.ParticleTypesManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.Types.ParticleTypesType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.PetManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.PetType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.Types.PetEntityManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.Types.PetEntityType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.PurchaseMenu.PurchaseManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Wardrobe.WardrobeManager;
import com.OnlyNoobDied.GadgetsMenu.Listeners.*;
import com.OnlyNoobDied.GadgetsMenu.Listeners.CosmeticsListeners.*;
import com.OnlyNoobDied.GadgetsMenu.Listeners.GadgetListeners.*;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Metrics.MetricsStarter;
import com.OnlyNoobDied.GadgetsMenu.MySQL.MySQLConnection;
import com.OnlyNoobDied.GadgetsMenu.MySQL.Table;
import com.OnlyNoobDied.GadgetsMenu.Utils.*;

public class GadgetsMenu extends JavaPlugin {

	public static String MySQLName = "GadgetsMenu_Data";
	private static GadgetsMenu GadgetsMenu;

	private ArrayList<Entity> disableBlockDamage = new ArrayList<Entity>();
	private ArrayList<Entity> disableFallDamage = new ArrayList<Entity>();
	private ArrayList<UUID> disableKick = new ArrayList<UUID>();
	private Random random = new Random();

	@SuppressWarnings("unused")
	private static BlockUtil blockutil;
	private static MySQLConnection mysql;

	public HashMap<String, ArrayList<SongPlayer>> playingSongs = new HashMap<String, ArrayList<SongPlayer>>();
	public HashMap<String, Byte> playerVolume = new HashMap<String, Byte>();

	public Gadget gd = null;

	@Override
	public void onEnable() {
		GadgetsMenu = this;
		if (!VersionManager.isHigherThan1_8()) {
			new LoggerManager().warn(MessageType.SUPPORTED_1_8_HIGHER.getFormatMessage());
			registerListener(new InvalidVersionListener());
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(MessageType.SUPPORTED_1_8_HIGHER.getFormatMessage());
			}
			return;
		}
		registerConfiguration();
		Version.checkConfigVersion();
		registerListeners();
		blockutil = new BlockUtil();
		mysql = new MySQLConnection();
		if (!MorphAPI.isLibDisguisesHooked() && !VersionManager.isHigherThan1_9()) {
			new LoggerManager().warn("Required Lib's Disguises and 1.9+ spigot to enable Morphs!");
		}
		if (MainAPI.isDatabaseEnabled()) {
			if (new MySQLConnection().checkConnection()) {
				Bukkit.getScheduler().cancelTasks(this);
				Bukkit.getScheduler().cancelAllTasks();
				HandlerList.unregisterAll();
				return;
			}
			getConnection();
			Table.createTable();
		}

		MetricsStarter metricsStarter = new MetricsStarter(this);
		if ((metricsStarter.getStart() != null) && (metricsStarter.getStart().booleanValue() == true)) {
			getServer().getScheduler().runTaskLaterAsynchronously(this, metricsStarter, 1L);
		}
		if (FileManager.getConfigFile().getBoolean("Check Update")) {
			getServer().getScheduler().runTaskLaterAsynchronously(this, new Runnable() {

				@Override
				public void run() {
					try {
						UpdaterManager.checkUpdate(getServer().getConsoleSender());
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (MainAPI.isDatabaseEnabled()) {
								new PlayerData(p).registerPlayerDB();
							}
							if (UpdaterManager.isUpdateAvailable()) {
								if (p.hasPermission("gadgetsmenu.checkupdate")) {
									UpdaterManager.checkUpdatePlayer(p);
								}
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}, 1L);
		}
	}

	@Override
	public void onDisable() {
		if (VersionManager.isHigherThan1_8()) {
			if (MainAPI.isDatabaseEnabled()) {
				MySQLConnection mysql = new MySQLConnection();
				mysql.closeConnection();
			}

			Bukkit.getScheduler().cancelTasks(this);
			Bukkit.getScheduler().cancelAllTasks();
		}
		GadgetsMenu = null;
	}

	private void registerListeners() {
		CommandManager cmdManager = new CommandManager(this);
		cmdManager.registerCommand(new CommandAbout());
		cmdManager.registerCommand(new CommandAdmin());
		cmdManager.registerCommand(new CommandBanners());
		cmdManager.registerCommand(new CommandCheckUpdate());
		cmdManager.registerCommand(new CommandCreditsAdd());
		cmdManager.registerCommand(new CommandCreditsCheck());
		cmdManager.registerCommand(new CommandCreditsPay());
		cmdManager.registerCommand(new CommandCreditsRemove());
		cmdManager.registerCommand(new CommandCreditsSet());
		cmdManager.registerCommand(new CommandDiscoArmor());
		cmdManager.registerCommand(new CommandEmotes());
		cmdManager.registerCommand(new CommandGadgets());
		cmdManager.registerCommand(new CommandGiveMenu());
		cmdManager.registerCommand(new CommandHats());
		cmdManager.registerCommand(new CommandHelp());
		cmdManager.registerCommand(new CommandMorphs());
		cmdManager.registerCommand(new CommandNamePet());
		cmdManager.registerCommand(new CommandParticles());
		cmdManager.registerCommand(new CommandPermission());
		cmdManager.registerCommand(new CommandPets());
		cmdManager.registerCommand(new CommandReload());
		cmdManager.registerCommand(new CommandReset());
		cmdManager.registerCommand(new CommandWardrobe());
		getCommand("menu").setTabCompleter(new AutoTabCompleter());

		// Cosmetics
		MainMenuType.checkEnabled();
		HatType.checkEnabled();
		ParticleType.checkEnabled();
		ParticleTypesType.checkEnabled();
		GadgetType.checkEnabled();
		PetType.checkEnabled();
		PetEntityType.checkEnabled();
		if (MorphAPI.isMorphsEnabled() && MorphAPI.isLibDisguisesHooked() && VersionManager.isHigherThan1_9()) {
			MorphType.checkEnabled();
		}
		BannerType.checkEnabled();
		EmoteType.checkEnabled();
		registerListener(new GadgetListener());
		new GadgetListener().register();

		// Cosmetics
		registerListener(new MainMenuManager());
		if (HatAPI.isHatsEnabled())
			registerListener(new HatManager());
		if (ParticleAPI.isParticlesEnabled()) {
			registerListener(new ParticleAPI());
			registerListener(new ParticleManager());
			registerListener(new ParticleTypesManager());
		}
		if (WardrobeAPI.isWardrobeEnabled())
			registerListener(new WardrobeManager());
		if (DiscoArmorAPI.isDiscoArmorEnabled())
			registerListener(new DiscoArmorManager());
		if (GadgetAPI.isGadgetsEnabled())
			registerListener(new GadgetManager());
		if (PetAPI.isPetsEnabled()) {
			registerListener(new PetManager());
			registerListener(new PetEntityManager());
		}
		if (MorphAPI.isMorphsEnabled() && MorphAPI.isLibDisguisesHooked() && VersionManager.isHigherThan1_9()) {
			registerListener(new MorphManager());
		}
		if (BannerAPI.isBannersEnabled())
			registerListener(new BannerManager());
		if (EmoteAPI.isEmotesEnabled())
			registerListener(new EmoteManager());

		// Purchase Cosmetics Menu
		registerListener(new PurchaseManager());

		// Listeners
		registerListener(new PlayerListeners());
		registerListener(new ClickMenuListener());
		registerListener(new DropItemListener());
		registerListener(new PickupItemListener());
		registerListener(new SelectItemListener());
		registerListener(new EntityListeners());
		registerListener(new PluginLoadListener());
		registerListener(new SlimeballListener());
		registerListener(new EmoteListener());

		registerListener(new BlockDamageListener());
		registerListener(new FallDamageListener());
		registerListener(new PlayerKickListener());

		// Morphs Watcher Listeners
		registerListener(new BatWatcher());
		registerListener(new BlazeWatcher());
	}

	public static void registerConfiguration() {
		FileManager.getConfigFile().reload();
		FileManager.getConfigFile().save();

		FileManager.getMessagesFile().reload();
		FileManager.getMessagesFile().save();

		FileManager.getMainMenuFile().reload();
		FileManager.getMainMenuFile().save();

		FileManager.getHatsFile().reload();
		FileManager.getHatsFile().save();

		FileManager.getParticlesFile().reload();
		FileManager.getParticlesFile().save();

		FileManager.getDiscoArmorFile().reload();
		FileManager.getDiscoArmorFile().save();

		FileManager.getGadgetsFile().reload();
		FileManager.getGadgetsFile().save();

		FileManager.getPetsFile().reload();
		FileManager.getPetsFile().save();

		FileManager.getMorphsFile().reload();
		FileManager.getMorphsFile().save();

		FileManager.getBannersFile().reload();
		FileManager.getBannersFile().save();

		FileManager.getEmotesFile().reload();
		FileManager.getEmotesFile().save();

		MessagesManager.importConfigMessages();
		MessagesManager.importDiscoArmorMessages();
		MessagesManager.importMessages();
	}

	public Connection getConnection() {
		try {
			return mysql.openConnection();
		} catch (Exception e) {
			new LoggerManager().consoleMessage(MessageType.FAILED_TO_CONNECT_DATEBASE.getFormatMessage());
			new LoggerManager().warn(e.getMessage());
		}
		return null;
	}

	public void registerListener(Listener listener) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, getInstance());
	}

	public Random random() {
		return random;
	}

	public ArrayList<Entity> disableBlockDamage() {
		return disableBlockDamage;
	}

	public ArrayList<Entity> disableFallDamage() {
		return disableFallDamage;
	}

	public ArrayList<UUID> disableKick() {
		return disableKick;
	}

	public static GadgetsMenu getInstance() {
		return GadgetsMenu;
	}

	public String getPluginName() {
		return "GadgetsMenu";
	}
}
