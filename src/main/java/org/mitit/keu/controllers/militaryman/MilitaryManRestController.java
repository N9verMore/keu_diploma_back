package org.mitit.keu.controllers.militaryman;

import org.mitit.keu.core.service.MilitaryManService;
import org.mitit.keu.core.entities.MilitaryMan;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/find")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MilitaryManRestController {
    private MilitaryManService militaryManService;

    @GetMapping("/")
    public Page<MilitaryMan> findMilitaryMan(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "50") int size,
                                             @RequestParam(required = false) String searchQuery) {
        Pageable pageable;
        if (size == 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }
        return militaryManService.freeFind(searchQuery, pageable);
    }

    @GetMapping("/findToday")
    public Page<MilitaryMan> findToday(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "50") int size) {
        Pageable pageable;
        if (size == 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }
        return militaryManService.getAllToday(pageable);
    }
}
