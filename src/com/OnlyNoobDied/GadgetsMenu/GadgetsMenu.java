package com.OnlyNoobDied.GadgetsMenu;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.OnlyNoobDied.GadgetsMenu.Updater.DownloadManager;
import com.OnlyNoobDied.GadgetsMenu.Updater.UpdaterManager;

import depend.xxmicloxx.NoteBlockAPI.SongPlayer;

import com.OnlyNoobDied.GadgetsMenu.API.*;
import com.OnlyNoobDied.GadgetsMenu.Commands.*;
import com.OnlyNoobDied.GadgetsMenu.Commands.Menu.AutoTabCompleter;
import com.OnlyNoobDied.GadgetsMenu.Commands.Menu.CommandManager;
import com.OnlyNoobDied.GadgetsMenu.Commands.Menu.SubCommands.*;
import com.OnlyNoobDied.GadgetsMenu.Commands.MysteryDust.SubCommands.*;
import com.OnlyNoobDied.GadgetsMenu.Configuration.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Banners.BannerManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Banners.BannerType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Cloaks.CloakManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Cloaks.CloakType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Emotes.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Gadgets.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Gadgets.Types.Gadget;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Hats.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.MainMenu.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Morphs.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Morphs.Types.*;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.ParticleManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.ParticleType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Particles.Types.ParticleTypesType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.PetManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.PetType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.Types.PetEntityManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Pets.Types.PetEntityType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.PurchaseMenu.PurchaseManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Suits.SuitManager;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Suits.SuitType;
import com.OnlyNoobDied.GadgetsMenu.Cosmetics.Suits.Equipment.EquipmentManager;
import com.OnlyNoobDied.GadgetsMenu.Listeners.*;
import com.OnlyNoobDied.GadgetsMenu.Listeners.CosmeticsListeners.*;
import com.OnlyNoobDied.GadgetsMenu.Listeners.GadgetListeners.*;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Metrics.MetricsStarter;
import com.OnlyNoobDied.GadgetsMenu.MySQL.*;
import com.OnlyNoobDied.GadgetsMenu.NMS.NMSManager;
import com.OnlyNoobDied.GadgetsMenu.Player.PlayerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.*;

public class GadgetsMenu extends JavaPlugin {

	public static String MySQLName = "GadgetsMenu_Data";
	private static GadgetsMenu GadgetsMenu;

	private static ArrayList<Entity> disableBlockDamage = new ArrayList<Entity>();
	private static ArrayList<Entity> disableFallDamage = new ArrayList<Entity>();
	private static ArrayList<UUID> disableKick = new ArrayList<UUID>();
	private static Random random = new Random();

	@SuppressWarnings("unused")
	private static BlockUtil blockutil;
	private static MySQLConnection mysql;
	private static NMSManager nmsManager;

	public HashMap<String, ArrayList<SongPlayer>> playingSongs = new HashMap<String, ArrayList<SongPlayer>>();
	public HashMap<String, Byte> playerVolume = new HashMap<String, Byte>();

	public Gadget gd = null;

	private static PlaceHolders placeHolders;

