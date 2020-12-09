public class animate {
    public static void Delivery(String Deli){
        if (Deli.equals("Go")) {
            for (int pox = 0; pox > -500; pox--) {
                InGameState.posiX = pox;
                InGameState.posiY = (pox%2)*5;
                try {
                    Thread.sleep(3);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        else {
            for (int pox = -500; pox < 0; pox++) {
                InGameState.posiX = pox;
                InGameState.posiY = (pox%2)*5;
                try {
                    Thread.sleep(3);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
    public static void upScore(){
        GameControler.setScore(1);
        Delivery("Back");
    }

    public static void alertObject() {
            for (int focus = 0; focus < 5; focus++) {
                if (focus % 2 == 0)
                    InGameState.alertObject = true;
                else
                    InGameState.alertObject = false;
                try {
                    Thread.sleep(200);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }

    }

    public static void notiPhone(){
        InGameState.newOrder = true;
        for (int noti = 0; noti < 100; noti++){
                InGameState.spikePhone = noti%5;
            try {
                Thread.sleep(10);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        InGameState.newOrder = false;
    }


}
