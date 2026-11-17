# Task A1 - Design a File Format

## A1.1 - Record Layout
1. **Delimited Text File (`.csv`):**
   - **Field Order:** `passId, regNo, name, routeNo, boardingStop, validityDate, amountPaid`
   - **Delimiter:** Comma (`,`)
   - **Handling Names with Delimiters:** If a student name contains a comma (e.g., "Doe, John"), the entire name field is enclosed in double quotes (`"Doe, John"`), or escaped using standard RFC 4180 CSV rules.
2. **Binary File (`DataOutputStream`):**
   - **Field Order:** `passId` (UTF string), `regNo` (UTF string), `name` (UTF string), `routeNo` (int, 4 bytes), `boardingStop` (UTF string), `validityDate` (long epoch millis, 8 bytes), `amountPaid` (double, 8 bytes).
   - **Field Sizes:** UTF strings use 2 bytes for length prefix followed by string bytes; fixed-length primitives use explicit byte widths.

## A1.2 - Design Operations (Pseudocode)
```pseudocode
// Stream choices: BufferedReader/BufferedWriter wrapped around FileReader/FileWriter
// Reason: Provides efficient line-oriented reading and writing with internal buffering.

Algorithm AddPass(filePath, recordString)
    open BufferedWriter in APPEND and CREATE mode
    write recordString
    write newline
    close writer
End Algorithm

Algorithm FindPass(filePath, targetPassId)
    open BufferedReader for reading filePath
    while line = readLine() from reader
        split line by comma into fields
        if fields[0] equals targetPassId then
            return fields
        end if
    end while
    close reader
    return null
End Algorithm

Algorithm ListRoute(filePath, targetRouteNo)
    open BufferedReader for reading filePath
    initialize list matchingPasses
    while line = readLine() from reader
        split line by comma into fields
        if fields[3] equals targetRouteNo then
            add fields to matchingPasses
        end if
    end while
    close reader
    return matchingPasses
End Algorithm
```

## A1.3 - Damaged Data & Crash Safety
- **Damaged Line:** During `find()` or `list()`, lines failing validation (e.g., incorrect field count or non-numeric amount) are logged and skipped without crashing.
- **Missing File:** Handled via `try-catch` on `FileNotFoundException`, automatically initializing a new empty file.
- **Incomplete Write (Power Failure):** Writes are performed to a temporary `.tmp` file and renamed atomically via `Files.move()`, or appended safely with a trailing checksum/newline verification.
