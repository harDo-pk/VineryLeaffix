# VineryLeaffix
Disables [Let's Do] Vinery's automatic grape-leaf growth around Grapvine Stems. This is a cosmetic feature of Vinery with no vanilla config option to turn it off - this mod removes the behavior via Mixin. Not affiliated with the Let's Do team.

## Update notice

As of a recent Vinery update, the mod now ships its own config option for this:

```toml
grapevineLeavesEnabled = true
```

found in Vinery's config file. Setting this to `false` disables the automatic leaf
growth natively - no separate mod required.

**If you're on that Vinery version or newer:** just set `grapevineLeavesEnabled = false`
in Vinery's config and you don't need this mod at all.

**If you're still on Vinery 1.5.0 - 1.5.3** (the versions this mod targets): the native option
doesn't exist yet in your version, so VineryLeafFix still does its job.

This mod isn't being actively developed further now that the upstream fix exists, but
it'll stay up for anyone who needs it on 1.5.0 - 1.5.3.

## Requirements
* Minecraft 1.21.1
* NeoForge 1.21.x
* Let's Do Vinery 1.5.1 - 1.5.3
Vinery may also require Architectury depending on the version you install.

## Credits & Copyright
This mod is a Tweak for the mod **[[Let's Do] Vinery](https://www.curseforge.com/minecraft/mc-mods/lets-do-vinery)**, developed by **Let's Do** (satisfyL). 
* I do not own any rights to the original assets or the core concept of Vinery.
* All credits for the original Vinery ecosystem go to the original development team.
