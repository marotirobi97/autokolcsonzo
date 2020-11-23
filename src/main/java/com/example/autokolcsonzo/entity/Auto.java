package com.example.autokolcsonzo.entity;

import com.example.autokolcsonzo.enums.Aktivalt_e;
import com.example.autokolcsonzo.enums.Allapot;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "hany_eves")
    private Integer hanyEves;

    @Column(name = "napi_ar")
    private Integer napiAr;

    @Column(name = "aktivalt_e")
    private String aktivalt_e = Aktivalt_e.AKTIV.toString();

    @Column(name = "allapot")
    private String allapot = Allapot.SZABAD.toString();

    @Column(name = "kep")
    private byte[] kep;
}
