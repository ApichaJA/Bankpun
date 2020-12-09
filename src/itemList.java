import java.util.HashMap;

public class itemList {

    public String itemList(String item){
        HashMap<String, String> items = new HashMap<String, String>();
        items.put ( "baget", "30" );
        items.put ( "cakeblue", "50" );
        items.put ( "cakefrozen", "60" );
        items.put ( "cakestr", "50" );
        items.put ( "cakevanilla", "40" );
        items.put ( "COFFEE", "30" );
        items.put ( "cupcake", "60" );
        items.put ( "cupcakestr", "65" );
        items.put ( "donut", "50" );
        items.put ( "magarong", "80");
        items.put ( "milktea", "40" );
        items.put ( "pafet", "55" );
        items.put ( "Pangneoy", "35" );
        items.put ( "pie", "70" );
        items.put ( "whitebread", "40" );

        return items.get(item);
    }

    public static String pickItem(int num){
        String[] TotalItem = new String[]{
                "baget",
                "cakeblue",
                "cakefrozen",
                "cakestr",
                "cakevanilla",
                "COFFEE",
                "cupcake",
                "cupcakestr",
                "donut",
                "magarong",
                "milktea",
                "pafet",
                "Pangneoy",
                "pie",
                "whitebread",
        };
        return TotalItem[num];
    }
}
