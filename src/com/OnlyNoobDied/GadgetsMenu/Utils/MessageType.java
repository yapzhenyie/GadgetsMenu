package com.OnlyNoobDied.GadgetsMenu.Utils;

import com.OnlyNoobDied.GadgetsMenu.API.MainAPI;
import com.OnlyNoobDied.GadgetsMenu.Configuration.FileManager;

public enum MessageType {
	
	BANNER_IS_DISABLED("{PREFIX}&cCosmetic banners is disabled!", "Banners-Is-Disabled"),
	CHECKING_FOR_UPDATE("{PREFIX}&rChecking for updates...", "Checking-For-Update"),
	CLOAK_IS_DISABLED("{PREFIX}&cCosmetic cloaks is disabled!", "Cloaks-Is-Disabled"),
	COLLIDED_GADGET("{PREFIX}&cThere is a {GADGET} &cactivated! Please try again later.", "Collided-Gadget"),
	COMING_SOON("{PREFIX}&cComing soon!", null),
	COOLDOWN("{PREFIX}&cYou must wait &b{COOLDOWN}s &cbefore using this!", "Cooldown"),
	DISABLED_WORLD("{PREFIX}&cYou don't have permission to do that on this world!", "World-Disabled"),
	//DISCO_ARMOR_IS_DISABLED("{PREFIX}&cCosmetic disco armor is disabled!", "DiscoArmor-Is-Disabled"),
	EMOTE_ACTIVATED("{PREFIX}&cEmote is already activate!", "Emote-Is-Activated"),
	EMOTE_IS_DISABLED("{PREFIX}&cCosmetic emotes is disabled!", "Emotes-Is-Disabled"),
	ERROR("{PREFIX}&cAn error occurred while executing this command!", "Error"),
	FAILED_TO_CONNECT_DATEBASE("&cFailed to connect to the database!", null),
	FAILED_TO_GET_DATA("&cAn error occurred while using database!", null),
	FAILED_TO_PURCHASE("&cFailed to get your purchase item!", "Failed-To-Purchase"),
	GADGET_ACTIVATED("{PREFIX}&c{GADGET} Gadget is already activate!", "Gadget-Is-Activated"),
	GADGETS_IS_DISABLED("{PREFIX}&cCosmetic gadgets is disabled!", "Gadgets-Is-Disabled"),
	GAVE_MENU("{PREFIX}&eYou've successfully gave a Menu to &b{PLAYER}&e.", "Gave-Menu"),
	HAT_IS_DISABLED("{PREFIX}&cCosmetic hats is disabled!", "Hats-Is-Disabled"),
	IS_NOT_MORPHED("&cYou're not currently morphed!", "Is-Not-Morphed"),
	LENGTH_TOO_LONG("{PREFIX}&cLength is too long! Please try it again.", "Length-Too-Long"),
	MORPH_IS_DISABLED("{PREFIX}&cCosmetic morphs is disabled!", "Morphs-Is-Disabled"),
	NO_PERMISSION("{PREFIX}&cYou don't have the required permission {PERMISSION}!", "No-Permission"),
	NO_PET_SPAWNED("{PREFIX}&cYou don't currently have a pet spawned!", "No-Pet-Spawned"),
	NO_PLAYER_NEARBY("{PREFIX}&cThere aren't any players nearby!", "No-Player-Nearby"),
	NOT_ALLOWED_FROM_CONSOLE("{PREFIX}&cYou cannot use this command from console!", "Not-Allowed-From-Console"),
	//NOT_ENOUGH_CREDITS("{PREFIX}&cSorry, you don't have enough Credits to buy this item!", "Not-Enough-Credits"),
	NOT_ENOUGH_SPACE("{PREFIX}&cNot enough space around you to use this gadget!", "Not-Enough-Space"),
	NOT_ON_GROUND("{PREFIX}&cYou must be on the ground to use this!", "Not-On-Ground"),
	PARTICLE_IS_DISABLED("{PREFIX}&cCosmetic particles is disabled!", "Particles-Is-Disabled"),
	//PAY_CREDITS_TO_YOURSELF("{PREFIX}&cYou can't pay credits to yourself!", "Pay-Credits-To-Self"),
	PET_IS_DISABLED("{PREFIX}&cCosmetic pets is disabled!", "Pets-Is-Disabled"),
	//PLAYER_ADDED_CREDITS("{PREFIX}&eYou've successfully added &b{CREDITS} &ecredits to &b{PLAYER}&e.", "Added-Credits"),
	//PLAYER_CHECK_CREDITS("&e{PLAYER} Credits: &b{CREDITS}", "Check-Credits"),
	PLAYER_NOT_FOUND("{PREFIX}&cPlayer not found!", "Player-Not-Found"),
	//PLAYER_RECEIVED_CREDITS("{PREFIX}&eYou've received &b{CREDITS} &ecredits from &b{PLAYER}&e.", "Received-Credits"),
	//PLAYER_REMOVED_CREDITS("{PREFIX}&eYou've removed &b{CREDITS} &ecredits.", "Removed-Credits"),
	//PLAYER_SENT_CREDITS("{PREFIX}&eYou've sent &b{CREDITS} &ecredits to &b{PLAYER}&e.", "Sent-Credits"),
	//PLAYER_SET_CREDITS("{PREFIX}&eYou've set &b{CREDITS} &ecredits for &b{PLAYER}&e.", "Set-Credits"),
	PURCHASE_IS_DISABLED("{PREFIX}&cPurchase feature is disabled!", "Purchase-Is-Disabled"),
	RECEIVED_MENU("{PREFIX}&eAdded a Menu into your inventory!", "Received-Menu"),
	RELOADED_CONFIG("{PREFIX}&3Successful reload configuration.", "Reloaded-Configuration"),
	RELOADING_CONFIG("{PREFIX}&3Reloading configuration...", "Reloading-Configuration"),
	//REMOVED_CREDITS_FROM_PLAYER("{PREFIX}&b{CREDITS} &ecredits has been removed from your account.", "Removed-Credits-From-Player"),
	RENAME_PET("&eSet your pet's name to &a{NAME}&e.", "Rename-Pet"),
	REQUIRED_LIBSDISGUISES_AND_PROTOCOLLIB("{PREFIX}&cRequired LibsDisguises and ProtocolLib.", "Required-LibsDisguises-And-ProtocolLib"),
	REQUIRED_NUMBER_FORMAT("{PREFIX}&cA number is required!", "Required-Number-Format"),
	REQUIRED_POSITIVE_NUMBER("{PREFIX}&cA positive number is required!", "Required-Positive-Number"),
	REQUIRED_1_9_VERSION("{PREFIX}&cRequired 1.9+ spigot to enable Morphs!", null),
	RESET_BANNER("&eReset your Banner.", "Reset-Banner"),
	RESET_CLOAK("&eReset your Cloak.", "Reset-Cloak"),
	RESET_COSMETICS("&eReset active Cosmetics.", "Reset-Cosmetics"),
	//RESET_DISCOARMOR("&eReset your DiscoArmor.", "Reset-DiscoArmor"),
	RESET_EMOTE("&eReset your Emote.", "Reset-Emote"),
	RESET_GADGET("&eReset your Gadget.", "Reset-Gadget"),
	RESET_HAT("&eReset your Hat.", "Reset-Hat"),
	RESET_MORPH("&eReset your Morph.", "Reset-Morph"),
	RESET_PARTICLE("&eReset your Particle.", "Reset-Particle"),
	RESET_PET("&eReset your Pet.", "Reset-Pet"),
	RESET_SUIT("&eReset your Suit.", "Reset-Suit"),
	//RESET_WARDROBE("&eReset your Wardrobe.", "Reset-Wardrobe"),
	ROCKET_COUNTDOWN("&c&lROCKET LAUNCH IN {TIMER} {SECOND}!", "Rocket-Countdown"),
	ROCKET_LIFT_OFF("&a&lWE HAVE LIFT OFF!", "Rocket-Lift-Off"),
	SELECT_BANNER("&eYou selected {BANNER}&e.", "Select-Banner"),
	SELECT_CLOAK("&eYou selected {CLOAK}&e.", "Select-Cloak"),
	SELECT_EMOTE("&eYou selected {EMOTE}&e.", "Select-Emote"),
	SELECT_GADGET("&eYou selected {GADGET}&e.", "Select-Gadget"),
	SELECT_HAT("&eYou selected {HAT}&e.", "Select-Hat"),
	SELECT_MORPH("&eYou are now morphed as a {MORPH}&e.", "Select-Morph"),
	SELECT_PARTICLE("&eYou selected {PARTICLE} &eparticle.", "Select-Particle"),
	SELECT_PET("&eSpawned your {PET} &epet.", "Select-Pet"),
	SELECT_SUIT("&eYou selected {SUIT}&e.", "Select-Suit"),
	//SET_CREDITS_FROM_PLAYER("{PREFIX}&eYour credits have been set to &b{CREDITS}&e.", null),//TODO
	SUIT_IS_DISABLED("{PREFIX}&cCosmetic suits is disabled!", "Suits-Is-Disabled"),
	SUPPORTED_1_8_HIGHER("Please use 1.8/1.9/1.10 spigot to run GadgetsMenu plugin!", null),//TODO
	TARGET_A_BLOCK("{PREFIX}&cYou must target a block!", "Target-A-Block"),
	//WARDROBE_IS_DISABLED("{PREFIX}&cCosmetic wardrobe is disabled!", "Wardrobe-Is-Disabled"),
	
