package com.smartcampus.backend.controller;

import java.util.List;

import com.smartcampus.backend.entity.Admin;
import com.smartcampus.backend.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.smartcampus.backend.dto.AdminDTO;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping
    public AdminDTO saveAdmin(@Valid @RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id,
                         @Valid @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin deleted successfully!";
    }
}