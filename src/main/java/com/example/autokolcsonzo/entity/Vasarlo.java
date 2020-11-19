package com.example.autokolcsonzo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vasarlo")
public class Vasarlo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "cim")
    private String cim;

    @Column(name = "telefonszam")
    private String telefonSzam;

    @Column(name = "foglalando_napok_szama")
    private Integer foglalandoNapokSzama;
}
