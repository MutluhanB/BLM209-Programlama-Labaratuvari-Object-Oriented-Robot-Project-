
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class hibrid extends gezginRobot implements manipulatorInterface {
    Scanner scanner = new Scanner(System.in);
    int sabitlemeSüresi;
    double kolUzunluk=-1;
    int tasımaHız=-1;
    String tip1;
    String tip2;


    public hibrid (ArrayList<Integer> tekerlekliHizList, ArrayList<Integer> paletliHizList){
        System.out.println("Hibrit robotun ilk kısmı (Tekerlekli / Paletli / Spider)");
        System.out.println("!-> Gezgin Ve Manipülatör robot kısımları oluşturulurken tekrar eden değerler için ilk girilen" +
                "değerler hibrid robot nesnesine işlenecektir");
        this.tip1 = scanner.nextLine().toLowerCase();
        if(this.tip1.equals("tekerlekli")){
            tekerlekli t = new tekerlekli();
            this.motorSayisi = t.motorSayisi;
            this.gezinmeHızı = t.gezinmeHızı;
            this.engelGecebilirmi = t.engelGecebilirmi;
            this.engelGec = t.engelSüresiBul();
            this.hareketliUnsurSayisi = t.hareketliUnsurSayisi;
            this.yükMiktari = t.yükMiktari;
        }
        if(this.tip1.equals("paletli")){
            if(tekerlekliHizList.isEmpty()){
                tekerlekliHizList.add(99999);
            }
            System.out.println("qwerqewr");
            paletli p = new paletli(tekerlekliHizList);
            this.motorSayisi = p.motorSayisi;
            this.gezinmeHızı = p.gezinmeHızı;
            this.engelGecebilirmi = p.engelGecebilirmi;
            this.engelGec = p.engelSüresiBul();
            this.hareketliUnsurSayisi = p.hareketliUnsurSayisi;
            this.yükMiktari = p.yükMiktari;
        }
        if(this.tip1.equals("spider")){
            if(tekerlekliHizList.isEmpty() && paletliHizList.isEmpty()){
                paletliHizList.add(99999);
            }
            else if(paletliHizList.isEmpty()){

                paletliHizList.add(Collections.min(tekerlekliHizList));
            }
            spider s = new spider(paletliHizList);
            this.motorSayisi = s.motorSayisi;
            this.gezinmeHızı = s.gezinmeHızı;
            this.engelGecebilirmi = s.engelGecebilirmi;
            this.hareketliUnsurSayisi = s.hareketliUnsurSayisi;
            this.yükMiktari = s.yükMiktari;
        }

        System.out.println("Hibrit robotun ikinci kısmı (Seri / Paralel)");
        this.tip2 = scanner.nextLine().toLowerCase();
        if(this.tip2.equals("paralel")){
            paralel p = new paralel();
            this.kolUzunluk = p.kolUzunluk;
            this.tasımaHız = p.tasımaHız;

        }
        if(this.tip2.equals("seri")){
            seri s = new seri();
            this.kolUzunluk = s.kolUzunluk;
            this.tasımaHız = s.tasımaHız;
        }
        System.out.println("Hibrid robotun ızgaraya sabitlenme süresi : ");
        this.sabitlemeSüresi = scanner.nextInt();

    }

    public void create(){};
    public void seriCreate(){};
    public boolean canCarry(int yukPosx, int yukPosy){
        double kolUzunluk = this.kolUzunluk;
        double uzaklik = Math.sqrt((this.xpos - yukPosx)*(this.xpos - yukPosx) + (this.ypos - yukPosy)*(this.ypos - yukPosy));
        System.out.println(this.kolUzunluk);
        System.out.println(uzaklik);
        return this.kolUzunluk >= uzaklik ;

    };
}
