public class popUp {

    public static int showSelect = 0;

    public popUp(int select){
        if (select == 1)
            Menu();
        if(select == 2){
            calculation();
        }
        if (select == 3){
            delivery();
        }
    }

    public void Menu(){
        this.showSelect = 1;
    }
    public void calculation(){
        this.showSelect = 2;
    }
    public void delivery(){
        this.showSelect = 3;
    }
}
