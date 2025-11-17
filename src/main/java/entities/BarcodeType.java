package entities;

public class BarcodeType {

    private String code;          // PK
    private String description;

    public BarcodeType() {
    }

    public BarcodeType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getters & Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BarcodeType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
