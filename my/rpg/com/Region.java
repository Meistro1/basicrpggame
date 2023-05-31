package my.rpg.com;
public class Region {
    private String name;
    private GameMap map;

    public Region(String name, GameMap map) {
        this.name = name;
        this.map = map;
    }

    // Getters and setters for name and map

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    // Other methods specific to the region
}
