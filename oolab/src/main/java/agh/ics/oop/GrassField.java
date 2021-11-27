package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final int numberOfClumps;
    private Map<Vector2d, Grass> grassHashMap = new HashMap<>();

    public GrassField(int numberOfClumps){
        this.numberOfClumps = numberOfClumps;
        this.mapTopRightCorner = new Vector2d((int) Math.sqrt(numberOfClumps * 10), (int) Math.sqrt(numberOfClumps * 10));
        Random random = new Random();
        int i = 0;
        while (i < numberOfClumps) {
            Vector2d randomPosition =  new Vector2d(random.nextInt(mapTopRightCorner.x),
                    random.nextInt(mapTopRightCorner.y));
            if( !(objectAt(randomPosition) instanceof Grass) ) {
                grassHashMap.put(randomPosition, new Grass(randomPosition));
                i++;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || (isOccupied(position) && !(objectAt(position) instanceof Animal));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        Boolean isOccupiedParent = super.isOccupied(position);
        return isOccupiedParent || grassHashMap.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(animalsHashMap.containsKey(position)) return animalsHashMap.get(position);
        return grassHashMap.get(position);
    }
}
