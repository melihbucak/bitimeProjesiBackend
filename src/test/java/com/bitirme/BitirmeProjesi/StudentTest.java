package com.bitirme.BitirmeProjesi;

import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.enums.UserType;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentRepository;
import com.bitirme.BitirmeProjesi.repo.UserRepository;
import com.bitirme.BitirmeProjesi.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private StudentCourseRepository studentCourseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(studentRepository, userRepository, studentCourseRepository);
    }
    void testSaveStudent() {
        // Öğrenci verilerini hazırla
        Student studentToSave = Student.builder()
                .ogrenciAdi("Test")
                .ogrenciSoyadi("Student")
                .ogrenci_no(1234567890L)
                .ogrenci_TC(11111111111L)
                .build();

        // Kullanıcı verilerini hazırla
        User userToSave = User.builder()
                .username("testuser")
                .password("testpassword")
                .role(UserType.OGRENCI)
                .build();

        // Öğrenci ile kullanıcıyı ilişkilendir
        studentToSave.setUser(userToSave);

        // Öğrenci kaydetme işlemi için sahte bir dönüş değeri tanımla
        when(studentRepository.save(any(Student.class))).thenReturn(studentToSave);

        // Öğrenci kaydetme işlemini gerçekleştir
        Student savedStudent = studentService.saveOgrenci(studentToSave);

        // Gerçekleşen işlemleri doğrula
        assertNotNull(savedStudent);
        assertEquals(studentToSave.getId(), savedStudent.getId());
        assertEquals(studentToSave.getOgrenci_no(), savedStudent.getOgrenci_no());
        assertEquals(studentToSave.getOgrenci_TC(), savedStudent.getOgrenci_TC());
        assertEquals(studentToSave.getOgrenciAdi(), savedStudent.getOgrenciAdi());
        assertEquals(studentToSave.getOgrenciSoyadi(), savedStudent.getOgrenciSoyadi());

        assertNotEquals("testpassword", savedStudent.getUser().getPassword());
        assertEquals(UserType.OGRENCI, savedStudent.getUser().getRole());

        // Öğrenci kaydetme işleminin çağrıldığını doğrula
        verify(studentRepository).save(studentToSave);
    }
}
