# Circe auto derivation bug repro

When compiled as-is, we get a `knownDirectSubclasses` error.

There are 2 ways to make compilation pass:

  1. Rename `Role` to `ARole`.
  2. Add `import Role._` at the top of `Main`, before `import io.circe.generic.auto._`
  
A decidedly less realistic way of reproducing this is possible [on Scastie](https://scastie.scala-lang.org/lloydmeta/0resq7ZBTkSH3smuWnXVUA)