	ITEM_UNPURCHASABLE("{PREFIX}&cYou can't purchase this item!", "Item-Unpurchasable"),//TODO
	RESET_COMPLETED("&ereset completed.", null),
	MORPHS_SELF_VIEW("{PREFIX}&eYou set morphs self view to {STATUS}&e.", "Morphs-Self-View"),
	PURCHASED_ITEM("{PREFIX}&eYou purchased {ITEM}&e.", "Purchased-Item"),
	CONNECTED_TO_DATABASE("Successful connect to database.", null),
	
	PLAYER_ADDED_MYSTERYDUST("{PREFIX}&eYou've successfully added &b{MYSTERY_DUST} &emystery dust to &b{PLAYER}&e.", "Added-MysteryDust"),
	PLAYER_CHECK_MYSTERYDUST("&e{PLAYER} Mystery Dust: &b{MYSTERY_DUST}", "Check-MysteryDust"),
	PLAYER_RECEIVED_MYSTERYDUST("{PREFIX}&eYou've received &b{MYSTERY_DUST} &emystery dust from &b{PLAYER}&e.", "Received-MysteryDust"),
	PLAYER_REMOVED_MYSTERYDUST("{PREFIX}&eYou've removed &b{MYSTERY_DUST} &emystery dust.", "Removed-MysteryDust"),
	PLAYER_SENT_MYSTERYDUST("{PREFIX}&eYou've sent &b{MYSTERY_DUST} &emystery dust to &b{PLAYER}&e.", "Sent-MysteryDust"),
	PLAYER_SET_MYSTERYDUST("{PREFIX}&eYou've set &b{MYSTERY_DUST} &emystery dust for &b{PLAYER}&e.", "Set-MysteryDust"),
	REMOVED_MYSTERYDUST_FROM_PLAYER("{PREFIX}&b{MYSTERY_DUST} &emystery dust has been removed from your account.", "Removed-MysteryDust-From-Player"),
	SET_MYSTERYDUST_FROM_PLAYER("{PREFIX}&eYour mystery dust have been set to &b{MYSTERY_DUST}&e.", null),//TODO	
	NOT_ENOUGH_MYSTERYDUST("{PREFIX}&cSorry, you don't have enough mystery dust to buy this item!", "Not-Enough-MysteryDust"),
	PAY_MYSTERYDUST_TO_SELF("{PREFIX}&cYou can't pay mystery dust to yourself!", "Pay-Credits-To-Self"),
	
