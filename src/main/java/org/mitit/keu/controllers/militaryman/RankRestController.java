package org.mitit.keu.controllers.militaryman;

import org.mitit.keu.core.service.RankService;
import org.mitit.keu.core.entities.Rank;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ranks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RankRestController {
    private final RankService rankService;


    @GetMapping("/")
    public List<Rank> findAll() {
        return rankService.findAll();
    }

    @GetMapping("/{id}")
    public Rank findById(@PathVariable Long id) {
        return rankService.findById(id);
    }

    @PostMapping("/")
    public Rank save(@RequestBody Rank rank) {
        return rankService.save(rank);
    }

//    @PostMapping("/all")
//    public void saveAll(Iterable<Rank> ranks) {
//        rankService.saveAll(ranks);
//    }
//
//    @DeleteMapping("/")
//    public void delete(@RequestBody Rank rank) {
//        rankService.delete(rank);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable Long id) {
//        rankService.deleteById(id);
//    }
//
//
//    @DeleteMapping("/all")
//    public void deleteAll() {
//        rankService.deleteAll();
//    }
}
