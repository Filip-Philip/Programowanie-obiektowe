package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap{
    protected final Vector2d MAP_BOTTOM_LEFT_CORNER = new Vector2d(0,0);
    protected Vector2d mapTopRightCorner;
    protected ArrayList<Animal> animals = new ArrayList<>();


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
    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition())) return false;
        else{
            animals.add(animal);
            return true;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPosition().equals(position)) return animal;
        }
        return null;
    }

}
