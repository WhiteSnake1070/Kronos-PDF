/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blackhorse.kronos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author whitesnake
 */
public class Functions {
    
    public String getDate(){
        
        SimpleDateFormat dateFormat= new SimpleDateFormat("ddMMyyyyHHmmss");
        String str_date=dateFormat.format(new Date());
        
        return str_date;
    }
    
    public void splitPDF(String sourceFilePath, int startPage, int endPage) {
        
    }

    
}
