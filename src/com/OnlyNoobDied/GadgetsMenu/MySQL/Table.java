package com.OnlyNoobDied.GadgetsMenu.MySQL;

import java.sql.Statement;

import com.OnlyNoobDied.GadgetsMenu.GadgetsMenu;
import com.OnlyNoobDied.GadgetsMenu.Log.LoggerManager;
import com.OnlyNoobDied.GadgetsMenu.Utils.MessageType;

public class Table {

	public static void createTable() {
		MySQLConnection mysql = new MySQLConnection();
		try {
			Statement localStatement = mysql.openConnection().createStatement();
			localStatement.executeUpdate("DROP TABLE IF EXISTS GadgetsMenu");
			localStatement.executeUpdate("CREATE TABLE IF NOT EXISTS " + GadgetsMenu.MySQLName
					+ "(id INT(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), UUID varchar(36) NOT NULL, Name VARCHAR(50) NOT NULL"
					+ ", Credits int DEFAULT 0 NOT NULL, PetName VARCHAR(50) NOT NULL"
					+ ", SelectedHat VARCHAR(50) DEFAULT 'none' not NULL, SelectedParticle VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedWardrobeHelmet VARCHAR(50) DEFAULT 'none' not NULL, SelectedWardrobeChestplate VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedWardrobeLeggings VARCHAR(50) DEFAULT 'none' not NULL, SelectedWardrobeBoots VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedDiscoArmorHelmet VARCHAR(50) DEFAULT 'none' not NULL, SelectedDiscoArmorChestplate VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedDiscoArmorLeggings VARCHAR(50) DEFAULT 'none' not NULL, SelectedDiscoArmorBoots VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedGadget VARCHAR(50) DEFAULT 'none' not NULL, SelectedPet VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedMorph VARCHAR(50) DEFAULT 'none' not NULL, SelectedBanner VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", SelectedEmote VARCHAR(50) DEFAULT 'none' not NULL" + ")");
		} catch (Exception e) {
			new LoggerManager().consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
	}

	//localStatement.executeUpdate("DROP TABLE IF EXISTS GadgetsMenu");
	
	// localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName + "
	// ADD SelectedWardrobe VARCHAR(50) DEFAULT 'none' not NULL");

	// localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName + "
	// ADD SelectedWardrobe VARCHAR(50) DEFAULT 'none' not NULL AFTER
	// 'PetName'");
}
