package org.mitit.keu.core.service;

import lombok.RequiredArgsConstructor;
import org.mitit.keu.core.repositories.FinanceSourceRepository;
import org.mitit.keu.entities.FinanceSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceSourceService {
    private final FinanceSourceRepository financeSourceRepository;
    public List<FinanceSource> getAllFinanceSources(){
        return financeSourceRepository.findAll();
    }
    public FinanceSource getFinanceSourceById(Long id) {
        return financeSourceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Finance source with id: " + id + " is not found"));
    }

    public FinanceSource createFinanceSource(FinanceSource financeSource) {
        financeSourceRepository.save(financeSource);
        return financeSource;
    }

    public FinanceSource updateFinanceSource(Long id, FinanceSource financeSource) {
        financeSource.setId(id);
        financeSourceRepository.save(financeSource);
        return financeSource;
    }

    public FinanceSource deleteFinanceSource(Long id){
        FinanceSource financeSourceById = getFinanceSourceById(id);
        financeSourceRepository.deleteById(id);
        return financeSourceById;
    }

}
