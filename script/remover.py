import json
import os
import glob

BIOME_FOLDER = "."
MOBS_FILE = "mobs.txt"

def load_mobs_to_remove(mobs_file):
    if not os.path.exists(mobs_file):
        print(f"Error: {mobs_file} not found.")
        return set()
    with open(mobs_file, 'r') as f:
        return {line.strip() for line in f if line.strip()}

def process_biome_file(file_path, mobs_to_remove, current, total):
    print(f"[{current}/{total}] {os.path.basename(file_path)}")
    with open(file_path, 'r') as f:
        data = json.load(f)

    modified = False
    if 'spawners' in data:
        for category_name, spawns in data['spawners'].items():
            if isinstance(spawns, list):
                new_spawns = []
                for spawn in spawns:
                    if isinstance(spawn, dict) and 'type' in spawn:
                        if spawn['type'] not in mobs_to_remove:
                            new_spawns.append(spawn)
                        else:
                            print(f"  🗑️  Removed {spawn['type']} from {category_name}")
                            modified = True
                data['spawners'][category_name] = new_spawns
                if not new_spawns:
                    print(f"  ⚠️   Empty {category_name} spawns")

    if modified:
        backup_path = file_path + ".backup"
        os.rename(file_path, backup_path)
        with open(file_path, 'w') as f:
            json.dump(data, f, indent=2)
        print(f"  ✓ Modified (backup: {os.path.basename(backup_path)})")
    else:
        print(f"  ⏭️  No changes needed")

def main():
    mobs_to_remove = load_mobs_to_remove(MOBS_FILE)
    if not mobs_to_remove:
        return

    print(f"Loaded {len(mobs_to_remove)} mobs: {sorted(mobs_to_remove)}")

    json_files = glob.glob(os.path.join(BIOME_FOLDER, "*.json"))
    if not json_files:
        print(f"No JSON files found in {os.getcwd()}")
        return

    print(f"Processing {len(json_files)} files...\n")
    for i, file_path in enumerate(json_files, 1):
        process_biome_file(file_path, mobs_to_remove, i, len(json_files))

    print("\n✅ Done!")

if __name__ == "__main__":
    main()
