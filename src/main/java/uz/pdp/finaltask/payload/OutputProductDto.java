package uz.pdp.finaltask.payload;


import lombok.Data;

@Data
public class OutputProductDto {
    private Long id;
    private ProductDto productDto;
    private Double amount;
    private Double price;

}
