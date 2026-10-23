# Ice Caves 1.21.1 convergence (work in progress)

Source baseline: recovered Zerw Fabric 1.21.1 Ice Caves mod.
Comparison/reference: Bananaman Ice Caves 0.1.7 (CC0-1.0).

## Initial integration
- Progressive Freezing ticks; no duplicated custom freeze damage tick.
- Ice Balls have velocity-dependent direct impact damage plus Freezing.
- Frostbites no longer crack Brittle Ice.
- Added Fabric-only build configuration and CI using intermediary mappings and Java 21.
- Preserved existing jukebox playlist code and potion duration values.
- Deliberately did NOT copy Bananaman's TerraBlender region, climate mixins or terrain mutation.

## Outstanding
- Verify first clean compile; fix decompilation-induced errors.
- Add stonecutting recipes, Ice Cave biome safely, crevasses, Frostbite AI, conversion, trades, advancement, arrow tint fix.
- Test with and without TrueVanillaBackport/WWOO and compare terrain on fixed seeds.
- Complete pack unavailable; no Minecraft game-run claimed.

No release should be considered stable before those checks.
