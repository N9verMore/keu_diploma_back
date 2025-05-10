package org.mitit.keu.core.service;

import org.mitit.keu.core.repositories.DeletedMilitaryManRepository;
import org.mitit.keu.entities.DeletedMilitaryMan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletedMilitaryManService {
    private final DeletedMilitaryManRepository deletedMilitaryManRepository;

    public DeletedMilitaryManService(DeletedMilitaryManRepository deletedMilitaryManRepository) {
        this.deletedMilitaryManRepository = deletedMilitaryManRepository;
    }


    public List<DeletedMilitaryMan> findAll() {
        return deletedMilitaryManRepository.findAll();
    }

    public DeletedMilitaryMan findById(Long id) {
        return deletedMilitaryManRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Deleted military man with id: " + id + " is not found"));
    }

    public DeletedMilitaryMan save(DeletedMilitaryMan deletedMilitaryMan) {
        return deletedMilitaryManRepository.save(deletedMilitaryMan);
    }

    public void delete(DeletedMilitaryMan deletedMilitaryMan) {
        deletedMilitaryManRepository.delete(deletedMilitaryMan);
    }

    public boolean existsById(Long id) {
        return deletedMilitaryManRepository.existsById(id);
    }

    public void deleteById(Long id) {
        deletedMilitaryManRepository.deleteById(id);
    }

    public DeletedMilitaryMan saveAndFlush(DeletedMilitaryMan deletedMilitaryMan) {
        return deletedMilitaryManRepository.saveAndFlush(deletedMilitaryMan);
    }

    public void flush() {
        deletedMilitaryManRepository.flush();
    }

    public void saveAll(Iterable<DeletedMilitaryMan> deletedMilitaryMen) {
        deletedMilitaryManRepository.saveAll(deletedMilitaryMen);
    }

    public long count() {
        return deletedMilitaryManRepository.count();
    }

    public void deleteAll() {
        deletedMilitaryManRepository.deleteAll();
    }
}
