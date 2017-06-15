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
public enum ESTAT_SINISTRE {
    NOU,
    ASSIGNAT,
    TANCAT;

    public static ESTAT_SINISTRE getEstatSinistreFromString(String estatSinistre) {
        switch (estatSinistre) {
            case "NOU":
                return NOU;
            case "ASSIGNAT":
                return ASSIGNAT;
            case "TANCAT":
                return TANCAT;
            default: return null;

        }
    }
}
