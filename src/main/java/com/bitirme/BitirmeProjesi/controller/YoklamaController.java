package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.dto.YoklamaDto;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import com.bitirme.BitirmeProjesi.service.YoklamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/yoklama")
public class YoklamaController {

    @Autowired
    private YoklamaService yoklamaService;

    @PostMapping("/saveYoklama")
    public ResponseEntity saveYoklama(@RequestBody YoklamaDto dto) throws Exception {
        return yoklamaService.saveYoklama(dto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Yoklama>> getYoklama(@PathVariable("id") Long id) {
        return ResponseEntity.ok(yoklamaService.getStudentAttendance(id));
    }

    //Teacher can make changes about student's attendance
    @PutMapping("/updateAttendance")
    public ResponseEntity updateYoklama(@RequestBody YoklamaDto dto) {
        return yoklamaService.updateAttendanceFromDto(dto);
    }

    @GetMapping("/getAll")
    public List<Yoklama> getAll() {
        return yoklamaService.getAllAttendanceInfo();
    }

    @PostMapping("/yoklamaDurumu")
    public boolean setYoklamaDurumu(@RequestBody YoklamaDto dto) {
        return yoklamaService.setYoklamaDurumuForTeacher(dto);
    }

    @PostMapping("/getYoklamaDurumu")
    public boolean getYoklamaDurumu(@RequestBody YoklamaDto dto) {
        return yoklamaService.getYoklamaDurumu(dto);
    }
}
