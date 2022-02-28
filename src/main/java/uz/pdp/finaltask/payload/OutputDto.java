package uz.pdp.finaltask.payload;

import lombok.Data;
import uz.pdp.finaltask.entity.Client;
import uz.pdp.finaltask.entity.Currency;

import java.util.List;

@Data
public class OutputDto {

    private Long id;
    private Long warehouseId;
    private Currency currency;
    private String factureNumber;
    private Long clientId;
    private List<OutputProductDto> outputProductDtos;
}
