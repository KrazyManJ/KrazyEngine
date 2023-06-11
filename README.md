<h1 align=center >KrazyEngine</h1>

<h2 align=center>Features:</h2>

### Any usage:

- <kbd>Command Log Filter</kbd> *Disable command from logging into console and logs*
- <kbd>Component Maker</kbd> *Make target, translate, and keybind component via Enums*
- <kbd>Cooldown Classes</kbd> *Create cooldown holders for any situation, also with key integration*
- <kbd>Regex Tools</kbd> *Contains list of useful regexes and also regex group matcher*
- <kbd>API Manipulation</kbd> *Allows to read data from url API request*
- <kbd>MySQL</kbd> *MySQL database manipulation, execute queries, read data, etc.*
- <kbd>Tab Builder</kbd> *Allows building simple tab completion.*

### Proxy server features

- <kbd>Bungee Config Container</kbd> *ConfigHolder for Bungeecord configuration*
- <kbd>Bungee Logging</kbd> *Easy logging to bungeecord, you can choose from info, warn, or error*
- <kbd>Bungee Source Manager</kbd> *Manage copying files from source easily*

### Backend server features

- <kbd>Stack Maker</kbd> *Create ItemStacks easily, with basic builder, set name, enchants, lore and even more*
- <kbd>Skull</kbd> *Create player head (Skull) from OfflinePlayer instance, player name, or even base64 value*
- <kbd>Material Aliases</kbd> *Check if material is in some kind of group (log, natural stone, container, ...)*
- <kbd>Bungee Message Channel</kbd> *Manipulate with bungeecord message channel*
- <kbd>Command Map Registry</kbd> *Can create commands without plugin.yml file*
- <kbd>Spigot Logging</kbd> *Easy logging to bungeecord, you can choose from info, warn, or error*
- <kbd>Shaped Recipe Maker</kbd> *Make recipes with basic recipe builder*
- <kbd>Spigot Source Manager</kbd> *Manage copying files from source easily*
- <kbd>Utilities</kbd> *Adding methods, that make life easier*

<h2>Third party libraries</h2>

This library uses a few libraries, that are not created by myself, so here is a full list:

- [BananaPuncher714/NBTEditor](https://github.com/BananaPuncher714/NBTEditor)

<h2>Use in local build (gradle)</h2>

```kotlin
plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("me.KrazyManJ:KrazyEngine:1.0.0")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        minimize() //Use only classes that are used in project
    }
}
```