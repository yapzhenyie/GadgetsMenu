package com.OnlyNoobDied.GadgetsMenu.Updater;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class UpdaterChecker {

	private String WRITE_STRING;
	public String newVersion;
	public String currentVersion;
	private UpdaterChecker.UpdateResult result = UpdaterChecker.UpdateResult.FAIL_SPIGOT;
	private HttpURLConnection connection;

	public enum UpdateResult {
		NO_UPDATE, FAIL_SPIGOT, MINOR_UPDATE_AVAILABLE, NORMAL_UPDATE_AVAILABLE, BIG_UPDATE_AVAILABLE
	}

	public UpdaterChecker(JavaPlugin plugin) {
		String RESOURCE_ID = "10885";
		currentVersion = plugin.getDescription().getVersion();
		try {
			String QUERY = "/api/general.php";
			String HOST = "http://www.spigotmc.org";
			connection = (HttpURLConnection) new URL(HOST + QUERY).openConnection();
		} catch (IOException e) {
			result = UpdateResult.FAIL_SPIGOT;
			return;
		}
		String API_KEY = "98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4";
		WRITE_STRING = "key=" + API_KEY + "&resource=" + RESOURCE_ID;
		runSpigot();
		currentVersion = currentVersion.replace("[^A-Za-z]", "").replace("|", "");
	}

	private void runSpigot() {
		connection.setDoOutput(true);
		try {
			String REQUEST_METHOD = "POST";
			connection.setRequestMethod(REQUEST_METHOD);
			connection.getOutputStream().write(WRITE_STRING.getBytes("UTF-8"));
		} catch (ProtocolException e1) {
			result = UpdateResult.FAIL_SPIGOT;
		} catch (UnsupportedEncodingException e) {
			result = UpdateResult.FAIL_SPIGOT;
		} catch (IOException e) {
			result = UpdateResult.FAIL_SPIGOT;
		}

		String version;
		try {
			version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
		} catch (Exception e) {
			result = UpdateResult.FAIL_SPIGOT;
			return;
		}
		if (version.length() <= 7) {
			this.newVersion = version.replace("[^A-Za-z]", "").replace("|", "");
			spigotCheckUpdate();
			return;
		}
		result = UpdateResult.FAIL_SPIGOT;
	}

	private void spigotCheckUpdate() {
		String[] currentParts = this.currentVersion.split("\\.");
		String[] newParts = this.newVersion.split("\\.");
		if (Integer.parseInt(currentParts[0]) < Integer.parseInt(newParts[0])) {
			result = UpdateResult.BIG_UPDATE_AVAILABLE;
			return;
		} else if (Integer.parseInt(currentParts[0]) == Integer.parseInt(newParts[0])
				&& Integer.parseInt(currentParts[1]) < Integer.parseInt(newParts[1])
				&& Integer.parseInt(currentParts[2]) == Integer.parseInt(newParts[2])) {
			result = UpdateResult.NORMAL_UPDATE_AVAILABLE;
			return;
		} else if (Integer.parseInt(currentParts[1]) == Integer.parseInt(newParts[1])
				&& Integer.parseInt(currentParts[2]) < Integer.parseInt(newParts[2])) {
			result = UpdateResult.MINOR_UPDATE_AVAILABLE;
			return;
		} else if (Integer.parseInt(currentParts[0]) == Integer.parseInt(newParts[0])
				&& Integer.parseInt(currentParts[1]) == Integer.parseInt(newParts[1])
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

	public String getVersion() {
		return newVersion;
	}
}
