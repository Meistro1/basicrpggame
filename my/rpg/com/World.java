package my.rpg.com;

import java.util.*;

public class World {
    private List<Region> regions;
    private Region currentRegion;

    public World() {
        regions = new ArrayList<>();
    }

    public void addRegion(Region region) {
        regions.add(region);
    }

    public void setActiveRegion(Region region) {
        currentRegion = region;
    }

    public Region getActiveRegion() {
        return currentRegion;
    }

    // Other methods for managing the world, such as loading maps, transitioning between regions, etc.
}