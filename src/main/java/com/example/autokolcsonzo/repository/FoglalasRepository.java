package com.example.autokolcsonzo.repository;

import com.example.autokolcsonzo.entity.Foglalas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoglalasRepository extends CrudRepository<Foglalas,Integer> {
}
