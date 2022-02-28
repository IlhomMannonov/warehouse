package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Input;

public interface InputRepo extends JpaRepository<Input, Long> {
}
