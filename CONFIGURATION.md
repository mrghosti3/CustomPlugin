# Plugin Configuration Guide

This guide explains how to configure the **Custom Plugin** using the `config.yml` file.
On the first run, the plugin will automatically create this file with default settings in your server's plugin directory.

## Configuration File: `config.yml`

The `config.yml` file controls which mobs can cause damage (grief) to your Minecraft world.
You can edit this file to enable or disable protection for specific mobs.

### **Grief Prevention Settings**

The `grief` section lists mobs that can potentially damage your world.
Each mob can be toggled **on** or **off** to allow or prevent their damaging actions.

#### **Available Options**
   Mob       | Description                                                                 | Default Value |
 |-----------|-----------------------------------------------------------------------------|---------------|
 | `CREEPER` | Prevents Creepers from exploding and destroying blocks.                     | `false`       |
 | `ENDERMAN`| Prevents Endermen from picking up, placing, or moving blocks.               | `false`       |
 | `GHAST`   | Prevents Ghasts from destroying blocks with their fireballs.                | `false`       |

> **Note:** Mob names **must be in UPPERCASE** (e.g., `CREEPER`, not `creeper`).

### **How to Configure**

1. **Locate the File**
   The `config.yml` file is automatically created in your plugin's folder when the server starts for the first time.

2. **Edit the File**
   Open `config.yml` in a text editor.
   Change the value of any mob from `false` to `true` to **block** its damaging actions.

   Example:
   ```yaml
   capture
     grief:
       CREEPER: true   # Blocks Creeper explosions
       ENDERMAN: true  # Blocks Enderman block manipulation
       GHAST: false    # Allows Ghast fireball damage
   ```
