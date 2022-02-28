package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {

}
