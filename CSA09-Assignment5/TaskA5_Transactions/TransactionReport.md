# Task A5 - Design for Correctness and Safety

## A5.1 - Wallet Transfer JDBC Pseudocode
```pseudocode
Algorithm TransferWallet(connection, fromWalletId, toWalletId, amount)
    set autoCommit to false
    try
        execute UPDATE deduct from wallet where id = fromWalletId
        execute UPDATE add to wallet where id = toWalletId
        commit transaction
    catch Exception e
        rollback transaction
        re-throw exception
    finally
        restore original autoCommit state
    end try
End Algorithm
```

## A5.2 - SQL Injection Analysis & Redesign
- **Unsafe Vulnerability:** `id='' OR '1'='1` bypasses authentication checks by making the WHERE clause permanently true.
- **PreparedStatement Redesign:** Using `?` placeholders ensures user input is treated strictly as data, neutralizing malicious SQL fragments.
