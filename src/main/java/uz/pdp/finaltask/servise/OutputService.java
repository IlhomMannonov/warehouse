package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.finaltask.entity.Output;
import uz.pdp.finaltask.entity.OutputProduct;
import uz.pdp.finaltask.entity.Product;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.OutputDto;
import uz.pdp.finaltask.payload.OutputProductDto;
import uz.pdp.finaltask.repo.*;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class OutputService {

    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    OutputRepo outputRepo;
    @Autowired
    OutputProductRepo outputProductRepo;

    @Autowired
    CurrencyRepo currencyRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ProductRepo productRepo;

    @Transactional
    public ApiResponce add(OutputDto outputDto) {
        try {

            Output output = generateOutputFromDTO(outputDto);
            Output savedOutpuet = outputRepo.save(output);
            List<OutputProduct> outputProductList = outputDto.getOutputProductDtos()
                    .stream()
                    .map(item -> generateOutputProductFromDTO(item, savedOutpuet))
                    .collect(Collectors.toList());

            outputProductRepo.saveAll(outputProductList);
            return new ApiResponce(true, "Saved");
        } catch (Exception e) {
            return new ApiResponce(true, "not Saved");

        }

    }

    public ApiResponce delete(Long id) {
        try {
            outputProductRepo.deleteById(id);
            return new ApiResponce(true, "Deleted");
        } catch (Exception e) {
            return new ApiResponce(true, "not Deleted");

        }
    }

    public ApiResponce getAllPageable(Long outputProductId, int page, int size, boolean all) throws PageSizeExeption {

        if (all) {
            return new ApiResponce(true, "ok", outputProductRepo.findAll());
        }
        return new ApiResponce(true, "ok", outputProductRepo.findAllByOutputId(outputProductId, CommandUtils.ascDescPageable(page, size, false)));
    }

    public Output generateOutputFromDTO(OutputDto outputDto) {
        Output output = new Output();
        output.setCode(UUID.randomUUID().toString());
        output.setWarehouse(warehouseRepo.getById(outputDto.getWarehouseId()));
        output.setCurrency(currencyRepo.getById(outputDto.getCurrency().getId()));
        output.setClient(clientRepo.getById(outputDto.getClientId()));
        output.setFactureNumber(outputDto.getFactureNumber());
        return output;
    }

    public OutputProduct generateOutputProductFromDTO(OutputProductDto outputProductDto, Output output) {
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setOutput(output);
        outputProduct.setProduct(productRepo.getById(outputProductDto.getProductDto().getId()));
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setAmount(outputProductDto.getAmount());
        return outputProduct;
    }

}
