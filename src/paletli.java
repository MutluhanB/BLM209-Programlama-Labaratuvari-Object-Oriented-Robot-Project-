import java.util.ArrayList;
import java.util.Scanner;

public class paletli extends gezginRobot {


    public double engelSüresiBul() {
        return (double)this.motorSayisi * 3;
    }

    public paletli(ArrayList<Integer> hızlist){
        this.create(hızlist);
        this.tip="paletli";
        this.engelGec = engelSüresiBul();

        this.engelGecebilirmi = true;
    }





}
