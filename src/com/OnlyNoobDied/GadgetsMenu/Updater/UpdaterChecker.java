package com.OnlyNoobDied.GadgetsMenu.Updater;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdaterChecker {

	private String currentVersion;
	private String newVersion;
	private UpdateResult result = UpdateResult.FAIL_SPIGOT;

	public enum UpdateResult {
		NO_UPDATE, FAIL_SPIGOT, MINOR_UPDATE_AVAILABLE, NORMAL_UPDATE_AVAILABLE, BIG_UPDATE_AVAILABLE
	}

	public UpdaterChecker(JavaPlugin plugin) {
		String Resource_ID = "10885";
		String key = "key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + Resource_ID;

		currentVersion = plugin.getDescription().getVersion().replace("[^A-Za-z]", "");
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php")
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.getOutputStream().write(key.getBytes("UTF-8"));

			String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
			if (version.length() <= 7) {
				this.newVersion = version.replace("[^A-Za-z]", "");
				spigotCheckUpdate();
				return;
			}
		} catch (IOException e) {
			result = UpdateResult.FAIL_SPIGOT;
			return;
		}
	}

	private void spigotCheckUpdate() {
		String[] currentParts = this.currentVersion.split("\\.");
		String[] newParts = this.newVersion.split("\\.");
		if (Integer.parseInt(currentParts[0]) < Integer.parseInt(newParts[0])) {
			result = UpdateResult.BIG_UPDATE_AVAILABLE;
			return;
		} else if (Integer.parseInt(currentParts[0]) == Integer.parseInt(newParts[0])
				&& Integer.parseInt(currentParts[1]) < Integer.parseInt(newParts[1])) {
			result = UpdateResult.NORMAL_UPDATE_AVAILABLE;
			return;
		} else if (Integer.parseInt(currentParts[1]) == Integer.parseInt(newParts[1])
				&& Integer.parseInt(currentParts[2]) < Integer.parseInt(newParts[2])) {
			result = UpdateResult.MINOR_UPDATE_AVAILABLE;
			return;
		} else {
			result = UpdateResult.NO_UPDATE;
			return;
		}
	}

	public UpdateResult getResult() {
		return result;
	}

	public String getOldVersion() {
		return currentVersion;
	}

	public String getNewVersion() {
		return newVersion;
	}
}
