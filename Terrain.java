package my.rpg.com;

public class Terrain {
    // These are just examples, you can add as many types as you like.
    public enum TerrainType {
        WALL, FLOOR, DOOR
    }

    private TerrainType type;
    private boolean passable;
    private String texture;

    public Terrain(TerrainType type, boolean passable, String texture) {
        this.type = type;
        this.passable = passable;
        this.texture = texture;
    }

    public TerrainType getType() {
        return type;
    }

    public void setType(TerrainType type) {
        this.type = type;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }
}