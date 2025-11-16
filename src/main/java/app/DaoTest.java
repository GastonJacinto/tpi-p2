package app;

import config.DatabaseConnection;
import dao.ProductDao;
import entities.Product;

import java.sql.Connection;

public class DaoTest {

    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnection()) {

            ProductDao dao = new ProductDao();

            System.out.println("=== TEST CREATE ===");
            Product p = new Product(
                    null,
                    false,
                    "Test product 1",
                    "Nike",
                    "Shoes",
                    120.50,
                    0.750,
                    null // sin barcode al inicio
            );
            dao.create(p, conn);
            System.out.println("CREATE OK");


            System.out.println("=== TEST READ ===");
            Product read = dao.read(1L, conn);
            System.out.println("READ RESULT: " + read);


            System.out.println("=== TEST READ ALL ===");
            var list = dao.read_all(conn);
            System.out.println("TOTAL PRODUCTS: " + list.size());


            System.out.println("=== TEST UPDATE ===");
            if (read != null) {
                read.setPrice(555.55);
                dao.update(read, conn);
                System.out.println("UPDATE OK");
            }


            System.out.println("=== TEST SOFT DELETE ===");
            dao.soft_delete(1L, conn);
            System.out.println("DELETE OK");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
