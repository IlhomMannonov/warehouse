package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.entity.Supplier;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.SupplierDto;
import uz.pdp.finaltask.servise.SupplierServise;


@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierServise supplierServise;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody SupplierDto supplier) {
        ApiResponce apiResponce = supplierServise.saveOrEdit(supplier);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "all") boolean all
    ) throws PageSizeExeption {
        ApiResponce apiResponce = supplierServise.getAll(page, size, all);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @GetMapping("/getById/{id}")
    private HttpEntity<?> getByid(@PathVariable Long id) {

        ApiResponce apiResponce = supplierServise.getById(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @GetMapping("/delete/{id}")
    private HttpEntity<?> delete(@PathVariable Long id) {

        ApiResponce apiResponce = supplierServise.delete(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 203 : 409).body(apiResponce);
    }
}
