# PocketIn
A plugin about obtaining resources, crafting and selling items, leveling up and repeating.

Mainly based on the beginning of Naofumi's journey (The Rising of the Shield Hero), where he gathered resources, transformed them into items and sold them.

---
#### How?
It works together with [MythicMobs](https://www.spigotmc.org/resources/%E2%9A%94-mythicmobs-free-version-%E2%96%BAthe-1-custom-mob-creator%E2%97%84.5702/) to manage custom items, features a simple crafting system via a list of items in a GUI that can take into account money, level, and items.

It implements PocketArea System* to manage areas with resources. Within these areas, depending on the level, tool, block, world or player situation, performed action and permission, a certain amount of some custom item can be given.

The selling price of the created item is calculated depending on the quality and polish of the item and the materials used.

---
#### Roadmap
✅ **PocketArea**  
Define and save regions. A PocketArea stores its own name, world, pair of coordinates, and a list of PocketDrops.

There are no permissions to use PocketAreas since PocketDrop is what controls the behavior.

PocketAreas are saved in /plugins/PocketIn/areas, you can create a PocketArea by selecting the area with a PocketWand (`/pocket area wand`) and while still holding the PocketWand, use `/pocket area create name`.

🟥 **PocketAreaRegen**  
PocketAreaRegen is another type of PocketArea that also stores the blocks that are inside the selection at the time of creation, it can regenerate automatically
or manually, all at once or block by block.

PocketAreasRegen are also saved in /plugins/PocketIn/areas, the creation process is almost the same, changing only the last command: `/pocket arearegen create name`.

🟥 **PocketRecipes**  
Create recipes for custom items (MythicMobs/Crucible) that take into account level, money, items, permissions, and world or player state.

They will be stored in /plugins/PocketIn/recipes, a graphical interface to facilitate creation and another to view the list of recipes are planned.

🟥 **PocketDrops**  
They are the heart of PocketAreas, they determine what each type of block within the PocketArea drops, according to the
level, block, world, world state and/or player and permission.

They will be stored in /plugins/PocketIn/drops, a graphical interface to facilitate creation and another to view the list
of drops are planned.

#### Info about development:
Why items created with MythicMobs? For the ease of creating and storing custom items, in addition to Crucible which allows creating items with skills. If it already exists, why reinvent the wheel?

Why the area system? The plugin was designed to be used in a prison-style server scope that can be expanded with new "mines", however, a system without regions is also planned.

Why not use WorldGuard? WorldGuard is expected to be used with PocketIn (the plugin doesn't regenerate broken blocks by default).