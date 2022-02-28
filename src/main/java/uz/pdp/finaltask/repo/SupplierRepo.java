package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {
}
