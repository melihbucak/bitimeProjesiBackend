package com.bitirme.BitirmeProjesi;

import com.bitirme.BitirmeProjesi.enums.UserType;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.repo.StudentRepository;
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
    StudentRepository studentRepository;
    StudentService studentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void testSaveOgrenci() {

        var studentToSave = Student.builder()
                .ogrenciAdi("test")
                .ogrenciSoyadi("test")
                .ogrenci_TC(11111123L)
                .ogrenci_no(1234567890l)
                .build();

        var userToSave = User.builder()
                .username("test")
                .password("test")
                .role(UserType.OGRENCI)
                .build();

        studentToSave.setUser(userToSave);

        when(studentRepository.save(any(Student.class))).thenReturn(studentToSave);

        var actual = studentService.saveOgrenci(studentToSave);


        assertNotNull(actual);
        assertEquals(studentToSave.getId(),actual.getId());
        assertEquals(studentToSave.getOgrenci_no(),actual.getOgrenci_no());
        assertEquals(studentToSave.getOgrenci_TC(), actual.getOgrenci_TC());
        assertEquals(studentToSave.getOgrenciAdi(),actual.getOgrenciAdi());
        assertEquals(studentToSave.getOgrenciSoyadi(),actual.getOgrenciSoyadi());

        assertNotEquals("test", studentToSave.getUser().getPassword());
        assertEquals(UserType.OGRENCI, studentToSave.getUser().getRole());
        verify(studentRepository).save(studentToSave);
    }
}
