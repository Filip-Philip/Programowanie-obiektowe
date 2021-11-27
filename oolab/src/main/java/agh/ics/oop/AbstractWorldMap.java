package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final Vector2d MAP_BOTTOM_LEFT_CORNER = new Vector2d(0,0);
    protected Vector2d mapTopRightCorner;
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected Map<Vector2d, Animal> animalsHashMap = new HashMap<>();

    @Override
    public String toString() {
        Vector2d bottomLeftMap = MAP_BOTTOM_LEFT_CORNER;
        Vector2d topRightMap = mapTopRightCorner;
        for(Animal animal : animals){
            topRightMap = animal.getPosition().upperRight(topRightMap);
            bottomLeftMap = animal.getPosition().lowerLeft(bottomLeftMap);
        }
        MapVisualiser mapVisualisation = new MapVisualiser(this);
        return mapVisualisation.draw(bottomLeftMap, topRightMap);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animalsHashMap.get(oldPosition);
        animalsHashMap.remove(oldPosition);
        animalsHashMap.put(newPosition, animal);
    }



    @Override
    public boolean place(Animal animal) {
        if(animalsHashMap.containsKey(animal.getPosition())) return false;
        else{
            animals.add(animal);
            animalsHashMap.put(animal.getPosition(), animal);
            return true;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalsHashMap.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalsHashMap.get(position);
    }

}
