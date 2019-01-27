import javax.swing.*;
        import java.awt.*;
        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Scanner;
        import java.util.Vector;

public class robots extends JFrame  {
    /*
    * Jframe sınıfım
    * */

    public robots(){

        /*
        * Jframe'in constructor'ı.
        * */
        setTitle("KocaeliDynamics 2018 Robot Showcase");
        setSize(700,700);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    class MyPanel extends JPanel {
        /*
        * Framein içerisindeki panel.
        * */
        public ArrayList<Object> readRobots(int robotCount){
            /*
            * inputs : kaç adet robot okunacağı.
            * output : oluşturulan robot nesnelerinin tutulduğu ArrayList.
            *   readRobotCount() fonksiyonu ile kullanıcıdan okuduğum istenilen robot sayısı kadar
            * robotu oluşturuluyorum.Oluştururken istenilen tipi sorarak switch yapısı içerisinde istenilen
            * tipte bir robot oluşturup bunu nesneleri tutan fonksiyonun döndereceği arrayListe ekliyorum.
                HızList listelerinde ise oluşturulan tekerlekli ve paletli tipteki robotların gezinme hızlarını tutarak
              nesneler arasındaki gezinme hızı hiyerarşisinin kontrolünü yaparken kullanıyorum.
            * */

            ArrayList<Integer> tekerlekliHızList = new ArrayList<>();
            ArrayList<Integer> paletliHızList = new ArrayList<>();

            ArrayList<Object> robotList = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < robotCount ; i++) {
                System.out.println("Oluşturmak istediğiniz robot tipini seçiniz(tekelekli / paletli / spider / seri / paraelel / hibrid : ");
                String tip = scanner.nextLine().toLowerCase();
                switch (tip) {
                    case "tekerlekli":
                        tekerlekli tekerlekli = new tekerlekli();
                        tekerlekliHızList.add(tekerlekli.gezinmeHızı);
                        robotList.add(tekerlekli);
                        break;
                    case "paletli":
                        if(tekerlekliHızList.isEmpty()){
                            tekerlekliHızList.add(99999);
                        }
                        paletli paletli = new paletli(tekerlekliHızList);
                        paletliHızList.add(paletli.gezinmeHızı);
                        robotList.add(paletli);
                        break;
                    case "spider":
                        if(tekerlekliHızList.isEmpty() && paletliHızList.isEmpty()){
                            paletliHızList.add(99999);
                        }
                        else if(paletliHızList.isEmpty()){

                            paletliHızList.add(Collections.min(tekerlekliHızList));
                        }

                        spider spider = new spider(paletliHızList);
                        robotList.add(spider);
                        break;
                    case "paralel":
                        paralel paralel = new paralel();
                        robotList.add(paralel);
                        break;
                    case "seri":
                        seri seri = new seri();
                        robotList.add(seri);
                        break;
                    case "hibrid":
                        hibrid hibrid = new hibrid(tekerlekliHızList,paletliHızList);
                        if(hibrid.tip1.equals("tekerlekli")){
                            tekerlekliHızList.add(hibrid.gezinmeHızı);
                        }
                        else if(hibrid.tip1.equals("paletli")){
                            paletliHızList.add(hibrid.gezinmeHızı);
                        }

                        robotList.add(hibrid);
                        break;
                    default:
                        i--;
                        System.out.println("Hatalı giriş !");
                        break;
                }

            }
            return robotList;
        }

        public Object pickRobot (ArrayList<Object> robotList){
            /*
            * inputs: readRobots() fonksiyonu ile oluşturduğum ve oluşturulmuş olan robot nesnelerinin tutulduğu
            * Object tipinden arrayList.
            * output: Obje listesinin istenilen indexindeki obje.
            * !not:return edilen obje Obje tipindedir cast edilmesi gerekir !
            * */
            Scanner scanner = new Scanner(System.in);
            System.out.println("Toplam " + robotList.size() + " Robot oluşturdunuz. Kaçıncıyı Seçmek İstiyorsunuz ? ");
            int pickindex = scanner.nextInt();
            return robotList.get(pickindex-1);
        }

        public int readRobotCount(){
            /*
            * output: oluşturulmak istenilen nesne sayısı.
            * basit okuma işlemi.
            * */
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hoş geldiniz...");
            System.out.println("Kaç adet robot oluşturacaksınız ? : ");

            return scanner.nextInt();
        }

        public void writeCoordinates(Graphics g){
            /*
             Panele kordinatların yazılması.
             */

            for(int i=0; i<21; i++){

                String stri=Integer.toString(i);
                g.drawString(stri,620,20+30*i);
            }
            for(int i=1; i<21; i++){
                String stri=Integer.toString(i);
                g.drawString(stri,-20+30*i,650);
            }
        }

        public void drawGrid(Graphics g){
            /*
            * Panele ızgaranın çizilmesi.
            * */
            for(int i=0; i<21; i++){
                g.drawLine(0+i*30,630,0+i*30,30);


            }
            for(int i=0; i<22; i++){
                g.drawLine(600,0+i*30,0,0+i*30);
            }


        }

        public void drawRange(Graphics g,hibrid r){
            g.setColor(Color.CYAN);
            int kolInt = (int)r.kolUzunluk;
            g.drawOval(((r.xpos-1)*30)- (int) r.kolUzunluk*30+15,((r.ypos)*30)- (int) r.kolUzunluk*30+15,(int)r.kolUzunluk*60,(int)r.kolUzunluk*60);
            System.out.println(r.xpos);
            System.out.println(r.ypos);
        }

        public void drawRange(Graphics g,manipulatorRobot r){
            g.setColor(Color.CYAN);
            int kolInt = (int)r.kolUzunluk;
            g.drawOval(((r.xpos-1)*30)- (int) r.kolUzunluk*30+15,((r.ypos)*30)- (int) r.kolUzunluk*30+15,(int)r.kolUzunluk*60,(int)r.kolUzunluk*60);
            System.out.println(r.xpos);
            System.out.println(r.ypos);
        }

        public Vector readDirection(){
            Scanner scanner = new Scanner(System.in);
            Vector directionVector = new Vector();
            while (true){
                System.out.println("Yön bilgisini giriniz( -1 çıkış ).");
                String directionString = scanner.nextLine().toLowerCase();
                if(directionString.equals("-1")){
                    break;
                }
                String[] givenDirection = directionString.split(" ");
                directionVector.add(givenDirection[0]);
                directionVector.add(givenDirection[1]);

            }

            return directionVector;

        }

        public ArrayList<Integer> createObstacle(Graphics g) {
            /*
            * Outputs: Oluşan engellerin listesi.
            * Engeller oluşturulur. İlgili kordinatlara çizilir.
            * */

            Scanner scanner = new Scanner(System.in);

            ArrayList<Integer> obstacleList = new ArrayList<>();
            int obstacleCount = readObstacleCount();

            for(int i=0;i<obstacleCount;i++){
                int[] coordinates = {};
                System.out.println(i+1 + ". Engelin x kordinatı :");
                int xpos = scanner.nextInt();
                System.out.println(i+1 + ". Engelin y kordinatı :");
                int ypos = scanner.nextInt();
                if(xpos < 1 || xpos > 20 || ypos < 1 || ypos > 20){
                    System.out.println("Girdiğiniz kordinatlar geçerli değil !");
                    i--;
                }
                else{
                    g.setColor(Color.RED);
                    g.fillRect((xpos-1)*30,(ypos)*30,30,30);
                    obstacleList.add(xpos);
                    obstacleList.add(ypos);
                }
            }
            return obstacleList;
        }

        public double moveRobot(Graphics graphics, Vector directionVector , gezginRobot g,ArrayList<Integer> obstacleList){
            ArrayList<Integer> destinationCoordinates = new ArrayList<>();
            int xLastPos = g.xpos;
            int yLastPos = g.ypos;
            int deltaX = 0;
            int deltaY = 0;
            double time=0;
            double obstaclePassTime = 0;
            graphics.setColor(Color.WHITE);
            graphics.fillRect(xLastPos*30+2-30,yLastPos*30+2,26,26);
            int obstacleEncounter = 0;
            int c = 1;
            for (int i = 1; i < directionVector.size() ; i=i+2) {

                String command = (String)directionVector.get(i);
                switch (command){
                    case "ileri":

                        String yincrementString = directionVector.get(i-1).toString();
                        int yincrement = Integer.parseInt(yincrementString);
                        for (int j = yLastPos; j < yLastPos+yincrement  ; j++) {
                            for (int k = 0; k < obstacleList.size() ; k+=2) {
                                if(obstacleList.get(k) == xLastPos && obstacleList.get(k+1) == j){
                                    if(!g.engelGecebilirmi){
                                        System.out.println("Spider robot verilen komutu uygularken engelden geçemedi !");
                                        yincrement = j-yLastPos-1;
                                        j=xLastPos+yincrement;
                                        directionVector.clear();
                                        break;
                                    }
                                    System.out.println("Engel bulundu " + obstacleList.get(k) + j);
                                    obstacleEncounter++;

                                }

                            }
                        }
                        if(yLastPos + yincrement > 20){
                            System.out.println("Izgaradan çıkamazsınız !!");
                            yincrement = 20 - yLastPos;
                        }
                        yLastPos = yLastPos + yincrement;
                        deltaY += yincrement;
                        break;
                    case "sağ":
                        String xincrementString = directionVector.get(i-1).toString();
                        int xincrement = Integer.parseInt(xincrementString);
                        for (int j = xLastPos; j < xLastPos+xincrement  ; j++) {
                            for (int k = 0; k < obstacleList.size() ; k+=2) {
                                if(obstacleList.get(k) == j && obstacleList.get(k+1) == yLastPos){
                                    if(!g.engelGecebilirmi){
                                        System.out.println("Spider robot verilen komutu uygularken engelden geçemedi !");
                                        xincrement = j-xLastPos-1;
                                        j=xLastPos+xincrement;
                                        directionVector.clear();
                                        break;
                                    }
                                    System.out.println("Engel bulundu " + obstacleList.get(k) + j);
                                    obstacleEncounter++;
                                }
                            }
                        }
                        if(xLastPos + xincrement > 20) {
                            System.out.println("Izgaradan çıkamazsınız !!");
                            xincrement = 20 - xLastPos;
                        }
                        xLastPos = xLastPos + xincrement;
                        deltaX += xincrement;

                        break;
                    case "sol":

                        String xdecrementString = directionVector.get(i-1).toString();
                        int xdecrement = Integer.parseInt(xdecrementString);
                        for (int j = xLastPos; j > xLastPos-xdecrement  ; j--) {
                            for (int k = 0; k < obstacleList.size() ; k+=2) {
                                if(obstacleList.get(k) == j && obstacleList.get(k+1) == yLastPos){
                                    if(!g.engelGecebilirmi){
                                        System.out.println("Spider robot verilen komutu uygularken engelden geçemedi !");
                                        xdecrement = xLastPos - j-1;
                                        j=xLastPos-xdecrement;
                                        directionVector.clear();
                                        break;
                                    }
                                    System.out.println("Engel bulundu " + obstacleList.get(k) + j);
                                    obstacleEncounter++;
                                }
                            }
                        }
                        if(xLastPos - xdecrement < 0) {
                            System.out.println("Izgaradan çıkamazsınız !!");
                            xdecrement = xLastPos-1;
                        }
                        xLastPos = xLastPos - xdecrement;
                        deltaX += xdecrement;
                        break;
                    case "geri":

                        String ydecrementString = directionVector.get(i-1).toString();
                        int ydecrement = Integer.parseInt(ydecrementString);
                        for (int j = yLastPos; j > yLastPos-ydecrement  ; j--) {
                            for (int k = 0; k < obstacleList.size() ; k+=2) {
                                if(obstacleList.get(k) == xLastPos && obstacleList.get(k+1) == j){
                                    if(!g.engelGecebilirmi){
                                        System.out.println("Spider robot verilen komutu uygularken engelden geçemedi !");
                                        ydecrement = yLastPos - j-1;
                                        j=yLastPos-ydecrement;
                                        directionVector.clear();
                                        break;
                                    }
                                    System.out.println("Engel bulundu " + obstacleList.get(k) + j);
                                    obstacleEncounter++;
                                }
                            }
                        }
                        if(yLastPos - ydecrement < 0) {
                            System.out.println("Izgaradan çıkamazsınız !!");
                            ydecrement = yLastPos-1;
                        }
                        yLastPos = yLastPos - ydecrement;
                        deltaY += ydecrement;
                        break;
                    default:
                        System.out.println("Bilinmeyen yön / Haritadan Çıktınız !");
                        break;
                }
                graphics.setColor(Color.lightGray);
                graphics.fillOval(7+(xLastPos-1)*30,8+(yLastPos)*30,15,15);
                graphics.setColor(Color.BLACK);


                graphics.drawString(Integer.toString(c),10+(xLastPos-1)*30, 20+(yLastPos)*30);
                c = c+1;
            }
            time = (double)(10*deltaY)/g.gezinmeHızı + (10*deltaX)/g.gezinmeHızı;

            time = time - obstacleEncounter*(10/g.gezinmeHızı - g.engelGec);
            destinationCoordinates.add(xLastPos);
            destinationCoordinates.add(yLastPos);
            graphics.setColor(Color.GREEN);

            System.out.println("Engel süresi : " + g.engelGec);

            graphics.fillRect((xLastPos-1)*30,(yLastPos)*30,30,30);
            g.xpos = xLastPos   ;
            g.ypos = yLastPos;
            System.out.println("Gezinme hızı =" + g.gezinmeHızı);
            System.out.println("Time = "+ time);
            return time;
        }

        public void carryLoad(Graphics g, Vector directionVector, manipulatorRobot m){
            int x = m.xpos;
            int y = m.ypos;
            int deltaX = 0;
            int deltaY = 0;
            for (int i = 1; i < directionVector.size() ; i = i+2) {
                String command = (String)directionVector.get(i);
                int delta = Integer.parseInt((String)directionVector.get(i-1));
                switch (command){
                    case "ileri":
                        deltaY += delta;
                        break;
                    case "geri":
                        deltaY -= delta;
                        break;
                    case "sağ":
                        deltaX += delta;
                        break;
                    case "sol":
                        deltaX -= delta;
                        break;
                    default:
                        System.out.println("Hatali yön bilgisi");
                        System.out.println(command);
                        break;
                }
            }

            if(m.canCarry(x+deltaX,y+deltaY)){
                g.setColor(Color.GREEN);
                g.drawLine((x-1)*30+15,y*30+15,(x-1+deltaX)*30+15,(y+deltaY)*30+15);
                g.setColor(Color.BLACK);
                g.fillRect(5+(x-1+deltaX)*30, 5+(y+deltaY)*30, 20,20);
                double time = 0;
                time = (deltaX*10) / m.tasımaHız + (deltaY*10) / m.tasımaHız;
                System.out.println("Robot yükü sırasıyla girilen tüm yönlere taşıdığında SÜRE  : " + time);
                double time2 = Math.sqrt( deltaX*deltaX + deltaY*deltaY )*10 / m.tasımaHız;
                System.out.println("Robot yükü direkt olarak son konuma taşıdığında SÜRE : " + time2);
            }
            else{
                System.out.println("Robotun kolu " + m.kolUzunluk +" birim kare");
                System.out.println(x+deltaX);
                System.out.println(y+deltaY);
                System.out.println("Robotun erişemeyeceği bir noktaya yük taşımaya çalıştınız");
                g.setColor(Color.RED);
                g.drawLine((x-1)*30+15,y*30+15,(x-1+deltaX)*30+15,(y+deltaY)*30+15);
                g.setColor(Color.BLACK);
                g.fillRect(5+(x-1+deltaX)*30, 5+(y+deltaY)*30, 20,20);
                System.out.println(x+deltaX);
            }

        }

        public void carryLoad(Graphics g, Vector directionVector, hibrid m, double moveTime){
            int x = m.xpos;
            int y = m.ypos;
            int deltaX = 0;
            int deltaY = 0;
            for (int i = 1; i < directionVector.size() ; i = i+2) {
                String command = (String)directionVector.get(i);
                int delta = Integer.parseInt((String)directionVector.get(i-1));
                switch (command){
                    case "ileri":
                        deltaY += delta;
                        break;
                    case "geri":
                        deltaY -= delta;
                        break;
                    case "sağ":
                        deltaX += delta;
                        break;
                    case "sol":
                        deltaX -= delta;
                        break;
                    default:
                        System.out.println("Hatali yön bilgisi");
                        System.out.println(command);
                        break;
                }
            }

            if(m.canCarry(x+deltaX,y+deltaY)){
                g.setColor(Color.GREEN);
                g.drawLine((x-1)*30+15,y*30+15,(x-1+deltaX)*30+15,(y+deltaY)*30+15);
                g.setColor(Color.BLACK);
                g.fillRect(5+(x-1+deltaX)*30, 5+(y+deltaY)*30, 20,20);
                System.out.println(x+deltaX);
                double time = 0;
                time = (deltaX*10) / m.tasımaHız + (deltaY*10) / m.tasımaHız;
                double totalTime = time+moveTime+m.sabitlemeSüresi;

                System.out.println("!! Hesaplamalara hareket ve sabitlenme zamanı ( " + moveTime + " " + m.sabitlemeSüresi +" dahil edilmiştir !!");
                System.out.println("Robot yükü sırasıyla girilen tüm yönlere taşıdığında SÜRE :  : " +totalTime );
                double time2 = Math.sqrt( deltaX*deltaX + deltaY*deltaY )*10 / m.tasımaHız;
                double totalTime2 = time2+moveTime+m.sabitlemeSüresi;
                System.out.println("Robot yükü direkt olarak son konuma taşıdığında SÜRE : " + totalTime2);
            }
            else{
                System.out.println("Robotun kolu " + m.kolUzunluk +" birim kare");
                System.out.println(x+deltaX);
                System.out.println(y+deltaY);
                System.out.println("Robotun erişemeyeceği bir noktaya yük taşımaya çalıştınız");
                g.setColor(Color.RED);
                g.drawLine((x-1)*30+15,y*30+15,(x-1+deltaX)*30+15,(y+deltaY)*30+15);
                g.setColor(Color.BLACK);
                g.fillRect(5+(x-1+deltaX)*30, 5+(y+deltaY)*30, 20,20);
                System.out.println(x+deltaX);
            }

        }

        public void placeRobot(Graphics g,ArrayList<Integer> arr, robot r){
            /*
            Robotun yerleştirilmesi.
             */
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("Robotun x kordinatı : ");
                r.xpos = scanner.nextInt();

                System.out.println("Robotun y kordinatı : ");
                r.ypos = scanner.nextInt();
                int flag=0;
                for (int i = 0; i < arr.size() ; i=i+2) {

                    if((r.xpos == arr.get(i) && r.ypos == arr.get(i+1)) || r.xpos > 20 || r.xpos < 1 || r.ypos > 20 || r.ypos<1){
                        flag=1;
                    }
                }
                if(flag == 1){
                    System.out.println("Seçilen kordinatlara robot yerleştiremezsiniz.");
                }
                else{
                    g.setColor(Color.GREEN);
                    g.fillRect((r.xpos-1)*30,(r.ypos)*30,30,30);
                    break;
                }
            }
        }


    }

    public void paint(Graphics g){
        /*
        * İşlemlerin yapıldığı paint methodu.
        * */
        ArrayList<Object> robotList = new ArrayList<>();
        MyPanel panel = new MyPanel();
        panel.setOpaque(true);

        panel.drawGrid(g);
        panel.writeCoordinates(g);
        int robotcount = panel.readRobotCount();
        robotList = panel.readRobots(robotcount);
        Object pickedObjRaw = panel.pickRobot(robotList);
        int problem=-1;

        gezginRobot pickedGezgin = null;
        manipulatorRobot pickedManipulator = null;
        hibrid pickedHibrid = null;
        if(pickedObjRaw instanceof gezginRobot && !(pickedObjRaw instanceof hibrid) ){
            System.out.println("Gezgin bir robot seçtiniz. 1.Problem çözülecektir.");
            pickedGezgin = (gezginRobot) pickedObjRaw;
            problem = 1;
        }
        if(pickedObjRaw instanceof manipulatorRobot){
            System.out.println("Manipülatör bir robot seçtiniz. 2.Problem çözülecektir.");
            pickedManipulator = (manipulatorRobot) pickedObjRaw;
            problem = 2;
        }
        if(pickedObjRaw instanceof hibrid){
            System.out.println("Hibrit bir robot seçtiniz 3. Problem çözülecektir");

            pickedHibrid = (hibrid) pickedObjRaw;
            problem = 3;
        }
        ArrayList obstacleList = new ArrayList();
        ArrayList lastCoordinates = new ArrayList();
        Vector directionVector = new Vector();
        switch (problem){
            case 1:

                obstacleList=panel.createObstacle(g);
                panel.placeRobot(g,obstacleList,pickedGezgin);
                directionVector = panel.readDirection();
                double time = panel.moveRobot(g,directionVector,pickedGezgin,obstacleList);
                break;
            case 2:
                panel.placeRobot(g,obstacleList,pickedManipulator);
                panel.drawRange(g,pickedManipulator);
                directionVector = panel.readDirection();
                System.out.println(directionVector);
                panel.carryLoad(g,directionVector,pickedManipulator);
            case 3:
                obstacleList = panel.createObstacle(g);
                panel.placeRobot(g,obstacleList,pickedHibrid);
                directionVector = panel.readDirection();
                double moveTime = panel.moveRobot(g,directionVector,pickedHibrid,obstacleList);
                directionVector.clear();
                panel.drawRange(g,pickedHibrid);
                directionVector = panel.readDirection();
                panel.carryLoad(g,directionVector,pickedHibrid,moveTime);

                break;
            default:
                System.out.println("Hata!");
                break;
        }



    }

    public int readObstacleCount(){
        //Engel sayısının okunması.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kaç Engel Gireceksiniz ?: ");
        return scanner.nextInt();
    }



    public static void main(String[] args){

        robots t = new robots();

    }
}