package com.blog.azerbaijani.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Makale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String baslik;

    @Column
    private String icerik;

    @Column(name = "makale_tipi")
    private String makaleTipi;

    @OneToMany(mappedBy = "makale")
    private List<Yorum> yorumlar;

    @Column(name = "begeni_sayisi")
    private Integer begeniSayisi;

    @OneToMany(mappedBy = "makale")
    private List<Like> likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
