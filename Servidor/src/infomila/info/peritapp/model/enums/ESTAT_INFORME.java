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
public enum ESTAT_INFORME {
    PENDENT,
    TANCAT,
    INEXISTENT;
    
     public static ESTAT_INFORME getEstatInformeFromString(String estatInforme) {
        switch (estatInforme) {
            case "PENDENT":
                return PENDENT;
            case "TANCAT":
                return TANCAT;
            case "INEXISTENT":
                return INEXISTENT;
            default: return null;

        }
    }
}

   

