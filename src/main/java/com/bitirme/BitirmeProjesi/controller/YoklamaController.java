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

    //    @PostMapping("/yoklama")
//    public ResponseEntity<Yoklama> saveYoklama(@RequestBody Yoklama yoklama) throws Exception {
//        Yoklama yoklama1 = yoklamaService.saveYoklama(yoklama);
//        return new ResponseEntity<>(yoklama1, HttpStatus.CREATED);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Yoklama>> getYoklama(@PathVariable("id") Long id) {
        return ResponseEntity.ok(yoklamaService.getStudentAttendance(id));
    }

    @PutMapping("/updateAttendance")
    public ResponseEntity updateYoklama(@RequestBody YoklamaDto dto) {
        return yoklamaService.updateAttendanceFromDto(dto);
    }

    @GetMapping("/getAll")
    public List<Yoklama> getAll() {
        return yoklamaService.getAllAttendanceInfo();
    }

}
