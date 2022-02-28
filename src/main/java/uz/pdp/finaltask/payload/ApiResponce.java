package uz.pdp.finaltask.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce {
    private boolean success;
    private String message;
    private Object object;
    private Long totalElement;

    public ApiResponce(Boolean success, String message, Object object) {
        this.success = success;
        this.message = message;
        this.object = object;
    }


    public ApiResponce(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
