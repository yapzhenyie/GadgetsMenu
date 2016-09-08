package com.OnlyNoobDied.GadgetsMenu.Updater;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.OnlyNoobDied.GadgetsMenu.GadgetsMenu;
import com.OnlyNoobDied.GadgetsMenu.API.MainAPI;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.ChatUtil;
import com.OnlyNoobDied.GadgetsMenu.Utils.ClickableText;
import com.OnlyNoobDied.GadgetsMenu.Utils.MessageType;

public class UpdaterManager {

	private static boolean updateAvailable = false;
	private static String updateMessage = "";

	// check update
	public static void checkUpdate(final ConsoleCommandSender console) {
		new LoggerManager().consoleMessage(ChatUtil.format(MessageType.CHECKING_FOR_UPDATE.getMessage().replace("{PREFIX}", "")));
		final UpdaterChecker updater = new UpdaterChecker(GadgetsMenu.getInstance());
		final UpdaterChecker.UpdateResult result = updater.getResult();
		switch (result) {
		default:
		case FAIL_SPIGOT:
			updateAvailable = false;
			updateMessage = ChatUtil.format("&cFailed to check GadgetMenu plugin latest version.");
			GadgetsMenu.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(GadgetsMenu.getInstance(),
					new Runnable() {
						public void run() {
							checkUpdate(console);
						}
					}, 3600 * 20L);
			break;
		case NO_UPDATE:
			updateAvailable = false;
			updateMessage = ChatUtil.format("&rNo update was found, you are running the latest version.");
			GadgetsMenu.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(GadgetsMenu.getInstance(),
					new Runnable() {
						public void run() {
							checkUpdate(console);
						}
					}, (3600 * 8) * 20L);
			break;
		case MINOR_UPDATE_AVAILABLE: // Minor Update
			updateAvailable = true;
			updateMessage = ChatUtil.format("&rYou have an old version of the plugin. You are using &e"
					+ GadgetsMenu.getInstance().getDescription().getVersion() + "&r, available version &e"
					+ updater.getVersion() + "&r.  &b&l*MINOR UPDATE*\n&rGet new version: http://bit.ly/GadgetsMenu");
			break;
		case NORMAL_UPDATE_AVAILABLE: // Normal Update
			updateAvailable = true;
			updateMessage = ChatUtil.format("&rYou have an old version of the plugin. You are using &e"
					+ GadgetsMenu.getInstance().getDescription().getVersion() + "&r, available version &e"
					+ updater.getVersion() + "&r.\n&rGet new version: http://bit.ly/GadgetsMenu");
			break;
		case BIG_UPDATE_AVAILABLE: // Big Update
			updateAvailable = true;
			updateMessage = ChatUtil.format("&rYou have an old version of the plugin. You are using &e"
					+ GadgetsMenu.getInstance().getDescription().getVersion() + "&r, available version &e"
					+ updater.getVersion() + "&r.  &c&l*BIGUPDATE*\n&rGet new version: http://bit.ly/GadgetsMenu");
			break;
		}
		new LoggerManager().consoleMessage(updateMessage);
	}

	public static void checkUpdatePlayer(final Player p) {
		p.sendMessage(MessageType.CHECKING_FOR_UPDATE.getFormatMessage());
		final UpdaterChecker updater = new UpdaterChecker(GadgetsMenu.getInstance());
		final UpdaterChecker.UpdateResult result = updater.getResult();
		switch (result) {
		default:
		case FAIL_SPIGOT:
			updateAvailable = false;
			updateMessage = ChatUtil
					.format(MainAPI.getPrefix() + "&cFailed to check GadgetMenu plugin latest version.");
			break;
		case NO_UPDATE:
			updateAvailable = false;
			updateMessage = ChatUtil
					.format(MainAPI.getPrefix() + "&9No update was found, you are running the latest version.");
			break;
		case MINOR_UPDATE_AVAILABLE:
			updateAvailable = true;
			updateMessage = ChatUtil
					.format(MainAPI.getPrefix() + "&9You have an old version of the plugin. You are using &b"
							+ GadgetsMenu.getInstance().getDescription().getVersion() + "&9, available version &b"
							+ updater.getVersion() + "&9.  &b&l*MINOR UPDATE*");
			break;
		case NORMAL_UPDATE_AVAILABLE: // Normal Update
			updateAvailable = true;
			updateMessage = ChatUtil
					.format(MainAPI.getPrefix() + "&9You have an old version of the plugin. You are using &b"
							+ GadgetsMenu.getInstance().getDescription().getVersion() + "&9, available version &b"
							+ updater.getVersion() + "&9.");
			break;
		case BIG_UPDATE_AVAILABLE: // Big Update
			updateAvailable = true;
			updateMessage = ChatUtil
					.format(MainAPI.getPrefix() + "&9You have an old version of the plugin. You are using &b"
							+ GadgetsMenu.getInstance().getDescription().getVersion() + "&9, available version &b"
							+ updater.getVersion() + "&9.  &c&l*BIG UPDATE*");
			break;
		}
		p.sendMessage(updateMessage);
		if (updateAvailable) {
			ClickableText.send(p, "&rGet latest version: ", "&eGadgetsMenu &f(Click-Here)",
					"&cClick here to update your GadgetsMenu plugin!", "http://bit.ly/GadgetsMenu");
		}
	}

	public static boolean isUpdateAvailable() {
		return updateAvailable;
	}

	public static String getUpdateMessage() {
		return updateMessage;
	}
}
