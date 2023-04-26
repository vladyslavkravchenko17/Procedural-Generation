package main.java.procedural.generation.service;

import main.java.procedural.generation.gui.PixelArrayDisplay;

import java.util.Random;

public class MapGenerator {
    public static final int OCEAN = 0;
    public static final int LAND = 1;
    public static final int SAND = 2;
    public static final int SHALLOW_WATER = 3;

    private int[][] map;
    private final int size;
    private final Random random;

    public MapGenerator(int size, long seed) {
        this.size = size;

        map = new int[size][size];
        random = new Random(seed);

        initMap();
    }

    private void initMap() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                map[x][y] = random.nextInt(2);
            }
        }
    }

    public void generateShallowWater(int iterationsNum, PixelArrayDisplay display) {
        generateShallowWater(display);
        for (int i = 0; i < iterationsNum; i++) {
            int[][] newMap = new int[size][size];

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (map[x][y] == OCEAN) {
                        int shallowWaterNeighbours = countNeighbours(x, y, SHALLOW_WATER);
                        if (shallowWaterNeighbours >= 5 && shallowWaterNeighbours <= 8 && Math.random() <= 0.30) {
                            newMap[x][y] = SHALLOW_WATER;
                        } else {
                            newMap[x][y] = OCEAN;
                        }
                    } else {
                        newMap[x][y] = map[x][y];
                    }
                }
            }
            map = newMap;
            displayMap(display);

        }
    }

    private void generateShallowWater(PixelArrayDisplay display) {
        int[][] newMap = new int[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (map[x][y] == OCEAN) {
                    int sandNeighbours = countNeighbours(x, y, SAND);
                    if (sandNeighbours >= 1 && sandNeighbours <= 6) {
                        newMap[x][y] = SHALLOW_WATER;
                    } else {
                        newMap[x][y] = OCEAN;
                    }
                } else {
                    newMap[x][y] = map[x][y];
                }
            }
        }
        map = newMap;
        displayMap(display);
    }

    public void generateSandNearWater(int iterationsNum, PixelArrayDisplay display) {
        for (int i = 0; i < iterationsNum; i++) {
            int[][] newMap = new int[size][size];

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (map[x][y] == LAND) {
                        int oceanNeighbours = countNeighbours(x, y, OCEAN);
                        if (oceanNeighbours >= 1 && oceanNeighbours <= 6) {
                            newMap[x][y] = SAND;
                        } else {
                            newMap[x][y] = LAND;
                        }
                    } else if (map[x][y] == OCEAN) {
                        int sandNeighbours = countNeighbours(x, y, SAND);
                        if (sandNeighbours >= 5 && sandNeighbours <= 8 && Math.random() <= 0.02) {
                            newMap[x][y] = SAND;
                        } else {
                            newMap[x][y] = OCEAN;
                        }
                    } else {
                        newMap[x][y] = map[x][y];
                    }
                }
            }
            map = newMap;
            displayMap(display);

        }
    }


    public void generateLandAndWater(int iterationsNum, PixelArrayDisplay display) {
        for (int i = 0; i < iterationsNum; i++) {
            int[][] newMap = new int[size][size];

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (map[x][y] == OCEAN) {
                        int landNeighbours = countNeighbours(x, y, LAND);

                        if (landNeighbours == 3 || (landNeighbours >= 6 && landNeighbours <= 8)) {
                            newMap[x][y] = LAND;
                        } else {
                            newMap[x][y] = OCEAN;
                        }
                    } else {
                        int oceanNeighbours = countNeighbours(x, y, OCEAN);

                        if (oceanNeighbours == 3 || (oceanNeighbours >= 6 && oceanNeighbours <= 8)) {
                            newMap[x][y] = OCEAN;
                        } else {
                            newMap[x][y] = LAND;
                        }
                    }
                }
            }
            map = newMap;
            displayMap(display);
        }
    }

    private int countNeighbours(int x, int y, int surfaceType) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighbourX = x + i;
                int neighbourY = y + j;
                if (neighbourX >= 0 && neighbourX < size && neighbourY >= 0 && neighbourY < size) {
                    if (i != 0 || j != 0) {
                        if (map[neighbourX][neighbourY] == surfaceType) {
                            count++;
                        }
                    }
                } else {

                    if (surfaceType == LAND) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void displayMap(PixelArrayDisplay display) {
        display.setPixelArray(map);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {
        }
    }
}
