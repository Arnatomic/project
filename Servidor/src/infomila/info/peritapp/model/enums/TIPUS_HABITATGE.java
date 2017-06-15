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
public enum TIPUS_HABITATGE {
    TRASTER,
    PIS,
    LOCAL,
    CASA_ADOSADA,
    CASA_INDIV,
    PARKING;

    public static TIPUS_HABITATGE getTipusHabitatgeFromString(String tipusHabitatge) {
        switch (tipusHabitatge) {
            case "TRASTER":
                return TRASTER;
            case "PIS":
                return PIS;
            case "LOCAL":
                return LOCAL;
            case "CASA_ADOSADA":
                return CASA_ADOSADA;
            case "CASA_INDIV":
                return CASA_INDIV;
            case "PARKING":
                return PARKING;
            default:
                return null;

        }
    }
}
