package com.sist.vo;

import lombok.Data;

/*
FNO             NOT NULL NUMBER
NAME            NOT NULL VARCHAR2(200)
TYPE            NOT NULL VARCHAR2(100)
PHONE           VARCHAR2(20)
ADDRESS         NOT NULL VARCHAR2(500)
SCORE           NUMBER(2,1)
THEME           CLOB
TIME            VARCHAR2(100)
PRICE           VARCHAR2(50)
PARKING         VARCHAR2(100)
POSTER          NOT NULL VARCHAR2(260)
IMAGES          CLOB
CONTEXT         CLOB
HIT             NUMBER
JJIMCOUNT       NUMBER*/
@Data
public class FoodVO {
	private int fno, hit, jjimcount;
	private double score;
	private String name, type, phone, address, theme, time, price, parking, poster, images, context;
}
