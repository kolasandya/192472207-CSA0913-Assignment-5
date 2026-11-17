# Task A4 - What Should Be Serialized?

## A4.1 - Transient Fields & Object Graph
- **Transient Fields:** `password` (security risk) and `Connection` (live database handle cannot be serialized across JVM instances). Both must be marked `transient`.
- **Object Graph:** Serialization recursively traverses the Hostel object graph (`Hostel` -> list of `Room` -> list of `Resident`), serializing all referenced serializable objects.

## A4.2 - Plan for Change (`serialVersionUID`)
- When a new field (`phone number`) is added, Java calculates a hash for class compatibility. Explicitly declaring `private static final long serialVersionUID = 1L;` ensures backward compatibility, safely ignoring missing fields in old files or initializing defaults.
