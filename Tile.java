package my.rpg.com;

public class Tile {
    private Terrain terrain;
    private boolean blocked;
    private boolean blockSight;

    public Tile(Terrain terrain, boolean blocked) {
        this.terrain = terrain;
        this.blocked = blocked;
        this.blockSight = terrain.getType() == Terrain.TerrainType.WALL;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlockSight() {
        return blockSight;
    }

    public void setBlockSight(boolean blockSight) {
        this.blockSight = blockSight;
    }
}
