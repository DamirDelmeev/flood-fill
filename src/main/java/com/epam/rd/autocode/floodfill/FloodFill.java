package com.epam.rd.autocode.floodfill;

public interface FloodFill {
    char LAND = '█';
    char WATER = '░';

    static FloodFill getInstance() {
        return new FloodFillImpl();
    }

    void flood(final String map, final FloodLogger logger);
}
