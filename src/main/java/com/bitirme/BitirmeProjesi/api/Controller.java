package com.bitirme.BitirmeProjesi.api;

import com.bitirme.BitirmeProjesi.entity.*;
import com.bitirme.BitirmeProjesi.repository.CourseRepository;
import com.bitirme.BitirmeProjesi.repository.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repository.StudentRepository;
import com.bitirme.BitirmeProjesi.repository.TeacherRepository;
import com.bitirme.BitirmeProjesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@AllArgsConstructor
public class Controller {
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    //cors error solved!
    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            //            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:3000");
            }
        };
    }


    @PostMapping("/checkLogin")
    public ResponseEntity checkLogin(@RequestBody User user) {
        ResponseEntity user1 = userService.checkLogin(user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Ogrenci> saveOgrenci(@RequestBody Ogrenci ogrenci) {
        Ogrenci ogrenci1 = userService.saveOgrenci(ogrenci);
        return new ResponseEntity<>(ogrenci1, HttpStatus.CREATED);
    }

    @PostMapping("/saveTeacher")
    public ResponseEntity<Ogretmen> saveOgretmen(@RequestBody Ogretmen ogretmen) {
        Ogretmen ogretmen1 = userService.saveOgretmen(ogretmen);
        return new ResponseEntity<>(ogretmen1, HttpStatus.CREATED);
    }

    @PostMapping("/saveCourse")
    public ResponseEntity<Dersler> saveDers(@RequestBody Dersler dersler) {

        Dersler dersler1 = userService.saveDers(dersler);
        return new ResponseEntity<>(dersler1, HttpStatus.CREATED);
    }

//    @GetMapping("/saveDeneme/{dersAdi}/{ogretmenId}")
//    public ResponseEntity<Dersler> saveDeneme(@PathVariable("dersAdi") String dersAdi, @PathVariable("ogretmenId") Long ogretmenId) {
//        Dersler dersler = new Dersler();
//        dersler.setDersAdi(dersAdi);
//        dersler.setOgretmen(teacherRepository.getById(ogretmenId));
//        Dersler dersler1 = userService.saveDers(dersler);
//        return new ResponseEntity<>(dersler1, HttpStatus.CREATED);
//    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Object> addCourseToStudent(@PathVariable Long studentId, @RequestBody Map<String, Long> request) {
        Long ders_Kodu = request.get("ders_Kodu");
        Optional<Ogrenci> student = studentRepository.findById(studentId);
        Optional<Dersler> course = courseRepository.findById(ders_Kodu);

        if (student.isPresent() && course.isPresent()) {
            StudentCourse studentCourse = new StudentCourse(student.get(), course.get());
            studentCourseRepository.save(studentCourse);

            return new ResponseEntity<>(studentCourse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student or course not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/yoklama")
    public ResponseEntity<Yoklama> saveYoklama(@RequestBody Yoklama yoklama) throws Exception {
        Yoklama yoklama1 = userService.saveYoklama(yoklama);
        return new ResponseEntity<>(yoklama1, HttpStatus.CREATED);
    }

    @GetMapping("/ogrenciListele")
    public ResponseEntity<List<Ogrenci>> getStudents() {
        List<Ogrenci> ogrenciList = userService.getOgrenciler();
        return ResponseEntity.ok(ogrenciList);
    }

    @GetMapping("/ogretmenListele")
    public ResponseEntity<List<Ogretmen>> getOgretmenler() {
        List<Ogretmen> ogretmenList = userService.getOgretmenler();
        return ResponseEntity.ok(ogretmenList);
    }

    @GetMapping("/dersListele")
    public ResponseEntity<List<Dersler>> getDersler() {
        List<Dersler> derslerList = userService.getDersler();
        return ResponseEntity.ok(derslerList);
    }

    @GetMapping("/ogrenciListeleById/{id}")
    public ResponseEntity<Ogrenci> getOgrenciById(@PathVariable("id") Long id) {
        Ogrenci ogrenci = userService.getOgrenciById(id);
        return ResponseEntity.ok(ogrenci);
    }

    @GetMapping("/ogretmenListeleById/{id}")
    public ResponseEntity<Ogretmen> getOgretmenById(@PathVariable("id") Long id) {
        Ogretmen ogretmen = userService.getOgretmenById(id);
        return ResponseEntity.ok(ogretmen);
    }

//    @GetMapping("/ogrenciListeleById/{id}")
//    public ResponseEntity<StudentCourse> getOgrenciByIdAndDersler(@PathVariable("id") Long id) {
//        Ogrenci ogrenci = userService.getOgrenciByIdAndDersler(id);
//        return ResponseEntity.ok(ogrenci);
//    }

}
