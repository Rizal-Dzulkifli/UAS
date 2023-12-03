import java.util.ArrayList;
import java.util.List;

public class Admin  {
    private String namaPengguna;
    private String kataSandi;
    private List<Pengguna> penggunaList;

    public Admin(String namaPengguna, String kataSandi) {
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
        this.penggunaList = new ArrayList<>();
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void tambahPengguna(Pengguna pengguna) {
        penggunaList.add(pengguna);
    }

    public void tampilkanSemuaPengguna() {
        System.out.println("Daftar semua pengguna:");
        for (Pengguna pengguna : penggunaList) {
            System.out.println(
                    "Nama Pengguna: " + pengguna.getNamaPengguna() + ", Nomor Akun: " + pengguna.getAkun().getNomorAkun());
        }
    }
    
}
