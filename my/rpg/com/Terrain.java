package my.rpg.com;

import java.awt.Color;

public class Terrain {
    public enum TerrainType {
        WALL(Color.BLACK),
        FLOOR(Color.LIGHT_GRAY),
        GRASS(Color.GREEN); 
        private final Color color;

        TerrainType(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
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
