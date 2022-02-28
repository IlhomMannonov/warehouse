package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
}
