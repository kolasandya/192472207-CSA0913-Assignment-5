# Task A3 - Discover the Data-Access Design Pattern

## A3.1 - Common Pattern vs Differences
- **Repeated Steps:** Opening JDBC connection, handling SQL exceptions, resource cleanup (`PreparedStatement`, `ResultSet`), and transaction management.
- **Differences:** SQL strings, table names, parameter bindings, and ResultSet mapping logic for each domain object (`Student`, `Course`, `Marks`).

## A3.2 - Generic Data-Access Interface (`Dao<T, K>`)
```java
public interface Dao<T, K> {
    void insert(T entity) throws SQLException;
    T findById(K id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(K id) throws SQLException;
}
```

## A3.3 - Object-to-Table Mapping (`Student`)
- Table Name: `students`
- Primary Key: `reg_no` (`VARCHAR`, PK)
- Columns: `name` (`VARCHAR`), `email` (`VARCHAR`), `department` (`VARCHAR`), `semester` (`INT`).
