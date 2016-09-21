package com.OnlyNoobDied.GadgetsMenu.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.OnlyNoobDied.GadgetsMenu.GadgetsMenu;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Player.PlayerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.MessageType;

public class SelectQuery {

	private UUID uuid;

	public SelectQuery(UUID uuid) {
		this.uuid = uuid;
	}

	public String getData(String key) {
		String data = "";
		try {
			new PlayerManager(this.uuid).registerToDatabase();
			Statement localStatement = GadgetsMenu.getInstance().getConnection().createStatement();
			ResultSet localResultSet = localStatement
					.executeQuery("SELECT * FROM " + GadgetsMenu.MySQLName + " WHERE UUID= '" + this.uuid + "'");
			if (localResultSet.next()) {
				data = localResultSet.getString(key);
			} else {
				new PlayerManager(this.uuid).registerToDatabase();
			}
		} catch (SQLException e) {
			new LoggerManager().consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
		return data;
	}
}
