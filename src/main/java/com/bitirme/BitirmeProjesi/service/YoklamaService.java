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

    //yoklama kaydedildi.
    public Yoklama saveYoklama(YoklamaDto dto) {
        try {
            List<Yoklama> oYoklama = yoklamaRepository.findByStudentId(dto.getStudentId());
            for (Yoklama yoklama : oYoklama) {
                if (yoklama.getDersKodu().getDersKodu().equals(dto.getDersKodu())) {
                    if (yoklama.isYoklamaDurumu() == true) {
                        yoklama.setDevamsizlikSayisi(dto.getDevamsizlikSayisi());
                        return yoklamaRepository.save(yoklama);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Yoklama kaydedilemedi.", e);
        }
        return null;
    }

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

    public boolean setYoklamaDurumuForTeacher(YoklamaDto dto) {
        try {
            List<Yoklama> yoklama1 = yoklamaRepository.findByDersKodu_DersKodu(dto.getDersKodu());
            for (Yoklama yoklama : yoklama1) {
                if (yoklama.getDersKodu().getDersKodu().equals(dto.getDersKodu())) {
                    yoklama.setYoklamaDurumu(dto.isYoklamaDurumu());
                    yoklamaRepository.save(yoklama);
                }
            }
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean getYoklamaDurumu(YoklamaDto dto) {
        try {
            boolean status = false;
            List<Yoklama> oYoklama = yoklamaRepository.findByStudentId(dto.getStudentId());
            for (Yoklama yoklama : oYoklama) {
                if (yoklama.getDersKodu().getDersKodu().equals(dto.getDersKodu())) {
                    status = yoklama.isYoklamaDurumu();
                }
            }
            return status;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Yoklama> getAllAttendanceInfo() {
        return yoklamaRepository.findAll();
    }

}
