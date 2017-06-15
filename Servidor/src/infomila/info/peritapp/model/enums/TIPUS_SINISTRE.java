/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infomila.info.peritapp.model.enums;

/**
 *
 * @author Mr. Robot
 */
public enum TIPUS_SINISTRE {
    FUITA_AIGUA,
    ELECTRICITAT,
    GAS,
    ELECTRODOMESTIC,
    ROBATORI,
    HUMITAT;

    public static TIPUS_SINISTRE getTipusFromString(String tipusSinistre){
        switch(tipusSinistre){
            case "FUITA_AIGUA": return FUITA_AIGUA;
            case "ELECTRICITAT": return ELECTRICITAT;
            case "GAS": return GAS;
            case "ELECTRODOMESTIC": return ELECTRODOMESTIC;
            case "ROBATORI": return ROBATORI;
            case "HUMITAT": return HUMITAT;
            default: return null;
        }
    }
}
