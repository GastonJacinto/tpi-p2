package service;

import config.DatabaseConnection;
import dao.BarcodeDao;
import entities.Barcode;

import java.sql.Connection;
import java.util.List;

public class BarcodeService {

    private final BarcodeDao dao;

    public BarcodeService() {
        this.dao = new BarcodeDao();
    }

    // CREATE
    public void createBarcode(Barcode b) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                dao.create(b, conn);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    // READ
    public Barcode getBarcode(Long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return dao.read(id, conn);
        }
    }

    // READ ALL
    public List<Barcode> getAllBarcodes() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return dao.read_all(conn);
        }
    }

    // UPDATE
    public void updateBarcode(Barcode b) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                dao.update(b, conn);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    // DELETE lógico
    public void deleteBarcode(Long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                dao.soft_delete(id, conn);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}
