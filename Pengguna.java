public class Pengguna implements PenggunaInterface {
    private String namaPengguna;
    private String kataSandi;
    private Akun akun;

    public Pengguna(String namaPengguna, String kataSandi, Akun akun) {
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
        this.akun = akun;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public Akun getAkun() {
        return akun;
    }
    public void tampilkanInfo() {
        System.out.println("Nama Pengguna: " + getNamaPengguna());
        System.out.println("Nomor Akun: " + getAkun().getNomorAkun());
        System.out.println("Saldo: " + getAkun().getSaldo());
    }
}
