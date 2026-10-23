# Ice Caves Backport — Fabric 1.21.1 (experimental merge)

Work-in-progress combined source project using the original recovered Zerw Ice Caves 1.21.1 implementation and independently adapted mechanics from the CC0 Bananaman 0.1.7 backport.

**Experimental:** compilation has succeeded in GitHub Actions; in-game/world-generation compatibility is not yet verified. Do not replace a known-good modpack JAR or open an important world without a backup.

## Current combined features

- Ice Crystal, icicles, Brittle Ice, Packed Ice building blocks and Ice Door assets from the original recovered mod.
- Frostbite, Ice Balls and Freezing potions/arrows, with gradual freezing, velocity-dependent impact damage, ranged AI, powder-snow conversion, and Brittle Ice exemptions.
- Water next to Blue Ice forms Brittle Ice; snow layers may sit on Packed Ice.
- Nine added Packed Ice stonecutter recipes and a Frostbite/Ice Ball advancement.
- Existing jukebox chest playlists preserved.
- Correct baseline (45s/90s) Freezing potions; brewing ingredient changed to Ice Balls.
- Arrow tint/model adjustments to make Freezing arrow appearances consistent.
- Spawn filtering constrained to locations with actual nearby Ice Cave blocks rather than entire underground cold biomes.

## Worldgen / compatibility

The standalone mod **does not yet add a real** `minecraft:ice_caves` biome. It retains the original mod's decorated-cave approach in tagged cold biomes.

**Intentionally excluded:** Bananaman's global biome-climate mutations, TerraBlender default-region mixins and terrain-changing code. Those were implicated in user-reported floating-island terrain and crashes when paired with TrueVanillaBackport. A biome integration must first be designed around deterministic seed and mod compatibility testing.

The separate Ice Caves mods must not be installed alongside this merged mod: they register overlapping content.

## Building

GitHub Actions > **Fabric 1.21.1 merged source build** builds the mod with Java 21, Fabric Loom and Gradle 8.11.1, then uploads its JARs as an artifact.

Build setup uses `net.minecraft.class_...` (intermediary-named) source recovered by Vineflower and a temporary identity intermediary->named mapping generated in CI. The original JAR included Forge and NeoForge files, excluded from the Fabric build.

## Verification

- Java compilation and mod JAR packaging passed in GitHub Actions.
- Automated dedicated-server startup smoke testing is being added to detect runtime mixin or registry failures.
- In-game behavior, client rendering, full modpack compatibility and fixed-seed worldgen comparison remain untested.
- The latest complete user modpack is not available in this session.

See `MERGE_PROGRESS.md` for details and outstanding work.

## Licensing

The original recovered Zerw Ice Caves assets/code included the license at `src/main/resources/LICENSE_Ice Caves` (All Rights Reserved). This remains subject to the original author's conditions. Additional gameplay approaches/reference material from Bananaman 0.1.7 were published under CC0-1.0; CC0 does not override Zerw's license.

This is source restoration and a user-requested experimental merge, **not** a statement of permission to redistribute third-party All Rights Reserved assets/source.
