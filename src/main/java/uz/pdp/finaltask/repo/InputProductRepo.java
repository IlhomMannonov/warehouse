package uz.pdp.finaltask.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.InputProduct;

public interface InputProductRepo extends JpaRepository<InputProduct, Long> {
    Page<InputProduct> findAllByInputId(Long input_id, Pageable pageable);
}
