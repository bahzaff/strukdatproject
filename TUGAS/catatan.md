# KALKULATOR TUGAS 
JAVA - PBO Barra Ahza Fakhrullah 023
## deskripsi kasus
> Dalam sebuah organisasi/kepanitiaan atau bahkan sekedar kelompok belajar kecil biasanya terdapat perbedaan tugas yang dikerjakan oleh masing masing individu hal tersebut bisanya menjadi pemicu ketimpangan tugas yang dapat dirasakan oleh beberapa anggota kelompok itu sendiri, maka dari itu code ini bertujuan untuk memanage dan menghitung bobot tugas dari masing masing anggota yang dilengkapi dengan pengingat deadline dengan trigger sisa hari.
## class diagram
![Output](mermaid%20diagram.png)
## kode java 
```java

import java.util.Scanner;


interface Evaluator {
    double hitungSkorPerforma();
}


abstract class Orang {
    protected String nrp, nama, divisi;
    public Orang(String nrp, String nama, String divisi) {
        this.nrp = nrp; this.nama = nama; this.divisi = divisi;
    }
    public abstract void tampilkanProfil();
}


class Tugas {
    public String namaTugas;
    public int bobot, sisaHari;
    public boolean isSelesai = false;

    public Tugas(String namaTugas, int bobot, int sisaHari) {
        this.namaTugas = namaTugas;
        this.bobot = bobot;
        this.sisaHari = sisaHari;
    }

    public void setSelesai() { this.isSelesai = true; }
}


class Staff extends Orang implements Evaluator {
    public Tugas[] daftarTugas = new Tugas[10];
    public int jumlahTugas = 0;

    public Staff(String nrp, String nama, String divisi) { super(nrp, nama, divisi); }

    public void tambahTugas(Tugas t) {
        if (jumlahTugas < 10) {
            daftarTugas[jumlahTugas++] = t;
            System.out.println("berhasil input beban (tugas)");
        } else { System.out.println("kebanyakan tugas woiii"); }
    }

    @Override
    public void tampilkanProfil() {
        System.out.println("\n[ PROFIL: " + nama + " | " + nrp + " | " + divisi + " ]");
    }

    public void lihatDaftarTugas() {
        System.out.println("\n--- DAFTAR TUGAS ---");
        for (int i = 0; i < jumlahTugas; i++) {
            String status = daftarTugas[i].isSelesai ? "SELESAI" : "BELUM";
            System.out.println((i + 1) + ". " + daftarTugas[i].namaTugas + " [" + status + "]");
        }
    }

    @Override
    public double hitungSkorPerforma() {
        if (jumlahTugas == 0) return 0;
        double selesai = 0, total = 0;
        for (int i = 0; i < jumlahTugas; i++) {
            total += daftarTugas[i].bobot;
            if (daftarTugas[i].isSelesai) selesai += daftarTugas[i].bobot;
        }
        return (selesai / total) * 100;
    }
}


public class KalkulatorTugas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== SISTEM SKORING TUGAS ===");
        System.out.print("Nama: "); String n = sc.nextLine();
        System.out.print("NRP : "); String nr = sc.nextLine();
        System.out.print("Div : "); String d = sc.nextLine();
        Staff s = new Staff(nr, n, d);

        int menu = 0;
        while (menu != 5) { 
            System.out.println("\nMENU:");
            System.out.println("1. Tambah Tugas\n2. Update Status Tugas\n3. Lihat Daftar & Skor\n4. Profil\n5. Keluar");
            System.out.print("Pilih: "); menu = sc.nextInt();
            sc.nextLine(); 

            switch (menu) {
                case 1:
                    System.out.print("Judul: "); String j = sc.nextLine();
                    System.out.print("Bobot (1-10): "); int b = sc.nextInt();
                    System.out.print("Sisa Hari: "); int sh = sc.nextInt();
                    s.tambahTugas(new Tugas(j, b, sh));
                    break;
                case 2:
                    s.lihatDaftarTugas();
                    if(s.jumlahTugas > 0) {
                        System.out.print("Nomor tugas yang selesai: ");
                        int no = sc.nextInt();
                        if(no > 0 && no <= s.jumlahTugas) s.daftarTugas[no-1].setSelesai();
                        else System.out.println("Nomor salah!");
                    }
                    break;
                case 3:
                    s.lihatDaftarTugas();
                    System.out.println("SKOR PERFORMA: " + s.hitungSkorPerforma());
                    break;
                case 4:
                    s.tampilkanProfil();
                    break;
            }
        }
        System.out.println("program done, semoga ga kena mental");
        sc.close();
    }
}
```
