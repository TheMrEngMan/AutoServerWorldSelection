package com.mrengman.autoserverworldselection.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;

@Config(name = "autoserverworldselection")
public class AutoServerWorldSelectionConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip()
    public ArrayList<String> enabledServerAddresses = new ArrayList<>();

}