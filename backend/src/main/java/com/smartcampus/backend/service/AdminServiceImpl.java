package com.smartcampus.backend.service;

import java.util.List;

import com.smartcampus.backend.entity.Admin;
import com.smartcampus.backend.repository.AdminRepository;
import org.springframework.stereotype.Service;
import com.smartcampus.backend.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminDTO saveAdmin(Admin admin) {

        Admin savedAdmin = adminRepository.save(admin);

        return new AdminDTO(
            savedAdmin.getId(),
            savedAdmin.getName(),
            savedAdmin.getEmail()
        );
    }

    @Override
    public List<AdminDTO> getAllAdmins() {

        return adminRepository.findAll()
                .stream()
                .map(admin -> new AdminDTO(
                        admin.getId(),
                        admin.getName(),
                        admin.getEmail()
                ))
                .toList();
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {

        Admin existingAdmin = adminRepository.findById(id).orElse(null);

        if (existingAdmin != null) {
            existingAdmin.setName(admin.getName());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setPassword(admin.getPassword());
            existingAdmin.setPhone(admin.getPhone());

            return adminRepository.save(existingAdmin);
        }

        return null;
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}