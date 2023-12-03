import java.time.LocalDateTime;

public class Toko {
    private double jumlah;
    private LocalDateTime dateTime;

    public Toko(double jumlah, LocalDateTime dateTime) {
        this.jumlah = jumlah;
        this.dateTime = dateTime;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
