package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.entity.Warehouse;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.servise.WarehouseService;
import uz.pdp.finaltask.utils.AppConstants;

@RestController
@RequestMapping("/warehouse")
public class WarehouserController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody Warehouse warehouse) {

        ApiResponce apiResponce = warehouseService.saveOrEdit(warehouse);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int size,
            @RequestParam(value = "all") boolean all
    ) throws PageSizeExeption {

        ApiResponce apiResponce = warehouseService.getAll(page, size, all);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {

        ApiResponce apiResponce = warehouseService.delete(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }
}
