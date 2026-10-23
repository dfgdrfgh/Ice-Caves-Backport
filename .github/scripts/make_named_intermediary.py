#!/usr/bin/env python3
"""Build intermediary->named identity Tiny v2 mappings for recovered class_ sources.

Fabric intermediary source mappings: CC0-1.0.
Loom already supplies official->intermediary; this file must have intermediary
as its SOURCE namespace, and named as its destination namespace.
"""
import pathlib
import re
import sys
import zipfile

src, dest = map(pathlib.Path, sys.argv[1:3])
with zipfile.ZipFile(src) as zf:
    original = zf.read("mappings/mappings.tiny").decode("utf-8")
lines = original.splitlines()
header = lines[0].split("\t")
if header != ["tiny", "2", "0", "official", "intermediary"]:
    raise ValueError("Unexpected input Tiny header: " + str(header))

official_to_intermediary = {}
for line in lines[1:]:
    if line.startswith("c\t"):
        parts = line.split("\t")
        official_to_intermediary[parts[1]] = parts[2]

def remap_desc(descriptor):
    return re.sub(
        r"L([^;]+);",
        lambda m: "L" + official_to_intermediary.get(m.group(1), m.group(1)) + ";",
        descriptor,
    )

result = ["tiny\t2\t0\tintermediary\tnamed"]
count = 0
for line in lines[1:]:
    if line.startswith("c\t"):
        name = line.split("\t")[2]
        result.append("c\t" + name + "\t" + name)
        count += 1
    elif line.startswith("\tf\t") or line.startswith("\tm\t"):
        parts = line.split("\t")
        if len(parts) != 5:
            raise ValueError("Unexpected field/method layout: " + repr(line))
        result.append("\t" + parts[1] + "\t" + remap_desc(parts[2]) + "\t" + parts[4] + "\t" + parts[4])
        count += 1

if count < 2000:
    raise ValueError("Incomplete input mappings")
dest.parent.mkdir(parents=True, exist_ok=True)
with zipfile.ZipFile(dest, "w", zipfile.ZIP_DEFLATED) as archive:
    archive.writestr("mappings/mappings.tiny", "\n".join(result) + "\n")
print("Created intermediary->named identity mapping:", dest, "entries:", count)
