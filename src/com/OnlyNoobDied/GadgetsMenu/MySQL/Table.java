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
					+ ", Mystery_Dust int DEFAULT 0 NOT NULL, Pet_Name VARCHAR(50) NOT NULL, Morphs_Self_View VARCHAR(5) DEFAULT 'true' NOT NULL"
					+ ", Bypass_Cooldown VARCHAR(5) DEFAULT 'none' NOT NULL, Selected_Hat VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", Selected_Particle VARCHAR(50) DEFAULT 'none' not NULL, Selected_Suit_Helmet VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", Selected_Suit_Chestplate VARCHAR(50) DEFAULT 'none' not NULL, Selected_Suit_Leggings VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", Selected_Suit_Boots VARCHAR(50) DEFAULT 'none' not NULL, Selected_Gadget VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", Selected_Pet VARCHAR(50) DEFAULT 'none' not NULL, Selected_Morph VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", Selected_Banner VARCHAR(50) DEFAULT 'none' not NULL, Selected_Emote VARCHAR(50) DEFAULT 'none' not NULL"
					+ ", Selected_Cloak VARCHAR(50) DEFAULT 'none' not NULL)");
			updateColumn();
		} catch (Exception e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
	}

	public static void createMysteryBoxTable() {
		MySQLConnection mysql = new MySQLConnection();
		try {
			Statement localStatement = mysql.openConnection().createStatement();
			localStatement.executeUpdate("CREATE TABLE IF NOT EXISTS " + "GadgetsMenu_MysteryBox"
					+ "(id INT(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), UUID varchar(36) NOT NULL, Name VARCHAR(50) NOT NULL"
					+ ", Quality int DEFAULT 1 NOT NULL, Contains VARCHAR(50) NOT NULL, Expire_Day LONG NOT NULL"
					+ ")");// TODO change LONG to datetime
		} catch (Exception e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
	}

	private static void updateColumn() {
		MySQLConnection mysql = new MySQLConnection();
		try {
			Statement localStatement = mysql.openConnection().createStatement();
			localStatement.executeUpdate("DROP TABLE IF EXISTS GadgetsMenu");
			localStatement.executeUpdate(
					"ALTER TABLE " + GadgetsMenu.MySQLName + " DROP COLUMN IF EXISTS SelectedDiscoArmorHelmet");
			localStatement.executeUpdate(
					"ALTER TABLE " + GadgetsMenu.MySQLName + " DROP COLUMN IF EXISTS SelectedDiscoArmorChestplate");
			localStatement.executeUpdate(
					"ALTER TABLE " + GadgetsMenu.MySQLName + " DROP COLUMN IF EXISTS SelectedDiscoArmorLeggings");
			localStatement.executeUpdate(
					"ALTER TABLE " + GadgetsMenu.MySQLName + " DROP COLUMN IF EXISTS SelectedDiscoArmorBoots");

			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS Credits Mystery_Dust int DEFAULT 0 NOT NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS PetName Pet_Name VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedHat Selected_Hat VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedParticle Selected_Particle VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedWardrobeHelmet Selected_SuitHelmet VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedWardrobeChestplate Selected_SuitChestplate VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedWardrobeLeggings Selected_SuitLeggings VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedWardrobeBoots Selected_SuitBoots VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedGadget Selected_Gadget VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedPet Selected_Pet VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedMorph Selected_Morph VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedBanner Selected_Banner VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " CHANGE IF EXISTS SelectedEmote Selected_Emote VARCHAR(50) DEFAULT 'none' not NULL");

			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " ADD IF NOT EXISTS Selected_Cloak VARCHAR(50) DEFAULT 'none' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " ADD IF NOT EXISTS Morphs_Self_View VARCHAR(5) DEFAULT 'true' not NULL");
			localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName
					+ " ADD IF NOT EXISTS Bypass_Cooldown VARCHAR(5) DEFAULT 'none' not NULL");
		} catch (Exception e) {
			new LoggerManager();
			LoggerManager.consoleMessage(MessageType.FAILED_TO_GET_DATA.getFormatMessage());
			e.printStackTrace();
		}
	}

	// localStatement.executeUpdate("DROP TABLE IF EXISTS GadgetsMenu");

	// localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName + "
	// ADD SelectedWardrobe VARCHAR(50) DEFAULT 'none' not NULL");

	// localStatement.executeUpdate("ALTER TABLE " + GadgetsMenu.MySQLName + "
	// ADD SelectedWardrobe VARCHAR(50) DEFAULT 'none' not NULL AFTER
	// 'PetName'");
}
