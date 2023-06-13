package com.bitirme.BitirmeProjesi;

import com.bitirme.BitirmeProjesi.entity.Teacher;
import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.enums.UserType;
import com.bitirme.BitirmeProjesi.repo.TeacherRepository;
import com.bitirme.BitirmeProjesi.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherTest {
    @Mock
    TeacherRepository teacherRepository;
    TeacherService teacherService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        teacherService = new TeacherService(teacherRepository);
    }

    @Test
    void testSaveOgretmen() {
        // Öğretmen verileri
        var ogretmenAdi = "Test";
        var ogretmenSoyadi = "Teacher";
        var ogretmenNo = 1234567890L;
        var ogretmenTC = 11111123L;
        var username = "testuser";
        var password = "testpassword";
        var role = UserType.OGRETMEN;

        // Mock öğretmen nesnesi
        var teacherToSave = new Teacher();
        teacherToSave.setOgretmenAdi(ogretmenAdi);
        teacherToSave.setOgretmenSoyadi(ogretmenSoyadi);
        teacherToSave.setOgretmen_no(ogretmenNo);
        teacherToSave.setOgretmen_TC(ogretmenTC);

        // Mock kullanıcı nesnesi
        var userToSave = new User();
        userToSave.setUsername(username);
        userToSave.setPassword(password);
        userToSave.setRole(role);

        teacherToSave.setUser(userToSave);

        // Mock dönüş nesnesi
        var savedTeacher = new Teacher();
        savedTeacher.setId(1L);
        savedTeacher.setOgretmenAdi(ogretmenAdi);
        savedTeacher.setOgretmenSoyadi(ogretmenSoyadi);
        savedTeacher.setOgretmen_no(ogretmenNo);
        savedTeacher.setOgretmen_TC(ogretmenTC);
        savedTeacher.setUser(userToSave);

        when(teacherRepository.save(any(Teacher.class))).thenReturn(savedTeacher);

        // Öğretmen kaydetme metodunu çağır
        Teacher actual = teacherService.saveOgretmen(teacherToSave);

        // Doğrulamalar
        assertNotNull(actual);
        assertEquals(savedTeacher.getId(), actual.getId());
        assertEquals(savedTeacher.getOgretmenAdi(), actual.getOgretmenAdi());
        assertEquals(savedTeacher.getOgretmenSoyadi(), actual.getOgretmenSoyadi());
        assertEquals(savedTeacher.getOgretmen_no(), actual.getOgretmen_no());
        assertEquals(savedTeacher.getOgretmen_TC(), actual.getOgretmen_TC());
        assertEquals(savedTeacher.getUser().getUsername(), actual.getUser().getUsername());
        assertEquals(savedTeacher.getUser().getRole(), actual.getUser().getRole());

        // Öğretmen nesnesi kaydedildi mi kontrol et
        ArgumentCaptor<Teacher> teacherCaptor = ArgumentCaptor.forClass(Teacher.class);
        verify(teacherRepository).save(teacherCaptor.capture());
        Teacher capturedTeacher = teacherCaptor.getValue();
        assertNotNull(capturedTeacher);
        assertEquals(ogretmenAdi, capturedTeacher.getOgretmenAdi());
        assertEquals(ogretmenSoyadi, capturedTeacher.getOgretmenSoyadi());
        assertEquals(ogretmenNo, capturedTeacher.getOgretmen_no());
        assertEquals(ogretmenTC, capturedTeacher.getOgretmen_TC());
        assertEquals(userToSave, capturedTeacher.getUser());
    }

}
