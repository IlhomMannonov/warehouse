package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.Currency;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.CurrencyDto;
import uz.pdp.finaltask.repo.CurrencyRepo;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyServise {
    @Autowired
    CurrencyRepo currencyRepo;

    public ApiResponce saveOrEdit(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        if (currencyDto.getId() != null) {
            currency = currencyRepo.getById(currencyDto.getId());
            currency.setName(currency.getName());
            currency.setActive(currencyDto.isActive());
        } else {
            currency = dtoToCurruncy(currencyDto);
        }
        currencyRepo.save(currency);
        return new ApiResponce(true, currencyDto.getId() != null ? "Edited" : "Saved");
    }

    public ApiResponce getAll(int page, int size, boolean all) throws PageSizeExeption {
        List<CurrencyDto> currencyDtos = new ArrayList<>();
        if (all) {
            currencyDtos = currencyRepo.findAll().stream()
                    .map(this::currencyToDto)
                    .collect(Collectors.toList());
        } else {
            currencyDtos = currencyRepo.findAll(CommandUtils.ascDescPageable(page, size, false))
                    .stream()
                    .map(this::currencyToDto)
                    .collect(Collectors.toList());
        }
        return new ApiResponce(true, "ok", currencyDtos);
    }

    public ApiResponce getById(Long id) {
        final Optional<Currency> op = currencyRepo.findById(id);
        if (op.isEmpty())
            return new ApiResponce(false, "topilmadi");

        return new ApiResponce(true, "ok", op.get());
    }


    public ApiResponce delete(Long id) {
        try {
            currencyRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponce(false, "Not Deleted");
        }
        return new ApiResponce(false, "Deleted");

    }

    public Currency dtoToCurruncy(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currency.setActive(currencyDto.isActive());
        return currency;
    }

    public CurrencyDto currencyToDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(currency.getId());
        currencyDto.setName(currency.getName());
        currencyDto.setActive(currency.isActive());
        return currencyDto;
    }
}
