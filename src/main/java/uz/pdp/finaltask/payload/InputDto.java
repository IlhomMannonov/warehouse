package uz.pdp.finaltask.payload;

import lombok.Data;
import uz.pdp.finaltask.entity.Warehouse;

import java.util.List;
@Data
public class InputDto {
    private Long warehouseId;
    private SupplierDto supplierDto;
    private CurrencyDto currencyDto;
    private String factureNumber;
    private List<InputProducyDto>inputProducyDtos;
}
