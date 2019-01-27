public interface manipulatorInterface {
    int yukKapasiteParalel=-1;
    int yukKapasiteSeri=-1;
    double kolUzunluk=-1;
    int tasımaHız=-1;

    void create();
    void seriCreate();
    boolean canCarry(int yukPosx, int yukPosy);
}
