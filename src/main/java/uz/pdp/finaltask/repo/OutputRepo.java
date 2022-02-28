package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Output;

public interface OutputRepo extends JpaRepository<Output, Long> {
}
