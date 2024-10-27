/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kalkulator;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.util.ArrayList;
/**
 *
 * @author ASUS TUF
 */
public class Kalkulator {
    private ArrayList<Long> operan;
    private ArrayList<jenisOP> operasi;

    public enum jenisOP {
        Kosong, Tambah, Kurang, Kali, Bagi;
    }

    jenisOP defaultOP;

    public Kalkulator() {
        operan = new ArrayList<Long>();
        operasi = new ArrayList<jenisOP>();
        defaultOP = jenisOP.Kosong;
    }

    public void reset() {
        operan.clear();
        operasi.clear();
        defaultOP = jenisOP.Kosong;
    }

    public void tambah(long OP) {
        operan.add(OP);
        operasi.add(jenisOP.Tambah);
    }

    public void kurang(long OP) {
        operan.add(OP);
        operasi.add(jenisOP.Kurang);
    }

    public void kali(long OP) {
        operan.add(OP);
        operasi.add(jenisOP.Kali);
    }

    public void bagi(long OP) {
        operan.add(OP);
        operasi.add(jenisOP.Bagi);
    }

    //memeriksa apakah operator terakhir adalah operator tambah
    public long hasil() {
    if (operan.size() < 2) {
        return operan.isEmpty() ? 0 : operan.get(0);//operator ternary
    }

    //operasi perkalian dan pembagian terlebih dahulu
    for (int i = 0; i < operasi.size()-1; i++) {
        jenisOP currentOP = operasi.get(i);
        if (currentOP == jenisOP.Kali || currentOP == jenisOP.Bagi) {
            long op1 = operan.get(i);
            long op2 = operan.get(i + 1);
            long hasil = currentOP == jenisOP.Kali ? op1 * op2 : op1 / op2;//operator ternary 
            operan.set(i, hasil);
            operan.remove(i + 1);
            operasi.remove(i);
            i--;
        }
    }

    // operasi penjumlahan dan pengurangan
    long hasil = operan.get(0);
    for (int i = 1; i < operan.size(); i++) {
        long op = operan.get(i);
        jenisOP currentOP = operasi.get(i - 1);
        switch (currentOP) {
            case Tambah:
                hasil += op;
                break;
            case Kurang:
                hasil -= op;
                break;
            default:
                break;
        }
    }
    reset();

    return hasil;
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            System.out.println("Gagal mengganti mode UI");
        }
        
        KalkulatorFrame java = new KalkulatorFrame();
        
        java.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.pack();
        java.setVisible(true);
    }
    
}
