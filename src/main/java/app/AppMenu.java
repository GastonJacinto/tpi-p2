package app;

import entities.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final ProductService productService;
    private final Scanner scanner;

    public AppMenu(ProductService productService) {
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String option;
        do {
            showMainMenu();
            option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "1":
                    createProduct();
                    break;
                case "2":
                    showProductById();
                    break;
                case "3":
                    listProducts();
                    break;
                case "4":
                    updateProduct();
                    break;
                case "5":
                    deleteProduct();
                    break;
                case "6":
                    searchProductsByName();
                    break;
                case "X":
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();

        } while (!"X".equals(option));
    }

    private void showMainMenu() {
        System.out.println("=====================================");
        System.out.println("   MENÚ PRINCIPAL - PRODUCTOS");
        System.out.println("=====================================");
        System.out.println("1) Crear producto");
        System.out.println("2) Buscar producto por ID");
        System.out.println("3) Listar productos");
        System.out.println("4) Actualizar producto");
        System.out.println("5) Eliminar producto (baja lógica)");
        System.out.println("6) Buscar productos por nombre");
        System.out.println("X) Salir");
        System.out.print("Seleccione una opción: ");
    }

    // ===== CRUD Producto (Entidad A) =====

    private void createProduct() {
        System.out.println("--- Crear producto ---");

        System.out.print("Nombre: ");
        String name = scanner.nextLine().trim();

        System.out.print("Descripción: ");
        String description = scanner.nextLine().trim();

        BigDecimal price = readBigDecimal("Precio (> 0): ");
        BigDecimal weight = readNullableBigDecimal("Peso (ENTER si no aplica): ");

        try {
            productService.createProduct(name, description, price, weight, null);
            System.out.println("Producto creado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
        }
    }

    private void showProductById() {
        System.out.println("--- Buscar producto por ID ---");
        Long id = readLong("ID del producto: ");

        try {
            Product p = productService.findById(id);
            if (p == null) {
                System.out.println("No se encontró un producto con ID = " + id);
            } else {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        }
    }

    private void listProducts() {
        System.out.println("--- Listar productos ---");
        try {
            List<Product> products = productService.findAll();
            if (products.isEmpty()) {
                System.out.println("No hay productos para mostrar.");
            } else {
                products.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    private void updateProduct() {
        System.out.println("--- Actualizar producto ---");
        Long id = readLong("ID del producto a actualizar: ");

        try {
            Product existing = productService.findById(id);
            if (existing == null) {
                System.out.println("No existe un producto con ese ID.");
                return;
            }

            System.out.println("Producto actual:");
            System.out.println(existing);

            System.out.print("Nuevo nombre (ENTER para mantener '" + existing.getName() + "'): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = existing.getName();
            }

            System.out.print("Nueva descripción (ENTER para mantener): ");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                description = existing.getDescription();
            }

            BigDecimal price = readBigDecimalWithDefault(
                    "Nuevo precio (ENTER para mantener " + existing.getPrice() + "): ",
                    existing.getPrice()
            );

            BigDecimal weight = readNullableBigDecimalWithDefault(
                    "Nuevo peso (ENTER para mantener " + existing.getWeight() + "): ",
                    existing.getWeight()
            );

            productService.updateProduct(
                    id, name, description, price, weight, existing.getBarCodeId()
            );
            System.out.println("Producto actualizado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.println("--- Eliminar producto (baja lógica) ---");
        Long id = readLong("ID del producto a eliminar: ");

        try {
            productService.deleteProduct(id);
            System.out.println("Producto eliminado (baja lógica) correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    private void searchProductsByName() {
        System.out.println("--- Buscar productos por nombre ---");
        System.out.print("Texto a buscar en el nombre: ");
        String term = scanner.nextLine().trim();

        try {
            List<Product> results = productService.findByName(term);
            if (results.isEmpty()) {
                System.out.println("No se encontraron productos con ese criterio.");
            } else {
                results.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error en la búsqueda: " + e.getMessage());
        }
    }

    // ===== Helpers de lectura =====

    private Long readLong(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido. Intente nuevamente.");
            }
        }
    }

    private BigDecimal readBigDecimal(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                BigDecimal value = new BigDecimal(input);
                if (value.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("El valor debe ser mayor a 0.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor decimal inválido. Intente nuevamente.");
            }
        }
    }

    private BigDecimal readNullableBigDecimal(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Valor decimal inválido. Intente nuevamente o presione ENTER para omitir.");
            }
        }
    }

    private BigDecimal readBigDecimalWithDefault(String message, BigDecimal defaultValue) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                BigDecimal value = new BigDecimal(input);
                if (value.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("El valor debe ser mayor a 0.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor decimal inválido. Intente nuevamente.");
            }
        }
    }

    private BigDecimal readNullableBigDecimalWithDefault(String message, BigDecimal defaultValue) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Valor decimal inválido. Intente nuevamente o presione ENTER para mantener el valor actual.");
            }
        }
    }
}