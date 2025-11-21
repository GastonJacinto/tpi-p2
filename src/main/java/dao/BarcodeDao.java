package dao;

import entities.Barcode;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BarcodeDao implements GenericDao<Barcode> {

    @Override
    public void create(Barcode b, Connection conn) throws Exception {

        String sql = """
            INSERT INTO barcode
            (deleted, type_code, value, assigned_at, metadata)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, b.getDeleted());
            stmt.setString(2, b.getType_code());
            stmt.setString(3, b.getValue());

            if (b.getAssigned_at() != null) {
                stmt.setDate(4, Date.valueOf(b.getAssigned_at()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            if (b.getMetadata() != null) {
                stmt.setString(5, b.getMetadata());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public Barcode read(Long id, Connection conn) throws Exception {

        String sql = """
            SELECT id, deleted, type_code, value, assigned_at, metadata
            FROM barcode
            WHERE id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }
        }
        return null;
    }

    @Override
    public List<Barcode> read_all(Connection conn) throws Exception {

        String sql = """
            SELECT id, deleted, type_code, value, assigned_at, metadata
            FROM barcode
            WHERE deleted = 0
        """;

        List<Barcode> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    @Override
    public void update(Barcode b, Connection conn) throws Exception {

        String sql = """
            UPDATE barcode
            SET deleted = ?, type_code = ?, value = ?, assigned_at = ?, metadata = ?
            WHERE id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, b.getDeleted());
            stmt.setString(2, b.getType_code());
            stmt.setString(3, b.getValue());

            if (b.getAssigned_at() != null) {
                stmt.setDate(4, Date.valueOf(b.getAssigned_at()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            if (b.getMetadata() != null) {
                stmt.setString(5, b.getMetadata());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }

            stmt.setLong(6, b.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void soft_delete(Long id, Connection conn) throws Exception {

        String sql = "UPDATE barcode SET deleted = 1 WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }


    // ---------------------------------
    // Helper: ResultSet → Barcode
    // ---------------------------------
    private Barcode map(ResultSet rs) throws Exception {

        Barcode b = new Barcode();

        b.setId(rs.getLong("id"));
        b.setDeleted(rs.getBoolean("deleted"));
        b.setType_code(rs.getString("type_code"));
        b.setValue(rs.getString("value"));

        Date d = rs.getDate("assigned_at");
        if (d != null) {
            b.setAssigned_at(d.toLocalDate());
        } else {
            b.setAssigned_at(null);
        }

        b.setMetadata(rs.getString("metadata"));

        return b;
    }
}
