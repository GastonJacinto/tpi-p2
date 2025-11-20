package app;

import entities.*;
import service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final ProductService productService = new ProductService();
    private final BarcodeService barcodeService = new BarcodeService();
    private final BarcodeTypeService barcodeTypeService = new BarcodeTypeService();

    public void start() {
        while (true) {
            System.out.println("\n==== MAIN MENU ====");
            System.out.println("1) Products");
            System.out.println("2) Barcodes");
            System.out.println("3) Barcode Types");
            System.out.println("0) Exit");
            System.out.print("> ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> productMenu();
                case "2" -> barcodeMenu();
                case "3" -> barcodeTypeMenu();
                case "0" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ==========================================
    // PRODUCT MENU
    // ==========================================
    private void productMenu() {
        System.out.println("\n=== PRODUCT MENU ===");
        System.out.println("1) Create");
        System.out.println("2) Read by ID");
        System.out.println("3) Read All");
        System.out.println("4) Update");
        System.out.println("5) Delete");
        System.out.print("> ");

        String opt = scanner.nextLine();

        try {
            switch (opt) {
                case "1" -> createProduct();
                case "2" -> readProduct();
                case "3" -> listProducts();
                case "4" -> updateProduct();
                case "5" -> deleteProduct();
                default -> System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void createProduct() throws Exception {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Weight: ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Barcode ID (nullable): ");
        String bc = scanner.nextLine();
        Long barcodeId = bc.isBlank() ? null : Long.parseLong(bc);

        Product p = new Product(null, false, name, brand, category, price, weight, barcodeId);

        productService.createProduct(p);
        System.out.println("Product created.");
    }

    private void readProduct() throws Exception {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Product p = productService.getProduct(id);

        System.out.println(p != null ? p : "Not found.");
    }

    private void listProducts() throws Exception {
        List<Product> list = productService.getAllProducts();
        list.forEach(System.out::println);
    }

    private void updateProduct() throws Exception {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Product p = productService.getProduct(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("New name (" + p.getName() + "): ");
        p.setName(scanner.nextLine());

        System.out.print("New price (" + p.getPrice() + "): ");
        p.setPrice(Double.parseDouble(scanner.nextLine()));

        productService.updateProduct(p);
        System.out.println("Updated.");
    }

    private void deleteProduct() throws Exception {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        productService.deleteProduct(id);
        System.out.println("Deleted.");
    }

    // ==========================================
    // BARCODE MENU
    // ==========================================
    private void barcodeMenu() {
        System.out.println("\n=== BARCODE MENU ===");
        System.out.println("1) Create");
        System.out.println("2) Read by ID");
        System.out.println("3) Read All");
        System.out.println("4) Update");
        System.out.println("5) Delete");
        System.out.print("> ");

        String opt = scanner.nextLine();

        try {
            switch (opt) {
                case "1" -> createBarcode();
                case "2" -> readBarcode();
                case "3" -> listBarcodes();
                case "4" -> updateBarcode();
                case "5" -> deleteBarcode();
                default -> System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void createBarcode() throws Exception {
        System.out.print("Type code: ");
        String type = scanner.nextLine();

        System.out.print("Value: ");
        String value = scanner.nextLine();

        System.out.print("Metadata: ");
        String metadata = scanner.nextLine();

        Barcode b = new Barcode(
                null,
                false,
                type,
                value,
                LocalDate.now(),
                metadata
        );

        barcodeService.createBarcode(b);
        System.out.println("Barcode created.");
    }

    private void readBarcode() throws Exception {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Barcode b = barcodeService.getBarcode(id);
        System.out.println(b != null ? b : "Not found.");
    }

    private void listBarcodes() throws Exception {
        List<Barcode> list = barcodeService.getAllBarcodes();
        list.forEach(System.out::println);
    }

    private void updateBarcode() throws Exception {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Barcode b = barcodeService.getBarcode(id);
        if (b == null) {
            System.out.println("Barcode not found.");
            return;
        }

        System.out.print("New metadata (" + b.getMetadata() + "): ");
        b.setMetadata(scanner.nextLine());

        barcodeService.updateBarcode(b);
        System.out.println("Updated.");
    }

    private void deleteBarcode() throws Exception {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        barcodeService.deleteBarcode(id);
        System.out.println("Deleted.");
    }

    // ==========================================
    // BARCODE TYPE MENU
    // ==========================================
    private void barcodeTypeMenu() {
        System.out.println("\n=== BARCODE TYPE MENU ===");
        System.out.println("1) Create");
        System.out.println("2) Read by Code");
        System.out.println("3) Read All");
        System.out.println("4) Update");
        System.out.println("5) Delete");
        System.out.print("> ");

        String opt = scanner.nextLine();

        try {
            switch (opt) {
                case "1" -> createBarcodeType();
                case "2" -> readBarcodeType();
                case "3" -> listBarcodeTypes();
                case "4" -> updateBarcodeType();
                case "5" -> deleteBarcodeType();
                default -> System.out.println("Invalid option");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void createBarcodeType() throws Exception {
        System.out.print("Code: ");
        String code = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        BarcodeType bt = new BarcodeType(code, desc);

        barcodeTypeService.createBarcodeType(bt);
        System.out.println("Barcode Type created.");
    }

    private void readBarcodeType() throws Exception {
        System.out.print("Code: ");
        String code = scanner.nextLine();

        BarcodeType bt = barcodeTypeService.getBarcodeType(code);
        System.out.println(bt != null ? bt : "Not found.");
    }

    private void listBarcodeTypes() throws Exception {
        List<BarcodeType> list = barcodeTypeService.getAllBarcodeTypes();
        list.forEach(System.out::println);
    }

    private void updateBarcodeType() throws Exception {
        System.out.print("Code: ");
        String code = scanner.nextLine();

        BarcodeType bt = barcodeTypeService.getBarcodeType(code);
        if (bt == null) {
            System.out.println("Not found");
            return;
        }

        System.out.print("New description (" + bt.getDescription() + "): ");
        bt.setDescription(scanner.nextLine());

        barcodeTypeService.updateBarcodeType(bt);
        System.out.println("Updated.");
    }

    private void deleteBarcodeType() throws Exception {
        System.out.print("Code: ");
        String code = scanner.nextLine();

        barcodeTypeService.deleteBarcodeType(code);
        System.out.println("Deleted.");
    }
}
