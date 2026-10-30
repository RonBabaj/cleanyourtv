# RonBabaj fork — TV home enhancements

Fork of [Gathin23/cleanyourtv](https://github.com/Gathin23/cleanyourtv) for the Xiaomi / Google TV
debloat mission. Package stays `com.gathin.tvhome`.

## Features added on this fork

### Dynamic rows (`dynamic_rows`)

When on, Home counts apps under the dock and sets `rows_below_dock` to the smallest value (0–3)
that fits them. The same value is written into Launcher settings so the “Rows below dock” row
updates on its own as you add or remove Home shortcuts.

### Dock add button (`dock_add_button`)

A **+** tile after the dock favourites opens the App library so you can pin custom APKs and apps
that never got a Home shortcut automatically. Turning this on also enables `app_library`.

Defaults in this fork’s `config.xml`: both features and `app_library` are **on**.

## Tailscale deploy

Same as upstream [`docs/REMOTE.md`](docs/REMOTE.md):

```bash
TV_HOST=<tailscale-100.x.y.z> launcher/build-and-install.sh
TV_HOST=<tailscale-100.x.y.z> tools/tv.py --screenshot shots/home.png
```

## Bug fixes bundled here

- `AppAdapter.containsPackage` ignores empty and + dock placeholders so the App library does not
  treat the launcher’s own package as already on Home.
