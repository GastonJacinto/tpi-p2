package dao;

import java.sql.Connection;
import java.util.List;

public interface StringKeyDao<T> {

    void create(T entity, Connection conn) throws Exception;

    T readByKey(String key, Connection conn) throws Exception;

    List<T> read_all(Connection conn) throws Exception;

    void update(T entity, Connection conn) throws Exception;

    void deleteByKey(String key, Connection conn) throws Exception;
}
