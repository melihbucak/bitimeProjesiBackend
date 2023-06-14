package com.bitirme.BitirmeProjesi;

import com.bitirme.BitirmeProjesi.entity.Teacher;
import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.repo.CourseRepository;
import com.bitirme.BitirmeProjesi.repo.StudentCourseRepository;
import com.bitirme.BitirmeProjesi.repo.TeacherRepository;
import com.bitirme.BitirmeProjesi.repo.UserRepository;
import com.bitirme.BitirmeProjesi.service.StudentService;
import com.bitirme.BitirmeProjesi.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class TeacherTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentCourseRepository studentCourseRepository;

    @Mock
    private TeacherService teacherService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        teacherService = new TeacherService(teacherRepository, userRepository,courseRepository, studentCourseRepository);
    }
    @Test
    public void saveOgretmenTest() {
        // Örnek veriler oluşturun
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setOgretmenAdi("John");
        teacher.setOgretmenSoyadi("Doe");
        teacher.setOgretmen_no(12345L);
        teacher.setOgretmen_TC(123456789L);

        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");
        user.setPassword("password");
        teacher.setUser(user);
        // Örnek davranışları belirtin
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        // Öğretmeni kaydet
        Teacher savedTeacher = teacherService.saveOgretmen(teacher);
        // Sonucu doğrula
        assertEquals(1L, savedTeacher.getId());
        assertEquals("John", savedTeacher.getOgretmenAdi());
        assertEquals("Doe", savedTeacher.getOgretmenSoyadi());
        assertEquals(12345L, savedTeacher.getOgretmen_no());
        assertEquals(123456789L, savedTeacher.getOgretmen_TC());
        verify(teacherRepository, times(1)).save(teacher);
    }
}
