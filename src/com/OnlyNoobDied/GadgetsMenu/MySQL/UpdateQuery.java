package com.OnlyNoobDied.GadgetsMenu.MySQL;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.OnlyNoobDied.GadgetsMenu.GadgetsMenu;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Player.PlayerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.MessageType;

public class UpdateQuery {

	private UUID uuid;

	public UpdateQuery(UUID uuid) {
		this.uuid = uuid;
	}

	public void update(String key, String value) {
		try {
			new PlayerManager(this.uuid).registerToDatabase();
			Statement localStatement = GadgetsMenu.getInstance().getConnection().createStatement();
			localStatement.executeUpdate("UPDATE " + GadgetsMenu.MySQLName + " SET " + key + "= '" + value
					+ "' WHERE UUID='" + this.uuid + "'");
		} catch (SQLException e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
	}
}
