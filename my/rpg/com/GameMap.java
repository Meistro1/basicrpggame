package my.rpg.com;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.FileReader;

public class GameMap {
    private int width;
    private int height;
    private Terrain[][] tiles;
    private Item[][] items;


    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Terrain[width][height];
        this.items = new Item[width][height];
        initializeTiles();
    }

    private void initializeTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Terrain(Terrain.TerrainType.GRASS, true, "grass_texture.png");
                items[x][y] = null; // No item initially
            }
        }
    }

    // Getters and setters for width and height

    public Terrain getTile(int x, int y) {
        return tiles[x][y];
    }

    public void setTile(int x, int y, Terrain terrain) {
        tiles[x][y] = terrain;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Item getItem(int x, int y) {
        return items[x][y];
    }

    public void setItem(int x, int y, Item item) {
        items[x][y] = item;
    }

    public void loadMapFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);
                    switch (c) {
                        case 'W':
                            tiles[x][y] = new Terrain(Terrain.TerrainType.WALL, true, "wall_texture.png");
                            break;
                        case 'G':
                            tiles[x][y] = new Terrain(Terrain.TerrainType.GRASS, true, "grass_texture.png");
                            break;
                        // add cases for other types of terrain
                        default:
                            throw new IllegalArgumentException("Invalid character in map file: " + c);
                    }
                }
                y++;
            }
        } catch (IOException e) {
            System.err.println("Error loading map: " + e);
        }
    }
    

    // Other methods specific to the map, such as rendering, loading from file, saving, etc.
}
