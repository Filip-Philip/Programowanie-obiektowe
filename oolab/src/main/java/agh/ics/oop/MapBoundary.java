package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver{
    private Vector2d topRightBoundary = new Vector2d(-1,-1);
    private Vector2d bottomLeftBoundary = new Vector2d(0,0);
    private Set<Vector2d> animalBoundaryPositions = new HashSet<>();
    private Set<Vector2d> animalPositions = new HashSet<>();

    public void addToSet(Animal animal){
        animalPositions.add(animal.getPosition());
        this.updateBoundarySet();
    }

    public void updateBoundarySet(){
        for(Vector2d position : animalPositions){
            topRightBoundary = position.upperRight(topRightBoundary);
            bottomLeftBoundary = position.lowerLeft(bottomLeftBoundary);
        }
        for(Vector2d position : animalPositions){
            if(this.isOnEdge(position)) animalBoundaryPositions.add(position);
        }
    }

    private void updateSetContent(){
        for (Vector2d position : animalPositions){
            if(this.isOnEdge(position)) animalBoundaryPositions.add(position);
            else animalBoundaryPositions.remove(position);
        }
    }

    private boolean isOnEdge(Vector2d position){
        return ((position.x == topRightBoundary.x || position.x == bottomLeftBoundary.x)
                && bottomLeftBoundary.y <= position.y  && position.y <= topRightBoundary.y)
                || ((position.y == topRightBoundary.y || position.y == bottomLeftBoundary.y)
                && bottomLeftBoundary.x <= position.x && position.x <= topRightBoundary.x);
    }

    private void updatePosition(Vector2d oldPosition, Vector2d newPosition){
        animalBoundaryPositions.add(newPosition);
        animalPositions.add(newPosition);
        animalBoundaryPositions.remove(oldPosition);
        animalPositions.remove(oldPosition);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (animalBoundaryPositions.contains(oldPosition) && !this.isOnEdge(newPosition)){
            this.updatePosition(oldPosition, newPosition);
            for(Vector2d position : animalPositions){
                topRightBoundary = position.upperRight(topRightBoundary);
                bottomLeftBoundary = position.lowerLeft(bottomLeftBoundary);
            }
            this.updateSetContent();
        }
        else if (this.isOnEdge(newPosition) && !this.isOnEdge(oldPosition)){
            animalBoundaryPositions.add(newPosition);
        }
        else if (this.isOnEdge(newPosition) && this.isOnEdge(oldPosition)){
            this.updatePosition(oldPosition, newPosition);
        }
    }
}
