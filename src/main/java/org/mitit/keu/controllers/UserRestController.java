package org.mitit.keu.controllers;

import org.mitit.keu.core.service.MilitaryManService;
import org.mitit.keu.core.service.UserService;
import org.mitit.keu.core.entities.MilitaryMan;
import org.mitit.keu.core.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserRestController {
    public UserService userService;
    public MilitaryManService militaryManService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with id: " + id + " is not found"));
    }

    @GetMapping("/roomCount/{militaryManId}")
    public int calculateRoomCount(@PathVariable Long militaryManId) {
        MilitaryMan militaryMan = militaryManService.findById(militaryManId).orElseThrow(
                () -> new IllegalArgumentException("Military man with id: " + militaryManId + " is not found"));
        return militaryManService.calculateRoomCount(militaryMan);
    }

    @GetMapping("/compensation/{militaryManId}")
    public double calculateCompensation(@PathVariable Long militaryManId) {
        MilitaryMan militaryMan = militaryManService.findById(militaryManId).orElseThrow(
                () -> new IllegalArgumentException("Military man with id: " + militaryManId + " is not found"));
        return militaryManService.calculateCompensation(militaryMan);
    }

    @GetMapping("/housingRentCompensation/{militaryManId}")
    public double calculateHousingRentCompensation(@PathVariable Long militaryManId) {
        MilitaryMan militaryMan = militaryManService.findById(militaryManId).orElseThrow(
                () -> new IllegalArgumentException("Military man with id: " + militaryManId + " is not found"));
        return militaryManService.calculateHousingRentCompensation(militaryMan);
    }
}
