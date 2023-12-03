import java.util.List;

public class ManajerAutentikasi {
    public static boolean autentikasiAdmin(String namaPengguna, String kataSandi, Admin admin) {
        return namaPengguna.equals(admin.getNamaPengguna()) && kataSandi.equals(admin.getKataSandi());
    }

    public static Pengguna autentikasiPengguna(String namaPengguna, String kataSandi, List<Pengguna> penggunaList) {
        for (Pengguna pengguna : penggunaList) {
            if (namaPengguna.equals(pengguna.getNamaPengguna()) && kataSandi.equals(pengguna.getKataSandi())) {
                return pengguna;
            }
        }
        return null;
    }
    public static boolean autentikasiRekeningKoran(String nomorAkun, String kataSandi, List<Pengguna> penggunaList) {
        for (Pengguna pengguna : penggunaList) {
            Akun akun = pengguna.getAkun();
            if (akun instanceof RekeningKoran && akun.getNomorAkun().equals(nomorAkun) && kataSandi.equals(pengguna.getKataSandi())) {
                return true;
            }
        }
        return false;
    }

    public static boolean autentikasiTeller(String namaPengguna, String kataSandi, Teller teller) {
        return namaPengguna.equals(teller.getNamaPengguna()) && kataSandi.equals(teller.getKataSandi());
    }
}
