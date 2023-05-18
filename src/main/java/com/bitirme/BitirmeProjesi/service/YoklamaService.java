package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.dto.YoklamaDto;
import com.bitirme.BitirmeProjesi.dtomapper.StudentDtoMapper;
import com.bitirme.BitirmeProjesi.dtomapper.YoklamaDtoMapper;
import com.bitirme.BitirmeProjesi.entity.Student;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import com.bitirme.BitirmeProjesi.repo.YoklamaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class YoklamaService {
    @Autowired
    private YoklamaRepository yoklamaRepository;

    //    public Yoklama saveYoklama(Yoklama yoklama) {
//        LocalDate localDate = LocalDate.now();
//        yoklama.setTarih(localDate);
//
//        return yoklamaRepository.save(yoklama);
//    }
    public List<Yoklama> getStudentAttendance(Long id) {
        return yoklamaRepository.findByStudentId(id);
    }

    public ResponseEntity updateAttendanceFromDto(YoklamaDto dto) {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<Yoklama> oYoklama = yoklamaRepository.findByStudentId(dto.getStudentId());
        for (Yoklama yoklama : oYoklama) {
            if (yoklama.getDersKodu().getDersKodu().equals(dto.getDersKodu())) {
                hm.put("status", true);
                hm.put("result", yoklama);
                YoklamaDtoMapper yoklamaDtoMapper = Mappers.getMapper(YoklamaDtoMapper.class);

                yoklamaDtoMapper.updateAttendanceFromDto(dto, yoklama);
                yoklamaRepository.save(yoklama);
                return new ResponseEntity(hm, HttpStatus.OK);
            }
        }
        hm.put("status", false);
        hm.put("message", "Update From Dto Failed");
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }


    public List<Yoklama> getAllAttendanceInfo() {
        return yoklamaRepository.findAll();
    }

}
