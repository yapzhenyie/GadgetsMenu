package com.OnlyNoobDied.GadgetsMenu.Metrics;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.OnlyNoobDied.GadgetsMenu.GadgetsMenu;
import com.OnlyNoobDied.GadgetsMenu.API.*;
import com.OnlyNoobDied.GadgetsMenu.Metrics.Metrics.Plotter;

public class MetricsStarter implements Runnable {

	private final Plugin plugin;
	private transient Boolean start;

	public MetricsStarter(final GadgetsMenu plugin) {
		this.plugin = plugin;
		try {

			final Metrics metrics = new Metrics(this.plugin);

			if (!metrics.isOptOut()) {
				start = true;
				return;
			}
		} catch (Exception ex) {
			Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			Metrics metrics = new Metrics(this.plugin);

			final Metrics.Graph enabledGraph = metrics.createGraph("Enabled_Features");
			enabledGraph.addPlotter(new SimplePlotter("Total"));
			if (HatAPI.isHatsEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Hats"));
			}
			if (ParticleAPI.isParticlesEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Particles"));
			}
			if (WardrobeAPI.isWardrobeEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Wardrobe"));
			}
			if (DiscoArmorAPI.isDiscoArmorEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("DiscoArmor"));
			}
			if (GadgetAPI.isGadgetsEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Gadgets"));
			}
			if (PetAPI.isPetsEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Pets"));
			}
			if (MorphAPI.isMorphsEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Morphs"));
			}
			if (BannerAPI.isBannersEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Banners"));
			}
			if (EmoteAPI.isEmotesEnabled()) {
				enabledGraph.addPlotter(new SimplePlotter("Emotes"));
			}
			final Metrics.Graph database = metrics.createGraph("Database");
			database.addPlotter(new SimplePlotter("Total"));
			if (MainAPI.isDatabaseEnabled()) {
				database.addPlotter(new SimplePlotter("Enabled"));
			} else {
				database.addPlotter(new SimplePlotter("Disabled"));
			}
			metrics.start();
		} catch (Exception ex) {
			Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
		}
	}

	public Boolean getStart() {
		return start;
	}

	private class SimplePlotter extends Plotter {
		public SimplePlotter(final String name) {
			super(name);
		}

		@Override
		public int getValue() {
			return 1;
		}
	}
}
