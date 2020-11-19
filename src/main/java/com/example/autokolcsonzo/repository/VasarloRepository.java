package com.example.autokolcsonzo.repository;

import com.example.autokolcsonzo.entity.Vasarlo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VasarloRepository extends CrudRepository<Vasarlo,Integer> {
}
