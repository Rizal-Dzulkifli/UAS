import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

class RekeningKoran extends Akun {
    private List<Toko> transaksiToko;

    public RekeningKoran(String nomorAkun) {
        super(nomorAkun);
        this.transaksiToko = new ArrayList<>();
    }

    public List<Toko> getTransaksiToko() {
        return transaksiToko;
    }

    public void tambahToko(double jumlah, LocalDateTime dateTime) {
        transaksiToko.add(new Toko(jumlah, dateTime));
        System.out.println("Transaksi toko berhasil.");
    }

    // Override toString method to include transaction history
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString() + "\nTransaksi Toko:\n");
        for (Toko toko : transaksiToko) {
            result.append("Jumlah: ").append(toko.getJumlah()).append(", Tanggal: ").append(toko.getDateTime()).append("\n");
        }
        return result.toString();
    }
}
