# Circe auto derivation bug repro

When compiled as-is, we get a `knownDirectSubclasses` error.

There are 2 ways to make compilation pass:

  1. Rename `Role` to `ARole`.
  2. Add `import Role._` at the top of `Main`, before `import io.circe.generic.auto._`