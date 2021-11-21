package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final int numberOfClumps;
    private ArrayList<Grass> grassClumps = new ArrayList<>();

    public GrassField(int numberOfClumps){
        this.numberOfClumps = numberOfClumps;
        this.mapTopRightCorner = new Vector2d((int) Math.sqrt(numberOfClumps * 10), (int) Math.sqrt(numberOfClumps * 10));
        Random random = new Random();
        int i = 0;
        while (i < numberOfClumps) {
            Vector2d randomPosition =  new Vector2d(random.nextInt(mapTopRightCorner.x),
                    random.nextInt(mapTopRightCorner.y));
            if( !(objectAt(randomPosition) instanceof Grass) ) {
                grassClumps.add(new Grass(randomPosition));
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
        for(Grass grassClump : grassClumps){
            if(grassClump.getPosition().equals(position)) return true;
        }
        return isOccupiedParent;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPosition().equals(position)) return animal;
        }
        for(Grass grassClump : grassClumps){
            if(grassClump.getPosition().equals(position)) return grassClump;
        }
        return null;
    }
}
