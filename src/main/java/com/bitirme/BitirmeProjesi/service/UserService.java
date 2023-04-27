package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.repo.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //BCrypt kullanarak kullanici sifreleri guvenli bir sekilde kaydedilir.
    public User createUser(User user) {
        String plainPassword = user.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

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
            if (rol&&result) {
                stringObjectMap.put("status", true);
                stringObjectMap.put("result", user1);
                return new ResponseEntity(stringObjectMap, HttpStatus.OK);
            }
        }
        stringObjectMap.put("status", false);
        stringObjectMap.put("message", "Kullanıcı adı, rolü ya da parola yanlış");
        return new ResponseEntity(stringObjectMap, HttpStatus.BAD_REQUEST);
    }
}
