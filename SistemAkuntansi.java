import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SistemAkuntansi {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();
        Admin admin = new Admin("admin", "adminpass");
        List<Pengguna> penggunaList = bank.getPenggunaList();
        Teller teller = new Teller("teller", "tellerpass", bank);

        // Tambahkan pengguna baru di sini jika diperlukan
        Pengguna penggunaBaru = new Pengguna("pengguna", "pengguna", new Akun("111111"));
        admin.tambahPengguna(penggunaBaru);
        bank.tambahPengguna(penggunaBaru);

        Pengguna penggunaBaru2 = new Pengguna("pengguna2", "sandi2", new Akun("222222"));
        admin.tambahPengguna(penggunaBaru2);
        bank.tambahPengguna(penggunaBaru2);
        RekeningKoran akunKoran = new RekeningKoran("123456");
        Pengguna pemilikAkunKoran = new Pengguna("Jon", "123", akunKoran);
        admin.tambahPengguna(pemilikAkunKoran);
        bank.tambahPengguna(pemilikAkunKoran);
        boolean keluarProgram = false;

        while (!keluarProgram) {
            System.out.println("\nMasukkan '1' untuk Admin, '2' untuk Pengguna, '3' untuk Teller, '4' untuk keluar:");
            int jenisPengguna = scanner.nextInt();

            switch (jenisPengguna) {
                case 1:
                    tampilkanMenuAdmin(admin, bank);
                    break;
                case 2:
                    tampilkanMenuPengguna(penggunaList, bank);
                    break;
                case 3:
                    tampilkanMenuTeller(teller, bank);
                    break;
                case 4:
                    keluarProgram = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        System.out.println("Terima kasih telah menggunakan Sistem Akuntansi!");
    }

    private static void tampilkanMenuAdmin(Admin admin, Bank bank) {
        boolean masuk = false;

        while (!masuk) {
            System.out.print("Masukkan nama pengguna: ");
            String namaPengguna = scanner.next();
            System.out.print("Masukkan kata sandi: ");
            String kataSandi = scanner.next();

            if (ManajerAutentikasi.autentikasiAdmin(namaPengguna, kataSandi, admin)) {
                masuk = true;
                tampilkanUIAdmin(admin, bank);
            } else {
                System.out.println("Kredensial tidak valid");
            }
        }
    }

    private static void tampilkanMenuPengguna(List<Pengguna> penggunaList, Bank bank) {
        boolean masuk = false;
        Pengguna penggunaAutentikasi = null;

        while (!masuk) {
            System.out.print("Masukkan nama pengguna: ");
            String namaPengguna = scanner.next();
            System.out.print("Masukkan kata sandi: ");
            String kataSandi = scanner.next();

            penggunaAutentikasi = ManajerAutentikasi.autentikasiPengguna(namaPengguna, kataSandi, penggunaList);
            if (penggunaAutentikasi != null) {
                masuk = true;
                tampilkanUIPengguna(penggunaAutentikasi, bank);
            } else {
                System.out.println("Kredensial tidak valid");
            }
        }

        System.out.println("Keluar sebagai pengguna. Terima kasih!");
    }

    private static void tampilkanMenuTeller(Teller teller, Bank bank) {
        boolean masuk = false;

        while (!masuk) {
            System.out.print("Masukkan nama pengguna: ");
            String namaPengguna = scanner.next();
            System.out.print("Masukkan kata sandi: ");
            String kataSandi = scanner.next();

            if (ManajerAutentikasi.autentikasiTeller(namaPengguna, kataSandi, teller)) {
                masuk = true;
                tampilkanUITeller(teller, bank);
            } else {
                System.out.println("Kredensial tidak valid");
            }
        }
    }

    private static void tampilkanUIAdmin(Admin admin, Bank bank) {
        boolean lanjutOperasi = true;

        while (lanjutOperasi) {
            System.out.println(
                    "\nMasukkan '1' untuk melihat semua pengguna, '2' untuk melihat semua toko, '3' untuk menambahkan pengguna baru, '4' untuk logout:");
            int pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    admin.tampilkanSemuaPengguna(); // Menampilkan informasi semua pengguna
                    break;
                case 2:
                    bank.tampilkanSemuaToko(); // Menampilkan informasi semua transaksi toko
                    break;
                case 3:
                    // Menambahkan pengguna baru
                    System.out.print("Masukkan nama pengguna baru: ");
                    String namaPenggunaBaru = scanner.next();
                    System.out.print("Masukkan kata sandi baru: ");
                    String kataSandiBaru = scanner.next();
                    System.out.print("Masukkan nomor akun baru: ");
                    String nomorAkunBaru = scanner.next();

                    // Membuat akun baru dan pengguna baru
                    Akun akunBaru = new Akun(nomorAkunBaru);
                    Pengguna penggunaBaru = new Pengguna(namaPenggunaBaru, kataSandiBaru, akunBaru);

                    // Menambahkan pengguna baru ke admin dan bank
                    admin.tambahPengguna(penggunaBaru);
                    bank.tambahPengguna(penggunaBaru);
                    System.out.println("Pengguna baru ditambahkan dengan berhasil.");
                    break;
                case 4:
                    lanjutOperasi = false;
                    break;
                case 5:
                    // Menambahkan RekeningKoran
                    System.out.print("Masukkan nomor akun RekeningKoran baru: ");
                    String nomorAkunRekeningKoran = scanner.next();
                    bank.tambahRekeningKoran(nomorAkunRekeningKoran);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        System.out.println("Logout sebagai admin. Terima kasih!");
    }

    private static void tampilkanUIPengguna(Pengguna pengguna, Bank bank) {
        boolean lanjutOperasi = true;

        while (lanjutOperasi) {
            pengguna.tampilkanInfo();
            System.out.println(
                    "\nMasukkan '1' untuk deposit, '2' untuk penarikan, '3' untuk transfer, '4' untuk histori, '5' untuk toko uang, '6' untuk logout:");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan jumlah deposit: ");
                    double jumlahDeposit = scanner.nextDouble();
                    pengguna.getAkun().deposit(jumlahDeposit);
                    break;
                case 2:
                    System.out.print("Masukkan jumlah penarikan: ");
                    double jumlahPenarikan = scanner.nextDouble();
                    pengguna.getAkun().penarikan(jumlahPenarikan);
                    break;
                case 3:
                    System.out.print("Masukkan nomor akun penerima: ");
                    String nomorAkunPenerima = scanner.next();
                    Pengguna penggunaPenerima = temukanPenggunaDenganNomorAkun(nomorAkunPenerima,
                            bank.getPenggunaList());
                    if (penggunaPenerima != null) {
                        System.out.print("Masukkan jumlah transfer: ");
                        double jumlahTransfer = scanner.nextDouble();
                        pengguna.getAkun().transfer(penggunaPenerima.getAkun(), jumlahTransfer);
                    } else {
                        System.out.println("Nomor akun penerima tidak ditemukan.");
                    }
                    break;
                case 4:
                    System.out.println("Histori Transaksi:");

                    List<Transaksi> transaksiList = pengguna.getAkun().getTransaksi();
                    for (Transaksi transaksi : transaksiList) {
                        System.out.println(transaksi);
                    }
                    break;
                case 5:
                    System.out.print("Masukkan jumlah uang untuk toko: ");
                    double jumlahToko = scanner.nextDouble();
                    LocalDateTime waktuToko = LocalDateTime.now();
                    bank.tambahToko(jumlahToko, waktuToko);
                    break;
                case 6:
                    lanjutOperasi = false;
                    break;
                case 7:
                    // Menampilkan informasi transaksi toko dari RekeningKoran
                    if (pengguna.getAkun() instanceof RekeningKoran) {
                        RekeningKoran rekeningKoran = (RekeningKoran) pengguna.getAkun();
                        System.out.println("Transaksi Toko:");
                        List<Toko> transaksiTokoList = rekeningKoran.getTransaksiToko();
                        for (Toko toko : transaksiTokoList) {
                            System.out.println("Jumlah: " + toko.getJumlah() + ", Tanggal: " + toko.getDateTime());
                        }
                    } else {
                        System.out.println("Akun bukan RekeningKoran.");
                    }
                    break;
                case 8:
                    // Login sebagai RekeningKoran
                    System.out.print("Masukkan nomor akun RekeningKoran: ");
                    String nomorAkunRekeningKoran = scanner.next();
                    System.out.print("Masukkan kata sandi: ");
                    String kataSandiRekeningKoran = scanner.next();
                    if (ManajerAutentikasi.autentikasiRekeningKoran(nomorAkunRekeningKoran, kataSandiRekeningKoran,
                            bank.getPenggunaList())) {
                        Pengguna penggunaRekeningKoran = ManajerAutentikasi.autentikasiPengguna(nomorAkunRekeningKoran,
                                kataSandiRekeningKoran, bank.getPenggunaList());
                        tampilkanUIPengguna(penggunaRekeningKoran, bank);
                    } else {
                        System.out.println("Autentikasi gagal untuk RekeningKoran.");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        System.out.println("Logout sebagai pengguna. Terima kasih!");
    }

    private static void tampilkanUITeller(Teller teller, Bank bank) {
        boolean lanjutOperasi = true;

        while (lanjutOperasi) {
            System.out.println("\nTeller: " + teller.getNamaPengguna());

            System.out.println(
                    "\nMasukkan '1' untuk transfer, '2' untuk deposit, '3' untuk penarikan, '4' untuk logout:");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nomor akun Anda: ");
                    String nomorAkunPengirim = scanner.next();
                    Pengguna pengirim = temukanPenggunaDenganNomorAkun(nomorAkunPengirim, bank.getPenggunaList());
                    if (pengirim != null) {
                        System.out.print("Masukkan nomor akun penerima: ");
                        String nomorAkunPenerima = scanner.next();
                        System.out.print("Masukkan jumlah transfer: ");
                        double jumlahTransfer = scanner.nextDouble();
                        teller.transfer(pengirim, nomorAkunPenerima, jumlahTransfer);
                    } else {
                        System.out.println("Nomor akun pengirim tidak ditemukan.");
                    }
                    break;
                case 2:
                    System.out.print("Masukkan nomor akun Anda: ");
                    String nomorAkunDeposit = scanner.next();
                    Pengguna penggunaDeposit = temukanPenggunaDenganNomorAkun(nomorAkunDeposit, bank.getPenggunaList());
                    if (penggunaDeposit != null) {
                        System.out.print("Masukkan jumlah deposit: ");
                        double jumlahDeposit = scanner.nextDouble();
                        teller.deposit(penggunaDeposit, jumlahDeposit);
                    } else {
                        System.out.println("Akun pengguna tidak ditemukan.");
                    }
                    break;
                case 3:
                    System.out.print("Masukkan nomor akun Anda: ");
                    String nomorAkunPenarikan = scanner.next();
                    Pengguna penggunaPenarikan = temukanPenggunaDenganNomorAkun(nomorAkunPenarikan,
                            bank.getPenggunaList());
                    if (penggunaPenarikan != null) {
                        System.out.print("Masukkan jumlah penarikan: ");
                        double jumlahPenarikan = scanner.nextDouble();
                        teller.penarikan(penggunaPenarikan, jumlahPenarikan);
                    } else {
                        System.out.println("Akun pengguna tidak ditemukan.");
                    }
                    break;
                case 4:
                    lanjutOperasi = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        System.out.println("Logout sebagai teller. Terima kasih!");
    }

    private static Pengguna temukanPenggunaDenganNomorAkun(String nomorAkun, List<Pengguna> penggunaList) {
        for (Pengguna penggunaLoop : penggunaList) {
            if (penggunaLoop.getAkun().getNomorAkun().equals(nomorAkun)) {
                return penggunaLoop;
            }
        }
        return null;
    }
}
