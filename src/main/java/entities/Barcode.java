package entities;

import java.time.LocalDate;

public class Barcode {

    private Long id;
    private Boolean deleted;
    private String type_code;     // FK → barcode_type.code
    private String value;
    private LocalDate assigned_at;
    private String metadata;

    public Barcode() {
    }

    public Barcode(Long id, Boolean deleted, String type_code, String value,
                   LocalDate assigned_at, String metadata) {
        this.id = id;
        this.deleted = deleted;
        this.type_code = type_code;
        this.value = value;
        this.assigned_at = assigned_at;
        this.metadata = metadata;
    }

    // Getters & Setters

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

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getAssigned_at() {
        return assigned_at;
    }

    public void setAssigned_at(LocalDate assigned_at) {
        this.assigned_at = assigned_at;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Barcode{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", type_code='" + type_code + '\'' +
                ", value='" + value + '\'' +
                ", assigned_at=" + assigned_at +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
