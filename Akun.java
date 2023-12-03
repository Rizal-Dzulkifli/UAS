import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Akun {
    private String nomorAkun;
    private double saldo;
    private List<Transaksi> transaksi;

    public Akun(String nomorAkun) {
        this.nomorAkun = nomorAkun;
        this.saldo = 0;
        this.transaksi = new ArrayList<>();
    }

    public String getNomorAkun() {
        return nomorAkun;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transaksi> getTransaksi() {
        return transaksi;
    }

    public void deposit(double jumlah) {
        saldo += jumlah;
        transaksi.add(new Transaksi("Deposit", jumlah, getFormattedDateTime()));
        System.out.println("Deposit berhasil.");
    }

    public void penarikan(double jumlah) {
        if (saldo >= jumlah) {
            saldo -= jumlah;
            transaksi.add(new Transaksi("Penarikan", jumlah, getFormattedDateTime()));
            System.out.println("Penarikan berhasil.");
        } else {
            System.out.println("Saldo tidak mencukupi untuk penarikan.");
        }
    }

    public void transfer(Akun penerima, double jumlah) {
        if (saldo >= jumlah) {
            saldo -= jumlah;
            penerima.deposit(jumlah);
            transaksi.add(new Transaksi("Transfer ke " + penerima.getNomorAkun(), jumlah, getFormattedDateTime()));
            System.out.println("Transfer berhasil.");
        } else {
            System.out.println("Saldo tidak mencukupi untuk transfer.");
        }
    }

    public void tampilkanTransaksi() {
        System.out.println("Histori transaksi untuk akun " + nomorAkun + ":");
        for (Transaksi transaksi : transaksi) {
            System.out.println(transaksi);
        }
    }

    private String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
