package com.bitirme.BitirmeProjesi.service.impl;

import com.bitirme.BitirmeProjesi.entity.*;
import com.bitirme.BitirmeProjesi.repository.*;
import com.bitirme.BitirmeProjesi.service.UserService;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
//@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    private StudentCourseRepository studentCourseRepository;
    private UserRepository userRepository;
    private YoklamaRepository yoklamaRepository;

    //BCrypt kullanarak kullanici sifreleri guvenli bir sekilde kaydedilir.
    @Override
    public User createUser(User user) {
        String plainPassword = user.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity checkLogin(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        Map<String, Object> stringObjectMap = new LinkedHashMap<>();
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            String dbPassword = user1.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean result = encoder.matches(user.getPassword(), dbPassword);
            String role = String.valueOf(user.getRole());
            boolean rol = false;
            if (role == user1.getRole().toString()) {
                rol = true;
            }
            if (result && rol) {
                stringObjectMap.put("status", true);
                stringObjectMap.put("result", user1);
                return new ResponseEntity(stringObjectMap, HttpStatus.OK);
            }
        }
        stringObjectMap.put("status", false);
        stringObjectMap.put("message", "Kullanıcı adı ya da parola yanlış");
        return new ResponseEntity(stringObjectMap, HttpStatus.BAD_REQUEST);
    }

    @Override
    public Ogrenci saveOgrenci(Ogrenci ogrenci) {
        return studentRepository.save(ogrenci);
    }

    @Override
    public Ogretmen saveOgretmen(Ogretmen ogretmen) {
        return teacherRepository.save(ogretmen);
    }

    @Override
    public Dersler saveDers(Dersler dersler) {
        return courseRepository.save(dersler);
    }

    @Override
    public List<Ogrenci> getOgrenciler() {
        return studentRepository.findAll();
    }

    @Override
    public List<Ogretmen> getOgretmenler() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Dersler> getDersler() {
        return courseRepository.findAll();
    }

    @Override
    public Ogrenci getOgrenciById(Long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public Ogretmen getOgretmenById(Long id) {
        return teacherRepository.getOne(id);
    }

    @Override
    public Yoklama saveYoklama(Yoklama yoklama) {
        LocalDate localDate = LocalDate.now();
        yoklama.setTarih(localDate);

        return yoklamaRepository.save(yoklama);
    }


}
