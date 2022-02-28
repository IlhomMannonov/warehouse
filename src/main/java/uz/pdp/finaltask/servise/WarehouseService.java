package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.Warehouse;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.repo.WarehouseRepo;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepo warehouseRepo;

    public ApiResponce saveOrEdit(Warehouse house) {
        Warehouse warehouse = new Warehouse();
        if (house.getId() != null) {
            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(house.getId());
            if (optionalWarehouse.isEmpty())
                return new ApiResponce(false, "Topilmadi");

            warehouse = optionalWarehouse.get();
            warehouse.setName(warehouse.getName());
            warehouse.setActive(warehouse.isActive());
        } else {
            warehouse = house;
        }
        warehouseRepo.save(warehouse);
        return new ApiResponce(true, house.getId() != null ? "saved" : "edited");
    }

    public ApiResponce getAll(int page, int size, boolean all) throws PageSizeExeption {
        List<Warehouse> warehouse = new ArrayList<>();
        if (all) {
            warehouse = warehouseRepo.findAll();
        } else {
            Page<Warehouse> pageable = warehouseRepo.findAll(CommandUtils.ascDescPageable(page, size, false));
            return new ApiResponce(true, "ok", pageable);
        }
        return new ApiResponce(true, "ok", warehouse);

    }

    public ApiResponce getById(Long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (optionalWarehouse.isEmpty())
            return new ApiResponce(true, "Warehouse toplimadi");

        return new ApiResponce(true, "ok", optionalWarehouse.get());


    }

    public ApiResponce delete(Long id) {
        try {
            warehouseRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponce(false, "Not deleted");
        }
        return new ApiResponce(true, "deleted");

    }
}
