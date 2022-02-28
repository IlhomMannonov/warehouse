package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.MeasurmentDto;
import uz.pdp.finaltask.servise.MeasurementServise;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementServise measurementServise;
    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody MeasurmentDto measurementDto) {
        ApiResponce apiResponce = measurementServise.saveOrEdit(measurementDto);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }

    @GetMapping("/get") // hammasini yoki bitasini
    public HttpEntity<?> get(@RequestParam(value = "id",required = false) Long id){
        ApiResponce apiResponce = measurementServise.get(id);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable Long id){
        ApiResponce apiResponce = measurementServise.delete(id);
        return ResponseEntity.status(apiResponce.isSuccess()?203:409).body(apiResponce);
    }

}
