#!/usr/bin/env python3
"""Materialize AppAdapter.java and MainActivity.java from .cursor-rebuild/ on this branch."""
import hashlib, pathlib, subprocess, sys
root = pathlib.Path(__file__).resolve().parents[1]
rebuild = root / '.cursor-rebuild'
adapter = root / 'launcher/app/src/main/java/com/gathin/tvhome/AppAdapter.java'
main = root / 'launcher/app/src/main/java/com/gathin/tvhome/MainActivity.java'
parts = [rebuild / f'AppAdapter.java.part{i}' for i in range(4)]
for p in parts:
    assert p.exists(), p
data = b''.join(p.read_bytes() for p in parts)
blob = hashlib.sha1(b'blob %d\0' % len(data) + data).hexdigest()
assert len(data) == 53994 and blob == '37354e4633c2d3850130fd1f256f7963c0acffea', (len(data), blob)
adapter.write_bytes(data)
subprocess.check_call(['git', 'apply', '--verbose', str(rebuild / 'MainActivity.patch')], cwd=root)
main_data = main.read_bytes()
blob2 = hashlib.sha1(b'blob %d\0' % len(main_data) + main_data).hexdigest()
assert blob2 == '1bc1254a4c86623903ebb3e38dfdad6f51be10cf', blob2
print('ok', adapter, main)
