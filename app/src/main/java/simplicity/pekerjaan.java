package simplicity;

public enum Pekerjaan {
    BADUT_SULAP("Badut Sulap", 15, 4),
    KOKI("Koki", 30, 4),
    POLISI("Polisi", 35, 4),
    PROGRAMMER("Programmer", 45, 4),
    DOKTER("Dokter", 50, 4);

    private final String namaPekerjaan;
    private final int gaji;
    private final int waktuKerja;

    Pekerjaan(String namaPekerjaan, int gaji, int waktuKerja) {
        this.namaPekerjaan = namaPekerjaan;
        this.gaji = gaji;
        this.waktuKerja = waktuKerja;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public int getGaji() {
        return gaji;
    }

    public int getWaktuKerja() {
        return waktuKerja;
    }

    public Pekerjaan[] getAllPekerjaan() {
        return values();
    }
}