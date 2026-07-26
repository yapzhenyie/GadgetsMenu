package com.yapzhenyie.GadgetsMenu.api.event.blacklistedregion;

import com.yapzhenyie.GadgetsMenu.extension.worldguard.util.BlacklistedRegionType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ExitBlacklistedRegionEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private boolean isReverseWhitelist;
    private BlacklistedRegionType regionType;

    public ExitBlacklistedRegionEvent(Player player, boolean isReverseWhitelist, BlacklistedRegionType regionType) {
        this.player = player;
        this.isReverseWhitelist = isReverseWhitelist;
        this.regionType = regionType;
    }

    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    public BlacklistedRegionType getRegionType() {
        return this.regionType;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}