package infomila.info.peritapp.model.enums;

/**
 * Created by Mr. Robot on 08/06/2017.
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