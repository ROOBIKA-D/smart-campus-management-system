package com.smartcampus.backend.service;

import java.util.List;
import com.smartcampus.backend.entity.Admin;
import com.smartcampus.backend.dto.AdminDTO;

public interface AdminService {

    AdminDTO saveAdmin(Admin admin);

    List<AdminDTO> getAllAdmins();

    Admin getAdminById(Long id);

    Admin updateAdmin(Long id, Admin admin);

    void deleteAdmin(Long id);

}