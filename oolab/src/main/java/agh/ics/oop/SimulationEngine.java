package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] directions;
    private IWorldMap map;
    private List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for(Vector2d position : positions){
            animals.add(new Animal(position, map));
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
