package com.dx.dxintercambio;

public class Post6 {

    private String user ;

    private String password ;

    private int idRemolque ;
    private String idSQL ;
    private int idIntercambio ;
    private String sello1 ;
    private String sello2 ;
    private String llanta1 ;
    private String llanta2 ;
    private String llanta3 ;
    private String llanta4 ;
    private String llanta5;
    private String llanta6 ;
    private String llanta7 ;
    private String llanta8 ;
    private int llantajumbo ;
    private int llantajumbo2 ;
    private int selloExtra ;
    private String sello3 ;
    private int incidencia ;
    private String incidenciaCancel ;
    private String  placasDatos ;
    private String comentario2;
    private String defensa ;
    private String llantas ;
    private String pisoTractor ;
    private String tanqueDiesel ;
    private String cabinaCompartimientos ;
    private String tanqueAire ;
    private String quintaRueda ;
    private String ejesTransmision ;
    private String tuboEscape ;
    private String motor ;
    private String baseRemolque ;
    private String puerta ;
    private String paredLateralDerecha ;
    private String techos ;
    private String paredFrontal ;
    private String paredLateralIzquierda ;
    private String pisoInterno ;
    private String vvtt ;
    private String IRP1_inspeccionMecanica ;
    private String IRP1_lucesCheck ;
    private String IRP1_luzGaliboIzqFrontalSup ;
    private String IRP1_manitas ;
    private String IRP1_manivela ;
    private String IRP1_patinIzq ;
    private String IRP2_cuartoLadoIzq ;
    private String IRP2_loderaIzq ;
    private String IRP2_lucesCheck ;
    private String IRP2_luzABS ;
    private String IRP2_luzBarcoIzq ;
    private String IRP2_rompevientosIzq ;
    private String LlIE1_birlosPivote ;
    private String LlIE1_llantasPos1 ;
    private String LlIE1_llantasPos2 ;
    private String LlIE1_masaYoyo ;
    private String LlIE1_rin ;
    private String LlIE1_tieneLodera ;
    private String Pu_bisagrasPuertas ;
    private String Pu_defensa ;
    private String Pu_luzGaliboSupTraseras ;
    private String Pu_plafonesDer ;
    private String Pu_plafonesIzq ;
    private String Pl_luzPlaca ;
    private String Pl_placa ;
    private String S1_sello1 ;
    private String S1_altaSeguridad ;
    private String LlDE1_birlosPivote ;
    private String LlDE1_llantasPos5 ;
    private String LlDE1_llantasPos6 ;
    private String LlDE1_masaYoyo ;
    private String LlDE1_rin ;
    private String LlDE1_tieneLodera ;
    private String DRP1_fondoPlaga ;
    private String DRP1_pisoPlaga ;
    private String DRP1_techoPlaga ;
    private String DRP1_lucesCheck ;
    private String DRP1_luzGaliboDerFrontalSup ;
    private String DRP1_derPlaga ;
    private String DRP1_izqPlaga ;
    private String DRP1_patinDer ;
    private String DRP2_cuartoLadoDer ;
    private String DRP2_loderaDer ;
    private String DRP2_lucesCheck ;
    private String DRP2_luzBarcoDer ;
    private String DRP2_rompevientosDer ;
    private String S2_escotilla ;
    private String S2_sello2 ;
    private String S2_altaSeguridad ;
    private String LlIE2_birlosPivote ;
    private String LlIE2_llantasPos3 ;
    private String LlIE2_llantasPos4 ;
    private String LlIE2_masaYoyo ;
    private String LlIE2_rin ;
    private String LlIE2_tieneLodera ;
    private String LlDE2_birlosPivote ;
    private String LlDE2_llantasPos7 ;
    private String LlDE2_llantasPos8 ;
    private String LlDE2_masaYoyo ;
    private String LlDE2_rin ;
    private String LlDE2_tieneLodera ;
    private String CFD_amortiguador ;
    private String CFD_bolsaAire ;
    private String CFD_gavilan ;
    private String CFD_muelle ;
    private String CFD_rotachamber ;
    private String CTD_amortiguador ;
    private String CTD_bolsaAire ;
    private String CTD_matraca ;
    private String CTD_muelle ;
    private String CTD_rotachamber ;
    private String CFI_amortiguador ;
    private String CFI_bolsaAire ;
    private String CFI_gavilan ;
    private String CFI_muelle ;
    private String CFI_rotachamber ;
    private String CTI_amortiguador ;
    private String CTI_bolsaAire ;
    private String CTI_matraca ;
    private String CTI_muelle ;
    private String CTI_rotachamber ;


