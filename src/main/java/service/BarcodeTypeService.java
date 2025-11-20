package service;

import config.DatabaseConnection;
import dao.BarcodeTypeDao;
import entities.BarcodeType;

import java.sql.Connection;
import java.util.List;

public class BarcodeTypeService {

    private final BarcodeTypeDao dao;

    public BarcodeTypeService() {
        this.dao = new BarcodeTypeDao();
    }

    // CREATE
    public void createBarcodeType(BarcodeType type) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            dao.create(type, conn);
            conn.commit();
        }
    }

    // READ (String key)
    public BarcodeType getBarcodeType(String code) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return dao.readByKey(code, conn);
        }
    }

    // READ ALL
    public List<BarcodeType> getAllBarcodeTypes() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return dao.read_all(conn);
        }
    }

    // UPDATE
    public void updateBarcodeType(BarcodeType type) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            dao.update(type, conn);
            conn.commit();
        }
    }

    // DELETE (real, no logical delete)
    public void deleteBarcodeType(String code) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            dao.deleteByKey(code, conn);
            conn.commit();
        }
    }
}
