import java.util.List;  // Add this import statement
import java.time.LocalDateTime;

public class Teller {
    private String namaPengguna;
    private String kataSandi;
    private Bank bank;

    public Teller(String namaPengguna, String kataSandi, Bank bank) {
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
        this.bank = bank;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void transfer(Pengguna pengirim, String nomorAkunPenerima, double jumlah) {
        Pengguna penerima = temukanPenggunaDenganNomorAkun(nomorAkunPenerima, bank.getPenggunaList());
        if (penerima != null) {
            pengirim.getAkun().transfer(penerima.getAkun(), jumlah);
            System.out.println("Transfer berhasil.");
        } else {
            System.out.println("Nomor akun penerima tidak ditemukan.");
        }
    }

    public void deposit(Pengguna pengguna, double jumlah) {
        pengguna.getAkun().deposit(jumlah);
        System.out.println("Deposit berhasil.");
    }

    public void penarikan(Pengguna pengguna, double jumlah) {
        pengguna.getAkun().penarikan(jumlah);
        System.out.println("Penarikan berhasil.");
    }

    private Pengguna temukanPenggunaDenganNomorAkun(String nomorAkun, List<Pengguna> penggunaList) {
        for (Pengguna penggunaLoop : penggunaList) {
            if (penggunaLoop.getAkun().getNomorAkun().equals(nomorAkun)) {
                return penggunaLoop;
            }
        }
        return null;
    }
}
