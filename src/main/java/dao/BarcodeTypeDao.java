package dao;

import entities.BarcodeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarcodeTypeDao implements GenericDao<BarcodeType> {

    @Override
    public void create(BarcodeType bt, Connection conn) throws Exception {
        String sql = """
            INSERT INTO barcode_type (code, description)
            VALUES (?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bt.getCode());
            stmt.setString(2, bt.getDescription());
            stmt.executeUpdate();
        }
    }

    @Override
    public BarcodeType read(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("BarcodeType uses String PK, not Long.");
    }

    public BarcodeType readByCode(String code, Connection conn) throws Exception {
        String sql = "SELECT code, description FROM barcode_type WHERE code = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new BarcodeType(
                            rs.getString("code"),
                            rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<BarcodeType> read_all(Connection conn) throws Exception {
        String sql = "SELECT code, description FROM barcode_type";

        List<BarcodeType> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new BarcodeType(
                        rs.getString("code"),
                        rs.getString("description")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(BarcodeType bt, Connection conn) throws Exception {
        String sql = """
            UPDATE barcode_type
            SET description = ?
            WHERE code = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bt.getDescription());
            stmt.setString(2, bt.getCode());
            stmt.executeUpdate();
        }
    }

    @Override
    public void soft_delete(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("BarcodeType has no deleted column.");
    }

    public void deleteByCode(String code, Connection conn) throws Exception {
        String sql = "DELETE FROM barcode_type WHERE code = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        }
    }
}
