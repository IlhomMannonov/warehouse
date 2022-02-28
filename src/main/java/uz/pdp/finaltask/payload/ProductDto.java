package uz.pdp.finaltask.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private Long photoId;
    private Long measurementId;
    private boolean active;
}
