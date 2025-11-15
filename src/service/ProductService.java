package service;

import entities.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    public void createProduct(String name, String description,
                              BigDecimal price, BigDecimal weight, Long barCodeId) {
        // TODO: implementar usando ProductDao y la base de datos
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Product findById(Long id) {
        // TODO: implementar
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Product> findAll() {
        // TODO: implementar
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void updateProduct(Long id, String name, String description,
                              BigDecimal price, BigDecimal weight, Long barCodeId) {
        // TODO: implementar
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void deleteProduct(Long id) {
        // TODO: implementar (baja lógica)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Product> findByName(String term) {
        // TODO: implementar búsqueda por nombre
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
