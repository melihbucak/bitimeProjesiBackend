package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.dto.YoklamaDto;
import com.bitirme.BitirmeProjesi.dtomapper.YoklamaDtoMapper;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import com.bitirme.BitirmeProjesi.repo.YoklamaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class YoklamaService {
    @Autowired
    private YoklamaRepository yoklamaRepository;

    //yoklama kaydedildi.
    public ResponseEntity saveYoklama(YoklamaDto dto) {
        try {
            List<Yoklama> oYoklama = yoklamaRepository.findByStudentId(dto.getStudentId());
            Map<String, Object> stringObjectMap = new LinkedHashMap<>();
            for (Yoklama yoklama : oYoklama) {
                if (yoklama.getDersKodu().getDersKodu().equals(dto.getDersKodu())) {
                    // Yoklama kaydı bulundu, işleme devam et
                    if (yoklama.isYoklamaDurumu()) {
                        // Öğrenci IP adresini alma
                        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                        String ipAddress = request.getRemoteAddr();
                        // Öğrencinin son giriş kaydını al
                        LocalDateTime lastLoginTime = yoklama.getLoginTime();

                        // Kontrol et: Eğer öğrencinin güncel IP adresi veritabanında kayıtlı ise, işlemi engelle
                        if (yoklamaRepository.existsByIpAddress(ipAddress)) {
                            stringObjectMap.put("yoklamaDurumu", false);
                            stringObjectMap.put("message", "Bir başkası adına yoklama veremezsiniz");
                            return new ResponseEntity(stringObjectMap, HttpStatus.BAD_REQUEST);
                        }
                        // Eğer son giriş kaydı yok veya son girişten 30 dakikadan daha uzun süre geçtiyse
                        if (lastLoginTime == null || lastLoginTime.plusMinutes(1).isBefore(LocalDateTime.now())) {
                            // Öğrencinin güncel IP adresini yoklama kaydına kaydet
//                            yoklama.setDevamsizlikSayisi(yoklama.getDevamsizlikSayisi());
                            yoklama.setKatilimDurumu(true);
                            yoklama.setIpAddress(ipAddress);
                            yoklama.setLoginTime(LocalDateTime.now());
                            yoklamaRepository.save(yoklama);

                            // Diğer işlemler...
                            stringObjectMap.put("yoklamaDurumu", true);
                            stringObjectMap.put("result", dto);
                            stringObjectMap.put("ipAddress", ipAddress);
                            return new ResponseEntity(stringObjectMap, HttpStatus.OK);
                        } else {
                            // Öğrenci daha önce yoklama vermiş ve 30 dakika içinde tekrar yoklama vermek istiyor
                            stringObjectMap.put("yoklamaDurumu", false);
                            stringObjectMap.put("message", "30 dakika içinde tekrar yoklama veremezsiniz");
                            return new ResponseEntity(stringObjectMap, HttpStatus.BAD_REQUEST);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Yoklama kaydedilemedi.", e);
        }
        return new ResponseEntity("Hatalı bir işlem gerçekleşti", HttpStatus.BAD_REQUEST);
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

    //öğretmen yoklama başlatma işlemi
    //gelen true-false değerine göre dersin yoklamasını başlatır ve bitirir
    //yoklama tablosundaki ip adresleri ve loginTime değişkenlerini sıfırlar
    public boolean setYoklamaDurumuForTeacher(YoklamaDto dto) {
        try {
            List<Yoklama> yoklama1 = yoklamaRepository.findByDersKodu_DersKodu(dto.getDersKodu());
            for (Yoklama yoklama : yoklama1) {
                if (yoklama.getDersKodu().getDersKodu().equals(dto.getDersKodu())) {

                    if (!(yoklama.isYoklamaDurumu() == dto.isYoklamaDurumu())){
                        yoklama.setYoklamaDurumu(dto.isYoklamaDurumu());
                    yoklama.setLoginTime(null);
                    yoklama.setIpAddress(null);
                    //eger ogrenci yoklamaya katılmamissa devamsızlık+1
                    if (!yoklama.isKatilimDurumu()&& !dto.isYoklamaDurumu()){
                        yoklama.setDevamsizlikSayisi(yoklama.getDevamsizlikSayisi()+1);
                    }
                    if (!dto.isYoklamaDurumu()){
                        yoklama.setKatilimDurumu(false);
                    }
                    yoklamaRepository.save(yoklama);
                    }

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
