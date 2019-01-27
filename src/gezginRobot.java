import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class gezginRobot extends robot {
    Scanner scanner = new Scanner(System.in);
    int gezinmeHızı;
    int hareketliUnsurSayisi;
    double engelGec;
    boolean engelGecebilirmi;


    public void create(){

        /*
        * Tekerlekli robotlar parametresiz create methodunu kullanarak özelliklerini okutacaktır.
        * */

        System.out.println("Motor Sayisi : ");
        this.motorSayisi = scanner.nextInt();
        System.out.println("Yük Miktari : ");
        this.yükMiktari = scanner.nextInt();
        System.out.println("Gezinme Hizi : ");
        this.gezinmeHızı = scanner.nextInt();
        System.out.println("Hareketli parça sayisi (Tekerlek/Palet/Bacak) : ");
        this.hareketliUnsurSayisi = scanner.nextInt();

    }

    public void create(ArrayList<Integer> hızlist) {

        /*
        *Paletli ve Spider robotlar 1 adet liste alan bu method ile özelliklerini okutacaklardır. Parametre olarak
        * alınan liste sayesinde. HızTekerlekli > HızPaletli > HızSpider hiyerarşisi sağlanacaktır.
        * */

        int minTekerliHız = Collections.min(hızlist);

        if(hızlist.isEmpty()){
            minTekerliHız = 99999;
        }
        System.out.println("Motor Sayisi : ");
        this.motorSayisi = scanner.nextInt();
        System.out.println("Yük Miktari : ");
        this.yükMiktari = scanner.nextInt();
        System.out.println("Gezinme Hizi : ");
        int gezinmeHiz = -1;
        int flag = 1;
        while (flag == 1) {
            gezinmeHiz = scanner.nextInt();
            if (gezinmeHiz > minTekerliHız) {
                System.out.println("Girdiğiniz değer gezgin robotlar arasındaki hız hiyerarşisine uymuyor !");
                System.out.println("! HızTekerlekli > HızPaletli > HızSpider !");
                System.out.println(minTekerliHız);

            } else {
                flag = 0;
            }
        }
        this.gezinmeHızı = gezinmeHiz;
        System.out.println("Hareketli parça sayisi (Tekerlek/Palet/Bacak) : ");
        this.hareketliUnsurSayisi = scanner.nextInt();
    }

}
