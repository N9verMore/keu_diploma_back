package org.mitit.keu.core.service;

import org.mitit.keu.core.repositories.ProvidedFlatRepository;
import org.mitit.keu.core.entities.ProvidedFlat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvidedFlatService {
    private final ProvidedFlatRepository providedFlatRepository;

    public ProvidedFlatService(ProvidedFlatRepository providedFlatRepository) {
        this.providedFlatRepository = providedFlatRepository;
    }

    public List<ProvidedFlat> findAll() {
        return providedFlatRepository.findAll();
    }

    public ProvidedFlat findById(Long id) {
        return providedFlatRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Provided flat with id: " + id + " is not found"));
    }

    public ProvidedFlat save(ProvidedFlat providedFlat) {
        return providedFlatRepository.save(providedFlat);
    }

    public void delete(ProvidedFlat providedFlat) {
        providedFlatRepository.delete(providedFlat);
    }

    public boolean existsById(Long id) {
        return providedFlatRepository.existsById(id);
    }

    public void deleteById(Long id) {
        providedFlatRepository.deleteById(id);
    }

    public ProvidedFlat saveAndFlush(ProvidedFlat providedFlat) {
        return providedFlatRepository.saveAndFlush(providedFlat);
    }

    public void flush() {
        providedFlatRepository.flush();
    }

    public void saveAll(Iterable<ProvidedFlat> providedFlats) {
        providedFlatRepository.saveAll(providedFlats);
    }

    public long count() {
        return providedFlatRepository.count();
    }

    public void deleteAll() {
        providedFlatRepository.deleteAll();
    }
}
