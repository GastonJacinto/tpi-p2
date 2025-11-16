package dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    void create(T entity, Connection conn) throws Exception;

    T read(Long id, Connection conn) throws Exception;

    List<T> read_all(Connection conn) throws Exception;

    void update(T entity, Connection conn) throws Exception;

    void soft_delete(Long id, Connection conn) throws Exception;
}
