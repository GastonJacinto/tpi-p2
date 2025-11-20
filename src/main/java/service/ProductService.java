package service;

import config.DatabaseConnection;
import dao.ProductDao;
import entities.Product;

import java.sql.Connection;
import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    // --------------------------
    // CREATE
    // --------------------------
    public void createProduct(Product p) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                productDao.create(p, conn);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw new Exception("Error creating product: " + e.getMessage(), e);
        }
    }

    // --------------------------
    // READ by ID
    // --------------------------
    public Product getProduct(Long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {

            return productDao.read(id, conn);

        } catch (Exception e) {
            throw new Exception("Error reading product: " + e.getMessage(), e);
        }
    }

    // --------------------------
    // READ ALL
    // --------------------------
    public List<Product> getAllProducts() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {

            return productDao.read_all(conn);

        } catch (Exception e) {
            throw new Exception("Error fetching products: " + e.getMessage(), e);
        }
    }

    // --------------------------
    // UPDATE
    // --------------------------
    public void updateProduct(Product p) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                productDao.update(p, conn);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw new Exception("Error updating product: " + e.getMessage(), e);
        }
    }

    // --------------------------
    // SOFT DELETE
    // --------------------------
    public void deleteProduct(Long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                productDao.soft_delete(id, conn);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw new Exception("Error deleting product: " + e.getMessage(), e);
        }
    }
}
