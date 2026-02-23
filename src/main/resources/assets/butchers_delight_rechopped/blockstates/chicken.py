import json

stages = ["intact", "sheared"]
facings = ["north", "east", "south", "west"]
y_rotations = {"north": 0, "east": 90, "south": 180, "west": 270}

multipart = []

for stage in stages:
    for facing in facings:
        entry = {
               "when": {"stage": stage, "facing": facing},
               "apply": {"model": f"butchers_delight_rechopped:block/carcass/chicken/{stage}", "y": y_rotations[facing]}
           }
        multipart.append(entry)

blockstate = {"multipart": multipart}

with open("chicken_carcass.json", "w") as f:
    json.dump(blockstate, f, indent=2)

print("Blockstate JSON generated: sheep_carcass_blockstate.json")