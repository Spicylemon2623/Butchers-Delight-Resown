import json

stages = ["intact", "sheared", "skinned"]
hooked = [False, True]
facings = ["north", "east", "south", "west"]
y_rotations = {"north": 0, "east": 90, "south": 180, "west": 270}

multipart = []

for stage in stages:
    for hook in hooked:
        for facing in facings:
            entry = {
                "when": {"stage": stage, "hooked": hook, "facing": facing},
                "apply": {"model": f"butchers_delight_rechopped:block/carcass/sheep/{"hooked/" if hook else ""}{stage}", "y": y_rotations[facing]}
            }
            multipart.append(entry)

blockstate = {"multipart": multipart}

with open("sheep_carcass.json", "w") as f:
    json.dump(blockstate, f, indent=2)

print("Blockstate JSON generated: sheep_carcass_blockstate.json")