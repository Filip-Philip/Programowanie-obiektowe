package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private Vector2d mapTopRightCorner;
    private Map<Vector2d, Grass> grassHashMap = new HashMap<>();
    private MapBoundary boundaryObserver = new MapBoundary();

    public GrassField(int numberOfClumps){
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
    public Vector2d getMapTopRightCorner(){
        Vector2d topRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Map.Entry mapElement : grassHashMap.entrySet()) {
            Vector2d position = (Vector2d) mapElement.getKey();
            topRightCorner = position.upperRight(topRightCorner);
        }
        for (Map.Entry mapElement : animalsHashMap.entrySet()) {
            Vector2d position = (Vector2d) mapElement.getKey();
            topRightCorner = position.upperRight(topRightCorner);
        }
        return topRightCorner;
    }

    @Override
    public Vector2d getMapBottomLeftCorner(){
        Vector2d bottomLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Map.Entry mapElement : grassHashMap.entrySet()) {
            Vector2d position = (Vector2d) mapElement.getKey();
            bottomLeftCorner = position.lowerLeft(bottomLeftCorner);
        }
        for (Map.Entry mapElement : animalsHashMap.entrySet()) {
            Vector2d position = (Vector2d) mapElement.getKey();
            bottomLeftCorner = position.lowerLeft(bottomLeftCorner);
        }
        return bottomLeftCorner;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        super.positionChanged(oldPosition, newPosition);
        boundaryObserver.positionChanged(oldPosition, newPosition);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || (isOccupied(position) && !(objectAt(position) instanceof Animal));
    }

    @Override
    public boolean place(Animal animal){
        super.place(animal);
        animal.addObserver(boundaryObserver);
        boundaryObserver.addToSet(animal);
        return true;
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
