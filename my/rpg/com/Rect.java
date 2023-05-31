package my.rpg.com;

public class Rect {
    public int x1, y1, x2, y2;

    public Rect(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + w;
        this.x2 = y + h;
    }
}
