package main.java.procedural.generation.service;

import main.java.procedural.generation.gui.PixelArrayDisplay;

public class GenerationService {
    public static void generateMap(long seed, int size, int pixelSize, PixelArrayDisplay display,
                                   int landOcean, int sand, int shallowWater) {
        display.setPixelSize(pixelSize);
        MapGenerator mapGenerator = new MapGenerator(size, seed);
        mapGenerator.generateLandAndWater(landOcean, display);
        mapGenerator.generateSandNearWater(sand, display);
        mapGenerator.generateShallowWater(shallowWater, display);
    }
}
