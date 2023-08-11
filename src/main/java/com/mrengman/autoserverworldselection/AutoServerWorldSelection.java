package com.mrengman.autoserverworldselection;

import com.mrengman.autoserverworldselection.config.AutoServerWorldSelectionConfig;
import com.mrengman.autoserverworldselection.config.EnabledServerAddressesGuiProvider;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.event.ConfigSerializeEvent;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import java.util.Objects;

public class AutoServerWorldSelection implements ClientModInitializer {

    public static boolean enabled = false;
    public static AutoServerWorldSelectionConfig config;
    public static String currentServerIP;

    @Override
    public void onInitializeClient() {

        loadConfig();

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (!client.isIntegratedServerRunning()) {
                try {
                    currentServerIP = Objects.requireNonNull(client.getCurrentServerEntry()).address;
                } catch (NullPointerException e) {
                    enabled = false;
                }
                enabled = config.enabledServerAddresses.contains(currentServerIP);
            }
            else {
                enabled = false;
            }

        });

    }

    public void loadConfig() {
        AutoConfig.register(AutoServerWorldSelectionConfig.class, GsonConfigSerializer::new);
        var guiRegistry = AutoConfig.getGuiRegistry(AutoServerWorldSelectionConfig.class);
        guiRegistry.registerPredicateProvider(
                new EnabledServerAddressesGuiProvider(),
                field -> field.getName().equals("enabledServerAddresses")
        );
        AutoConfig.getConfigHolder(AutoServerWorldSelectionConfig.class).registerSaveListener(onConfigSaved());
        config = AutoConfig.getConfigHolder(AutoServerWorldSelectionConfig.class).getConfig();
    }

    public ConfigSerializeEvent.Save<AutoServerWorldSelectionConfig> onConfigSaved() {
        return (configHolder, autoServerWorldSelectionConfig) -> {
            enabled = autoServerWorldSelectionConfig.enabledServerAddresses.contains(currentServerIP);
            return null;
        };
    }

}