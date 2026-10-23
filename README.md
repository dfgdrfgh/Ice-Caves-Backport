# Ice Caves 1.21.1 — recovered Java source

This archive contains **actual Java method bodies** recovered using Vineflower 1.12.0 from the supplied `IceCaves-1.21.1(1).jar`. Unlike a class-signature dump, this is a real bytecode decompilation.

## Contents

- `src/main/java/`: **90** decompiled `.java` files corresponding to the **96** original `.class` files. The difference is due to Java nested / synthetic classes whose code appears within the parent source, and an additional named nested source output.
- `src/main/resources/`: **210** files retained unmodified from the input JAR (textures, sound files, data, mixin configurations, loader metadata, license, etc.).
- `DECOMPILATION_REPORT.json`: input SHA-256, file manifest and a source-coverage check.

## Important development notes

- This is a **source recovery**, not a reconstructed or verified buildable Fabric/Forge/NeoForge Gradle project. Original `build.gradle`, dependency versions and project configuration cannot be fully recovered from a compiled JAR.
- The input JAR contains **Fabric, Forge, and NeoForge** components and metadata together. Those targets may need to be separated into source sets or Gradle modules if you want to rebuild the mod.
- The Fabric classes use Minecraft **intermediary mapping names** such as `class_3031` and `method_13151`; to edit against named Yarn mappings, remapping and project setup would be required. Compiling will also require the correct Minecraft version, mappings, loader APIs and dependencies.
- Decompiled source does not necessarily match the original author's formatting, comments, local variable names or some synthetic structures, and may require manual fixes to compile.
- No Minecraft launch, modpack test, or compile test was performed here. This output was checked for recovery coverage, not gameplay compatibility.

Original mod license, where included, is preserved at `src/main/resources/LICENSE_Ice Caves`. Respect its terms when redistributing source or derivatives.
