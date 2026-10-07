package com.bloodbank.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.backend.model.Donor;
import com.bloodbank.backend.repository.DonorRepository;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    // CREATE
    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    // READ ALL
    @GetMapping
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return donorRepository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Donor updateDonor(@PathVariable Long id, @RequestBody Donor donor) {

        Donor existingDonor = donorRepository.findById(id).orElse(null);

        if (existingDonor != null) {
            existingDonor.setName(donor.getName());
            existingDonor.setAge(donor.getAge());
            existingDonor.setBloodGroup(donor.getBloodGroup());
            existingDonor.setPhone(donor.getPhone());
            existingDonor.setAddress(donor.getAddress());
            existingDonor.setGender(donor.getGender());
            existingDonor.setEmail(donor.getEmail());

            return donorRepository.save(existingDonor);
        }

        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorRepository.deleteById(id);
        return "Donor deleted successfully";
    }
}