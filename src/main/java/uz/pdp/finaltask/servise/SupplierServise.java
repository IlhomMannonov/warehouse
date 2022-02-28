package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.Supplier;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.SupplierDto;
import uz.pdp.finaltask.repo.SupplierRepo;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServise {

    @Autowired
    SupplierRepo supplierRepo;


    public ApiResponce saveOrEdit(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        if (supplierDto.getId() != null) {
            supplier = supplierRepo.getById(supplierDto.getId());
        }
        supplier.setActive(supplierDto.isActive());
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());

        supplierRepo.save(supplier);
        return new ApiResponce(true, "ok");

    }

    public ApiResponce getAll(int page, int size, boolean all) throws PageSizeExeption {

        List<SupplierDto> suppliers = new ArrayList<>();
        try {

            if (all) {
                suppliers = supplierRepo.findAll()
                        .stream()
                        .map(this::supplierToDto)
                        .collect(Collectors.toList());
            } else {
                suppliers = supplierRepo.findAll(CommandUtils.ascDescPageable(page, size, false))
                        .stream()
                        .map(this::supplierToDto)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            return new ApiResponce(false, "Something error");
        }
        return new ApiResponce(true, "ok", suppliers);
    }

    public ApiResponce getById(Long id) {
        final Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty())
            return new ApiResponce(false, "topilmandi");

        return new ApiResponce(true, "ok", optionalSupplier.get());
    }

    public ApiResponce delete(Long id) {
        try {
            supplierRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponce(true, "Not Deleted");
        }
        return new ApiResponce(true, "Deleted");

    }

    public SupplierDto supplierToDto(Supplier supplier) {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(supplier.getId());
        supplierDto.setName(supplier.getName());
        supplierDto.setPhoneNumber(supplier.getPhoneNumber());
        supplierDto.setActive(supplier.isActive());
        return supplierDto;
    }
}
