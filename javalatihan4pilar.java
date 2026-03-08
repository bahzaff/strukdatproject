// PILAR 1: ENCAPSULATION (Enkapsulasi)
class Rakyat {
    private String nama;
    private double uang;
    
    // Constructor
    public Rakyat(String nama, double uang) {
        this.nama = nama;
        this.uang = uang;
    }
    
    // Method untuk bayar pajak
    public void bayarPajak(double jumlah) {
        if (uang >= jumlah) {
            uang -= jumlah;
            System.out.println(nama + " bayar pajak " + jumlah);
        } else {
            System.out.println(nama + " uang tidak cukup!");
        }
    }
    
   
    
    // Method untuk lihat status
    public void lihatUang() {
        System.out.println(nama + " punya uang: " + uang);
    }
    
    // Getter untuk nama
    public String getNama() {
        return nama;
    }
}


// PILAR 2: ABSTRACTION (Abstraksi)
abstract class Raja {
    protected String nama;
    
    public Raja(String nama) {
        this.nama = nama;
    }
    
    // Method abstrak - harus diimplementasikan subclass
    abstract double hitungPajak(Rakyat rakyat);
    
}


// PILAR 3: INHERITANCE (Pewarisan)

class RajaAdil extends Raja {
    
    public RajaAdil(String nama) {
        super(nama);  // Panggil constructor parent
    }
    
    // Implementasi method abstrak
    @Override
    double hitungPajak(Rakyat rakyat) {
        return 100;  // Pajak tetap 100
    }
    
    
    
}

class RajaSerakah extends Raja {
    
    public RajaSerakah(String nama) {
        super(nama);  // Panggil constructor parent
    }
    
    // Implementasi berbeda dari RajaAdil
    @Override
    double hitungPajak(Rakyat rakyat) {
        return 500;  // Pajak besar 500
    }

}


// PILAR 4: POLYMORPHISM (Polimorfisme)
class Kerajaan {
    private Raja raja;
    private Rakyat[] rakyat;
    
    public Kerajaan(Raja raja, int jumlahRakyat) {
        this.raja = raja;
        this.rakyat = new Rakyat[jumlahRakyat];
    }
    
    public void tambahRakyat(int index, Rakyat r) {
        rakyat[index] = r;
    }
    
    // POLYMORPHISM: raja.hitungPajak() berbeda untuk RajaAdil dan RajaSerakah
    public void pungutPajak() {
        System.out.println("\n=== PEMUNGUTAN PAJAK ===");
        for (Rakyat r : rakyat) {
            if (r != null) {
                double pajak = raja.hitungPajak(r);  // <- POLYMORPHISM!
                r.bayarPajak(pajak);
            }
        }
    }
    
    public void lihatStatus() {
        System.out.println("\n=== STATUS RAKYAT ===");
        for (Rakyat r : rakyat) {
            if (r != null) {
                r.lihatUang();
            }
        }
    }
}

public class javalatihan4pilar {
    public static void main(String[] args) {
        System.out.println("SISTEM PAJAK - 4 PILAR OOP SIMPLE");
        System.out.println("=================================\n");
        
        // ===== SKENARIO 1: RAJA ADIL =====
        System.out.println(">>> SKENARIO 1: RAJA ADIL <<<\n");
        
        // Buat raja adil
        Raja rajaAdil = new RajaAdil("Joyoboyo");  // Abstraction + Inheritance
        System.out.println("Raja bernama: " + rajaAdil.nama);
        // Buat kerajaan
        Kerajaan kerajaan1 = new Kerajaan(rajaAdil, 3);
        
        // Tambah rakyat (Encapsulation)
        kerajaan1.tambahRakyat(0, new Rakyat("Budi", 500));
        kerajaan1.tambahRakyat(1, new Rakyat("Siti", 600));
        kerajaan1.tambahRakyat(2, new Rakyat("Hendra", 700));
        
        System.out.println("Status awal:");
        kerajaan1.lihatStatus();
        
        // Pungut pajak (Polymorphism)
        kerajaan1.pungutPajak();
        
        System.out.println("Status setelah pajak:");
        kerajaan1.lihatStatus();
        
        // ===== SKENARIO 2: RAJA SERAKAH =====
        System.out.println("\n\n>>> SKENARIO 2: RAJA SERAKAH <<<\n");
        
        // Buat raja serakah (berbeda implementasi!)
        Raja rajaSerakah = new RajaSerakah("Mahabarata");  // Polymorphism!
        System.out.println("Raja bernama: " + rajaSerakah.nama);
        // Buat kerajaan baru
        Kerajaan kerajaan2 = new Kerajaan(rajaSerakah, 3);
        
        // Tambah rakyat yang sama
        kerajaan2.tambahRakyat(0, new Rakyat("Budi", 500));
        kerajaan2.tambahRakyat(1, new Rakyat("Siti", 600));
        kerajaan2.tambahRakyat(2, new Rakyat("Hendra", 700));
        
        System.out.println("Status awal:");
        kerajaan2.lihatStatus();
        
        // Pungut pajak (Hasil BERBEDA karena RajaSerakah.hitungPajak())
        kerajaan2.pungutPajak();
        
        System.out.println("Status setelah pajak:");
        kerajaan2.lihatStatus();
    }
}

