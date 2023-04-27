package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.AdminDto;
import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.entity.Admin;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/saveAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        Admin admin1 = adminService.saveAdmin(admin);
        return new ResponseEntity<>(admin1, HttpStatus.CREATED);
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity updateAdmin(@RequestBody AdminDto dto){
        return adminService.updateAdminFromDto(dto);
    }

    @GetMapping("/getAdmins")
    public List<Admin> getAdmin() {
        List<Admin> adminList = adminService.getAdmins();
        return adminList;
    }
}
