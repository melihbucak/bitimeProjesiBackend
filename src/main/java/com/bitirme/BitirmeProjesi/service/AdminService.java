package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.AdminDto;
import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.dtomapper.AdminDtoMapper;
import com.bitirme.BitirmeProjesi.dtomapper.TeacherDtoMapper;
import com.bitirme.BitirmeProjesi.entity.Admin;
import com.bitirme.BitirmeProjesi.entity.Teacher;
import com.bitirme.BitirmeProjesi.repo.AdminRepository;
import org.mapstruct.factory.Mappers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
        String plainPassword = admin.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        admin.setPassword(hashedPassword);
        admin.getUser().setPassword(hashedPassword);
        admin.getUser().setId(admin.getId());
        return adminRepository.save(admin);
    }

    public ResponseEntity updateAdminFromDto(AdminDto dto) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<Admin> oAdmin = adminRepository.findById(dto.getId());
        if (oAdmin.isPresent()) {
            Admin admin = oAdmin.get();
            hm.put("status", true);
            hm.put("result", oAdmin);
            String dbPassword = admin.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean result = encoder.matches(dto.getOldPassword(), dbPassword);

            if(result){
                String newPassword = dto.getNewPassword();
                String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                admin.setPassword(newHashedPassword);
                admin.getUser().setPassword(newHashedPassword);
            }
            else{
                hm.put("status", false);
                hm.put("message", "Eski şifrenizi yanlış girdiniz");
                return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }
            AdminDtoMapper adminDtoMapper = Mappers.getMapper(AdminDtoMapper.class);
            adminDtoMapper.updateAdminFromDto(dto, admin);
            adminRepository.save(admin);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put("status", false);
        hm.put("message", "Update From Dto Failed");
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
