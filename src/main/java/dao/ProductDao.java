package dao;

import entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements GenericDao<Product> {

    @Override
    public void create(Product p, Connection conn) throws Exception {

        String sql = """
            INSERT INTO product
            (deleted, name, brand, category, price, weight, bar_code_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, p.getDeleted());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getBrand());
            stmt.setString(4, p.getCategory());
            stmt.setDouble(5, p.getPrice());

            if (p.getWeight() != null) {
                stmt.setDouble(6, p.getWeight());
            } else {
                stmt.setNull(6, Types.DECIMAL);
            }

            if (p.getBar_code_id() != null) {
                stmt.setLong(7, p.getBar_code_id());
            } else {
                stmt.setNull(7, Types.BIGINT);
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public Product read(Long id, Connection conn) throws Exception {

        String sql = """
            SELECT id, deleted, name, brand, category, price, weight, bar_code_id
            FROM product
            WHERE id = ? AND deleted = 0
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> read_all(Connection conn) throws Exception {

        String sql = """
            SELECT id, deleted, name, brand, category, price, weight, bar_code_id
            FROM product
            WHERE deleted = 0
        """;

        List<Product> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public void update(Product p, Connection conn) throws Exception {

        String sql = """
            UPDATE product
            SET deleted = ?, name = ?, brand = ?, category = ?, price = ?, 
                weight = ?, bar_code_id = ?
            WHERE id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, p.getDeleted());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getBrand());
            stmt.setString(4, p.getCategory());
            stmt.setDouble(5, p.getPrice());

            if (p.getWeight() != null) {
                stmt.setDouble(6, p.getWeight());
            } else {
                stmt.setNull(6, Types.DECIMAL);
            }

            if (p.getBar_code_id() != null) {
                stmt.setLong(7, p.getBar_code_id());
            } else {
                stmt.setNull(7, Types.BIGINT);
            }

            stmt.setLong(8, p.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void soft_delete(Long id, Connection conn) throws Exception {

        String sql = "UPDATE product SET deleted = 1 WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Product map(ResultSet rs) throws Exception {
        Product p = new Product();

        p.setId(rs.getLong("id"));
        p.setDeleted(rs.getBoolean("deleted"));
        p.setName(rs.getString("name"));
        p.setBrand(rs.getString("brand"));
        p.setCategory(rs.getString("category"));
        p.setPrice(rs.getDouble("price"));

        double w = rs.getDouble("weight");
        if (rs.wasNull()) {
            p.setWeight(null);
        } else {
            p.setWeight(w);
        }

        long bar = rs.getLong("bar_code_id");
        if (rs.wasNull()) {
            p.setBar_code_id(null);
        } else {
            p.setBar_code_id(bar);
        }

        return p;
    }
}
