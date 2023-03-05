package com.bitirme.BitirmeProjesi.api;

import com.bitirme.BitirmeProjesi.entity.Dersler;
import com.bitirme.BitirmeProjesi.entity.Ogrenci;
import com.bitirme.BitirmeProjesi.entity.Ogretmen;
import com.bitirme.BitirmeProjesi.entity.StudentCourse;
import com.bitirme.BitirmeProjesi.repository.CourseRepository;
import com.bitirme.BitirmeProjesi.repository.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repository.StudentRepository;
import com.bitirme.BitirmeProjesi.repository.TeacherRepository;
import com.bitirme.BitirmeProjesi.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    public BitirmeController(ServiceInterface serviceInterface) {
//        this.serviceInterface = serviceInterface;
//    }

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

    @GetMapping("/saveDeneme/{dersAdi}/{ogretmenId}")
    public ResponseEntity<Dersler> saveDeneme(@PathVariable("dersAdi") String dersAdi, @PathVariable("ogretmenId") Long ogretmenId) {
        Dersler dersler = new Dersler();
        dersler.setDersAdi(dersAdi);
        dersler.setOgretmen(teacherRepository.getById(ogretmenId));
        Dersler dersler1 = userService.saveDers(dersler);
        return new ResponseEntity<>(dersler1, HttpStatus.CREATED);
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Object> addCourseToStudent(@PathVariable Long studentId, @RequestBody Map<String, Long> request) {
        Long ders_Kodu = request.get("ders_Kodu");
        Optional<Ogrenci> student = studentRepository.findById(studentId);
        Optional<Dersler> course = courseRepository.findById(ders_Kodu);

        if (student.isPresent() && course.isPresent()) {
            StudentCourse studentCourse = new StudentCourse(student.get(), course.get());
            studentCourseRepository.save(studentCourse);

            return new ResponseEntity<>("Course added to student.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student or course not found.", HttpStatus.NOT_FOUND);
        }
    }

}
