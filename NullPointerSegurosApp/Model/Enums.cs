using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Enums
    {

        public enum ESTAT_SINISTRE
        {
            NOU,
            ASSIGNAT,
            TANCAT,
            NOTHING
        }

        public static ESTAT_SINISTRE getEstatSinistreFromString(String estatSinistre)
        {
            switch (estatSinistre)
            {
                case "NOU":
                    return ESTAT_SINISTRE.NOU;
                case "ASSIGNAT":
                    return ESTAT_SINISTRE.ASSIGNAT;
                case "TANCAT":
                    return ESTAT_SINISTRE.TANCAT;
                default: return ESTAT_SINISTRE.NOU;

            }
        }


        public enum TIPUS_SINISTRE
        {
            FUITA_AIGUA,
            ELECTRICITAT,
            GAS,
            ELECTRODOMESTIC,
            ROBATORI,
            HUMITAT
        }

        public static TIPUS_SINISTRE getTipusSinistreFromString(String tipusSinistre)
        {
            switch (tipusSinistre)
            {
                case "FUITA_AIGUA": return TIPUS_SINISTRE.FUITA_AIGUA;
                case "ELECTRICITAT": return TIPUS_SINISTRE.ELECTRICITAT;
                case "GAS": return TIPUS_SINISTRE.GAS;
                case "ELECTRODOMESTIC": return TIPUS_SINISTRE.ELECTRODOMESTIC;
                case "ROBATORI": return TIPUS_SINISTRE.ROBATORI;
                case "HUMITAT": return TIPUS_SINISTRE.HUMITAT;
                default: return TIPUS_SINISTRE.FUITA_AIGUA;
            }
        }

        public enum ESTAT_INFORME
        {
            PENDENT,
            TANCAT
        }

        public static ESTAT_INFORME getEstatInformeFromString(String estatInforme)
        {
            switch (estatInforme)
            {
                case "PENDENT":
                    return ESTAT_INFORME.PENDENT;
                case "TANCAT":
                    return ESTAT_INFORME.TANCAT;                  
                default: return ESTAT_INFORME.PENDENT;

            }
        }

        public enum RESULTAT_PERITATGE
        {
            COBERT_TOTAL,
            COBERT_PARCIAL,
            SENSE_COBERTURA,
            REPARAT
        }

        public static RESULTAT_PERITATGE getResultatPeritatgeFromString(String resultatPeritatge)
        {
            switch (resultatPeritatge)
            {
                case "COBERT_TOTAL":
                    return RESULTAT_PERITATGE.COBERT_TOTAL;
                case "COBERT_PARCIAL":
                    return RESULTAT_PERITATGE.COBERT_PARCIAL;
                case "SENSE_COBERTURA":
                    return RESULTAT_PERITATGE.SENSE_COBERTURA;
                case "REPARAT":
                    return RESULTAT_PERITATGE.REPARAT;
                default: return RESULTAT_PERITATGE.COBERT_TOTAL;

            }
        }


        public enum TIPUS_HABITATGE
        {
            TRASTER,
            PIS,
            LOCAL,
            CASA_ADOSADA,
            CASA_INDIV,
            PARKING
        }

        public static TIPUS_HABITATGE getTipusHabitatgeFromString(String tipusHabitatge)
        {
            switch (tipusHabitatge)
            {
                case "TRASTER": return TIPUS_HABITATGE.TRASTER;
                case "PIS": return TIPUS_HABITATGE.PIS;
                case "LOCAL": return TIPUS_HABITATGE.LOCAL;
                case "CASA_ADOSADA": return TIPUS_HABITATGE.CASA_ADOSADA;
                case "CASA_INDIV": return TIPUS_HABITATGE.CASA_INDIV;
                case "PARKING": return TIPUS_HABITATGE.PARKING;
                default: return TIPUS_HABITATGE.TRASTER;
            }
        }


    }
    }
