import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Background(String s){
        try{
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y){
        this.x = (x * moveScale) % (GamePanel.WIDTH);
        this.y = (y * moveScale) % (GamePanel.HEIGHT);
    }

    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void update(){
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g){
        g.drawImage(image, 0, 0, null);
    }
}