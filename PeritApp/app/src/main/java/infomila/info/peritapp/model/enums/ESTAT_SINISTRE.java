package infomila.info.peritapp.model.enums;

/**
 * Created by Mr. Robot on 08/06/2017.
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