    public Post6(String user, String password, int idRemolque, String idSQL, int idIntercambio, String sello1, String sello2, String llanta1, String llanta2, String llanta3, String llanta4, String llanta5, String llanta6, String llanta7, String llanta8, int llantajumbo, int llantajumbo2, int selloExtra, String sello3, int incidencia, String incidenciaCancel, String placasDatos, String comentario2, String defensa, String llantas, String pisoTractor, String tanqueDiesel, String cabinaCompartimientos, String tanqueAire, String quintaRueda, String ejesTransmision, String tuboEscape, String motor, String baseRemolque, String puerta, String paredLateralDerecha, String techos, String paredFrontal, String paredLateralIzquierda, String pisoInterno, String vvtt, String IRP1_inspeccionMecanica, String IRP1_lucesCheck, String IRP1_luzGaliboIzqFrontalSup, String IRP1_manitas, String IRP1_manivela, String IRP1_patinIzq, String IRP2_cuartoLadoIzq, String IRP2_loderaIzq, String IRP2_lucesCheck, String IRP2_luzABS, String IRP2_luzBarcoIzq, String IRP2_rompevientosIzq, String llIE1_birlosPivote, String llIE1_llantasPos1, String llIE1_llantasPos2, String llIE1_masaYoyo, String llIE1_rin, String llIE1_tieneLodera, String pu_bisagrasPuertas, String pu_defensa, String pu_luzGaliboSupTraseras, String pu_plafonesDer, String pu_plafonesIzq, String pl_luzPlaca, String pl_placa, String s1_sello1, String s1_altaSeguridad, String llDE1_birlosPivote, String llDE1_llantasPos5, String llDE1_llantasPos6, String llDE1_masaYoyo, String llDE1_rin, String llDE1_tieneLodera, String DRP1_fondoPlaga, String DRP1_pisoPlaga, String DRP1_techoPlaga, String DRP1_lucesCheck, String DRP1_luzGaliboDerFrontalSup, String DRP1_derPlaga, String DRP1_izqPlaga, String DRP1_patinDer, String DRP2_cuartoLadoDer, String DRP2_loderaDer, String DRP2_lucesCheck, String DRP2_luzBarcoDer, String DRP2_rompevientosDer, String s2_escotilla, String s2_sello2, String s2_altaSeguridad, String llIE2_birlosPivote, String llIE2_llantasPos3, String llIE2_llantasPos4, String llIE2_masaYoyo, String llIE2_rin, String llIE2_tieneLodera, String llDE2_birlosPivote, String llDE2_llantasPos7, String llDE2_llantasPos8, String llDE2_masaYoyo, String llDE2_rin, String llDE2_tieneLodera, String CFD_amortiguador, String CFD_bolsaAire, String CFD_gavilan, String CFD_muelle, String CFD_rotachamber, String CTD_amortiguador, String CTD_bolsaAire, String CTD_matraca, String CTD_muelle, String CTD_rotachamber, String CFI_amortiguador, String CFI_bolsaAire, String CFI_gavilan, String CFI_muelle, String CFI_rotachamber, String CTI_amortiguador, String CTI_bolsaAire, String CTI_matraca, String CTI_muelle, String CTI_rotachamber) {
        this.user = user;
        this.password = password;
        this.idRemolque = idRemolque;
        this.idSQL = idSQL;
        this.idIntercambio = idIntercambio;
        this.sello1 = sello1;
        this.sello2 = sello2;
        this.llanta1 = llanta1;
        this.llanta2 = llanta2;
        this.llanta3 = llanta3;
        this.llanta4 = llanta4;
        this.llanta5 = llanta5;
        this.llanta6 = llanta6;
        this.llanta7 = llanta7;
        this.llanta8 = llanta8;
        this.llantajumbo = llantajumbo;
        this.llantajumbo2 = llantajumbo2;
        this.selloExtra = selloExtra;
        this.sello3 = sello3;
        this.incidencia = incidencia;
        this.incidenciaCancel = incidenciaCancel;
        this.placasDatos = placasDatos;
        this.comentario2 = comentario2;
        this.defensa = defensa;
        this.llantas = llantas;
        this.pisoTractor = pisoTractor;
        this.tanqueDiesel = tanqueDiesel;
        this.cabinaCompartimientos = cabinaCompartimientos;
        this.tanqueAire = tanqueAire;
        this.quintaRueda = quintaRueda;
        this.ejesTransmision = ejesTransmision;
        this.tuboEscape = tuboEscape;
        this.motor = motor;
        this.baseRemolque = baseRemolque;
        this.puerta = puerta;
        this.paredLateralDerecha = paredLateralDerecha;
        this.techos = techos;
        this.paredFrontal = paredFrontal;
        this.paredLateralIzquierda = paredLateralIzquierda;
        this.pisoInterno = pisoInterno;
        this.vvtt = vvtt;
        this.IRP1_inspeccionMecanica = IRP1_inspeccionMecanica;
        this.IRP1_lucesCheck = IRP1_lucesCheck;
        this.IRP1_luzGaliboIzqFrontalSup = IRP1_luzGaliboIzqFrontalSup;
        this.IRP1_manitas = IRP1_manitas;
        this.IRP1_manivela = IRP1_manivela;
        this.IRP1_patinIzq = IRP1_patinIzq;
        this.IRP2_cuartoLadoIzq = IRP2_cuartoLadoIzq;
        this.IRP2_loderaIzq = IRP2_loderaIzq;
        this.IRP2_lucesCheck = IRP2_lucesCheck;
        this.IRP2_luzABS = IRP2_luzABS;
        this.IRP2_luzBarcoIzq = IRP2_luzBarcoIzq;
        this.IRP2_rompevientosIzq = IRP2_rompevientosIzq;
        LlIE1_birlosPivote = llIE1_birlosPivote;
        LlIE1_llantasPos1 = llIE1_llantasPos1;
        LlIE1_llantasPos2 = llIE1_llantasPos2;
        LlIE1_masaYoyo = llIE1_masaYoyo;
        LlIE1_rin = llIE1_rin;
        LlIE1_tieneLodera = llIE1_tieneLodera;
        Pu_bisagrasPuertas = pu_bisagrasPuertas;
        Pu_defensa = pu_defensa;
        Pu_luzGaliboSupTraseras = pu_luzGaliboSupTraseras;
        Pu_plafonesDer = pu_plafonesDer;
        Pu_plafonesIzq = pu_plafonesIzq;
        Pl_luzPlaca = pl_luzPlaca;
        Pl_placa = pl_placa;
        S1_sello1 = s1_sello1;
        S1_altaSeguridad = s1_altaSeguridad;
        LlDE1_birlosPivote = llDE1_birlosPivote;
        LlDE1_llantasPos5 = llDE1_llantasPos5;
        LlDE1_llantasPos6 = llDE1_llantasPos6;
        LlDE1_masaYoyo = llDE1_masaYoyo;
        LlDE1_rin = llDE1_rin;
        LlDE1_tieneLodera = llDE1_tieneLodera;
        this.DRP1_fondoPlaga = DRP1_fondoPlaga;
        this.DRP1_pisoPlaga = DRP1_pisoPlaga;
        this.DRP1_techoPlaga = DRP1_techoPlaga;
        this.DRP1_lucesCheck = DRP1_lucesCheck;
        this.DRP1_luzGaliboDerFrontalSup = DRP1_luzGaliboDerFrontalSup;
        this.DRP1_derPlaga = DRP1_derPlaga;
        this.DRP1_izqPlaga = DRP1_izqPlaga;
        this.DRP1_patinDer = DRP1_patinDer;
        this.DRP2_cuartoLadoDer = DRP2_cuartoLadoDer;
        this.DRP2_loderaDer = DRP2_loderaDer;
        this.DRP2_lucesCheck = DRP2_lucesCheck;
        this.DRP2_luzBarcoDer = DRP2_luzBarcoDer;
        this.DRP2_rompevientosDer = DRP2_rompevientosDer;
        S2_escotilla = s2_escotilla;
        S2_sello2 = s2_sello2;
        S2_altaSeguridad = s2_altaSeguridad;
        LlIE2_birlosPivote = llIE2_birlosPivote;
        LlIE2_llantasPos3 = llIE2_llantasPos3;
        LlIE2_llantasPos4 = llIE2_llantasPos4;
        LlIE2_masaYoyo = llIE2_masaYoyo;
        LlIE2_rin = llIE2_rin;
        LlIE2_tieneLodera = llIE2_tieneLodera;
        LlDE2_birlosPivote = llDE2_birlosPivote;
        LlDE2_llantasPos7 = llDE2_llantasPos7;
        LlDE2_llantasPos8 = llDE2_llantasPos8;
        LlDE2_masaYoyo = llDE2_masaYoyo;
        LlDE2_rin = llDE2_rin;
        LlDE2_tieneLodera = llDE2_tieneLodera;
        this.CFD_amortiguador = CFD_amortiguador;
        this.CFD_bolsaAire = CFD_bolsaAire;
        this.CFD_gavilan = CFD_gavilan;
        this.CFD_muelle = CFD_muelle;
        this.CFD_rotachamber = CFD_rotachamber;
        this.CTD_amortiguador = CTD_amortiguador;
        this.CTD_bolsaAire = CTD_bolsaAire;
        this.CTD_matraca = CTD_matraca;
        this.CTD_muelle = CTD_muelle;
        this.CTD_rotachamber = CTD_rotachamber;
        this.CFI_amortiguador = CFI_amortiguador;
        this.CFI_bolsaAire = CFI_bolsaAire;
        this.CFI_gavilan = CFI_gavilan;
        this.CFI_muelle = CFI_muelle;
        this.CFI_rotachamber = CFI_rotachamber;
        this.CTI_amortiguador = CTI_amortiguador;
        this.CTI_bolsaAire = CTI_bolsaAire;
        this.CTI_matraca = CTI_matraca;
        this.CTI_muelle = CTI_muelle;
        this.CTI_rotachamber = CTI_rotachamber;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getIdRemolque() {
        return idRemolque;
    }

    public String getIdSQL() {
        return idSQL;
    }

    public int getIdIntercambio() {
        return idIntercambio;
    }

    public String getSello1() {
        return sello1;
    }

    public String getSello2() {
        return sello2;
    }

    public String getLlanta1() {
        return llanta1;
    }

    public String getLlanta2() {
        return llanta2;
    }

    public String getLlanta3() {
        return llanta3;
    }

    public String getLlanta4() {
        return llanta4;
    }

    public String getLlanta5() {
        return llanta5;
    }

    public String getLlanta6() {
        return llanta6;
    }

    public String getLlanta7() {
        return llanta7;
    }

    public String getLlanta8() {
        return llanta8;
    }

    public int getLlantajumbo() {
        return llantajumbo;
    }

    public int getLlantajumbo2() {
        return llantajumbo2;
    }

    public int getSelloExtra() {
        return selloExtra;
    }

    public String getSello3() {
        return sello3;
    }

    public int getIncidencia() {
        return incidencia;
    }

    public String getIncidenciaCancel() {
        return incidenciaCancel;
    }

    public String getPlacasDatos() {
        return placasDatos;
    }

    public String getComentario2() {
        return comentario2;
    }

    public String getDefensa() {
        return defensa;
    }

    public String getLlantas() {
        return llantas;
    }

    public String getPisoTractor() {
        return pisoTractor;
    }

    public String getTanqueDiesel() {
        return tanqueDiesel;
    }

    public String getCabinaCompartimientos() {
        return cabinaCompartimientos;
    }

    public String getTanqueAire() {
        return tanqueAire;
    }

    public String getQuintaRueda() {
        return quintaRueda;
    }

    public String getEjesTransmision() {
        return ejesTransmision;
    }

    public String getTuboEscape() {
        return tuboEscape;
    }

    public String getMotor() {
        return motor;
    }

    public String getBaseRemolque() {
        return baseRemolque;
    }

    public String getPuerta() {
        return puerta;
    }

    public String getParedLateralDerecha() {
        return paredLateralDerecha;
    }

    public String getTechos() {
        return techos;
    }

    public String getParedFrontal() {
        return paredFrontal;
    }

    public String getParedLateralIzquierda() {
        return paredLateralIzquierda;
    }

    public String getPisoInterno() {
        return pisoInterno;
    }

    public String getVvtt() {
        return vvtt;
    }

    public String getIRP1_inspeccionMecanica() {
        return IRP1_inspeccionMecanica;
    }

    public String getIRP1_lucesCheck() {
        return IRP1_lucesCheck;
    }

    public String getIRP1_luzGaliboIzqFrontalSup() {
        return IRP1_luzGaliboIzqFrontalSup;
    }

    public String getIRP1_manitas() {
        return IRP1_manitas;
    }

    public String getIRP1_manivela() {
        return IRP1_manivela;
    }

    public String getIRP1_patinIzq() {
        return IRP1_patinIzq;
    }

    public String getIRP2_cuartoLadoIzq() {
        return IRP2_cuartoLadoIzq;
    }

    public String getIRP2_loderaIzq() {
        return IRP2_loderaIzq;
    }

    public String getIRP2_lucesCheck() {
        return IRP2_lucesCheck;
    }

    public String getIRP2_luzABS() {
        return IRP2_luzABS;
    }

    public String getIRP2_luzBarcoIzq() {
        return IRP2_luzBarcoIzq;
    }

    public String getIRP2_rompevientosIzq() {
        return IRP2_rompevientosIzq;
    }

    public String getLlIE1_birlosPivote() {
        return LlIE1_birlosPivote;
    }

    public String getLlIE1_llantasPos1() {
        return LlIE1_llantasPos1;
    }

    public String getLlIE1_llantasPos2() {
        return LlIE1_llantasPos2;
    }

    public String getLlIE1_masaYoyo() {
        return LlIE1_masaYoyo;
    }

    public String getLlIE1_rin() {
        return LlIE1_rin;
    }

    public String getLlIE1_tieneLodera() {
        return LlIE1_tieneLodera;
    }

    public String getPu_bisagrasPuertas() {
        return Pu_bisagrasPuertas;
    }

    public String getPu_defensa() {
        return Pu_defensa;
    }

    public String getPu_luzGaliboSupTraseras() {
        return Pu_luzGaliboSupTraseras;
    }

    public String getPu_plafonesDer() {
        return Pu_plafonesDer;
    }

    public String getPu_plafonesIzq() {
        return Pu_plafonesIzq;
    }

    public String getPl_luzPlaca() {
        return Pl_luzPlaca;
    }

    public String getPl_placa() {
        return Pl_placa;
    }

    public String getS1_sello1() {
        return S1_sello1;
    }

    public String getS1_altaSeguridad() {
        return S1_altaSeguridad;
    }

    public String getLlDE1_birlosPivote() {
        return LlDE1_birlosPivote;
    }

    public String getLlDE1_llantasPos5() {
        return LlDE1_llantasPos5;
    }

    public String getLlDE1_llantasPos6() {
        return LlDE1_llantasPos6;
    }

    public String getLlDE1_masaYoyo() {
        return LlDE1_masaYoyo;
    }

    public String getLlDE1_rin() {
        return LlDE1_rin;
    }

    public String getLlDE1_tieneLodera() {
        return LlDE1_tieneLodera;
    }

    public String getDRP1_fondoPlaga() {
        return DRP1_fondoPlaga;
    }

    public String getDRP1_pisoPlaga() {
        return DRP1_pisoPlaga;
    }

    public String getDRP1_techoPlaga() {
        return DRP1_techoPlaga;
    }

    public String getDRP1_lucesCheck() {
        return DRP1_lucesCheck;
    }

    public String getDRP1_luzGaliboDerFrontalSup() {
        return DRP1_luzGaliboDerFrontalSup;
    }

    public String getDRP1_derPlaga() {
        return DRP1_derPlaga;
    }

    public String getDRP1_izqPlaga() {
        return DRP1_izqPlaga;
    }

    public String getDRP1_patinDer() {
        return DRP1_patinDer;
    }

    public String getDRP2_cuartoLadoDer() {
        return DRP2_cuartoLadoDer;
    }

    public String getDRP2_loderaDer() {
        return DRP2_loderaDer;
    }

    public String getDRP2_lucesCheck() {
        return DRP2_lucesCheck;
    }

    public String getDRP2_luzBarcoDer() {
        return DRP2_luzBarcoDer;
    }

    public String getDRP2_rompevientosDer() {
        return DRP2_rompevientosDer;
    }

    public String getS2_escotilla() {
        return S2_escotilla;
    }

    public String getS2_sello2() {
        return S2_sello2;
    }

    public String getS2_altaSeguridad() {
        return S2_altaSeguridad;
    }

    public String getLlIE2_birlosPivote() {
        return LlIE2_birlosPivote;
    }

    public String getLlIE2_llantasPos3() {
        return LlIE2_llantasPos3;
    }

    public String getLlIE2_llantasPos4() {
        return LlIE2_llantasPos4;
    }

    public String getLlIE2_masaYoyo() {
        return LlIE2_masaYoyo;
    }

    public String getLlIE2_rin() {
        return LlIE2_rin;
    }

    public String getLlIE2_tieneLodera() {
        return LlIE2_tieneLodera;
    }

    public String getLlDE2_birlosPivote() {
        return LlDE2_birlosPivote;
    }

    public String getLlDE2_llantasPos7() {
        return LlDE2_llantasPos7;
    }

    public String getLlDE2_llantasPos8() {
        return LlDE2_llantasPos8;
    }

    public String getLlDE2_masaYoyo() {
        return LlDE2_masaYoyo;
    }

    public String getLlDE2_rin() {
        return LlDE2_rin;
    }

    public String getLlDE2_tieneLodera() {
        return LlDE2_tieneLodera;
    }

    public String getCFD_amortiguador() {
        return CFD_amortiguador;
    }

    public String getCFD_bolsaAire() {
        return CFD_bolsaAire;
    }

    public String getCFD_gavilan() {
        return CFD_gavilan;
    }

    public String getCFD_muelle() {
        return CFD_muelle;
    }

    public String getCFD_rotachamber() {
        return CFD_rotachamber;
    }

    public String getCTD_amortiguador() {
        return CTD_amortiguador;
    }

    public String getCTD_bolsaAire() {
        return CTD_bolsaAire;
    }

    public String getCTD_matraca() {
        return CTD_matraca;
    }

    public String getCTD_muelle() {
        return CTD_muelle;
    }

    public String getCTD_rotachamber() {
        return CTD_rotachamber;
    }

    public String getCFI_amortiguador() {
        return CFI_amortiguador;
    }

    public String getCFI_bolsaAire() {
        return CFI_bolsaAire;
    }

    public String getCFI_gavilan() {
        return CFI_gavilan;
    }

    public String getCFI_muelle() {
        return CFI_muelle;
    }

    public String getCFI_rotachamber() {
        return CFI_rotachamber;
    }

    public String getCTI_amortiguador() {
        return CTI_amortiguador;
    }

    public String getCTI_bolsaAire() {
        return CTI_bolsaAire;
    }

    public String getCTI_matraca() {
        return CTI_matraca;
    }

    public String getCTI_muelle() {
        return CTI_muelle;
    }

    public String getCTI_rotachamber() {
        return CTI_rotachamber;
    }
}
