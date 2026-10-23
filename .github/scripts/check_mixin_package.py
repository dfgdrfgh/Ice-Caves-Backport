"""Keep helper classes OUTSIDE the package reserved by ice_caves.mixins.json.

Mixin configurations own the entire named package. Normal classes in that
package cannot be loaded through Fabric Loader and may crash during worldgen.
"""
from pathlib import Path
import json
root=Path(__file__).resolve().parents[2]
src=root/"src/main/java/org/zerw/net/icecaves"
manifest=json.loads((root/"src/main/resources/ice_caves.mixins.json").read_text())
expected={x.split(".")[-1] for x in manifest["mixins"]+manifest["client"]}
actual={p.stem for p in (src/"mixin").glob("*.java")}
assert actual==expected, f"Mixin folder contains unexpected or missing classes: {actual^expected}"
for name in ("IceCavesMixinLogic","IceCavesNoteParticleLogic"):
    assert not (src/"mixin"/(name+".java")).exists(), "Mixin helper incorrectly placed in mixin package"
    assert (src/"hooks"/(name+".java")).exists(), "Missing external helper class"
print("PASS: mixin package contains only declared mixins; helpers are outside")
