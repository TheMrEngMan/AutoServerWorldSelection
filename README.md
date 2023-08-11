# Auto Server World Selection

Normally when using [Xaero's World Map](https://legacy.curseforge.com/minecraft/mc-mods/xaeros-world-map) on servers in bungee / velocity networks with multiple sub-servers, it can be problematic to switch between overworld / nether / end worlds for each server. This can cause maps of one server to override maps of a different server since currently Xaero's World Map can only differentiate each world based on its spawn coordinates, which are not always unique.  

Auto Server World Selection attempts to mitigate this issue by identifying worlds based on their biome seed, which is normally different for each world in a server network.  

## Setup
Requires Xaero's World Map to work, and [Mod Menu](https://modrinth.com/mod/modmenu) to configure in-game.

Server addresses in mod settings must match the server address exactly as they are written in the server settings, like so:

![Server address must match](/images/server_address_setup.png)

When loading into worlds for the first time, a new map in the world map screen will be created based on the newly applied biome seed of that world. Simply delete that new map and select your previously named map, then confirm it, like so:

![World names](/images/world_names_setup.png)

## Development

This mod can be built by cloning this repository, then running:

```sh
./gradlew build
```

The resulting mod files are stored in `build/libs/`
