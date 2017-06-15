package infomila.info.peritapp.model.enums;

/**
 * Created by Mr. Robot on 08/06/2017.
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