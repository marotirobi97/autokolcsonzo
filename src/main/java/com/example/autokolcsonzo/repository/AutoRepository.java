package com.example.autokolcsonzo.repository;

import com.example.autokolcsonzo.entity.Auto;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoRepository extends CrudRepository<Auto, Integer> {

    List<Auto> findAll();

    @Query("FROM Auto a WHERE a.id = :carId")
    Auto findAutoById(Integer carId);

    @Query("FROM Auto a WHERE a.allapot = 'FOGLALT'")
    List<Auto> findAllRentedCar();

    @Query("FROM Auto a WHERE a.allapot = 'SZABAD' AND a.aktivalt_e = 'AKTIV'")
    List<Auto> findAllFreeCar();

}
