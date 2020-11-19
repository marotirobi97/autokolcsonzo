package com.example.autokolcsonzo.repository;

import com.example.autokolcsonzo.entity.Kep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KepRepository extends CrudRepository<Kep, Integer> {

}
