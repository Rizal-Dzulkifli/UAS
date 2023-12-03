import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {
    private String jenis;
    private double jumlah;
    private String tanggal;

    public Transaksi(String jenis, double jumlah, String tanggal) {
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public double getJumlah() {
        return jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    @Override
    public String toString() {
        return "Jenis: " + jenis + ", Jumlah: " + jumlah + ", Tanggal: " + tanggal;
    }
}
