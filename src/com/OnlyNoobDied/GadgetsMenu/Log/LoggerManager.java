package com.OnlyNoobDied.GadgetsMenu.Log;

import java.util.logging.Level;

import org.bukkit.Bukkit;

public class LoggerManager {

	public static void consoleMessage(Object... messages) {
		if (messages.length == 0) {
			Bukkit.getServer().getLogger().log(Level.INFO, prefix() + "", new Throwable().getStackTrace());
		}
		for (Object object : messages) {
			Bukkit.getServer().getConsoleSender().sendMessage(prefix() + object.toString());
		}
	}

	public static void info(Object... messages) {
		if (messages.length == 0) {
			Bukkit.getServer().getLogger().log(Level.INFO, prefix() + "", new Throwable().getStackTrace());
		}
		for (Object object : messages) {
			Bukkit.getServer().getLogger().log(Level.INFO, prefix() + object.toString(),
					new Throwable().getStackTrace());
		}
	}

	public static void severe(Object... messages) {
		if (messages.length == 0) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, prefix() + "", new Throwable().getStackTrace());
		}
		for (Object object : messages) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, prefix() + object.toString(),
					new Throwable().getStackTrace());
		}
	}

	public static void warn(Object... messages) {
		if (messages.length == 0) {
			Bukkit.getServer().getLogger().log(Level.WARNING, prefix() + "", new Throwable().getStackTrace());
		}
		for (Object object : messages) {
			Bukkit.getServer().getLogger().log(Level.WARNING, prefix() + object.toString(),
					new Throwable().getStackTrace());
		}
	}

	private static String prefix() {
		return "[GadgetsMenu] ";
	}
}