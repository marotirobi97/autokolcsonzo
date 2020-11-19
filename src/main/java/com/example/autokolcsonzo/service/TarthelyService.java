package com.example.autokolcsonzo.service;

import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.entity.Kep;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.repository.KepRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class TarthelyService implements IsTarhelyService{
    @Autowired
    private KepRepository kepRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void saveImage(MultipartFile imageFile,Integer carId) throws Exception {
        Auto auto = autoRepository.findAutoById(carId);

        byte[] byteImage = imageFile.getBytes();
        Kep kep = new Kep();
        kep.setKep(byteImage);
        kep.setAuto(auto);

        kepRepository.save(kep);
    }
}
