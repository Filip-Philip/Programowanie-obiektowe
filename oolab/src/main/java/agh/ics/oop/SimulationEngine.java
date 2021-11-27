package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] directions;
    private IWorldMap map;
    private List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for(Vector2d position : positions){
            Animal newAnimal = new Animal(position, map);
            newAnimal.addObserver(map);
            animals.add(newAnimal);
        }
    }

    @Override
    public void run() {
        int moveNumber = 0;
        Animal currentAnimal;
        for(MoveDirection direction : directions){
            currentAnimal = animals.get(moveNumber % animals.size());
            currentAnimal.move(direction, map);
            moveNumber++;
        }
    }
}
