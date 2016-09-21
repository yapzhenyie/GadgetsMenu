package com.OnlyNoobDied.GadgetsMenu.Updater;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.OnlyNoobDied.GadgetsMenu.GadgetsMenu;
import com.OnlyNoobDied.GadgetsMenu.API.MainAPI;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.ChatUtil;
import com.OnlyNoobDied.GadgetsMenu.Utils.MessageType;

public class UpdaterManager {

	private static boolean updateAvailable = false;
	private static boolean uptodate = false;
	private static String message = "";

	// check update
	public static void checkUpdate(final CommandSender sender) {
		if (sender instanceof Player) {
			sender.sendMessage(MessageType.CHECKING_FOR_UPDATE.getFormatMessage());
		} else if (sender instanceof ConsoleCommandSender) {
			new LoggerManager().consoleMessage(
					ChatUtil.format(MessageType.CHECKING_FOR_UPDATE.getMessage().replace("{PREFIX}", "")));
		}
		final UpdaterChecker updater = new UpdaterChecker(GadgetsMenu.getInstance());
		final UpdaterChecker.UpdateResult result = updater.getResult();
		switch (result) {
		default:
		case FAIL_SPIGOT:
			updateAvailable = false;
			message = ChatUtil.format("&cFailed to check the latest version.");
			GadgetsMenu.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(GadgetsMenu.getInstance(),
					new Runnable() {
						public void run() {
							checkUpdate(sender);
						}
					}, 3600 * 20L);
			break;
		case NO_UPDATE:
			updateAvailable = false;
			uptodate = true;
			message = ChatUtil.format("&rNo update was found, you are running the latest version.");
			GadgetsMenu.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(GadgetsMenu.getInstance(),
					new Runnable() {
						public void run() {
							checkUpdate(sender);
						}
					}, (3600 * 8) * 20L);
			break;
		case MINOR_UPDATE_AVAILABLE: // Minor Update
			updateAvailable = true;
			message = ChatUtil.format("&rYou have an old version of the plugin. You are using &e"
					+ GadgetsMenu.getInstance().getDescription().getVersion() + "&r, available version &e"
					+ updater.getNewVersion() + "&r.  &b&l*MINOR UPDATE*");
			break;
		case NORMAL_UPDATE_AVAILABLE: // Normal Update
			updateAvailable = true;
			message = ChatUtil.format("&rYou have an old version of the plugin. You are using &e"
					+ GadgetsMenu.getInstance().getDescription().getVersion() + "&r, available version &e"
					+ updater.getNewVersion() + "&r.");
			break;
		case BIG_UPDATE_AVAILABLE: // Big Update
			updateAvailable = true;
			message = ChatUtil.format("&rYou have an old version of the plugin. You are using &e"
					+ GadgetsMenu.getInstance().getDescription().getVersion() + "&r, available version &e"
					+ updater.getNewVersion() + "&r.  &c&l*BIGUPDATE*");
			break;
		}
		if (sender instanceof Player) {
			sender.sendMessage(MainAPI.getPrefix() + message);
		} else if (sender instanceof ConsoleCommandSender) {
			new LoggerManager().consoleMessage(message);
		}
		if (updateAvailable) {
			if (sender instanceof Player) {
				sender.sendMessage(ChatUtil.format("&rGet latest version: http://bit.ly/GadgetsMenu"));
			} else if (sender instanceof ConsoleCommandSender) {
				new LoggerManager().consoleMessage(ChatUtil.format("&rGet latest version: http://bit.ly/GadgetsMenu"));
			}
		}
	}

	public static boolean isUpdateAvailable() {
		return updateAvailable;
	}

	public static boolean isUpToDate() {
		return uptodate;
	}

	public static String getMessage() {
		return message;
	}
}
