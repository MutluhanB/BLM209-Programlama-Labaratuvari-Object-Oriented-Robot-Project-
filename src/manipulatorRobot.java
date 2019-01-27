import java.util.Scanner;

public abstract class manipulatorRobot extends robot implements manipulatorInterface{
    Scanner scanner = new Scanner(System.in);
    static int yukKapasiteParalel=-1;
    static int yukKapasiteSeri=-1;
    double kolUzunluk;
    int tasımaHız;

    @Override
    public void create(){
        System.out.println("Motor sayisi : ");
        this.motorSayisi = scanner.nextInt();

        if(yukKapasiteParalel == -1 ){

            do{
                System.out.println("Paralel Robot Sınıfının Yük taşıma kapasitesi : ");
                this.yukKapasiteParalel = scanner.nextInt();
                if(this.yukKapasiteParalel < this.yukKapasiteSeri){
                    System.out.println("Paralel robotların kapasitesi seri robotların kapasitesinden küçük olamaz! ");
                }
            }while (this.yukKapasiteParalel < this.yukKapasiteSeri);

        }


        int yukMiktari;
        do{
            System.out.println("Taşınacak yük miktarı : ");
            yukMiktari = scanner.nextInt();
            if(yukMiktari > this.yukKapasiteParalel){
                System.out.println("Aşırı yükleme ! Tekrar giriniz !");
            }
        }while (yukMiktari > this.yukKapasiteParalel);
        System.out.println("Kol uzunluğu (metre cinsinden (1 birim kare 10x10 metredir)): ");
        this.kolUzunluk = scanner.nextDouble() / 10;
        System.out.println("Taşıma hızı : ");
        this.tasımaHız = scanner.nextInt();
    }

    @Override
    public void seriCreate(){
        System.out.println("Motor sayisi : ");
        this.motorSayisi = scanner.nextInt();

        if(this.yukKapasiteSeri == -1 && yukKapasiteParalel != -1) {
            do {
                System.out.println("Seri Robot Sınıfının Yük taşıma kapasitesi : ");
                this.yukKapasiteSeri = scanner.nextInt();
                if (yukKapasiteParalel != -1 && this.yukKapasiteSeri > this.yukKapasiteParalel) {
                    System.out.println("Paralel robotların kapasitesi seri robotların kapasitesinden küçük olamaz! ");
                }
            } while (this.yukKapasiteParalel < this.yukKapasiteSeri);
        }



        if(yukKapasiteSeri == -1 ){
                System.out.println("Seri Robot Sınıfının Yük taşıma kapasitesi : ");
                this.yukKapasiteSeri = scanner.nextInt();

        }


        int yukMiktari;
        do{
            System.out.println("Taşınacak yük miktarı : ");
            yukMiktari = scanner.nextInt();
            if(yukMiktari > this.yukKapasiteSeri){
                System.out.println("Aşırı yükleme ! Tekrar giriniz !");
            }
        }while (yukMiktari > this.yukKapasiteSeri);
        System.out.println("Kol uzunluğu (metre cinsinden (1 birim kare 10x10 metredir)): ");
        this.kolUzunluk = scanner.nextDouble() / 10;
        System.out.println("Taşıma hızı : ");
        this.tasımaHız = scanner.nextInt();
    }

    @Override
    public boolean canCarry(int yukPosx, int yukPosy){
        double kolUzunluk = this.kolUzunluk;
        double uzaklik = Math.sqrt((this.xpos - yukPosx)*(this.xpos - yukPosx) + (this.ypos - yukPosy)*(this.ypos - yukPosy));
        System.out.println(this.kolUzunluk);
        System.out.println(uzaklik);
        return this.kolUzunluk >= uzaklik ;
    }
}
