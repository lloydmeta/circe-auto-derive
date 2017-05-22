# Circe auto derivation bug repro

When compiled as-is, we get a `knownDirectSubclasses` error.

When you rename `Role` to `ARole`, compilation succeeds.