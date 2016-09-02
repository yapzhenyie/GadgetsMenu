package com.OnlyNoobDied.GadgetsMenu;

import java.io.File;

import com.OnlyNoobDied.GadgetsMenu.Configuration.FileManager;

public class Version {

	// private static FileManager gadgetsmenu = FileManager.getMainMenuFile();
	private static FileManager config = FileManager.getConfigFile();
	// private static FileManager hats = FileManager.getHatsFile();
	// private static FileManager particles = FileManager.getParticlesFile();
	// private static FileManager discoarmor = FileManager.getDiscoArmorFile();
	// private static FileManager gadgets = FileManager.getGadgetsFile();
	// private static FileManager pets = FileManager.getPetsFile();

	public static void checkConfigVersion() {
		configVersion();
	}

	private static void configVersion() {
		if (config.getInt("Config Version") == 4) {
			File fconfig = new File(GadgetsMenu.getInstance().getDataFolder(), "config.yml");
			File fgadgetsmenu = new File(GadgetsMenu.getInstance().getDataFolder(), "GadgetsMenu.yml");
			File fhats = new File(GadgetsMenu.getInstance().getDataFolder(), "Hats.yml");
			File fparticles = new File(GadgetsMenu.getInstance().getDataFolder(), "Particles.yml");
			File fwardrobe = new File(GadgetsMenu.getInstance().getDataFolder(), "Wardrobe.yml");
			File fdiscoarmor = new File(GadgetsMenu.getInstance().getDataFolder(), "DiscoArmor.yml");
			File ftags = new File(GadgetsMenu.getInstance().getDataFolder(), "Tags.yml");
			File fgadgets = new File(GadgetsMenu.getInstance().getDataFolder(), "Gadgets.yml");
			File fpets = new File(GadgetsMenu.getInstance().getDataFolder(), "Pets.yml");
			if (fconfig.exists())
				fconfig.delete();
			if (fgadgetsmenu.exists())
				fgadgetsmenu.delete();
			if (fhats.exists())
				fhats.delete();
			if (fparticles.exists())
				fparticles.delete();
			if (fwardrobe.exists())
				fwardrobe.delete();
			if (fdiscoarmor.exists())
				fdiscoarmor.delete();
			if (ftags.exists())
				ftags.delete();
			if (fgadgets.exists())
				fgadgets.delete();
			if (fpets.exists())
				fpets.delete();
		} else if (config.getInt("Config Version") == 5) {
			File fgadgetsmenu = new File(GadgetsMenu.getInstance().getDataFolder(), "GadgetsMenu.yml");
			File fhats = new File(GadgetsMenu.getInstance().getDataFolder(), "Hats.yml");
			File fparticles = new File(GadgetsMenu.getInstance().getDataFolder(), "Particles.yml");
			File fwardrobe = new File(GadgetsMenu.getInstance().getDataFolder(), "Wardrobe.yml");
			File fdiscoarmor = new File(GadgetsMenu.getInstance().getDataFolder(), "DiscoArmor.yml");
			File ftags = new File(GadgetsMenu.getInstance().getDataFolder(), "Tags.yml");
			File fgadgets = new File(GadgetsMenu.getInstance().getDataFolder(), "Gadgets.yml");
			File fpets = new File(GadgetsMenu.getInstance().getDataFolder(), "Pets.yml");
			if (fgadgetsmenu.exists())
				fgadgetsmenu.delete();
			if (fhats.exists())
				fhats.delete();
			if (fparticles.exists())
				fparticles.delete();
			if (fwardrobe.exists())
				fwardrobe.delete();
			if (fdiscoarmor.exists())
				fdiscoarmor.delete();
			if (ftags.exists())
				ftags.delete();
			if (fgadgets.exists())
				fgadgets.delete();
			if (fpets.exists())
				fpets.delete();
		} else if (config.getInt("Config Version") == 6) {
			File fgadgetsmenu = new File(GadgetsMenu.getInstance().getDataFolder(), "MainMenu.yml");
			File fhats = new File(GadgetsMenu.getInstance().getDataFolder(), "Hats.yml");
			File fparticles = new File(GadgetsMenu.getInstance().getDataFolder(), "Particles.yml");
			File fdiscoarmor = new File(GadgetsMenu.getInstance().getDataFolder(), "DiscoArmor.yml");
			File fgadgets = new File(GadgetsMenu.getInstance().getDataFolder(), "Gadgets.yml");
			File fpets = new File(GadgetsMenu.getInstance().getDataFolder(), "Pets.yml");
			File fmorphs = new File(GadgetsMenu.getInstance().getDataFolder(), "Morphs.yml");
			File fbanners = new File(GadgetsMenu.getInstance().getDataFolder(), "Banners.yml");
			if (fgadgetsmenu.exists())
				fgadgetsmenu.delete();
			if (fhats.exists())
				fhats.delete();
			if (fparticles.exists())
				fparticles.delete();
			if (fdiscoarmor.exists())
				fdiscoarmor.delete();
			if (fgadgets.exists())
				fgadgets.delete();
			if (fpets.exists())
				fpets.delete();
			if (fmorphs.exists())
				fmorphs.delete();
			if (fbanners.exists())
				fbanners.delete();
			for (File f : GadgetsMenu.getInstance().getDataFolder().listFiles()) {
				f.setWritable(true);
				f.delete();
			}
			for (File f : new File(GadgetsMenu.getInstance().getDataFolder(), "/userdata").listFiles()) {
				f.setWritable(true);
				f.delete();
			}
			FileManager.getConfigFile().file.delete();
			GadgetsMenu.getInstance().getDataFolder().delete();
			config.set("Config Version", 7);
		}
	}

}
