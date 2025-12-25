package com.example.moro.app.colormap.entity;

import jakarta.persistence.Entity;

@Entity
public class ColorMap {
    //private Long id; // PostColor 테이블과 연결예정
    private String colorTheme;
    private String hexCode;
}
