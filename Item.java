package saved;

public abstract class Item {
    private String name;
    private int value;
    private char type; // type as char
    int x, y;

    public Item(String name, int value, char type, int x, int y) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public char getType() {
        return this.type;
    }

    public String getName(){

        return this.name;

    }

    // getters and setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // other methods
}
