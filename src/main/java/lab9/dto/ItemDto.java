package lab9.dto;

public class ItemDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Long manufacturerId;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Long getManufacturerId() { return manufacturerId; }

    public void setManufacturerId(Long manufacturerId) { this.manufacturerId = manufacturerId; }
}
