package saved;


class Grid {
    Item[][] floor;
    // constructor
    public Grid(int width, int height) {
        floor = new Item[width][height];
    }
    // method to place item
    public void placeItem(Item item, int x, int y) {
        floor[x][y] = item;
    }
    // method to get item
    public Item getItem(int x, int y) {
        Item item = floor[x][y];
        floor[x][y] = null; // clear the spot
        return item;
    }
}