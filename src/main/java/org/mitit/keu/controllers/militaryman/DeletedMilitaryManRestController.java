package org.mitit.keu.controllers.militaryman;

import org.mitit.keu.core.service.DeletedMilitaryManService;
import org.mitit.keu.core.entities.DeletedMilitaryMan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deletedMilitaryMen")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeletedMilitaryManRestController {

    private final DeletedMilitaryManService deletedMilitaryManService;

    public DeletedMilitaryManRestController(DeletedMilitaryManService deletedMilitaryManService) {
        this.deletedMilitaryManService = deletedMilitaryManService;
    }

    @GetMapping("/")
    public List<DeletedMilitaryMan> findAll() {
        return deletedMilitaryManService.findAll();
    }

    @GetMapping("/{id}")
    public DeletedMilitaryMan findById(@PathVariable Long id) {
        return deletedMilitaryManService.findById(id);
    }

    @PostMapping("/")
    public DeletedMilitaryMan add(@RequestBody DeletedMilitaryMan deletedMilitaryMan) {
        return deletedMilitaryManService.save(deletedMilitaryMan);
    }

//    @PostMapping("/all")
//    public void saveAll(Iterable<DeletedMilitaryMan> deletedMilitaryMen) {
//        deletedMilitaryManService.saveAll(deletedMilitaryMen);
//    }

}
