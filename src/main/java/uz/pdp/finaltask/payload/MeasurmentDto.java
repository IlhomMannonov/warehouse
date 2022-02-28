package uz.pdp.finaltask.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurmentDto {
    private Long id;
    private String name;
    private boolean active;
}
