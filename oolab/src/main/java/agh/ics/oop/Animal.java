package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;

    @Override
    public String toString() {
        return "(" +
               orientation +
                ", " +
                position +
                ')';
    }

    public void move(MoveDirection direction){
        if(direction.equals(MoveDirection.LEFT)){
            orientation = switch (orientation){
                case NORTH -> MapDirection.WEST;
                case SOUTH -> MapDirection.EAST;
                case WEST -> MapDirection.SOUTH;
                case EAST -> MapDirection.NORTH;
            };
        }
        else if(direction.equals(MoveDirection.RIGHT)){
            orientation = switch (orientation){
                case NORTH -> MapDirection.EAST;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
                case EAST -> MapDirection.SOUTH;
            };
        }
        else{
            Vector2d movementVector = switch (orientation) {
                case NORTH -> new Vector2d(0,1);
                case SOUTH -> new Vector2d(0,-1);
                case WEST -> new Vector2d(-1,0);
                case EAST -> new Vector2d(1,0);
            };
            if(direction.equals(MoveDirection.FORWARD) && position.add(movementVector).precedes(new Vector2d( 4,4))
                && position.add(movementVector).follows(new Vector2d(0,0))){
                position = position.add(movementVector);
            }
            else if(direction.equals(MoveDirection.BACKWARD) && position.add(movementVector).precedes(new Vector2d( 4,4))
                    && position.add(movementVector).follows(new Vector2d(0,0))){
                position = position.subtract(movementVector);
            }
        }
    }
}
