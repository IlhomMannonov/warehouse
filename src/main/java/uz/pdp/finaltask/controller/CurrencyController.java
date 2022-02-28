package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.CurrencyDto;
import uz.pdp.finaltask.servise.CurrencyServise;
import uz.pdp.finaltask.utils.AppConstants;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyServise currencyServise;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody CurrencyDto currencyDto) {
        ApiResponce apiResponce = currencyServise.saveOrEdit(currencyDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int size,
            @RequestParam(value = "all") boolean all) throws PageSizeExeption {

        ApiResponce apiResponce = currencyServise.getAll(page, size, all);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);

    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        ApiResponce apiResponce = currencyServise.getById(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @DeleteMapping("/delete/{id}")
    private HttpEntity<?>delete(@PathVariable Long id){
        ApiResponce apiResponce = currencyServise.delete(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);

    }
}
