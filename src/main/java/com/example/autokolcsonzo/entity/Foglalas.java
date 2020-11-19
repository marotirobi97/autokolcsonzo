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
@Table(name = "foglalasok")
public class Foglalas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "vasarlo_id")
    private Vasarlo vasarlo;

    @OneToOne
    @JoinColumn(name = "auto_id")
    private Auto auto;

    @Column(name = "foglalando_napok_szama")
    private Integer foglalandoNapokSzama;

    @Column(name = "foglalas_osszege")
    private Integer foglalasOsszege;

}
