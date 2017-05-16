/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author EDU
 */
public class Proves {
    public static void main(String[] args) {
        Date avui = new Date();
        
        System.out.println("avui: " + avui);
        
        Date xx = new Date(avui.getTime());
        
        System.out.println("avui xx: " +xx);
        
        String s = "";
        
        System.out.println(s.isEmpty());
        
        
        List<Person> persones = new ArrayList();
        persones.add(new Person("Nil"));
        persones.add(new Person("Arnau"));
        
        Person mayte = new Person("Mayte");
        persones.remove(mayte);
        
        persones.remove(-1);
        
    }
    
 
    
}
