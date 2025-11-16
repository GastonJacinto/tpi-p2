package entities;

public class Product {

    private Long id;
    private Boolean deleted;
    private String name;
    private String brand;
    private String category;
    private Double price;
    private Double weight;
    private Long bar_code_id; // FK → barcode.id

    public Product() {
    }

    public Product(Long id, Boolean deleted, String name, String brand, String category,
                   Double price, Double weight, Long bar_code_id) {
        this.id = id;
        this.deleted = deleted;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.bar_code_id = bar_code_id;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getBar_code_id() {
        return bar_code_id;
    }

    public void setBar_code_id(Long bar_code_id) {
        this.bar_code_id = bar_code_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", bar_code_id=" + bar_code_id +
                '}';
    }
}
