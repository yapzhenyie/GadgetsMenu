package com.OnlyNoobDied.GadgetsMenu.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.OnlyNoobDied.GadgetsMenu.Configuration.FileManager;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.MessageType;

public class MySQLConnection {

	private final String hostname;
	private final String username;
	private final String database;
	private final String password;
	private final String port;

	private Connection connection;

	public MySQLConnection() {
		this.hostname = FileManager.getConfigFile().getString("Player-Data.MySQL.hostname");
		this.username = FileManager.getConfigFile().getString("Player-Data.MySQL.username");
		this.database = FileManager.getConfigFile().getString("Player-Data.MySQL.database");
		this.password = FileManager.getConfigFile().getString("Player-Data.MySQL.password");
		this.port = FileManager.getConfigFile().getString("Player-Data.MySQL.port");
		this.connection = null;
	}

	public Connection openConnection() {
		if (checkConnection()) {
			return this.connection;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.username,
					this.password);
		} catch (SQLException | ClassNotFoundException e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
		return this.connection;
	}

	public boolean checkConnection() {
		try {
			return (this.connection != null) && (!this.connection.isClosed());
		} catch (SQLException e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_CONNECT_DATEBASE.getFormatMessage());
			e.printStackTrace();
		}
		return false;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public boolean closeConnection() {
		if (this.connection == null) {
			return false;
		}
		try {
			this.connection.close();
		} catch (SQLException e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_CONNECT_DATEBASE.getFormatMessage());
			e.printStackTrace();
			;
		}
		return true;
	}
}