package uz.pdp.finaltask.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private boolean active;
}
