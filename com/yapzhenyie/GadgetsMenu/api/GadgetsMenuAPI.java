package com.yapzhenyie.GadgetsMenu.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.yapzhenyie.GadgetsMenu.GadgetsMenu;
import com.yapzhenyie.GadgetsMenu.player.OfflinePlayerManager;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;

public class GadgetsMenuAPI {

	public static PlayerManager getPlayerManager(Player player) {
		return GadgetsMenu.getPlayerManager(player);
	}

	public static OfflinePlayerManager getOfflinePlayerManager(UUID uuid) {
		return new OfflinePlayerManager(uuid);
	}

	public static void goBackToMainMenu(Player player) {
		GadgetsMenu.getPlayerManager(player).goBackToMainMenu();
	}

}
