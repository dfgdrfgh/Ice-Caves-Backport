#!/usr/bin/env python3
"""Build a custom Loom mapping JAR using intermediary identifiers as named.
Only for compiling original intermediary decompiled sources; not a remap to Yarn.
The input Tiny mappings are from FabricMC (CC0-1.0).
"""
import pathlib
import sys
import zipfile

src, dest = map(pathlib.Path, sys.argv[1:3])
with zipfile.ZipFile(src) as zf:
    original = zf.read("mappings/mappings.tiny").decode("utf-8")

lines = original.splitlines()
header = lines[0].split("\t")
if header[:3] != ["tiny", "2", "0"] or header[3:] != ["official", "intermediary"]:
    raise ValueError(f"Unexpected Tiny v2 header: {lines[0]!r}")
lines[0] = "tiny\t2\t0\tofficial\tintermediary\tnamed"
count = 0
for i in range(1, len(lines)):
    line = lines[i]
    fields = line.split("\t")
    if line.startswith("c\t") or line.startswith("\tf\t") or line.startswith("\tm\t"):
        # Preserve original mapping and clone intermediary name into named.
        lines[i] = line + "\t" + fields[-1]
        count += 1

if count < 2000:
    raise ValueError("Incomplete mapping output")
dest.parent.mkdir(parents=True, exist_ok=True)
with zipfile.ZipFile(dest, "w", zipfile.ZIP_DEFLATED) as out:
    out.writestr("mappings/mappings.tiny", "\n".join(lines) + "\n")
print("Created", dest, "with", count, "intermediary-to-named entries")
