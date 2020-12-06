import javax.swing.*;
import java.awt.*;

public class MenuState extends JPanel {

    private JPanel p;

    public MenuState(){
        System.out.println(1);
        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.gray);
        p.setVisible(true);
    }

}
