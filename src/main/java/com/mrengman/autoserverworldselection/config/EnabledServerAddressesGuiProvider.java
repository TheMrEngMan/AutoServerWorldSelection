package com.mrengman.autoserverworldselection.config;

import me.shedaniel.autoconfig.gui.registry.api.GuiProvider;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.autoconfig.util.Utils;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import net.minecraft.text.Text;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class EnabledServerAddressesGuiProvider implements GuiProvider {

    @SuppressWarnings("rawtypes")
    @Override
    public List<AbstractConfigListEntry> get(String i13n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
        return Collections.singletonList(
                ConfigEntryBuilder.create()
                        .startStrList(Text.translatable(i13n), Utils.getUnsafely(field, config))
                        .setExpanded(true)
                        .setCreateNewInstance(entry -> new StringListListEntry.StringListCell("play.example.com", entry))
                        .setDefaultValue(() -> Utils.getUnsafely(field, defaults))
                        .setSaveConsumer(newValue -> Utils.setUnsafely(field, config, newValue))
                        .build()
        );
    }
}
