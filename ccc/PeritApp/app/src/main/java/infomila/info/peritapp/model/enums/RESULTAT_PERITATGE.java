package infomila.info.peritapp.model.enums;

/**
 * Created by Mr. Robot on 08/06/2017.
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