/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.blackhorse.kronos;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author whitesnake
 */
public class Kronos {

    public static void main(String[] args) {
        
        try{
            UIManager.setLookAndFeel(UIManager.createLookAndFeel("Windows"));
        }catch(UnsupportedLookAndFeelException e){
            System.out.println("UIManager Exception : "+e);
        }
        
        MainFrame mn = new MainFrame();
        mn.setVisible(true);
    }
}
