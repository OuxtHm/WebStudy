package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
ID    NOT NULL VARCHAR2(20)  
PWD   NOT NULL VARCHAR2(10)  
NAME  NOT NULL VARCHAR2(51)  
POST  NOT NULL VARCHAR2(7)   
ADDR1 NOT NULL VARCHAR2(250) 
ADDR2          VARCHAR2(200) 
LOGIN          NUMBER   
*/
@Data
public class ReplyVO {
	private int rno, fno;
	private String id, name, msg, dbday;
	private Date regdate;
}
