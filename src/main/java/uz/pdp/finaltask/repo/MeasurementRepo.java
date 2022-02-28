package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Measurment;

public interface MeasurementRepo extends JpaRepository<Measurment, Long> {

}
