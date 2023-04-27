package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.entity.Yoklama;
import com.bitirme.BitirmeProjesi.service.YoklamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/yoklama")
public class YoklamaController {

    @Autowired
    private YoklamaService yoklamaService;

    @PostMapping("/yoklama")
    public ResponseEntity<Yoklama> saveYoklama(@RequestBody Yoklama yoklama) throws Exception {
        Yoklama yoklama1 = yoklamaService.saveYoklama(yoklama);
        return new ResponseEntity<>(yoklama1, HttpStatus.CREATED);
    }
}
