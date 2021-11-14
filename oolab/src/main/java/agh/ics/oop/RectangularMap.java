package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public static final Vector2d MAP_BOTTOM_LEFT_CORNER = new Vector2d(0,0);
    private final int width;
    private final int height;
    private List<Animal> animals;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<>();
    }

    @Override
    public String toString() {
        MapVisualiser mapVisualisation = new MapVisualiser(this);
        return mapVisualisation.draw(MAP_BOTTOM_LEFT_CORNER, new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.precedes(new Vector2d(width, height))
                && position.follows(MAP_BOTTOM_LEFT_CORNER);
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
