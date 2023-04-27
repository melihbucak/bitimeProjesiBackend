package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.entity.Yoklama;
import com.bitirme.BitirmeProjesi.repo.YoklamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class YoklamaService {
    @Autowired
    private YoklamaRepository yoklamaRepository;

    public Yoklama saveYoklama(Yoklama yoklama) {
        LocalDate localDate = LocalDate.now();
        yoklama.setTarih(localDate);

        return yoklamaRepository.save(yoklama);
    }
}
