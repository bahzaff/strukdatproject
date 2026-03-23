package tugasjava;
import java.util.Scanner;


class Tugas {
    String namaTugas;
    int bobot;
    int sisaHari;

    public Tugas(String nama, int bbt, int hari) {
        this.namaTugas = nama;
        this.bobot = bbt;
        this.sisaHari = hari;
    }
}


abstract class Orang {
    protected String nrp, nama, divisi;
    public Orang(String nama, String nrp, String divisi) {
        this.nama = nama;
        this.nrp = nrp;
        this.divisi = divisi;
    }
    public abstract void tampilkanProfil();
}


abstract class Evaluator extends Orang {
    public Evaluator(String nama, String nrp, String divisi) {
        super(nama, nrp, divisi);
    }
    public abstract double hitungSkorPerforma();
}


class Staff extends Evaluator {
    private Tugas[] daftarTugas = new Tugas[5]; 
    private int jumlahTugas = 0;

    public Staff(String nama, String nrp, String divisi) {
        super(nama, nrp, divisi);
    }

    public void tambahTugas(String nama, int bbt, int hari) {
        if (jumlahTugas < 5) {
          
            daftarTugas[jumlahTugas] = new Tugas(nama, bbt, hari);
            jumlahTugas++;
            System.out.println("done tambah beban (tugas)");
        } else {
            System.out.println("kebanyakan tugas woii");
        }
    }

    public void lihatRincianTugas() {
        if (jumlahTugas == 0) {
            System.out.println("   (Belum ada tugas)");
        } else {
            for (int i = 0; i < jumlahTugas; i++) {
                Tugas t = daftarTugas[i]; 
                String status = (t.sisaHari <= 3) ? "[!] MEPET DL NIH " : "[^] MASIH AMANN   ";
                System.out.println("   " + status + "| tugas: " + t.namaTugas + " | Bobot: " + t.bobot + " | Sisa: " + t.sisaHari + " hari");
            }
        }
    }

    @Override
    public double hitungSkorPerforma() {
        int totalBobot = 0;
        for (int i = 0; i < jumlahTugas; i++) {
            totalBobot += daftarTugas[i].bobot;
        }
        return totalBobot * 2;
    }

    @Override
    public void tampilkanProfil() {
        System.out.println("ANGGOTA: " + nama + " (" + nrp + ") | Skor: " + hitungSkorPerforma());
    }
}

public class KalkulatorTugas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Staff[] kumpulanStaff = new Staff[10];
        int totalStaff = 0;

        while (true) {
            System.out.println("\n===== MANAGEMENT TUGAS =====");
            System.out.println("1. input anggota");
            System.out.println("2. Input Tugas & Deadline");
            System.out.println("3. list skor anggota dengan tugas n deadline");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu: ");
            int pil = sc.nextInt(); sc.nextLine();

            if (pil == 1) {
                if (totalStaff < 10) {
                    System.out.print("Masukkan Nama   : "); 
                    String nama = sc.nextLine();
                    System.out.print("Masukkan NRP    : "); 
                    String nrp = sc.nextLine();
                    System.out.print("Masukkan Divisi : "); 
                    String div = sc.nextLine();
                    kumpulanStaff[totalStaff] = new Staff(nama, nrp, div);
                    totalStaff++;
                    System.out.println("Status: Berhasil diinput!");
                }
            } else if (pil == 2) {
                if (totalStaff == 0) { System.out.println("Belum ada data anggota"); continue; }

                System.out.println("\n--- Pilih anggota ---");
                for (int i = 0; i < totalStaff; i++) System.out.println((i + 1) + ". " + kumpulanStaff[i].nama);
                System.out.print("Nomor anggota: ");
                int target = sc.nextInt() - 1; sc.nextLine();

                if (target >= 0 && target < totalStaff) {
                    System.out.print("Nama Tugas  : "); 
                    String tgs = sc.nextLine();
                    System.out.print("Bobot (1-10): "); 
                    int bbt = sc.nextInt();
                    System.out.print("Sisa Hari   : "); 
                    int hari = sc.nextInt();
                    kumpulanStaff[target].tambahTugas(tgs, bbt, hari);
                }
            } else if (pil == 3) {
                if (totalStaff == 0) { System.out.println("gaada apa-apa nih"); continue; }

                System.out.println("\n========= REKAP TUGAS & DEADLINE =========");
                for (int i = 0; i < totalStaff; i++) {
                    System.out.print((i + 1) + ". ");
                    kumpulanStaff[i].tampilkanProfil();
                    kumpulanStaff[i].lihatRincianTugas();
                    System.out.println("------------------------------------------");
                }
            } else if (pil == 4) {
                System.out.println("selesaiiii");
                break;
            }
        }
        sc.close();
    }
}