	SECOND("Second", "Second"),
	SECONDS("Seconds", "Seconds"),
	;

	private String message;
	private String fileMessage;
	
	private MessageType(String message, String fileMessage) {
		this.message = message;
		this.fileMessage = fileMessage;
		if (this.getFileMessage() != null) {
			if (FileManager.getMessagesFile().get(this.getFileMessage()) == null) {
				FileManager.getMessagesFile().addDefault(this.getFileMessage(), this.getMessage());
			}
		}
	}

	public String getMessage() {
		return this.message;
	}

	public String getFileMessage() {
		return this.fileMessage;
	}

	public String getFormatMessage() {
		if (this.getFileMessage() == null) {
			return ChatUtil.format(this.getMessage().replace("{PREFIX}", MainAPI.getPrefix()));
		}
		if (FileManager.getMessagesFile().getString(this.getFileMessage()) == null) {
			FileManager.getMessagesFile().addDefault(this.getFileMessage(), this.getMessage());
			return ChatUtil.format(this.getMessage().replace("{PREFIX}", MainAPI.getPrefix()));
		}
		return ChatUtil.format(FileManager.getMessagesFile().getString(this.getFileMessage()).replace("{PREFIX}",
				MainAPI.getPrefix()));
	}

	@Override
	public String toString() {
		return this.toString();
	}
}
