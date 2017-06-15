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
public enum RESULTAT_PERITATGE {
    COBERT_TOTAL,
    COBERT_PARCIAL,
    SENSE_COBERTURA,
    REPARAT;

    public static RESULTAT_PERITATGE getResultatPeritatgeFromString(String resultatPeritatge) {
        switch (resultatPeritatge) {
            case "COBERT_TOTAL":
                return COBERT_TOTAL;
            case "COBERT_PARCIAL":
                return COBERT_PARCIAL;
            case "SENSE_COBERTURA":
                return SENSE_COBERTURA;
            case "REPARAT":
                return REPARAT;
            default:
                return null;

        }
    }
}
