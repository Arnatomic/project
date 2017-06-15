/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mr. Robot
 */
public class Proves {

    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.out.println("L'execució d'aquest programa necessita d'1 o 2 arguments: ");
            System.out.println("Argument 1: Nom de la classe de la capa de persistència (obligatori)");
            System.out.println("Argument 2: Nom del fitxer de configuració que espera la classe (optatiu)");
            System.exit(1);
        }

        String classeCapaPersistencia = args[0];
        String nomFitxerConfiguracio = null;
        if (args.length == 2) {
            nomFitxerConfiguracio = args[1];
        }

        IComponentSGBD obj = null;
        Class c = null;
        try {
            System.out.println(classeCapaPersistencia);
            c = Class.forName(classeCapaPersistencia);
        } catch (ClassNotFoundException ex) {
            System.out.println("No es troba la classe " + classeCapaPersistencia);
            System.exit(1);
        }
        try {
            if (nomFitxerConfiguracio == null) {
                obj = (IComponentSGBD) c.newInstance();
            } else {
                try {
                    Constructor x = c.getConstructor(String.class);
                    obj = (IComponentSGBD) x.newInstance(nomFitxerConfiguracio);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    System.out.println("Problemes en intentar executar constructor de la " + classeCapaPersistencia + " amb paràmetre=" + nomFitxerConfiguracio);
                    System.out.println("Més informació: " + ex.getMessage());
                    if (ex.getCause() != null) {
                        System.out.println("Causat per: " + ex.getCause().getMessage());
                        if (ex.getCause().getCause() != null) {
                            System.out.println(ex.getCause().getCause().getMessage());
                        }
                    }
                    System.exit(1);
                }
            }

            final IComponentSGBD bdConector = obj;

            Servidor server = new Servidor(1234, bdConector);
            new Thread(server).start();
            
            String s = "";
            do{              
            try {
                
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                s = bufferRead.readLine();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            }while(!s.equals("Exit:"));
            
            System.out.println("Servidor Aturat.");
            System.exit(0);
                    

        } catch (InstantiationException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "No es pot obtenir l'objecte de persistència", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Més informació: " + ex.getMessage());
            if (ex.getCause() != null) {
                System.out.println("Causat per: " + ex.getCause().getMessage());
            }
        }
    }
}