	@Override
	public void onEnable() {
		GadgetsMenu = this;
		if (!VersionManager.is1_8OrAbove()) {
			new LoggerManager();
			LoggerManager.warn(MessageType.SUPPORTED_1_8_HIGHER.getFormatMessage());
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
		if (!MorphAPI.isLibDisguisesHooked() && !VersionManager.is1_9OrAbove()) {
			new LoggerManager();
			LoggerManager.warn("Required Lib's Disguises and 1.9+ spigot to enable Morphs!");
		}
		if (MainAPI.isDatabaseEnabled()) {
			if (getConnection() == null) {
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
			getConnection();
			Table.createTable();
			Table.createMysteryBoxTable();
		}
		MetricsStarter metricsStarter = new MetricsStarter(this);
		if ((metricsStarter.getStart() != null) && (metricsStarter.getStart().booleanValue() == true)) {
			getServer().getScheduler().runTaskLaterAsynchronously(this, metricsStarter, 1L);
		}
		if (VersionManager.is1_8_R1Version()) {
			nmsManager = new com.OnlyNoobDied.GadgetsMenu.NMS.V1_8_R1.NMSManagerImpl();
		} else if (VersionManager.is1_8_R2Version()) {
			nmsManager = new com.OnlyNoobDied.GadgetsMenu.NMS.V1_8_R2.NMSManagerImpl();
		} else if (VersionManager.is1_8_R3Version()) {
			nmsManager = new com.OnlyNoobDied.GadgetsMenu.NMS.V1_8_R3.NMSManagerImpl();
		} else if (VersionManager.is1_9_R1Version()) {
			nmsManager = new com.OnlyNoobDied.GadgetsMenu.NMS.V1_9_R1.NMSManagerImpl();
		} else if (VersionManager.is1_9_R2Version()) {
			nmsManager = new com.OnlyNoobDied.GadgetsMenu.NMS.V1_9_R2.NMSManagerImpl();
		} else if (VersionManager.is1_10_R1Version()) {
			nmsManager = new com.OnlyNoobDied.GadgetsMenu.NMS.V1_10_R1.NMSManagerImpl();
		}
		try {
			if (FileManager.getConfigFile().getBoolean("Check Update")) {
				UpdaterManager.checkUpdate(getServer().getConsoleSender());
			}
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (MainAPI.isDatabaseEnabled()) {
					new PlayerManager(p.getUniqueId()).registerToDatabase();
				}
				if (FileManager.getConfigFile().getBoolean("Check Update") && UpdaterManager.isUpdateAvailable()) {
					if (p.hasPermission("gadgetsmenu.commands.checkupdate")) {
						UpdaterManager.checkUpdate(p);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (!UpdaterManager.isUpToDate() && UpdaterManager.isUpdateAvailable()
					&& FileManager.getConfigFile().getBoolean("Auto Update")) {
				DownloadManager.download(UpdaterManager.getPluginURL(),
						new File("plugins", UpdaterManager.getFileLocation()));
				Bukkit.shutdown();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		placeHolders = new PlaceHolders();
	}

	@Override
	public void onDisable() {
		if (VersionManager.is1_8OrAbove()) {
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
		cmdManager.registerCommand(new CommandMain());
		cmdManager.registerCommand(new CommandAbout());
		cmdManager.registerCommand(new CommandAdmin());
		cmdManager.registerCommand(new CommandBanners());
		cmdManager.registerCommand(new CommandCheckUpdate());
		cmdManager.registerCommand(new CommandCloaks());
		cmdManager.registerCommand(new CommandDevMode());
		cmdManager.registerCommand(new CommandEmotes());
		cmdManager.registerCommand(new CommandGadgets());
		cmdManager.registerCommand(new CommandHats());
		cmdManager.registerCommand(new CommandHelp());
		cmdManager.registerCommand(new CommandMorphs());
		cmdManager.registerCommand(new CommandNamePet());
		cmdManager.registerCommand(new CommandParticles());
		cmdManager.registerCommand(new CommandPermission());
		cmdManager.registerCommand(new CommandPets());
		cmdManager.registerCommand(new CommandReload());
		cmdManager.registerCommand(new CommandReset());
		getCommand("menu").setTabCompleter(new AutoTabCompleter());
		getCommand("gmenu").setTabCompleter(new AutoTabCompleter());
		getCommand("gadgetsmenu").setTabCompleter(new AutoTabCompleter());
		getServer().getPluginCommand("mystery").setExecutor(new MysteryBoxCommand());
		com.OnlyNoobDied.GadgetsMenu.Commands.MysteryDust.CommandManager dustCmdManager = new com.OnlyNoobDied.GadgetsMenu.Commands.MysteryDust.CommandManager(
				this);
		dustCmdManager.registerCommand(new CommandAddMysteryDust());
		dustCmdManager.registerCommand(new CommandCheckMysteryDust());
		dustCmdManager.registerCommand(new CommandPayMysteryDust());
		dustCmdManager.registerCommand(new CommandRemoveMysteryDust());
		dustCmdManager.registerCommand(new CommandSetMysteryDust());

		// Cosmetics
		MainMenuType.checkEnabled();
		HatType.checkEnabled();
		ParticleType.checkEnabled();
		ParticleTypesType.checkEnabled();
		GadgetType.checkEnabled();
		PetType.checkEnabled();
		PetEntityType.checkEnabled();
		if (MorphAPI.isMorphsEnabled() && MorphAPI.isLibDisguisesHooked() && VersionManager.is1_9OrAbove()) {
			MorphType.checkEnabled();
		}
		BannerType.checkEnabled();
		EmoteType.checkEnabled();
		CloakType.checkEnabled();
		SuitType.checkEnabled();

		// registerListener(new GadgetListener());
		// new GadgetListener().register();

		// Cosmetics
		registerListener(new MainMenuManager());
		if (HatAPI.isHatsEnabled())
			registerListener(new HatManager());
		if (ParticleAPI.isParticlesEnabled()) {
			registerListener(new ParticleAPI());
			registerListener(new ParticleManager());
			// registerListener(new ParticleTypesManager());
		}
		if (SuitAPI.isSuitsEnabled()) {
			registerListener(new SuitManager());
			registerListener(new EquipmentManager());
		}
		if (GadgetAPI.isGadgetsEnabled())
			registerListener(new GadgetManager());
		if (PetAPI.isPetsEnabled()) {
			registerListener(new PetManager());
			registerListener(new PetEntityManager());
		}
		if (MorphAPI.isMorphsEnabled() && MorphAPI.isLibDisguisesHooked() && VersionManager.is1_9OrAbove()) {
			registerListener(new MorphManager());
		}
		if (BannerAPI.isBannersEnabled())
			registerListener(new BannerManager());
		if (EmoteAPI.isEmotesEnabled())
			registerListener(new EmoteManager());
		if (CloakAPI.isCloaksEnabled())
			registerListener(new CloakManager());

		/// registerListener(new AdminManager());//TODO
		// registerListener(new HatEditManager());
		// registerListener(new AdminSettingManager());

		// Mystery Box
		// registerListener(new MysteryBoxPreviewListener());
		// registerListener(new MysteryBoxPlaceListener());
		// registerListener(new MysteryBoxBreakListener());

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
		registerListener(new DevModeListener());

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
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_CONNECT_DATEBASE.getFormatMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void registerListener(Listener listener) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, getInstance());
	}

	public static NMSManager getNMSManager() {
		return nmsManager;
	}

	public static PlayerManager getCustomPlayer(Player player) {
		return placeHolders.getCustomPlayer(player);
	}

	public static Random random() {
		return random;
	}

	public static ArrayList<Entity> disableBlockDamage() {
		return disableBlockDamage;
	}

	public static ArrayList<Entity> disableFallDamage() {
		return disableFallDamage;
	}

	public static ArrayList<UUID> disableKick() {
		return disableKick;
	}

	public static GadgetsMenu getInstance() {
		return GadgetsMenu;
	}

	public String getPluginName() {
		return "GadgetsMenu";
	}
}