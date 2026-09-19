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
