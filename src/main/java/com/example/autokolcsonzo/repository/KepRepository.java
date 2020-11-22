package com.example.autokolcsonzo.repository;

import com.example.autokolcsonzo.entity.Kep;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface KepRepository extends CrudRepository<Kep, Integer> {

    @Query("SELECT count(k) FROM Kep k WHERE k.auto.id = :autoId")
    Integer findIfItsContains(Integer autoId);

    @Query("FROM Kep k Where k.auto.id = :autoId")
    Kep findKep(Integer autoId);

}
