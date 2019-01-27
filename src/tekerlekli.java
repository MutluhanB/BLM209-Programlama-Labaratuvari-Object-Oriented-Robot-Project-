public class tekerlekli extends gezginRobot {


    public double engelSüresiBul() {
        return (double)this.motorSayisi/2;
    }

    public tekerlekli(){
        this.create();
        this.tip="tekerlekli";
        this.engelGecebilirmi = true;
        this.engelGec = this.engelSüresiBul();

    }


}
