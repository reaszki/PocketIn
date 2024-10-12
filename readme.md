# PocketIn
A plugin about obtaining resources, crafting and selling items, leveling up and repeating.

I've always wanted to play something similar to the beginning of Naofumi's journey (The Rising of the Shield Hero) or the anime Spice and Wolf, collecting materials, transforming them into items and selling them. The plugin is initially being developed on a prison or mines style server, but I plan to configure and adapt it later so that it works in a common survival where you will have encounters with NPCs to be able to sell your items.

---
### How?
Define a PocketArea with PocketDrops to control what each block drops, define a PocketRecipe to create an item, and finally sell that item to an NPC.

A complete wiki will be released as soon as the plugin is stable and out of alpha.

---
### Features
- Create and save PocketAreas (`/pocket area create name`).
- Load PocketAreas (`/pocket area load`).
- Wand to create PocketAreas (`/pocket area wand`).

---
### Roadmap
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

---
### Info about development:
Why items created with MythicMobs? For the ease of creating and storing custom items, in addition to Crucible which allows creating items with skills. If it already exists, why reinvent the wheel?

Why the area system? The plugin was designed to be used in a prison-style server scope that can be expanded with new "mines", however, a system without regions is also planned.

Why not use WorldGuard? WorldGuard is expected to be used with PocketIn (the plugin doesn't regenerate broken blocks by default).