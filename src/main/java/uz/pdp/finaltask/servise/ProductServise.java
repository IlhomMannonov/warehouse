package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.Product;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.ProductDto;
import uz.pdp.finaltask.repo.AttachmentRepo;
import uz.pdp.finaltask.repo.CategoryRepo;
import uz.pdp.finaltask.repo.MeasurementRepo;
import uz.pdp.finaltask.repo.ProductRepo;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServise {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    MeasurementRepo measurementRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    AttachmentRepo attachmentRepo;

    public ApiResponce saveOrEdit(ProductDto productDto) {
        Product product = dtoToProduct(productDto);
        try {
            if (!product.getMeasurment().isActive()) {
                return new ApiResponce(false, "measuerement activ emas");
            }
            productRepo.save(product);


        } catch (Exception e) {
            return new ApiResponce(true, productDto.getId() == null ? "Not saved" : "Not edited");

        }
        return new ApiResponce(true, productDto.getId() == null ? "saved" : "edited");

    }

    public Product dtoToProduct(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) {
            product = productRepo.getById(productDto.getId());
        }
        product = genearateProductfromDTO(productDto);

        return product;
    }

    public ApiResponce getAll(Long categoryId, int page, int size, boolean all) throws PageSizeExeption {
        List<ProductDto> productDtoList;

        if (categoryId != null) {
            if (all) {
                productDtoList = productRepo.findByCategoryId(categoryId)
                        .stream()
                        .map(this::productToDto)
                        .collect(Collectors.toList());
            } else {
                productDtoList = productRepo.findByCategoryId(categoryId, CommandUtils.ascDescPageable(page, size, false))
                        .stream()
                        .map(this::productToDto)
                        .collect(Collectors.toList());
            }

        } else {
            productDtoList = productRepo.findAll()
                    .stream()
                    .map(this::productToDto)
                    .collect(Collectors.toList());
        }
        return new ApiResponce(true, "ok", productDtoList);
    }

    public ProductDto productToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPhotoId(product.getPhoto().getId());
        productDto.setMeasurementId(product.getMeasurment().getId());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    public ApiResponce delete(Long id) {
        try {
            productRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponce(false, "Not Deleted");
        }
        return new ApiResponce(true, "Deleted");

    }

    public Product genearateProductfromDTO(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setMeasurment(measurementRepo.getById(productDto.getMeasurementId()));
        product.setCategory(categoryRepo.getById(productDto.getCategoryId()));
        product.setCode(UUID.randomUUID().toString());
        product.setActive(productDto.isActive());
        product.setPhoto(attachmentRepo.getById(productDto.getPhotoId()));
        return product;
    }

}
