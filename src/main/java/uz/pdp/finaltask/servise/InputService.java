package uz.pdp.finaltask.servise;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.*;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.InputDto;
import uz.pdp.finaltask.payload.InputProducyDto;
import uz.pdp.finaltask.repo.*;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InputService {
    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    InputRepo inputRepo;

    @Autowired
    InputProductRepo inputProductRepo;

    @Autowired
    ProductServise productServise;

    public ApiResponce add(InputDto inputDto) {
        try {

            Input savedInput = inputRepo.save(generateInputFromDTO(inputDto));
            List<InputProduct> inputProducts = inputDto.getInputProducyDtos()
                    .stream()
                    .map(item -> generateInputProductFromDTO(item, savedInput))
                    .collect(Collectors.toList());
            inputProductRepo.saveAll(inputProducts);
        } catch (Exception e) {
            return new ApiResponce(false, "Error");
        }
        return new ApiResponce(true, "Added");

    }

    public ApiResponce getInputProductByInputId(Long inputId, int page, int size, boolean all) throws PageSizeExeption {
        long totalElement = 0;

        if (all) {
            return new ApiResponce(true, "ok", inputProductRepo.findAll());
        }
        return new ApiResponce(true, "ok", inputProductRepo.findAllByInputId(inputId, CommandUtils.ascDescPageable(page, size, false)));
    }

    public Input generateInputFromDTO(InputDto inputDto) {

        Input input = new Input();
        input.setWarehouse(warehouseRepo.getById(inputDto.getWarehouseId()));
        input.setCurrency(currencyRepo.getById(inputDto.getCurrencyDto().getId()));
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setSupplier(supplierRepo.getById(inputDto.getSupplierDto().getId()));
        input.setCode(UUID.randomUUID().toString());
        return input;
    }


    public InputProduct generateInputProductFromDTO(InputProducyDto inputProducyDto, Input input) {
        Product product = productServise.genearateProductfromDTO(inputProducyDto.getProductDto());
        InputProduct inputProduct = new InputProduct(
                product,
                inputProducyDto.getAmount(),
                inputProducyDto.getPrice(),
                null, input
        );
        return inputProduct;


    }


    public ApiResponce delete(Long id) {
        try {
            inputProductRepo.deleteById(id);
            return new ApiResponce(false, "Deleted");
        } catch (Exception e) {
            return new ApiResponce(false, "Not Deleted");
        }
    }
}
