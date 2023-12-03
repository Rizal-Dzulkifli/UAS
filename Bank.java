import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime; 

public class Bank {
    private List<Pengguna> penggunaList;
    private List<Toko> tokoList;

    public Bank() {
        this.penggunaList = new ArrayList<>();
        this.tokoList = new ArrayList<>();
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public List<Toko> getTokoList() {
        return tokoList;
    }

    public void tambahPengguna(Pengguna pengguna) {
        penggunaList.add(pengguna);
    }

    public void tambahToko(double jumlah, LocalDateTime dateTime) {
        tokoList.add(new Toko(jumlah, dateTime));
    }

    public void tampilkanSemuaPengguna() {
        System.out.println("Daftar semua pengguna:");
        for (Pengguna pengguna : penggunaList) {
            System.out.println(
                    "Nama Pengguna: " + pengguna.getNamaPengguna() + ", Nomor Akun: " + pengguna.getAkun().getNomorAkun());
        }
    }

    public void tampilkanSemuaToko() {
        System.out.println("Daftar semua toko:");
        for (Toko toko : tokoList) {
            System.out.println("Jumlah: " + toko.getJumlah() + ", Tanggal: " + toko.getDateTime());
        }
    }
    public void tambahRekeningKoran(String nomorAkun) {
        RekeningKoran rekeningKoran = new RekeningKoran(nomorAkun);
        penggunaList.add(new Pengguna("RekeningKoran", "password", rekeningKoran));
        System.out.println("Rekening Koran berhasil ditambahkan.");
    }
}
