package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.Measurment;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.MeasurmentDto;
import uz.pdp.finaltask.repo.MeasurementRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeasurementServise {

    @Autowired
    MeasurementRepo measurementRepo;

    public ApiResponce saveOrEdit(MeasurmentDto measurementDto) {

        Measurment measurment = new Measurment();
        if (measurementDto.getId() == null) {
            measurment.setId(measurementDto.getId());
            measurment.setName(measurementDto.getName());
            measurment.setActive(measurementDto.isActive());

        } else {
            Optional<Measurment> optionalMeasurment = measurementRepo.findById(measurementDto.getId());

            if (optionalMeasurment.isEmpty())
                return new ApiResponce(false, "measurement topilmadi");

            measurment = optionalMeasurment.get();
            measurment.setActive(measurementDto.isActive());
            if (measurementDto.getName() != null)
                measurment.setName(measurementDto.getName());
        }
        measurementRepo.save(measurment);
        return new ApiResponce(true, measurementDto.getId() != null ? "Edited" : "Saved");

    }

    public ApiResponce get(Long id) {
        if (id != null) {
            Optional<Measurment> optionalMeasurment = measurementRepo.findById(id);
            if (optionalMeasurment.isEmpty())
                return new ApiResponce(false, "measurement topilmadi");
            MeasurmentDto measurementDto = new MeasurmentDto();
            measurementDto.setActive(optionalMeasurment.get().isActive());
            measurementDto.setName(optionalMeasurment.get().getName());
            measurementDto.setId(id);
            return new ApiResponce(true, "ok", measurementDto);
        }
        List<MeasurmentDto> all = measurementRepo.findAll()
                .stream()
                .map(this::measurementToDTO)
                .collect(Collectors.toList());
        return new ApiResponce(true, "ok", all);

    }

    public ApiResponce delete(Long id) {
        try {
            measurementRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponce(false, "Not deleted");
        }
        return new ApiResponce(false, "deleted");

    }

    public MeasurmentDto measurementToDTO(Measurment measurment) {
        MeasurmentDto measurmentDto = new MeasurmentDto();
        measurmentDto.setId(measurment.getId());
        measurmentDto.setName(measurment.getName());
        measurmentDto.setActive(measurment.isActive());
        return measurmentDto;
    }

}


