package agh.ics.oop;

public class Animal {
    public static final Vector2d MAP_TOP_RIGHT_CORNER = new Vector2d(4, 4);
    public static final Vector2d MAP_BOTTOM_LEFT_CORNER = new Vector2d(0, 0);
    private Vector2d position;
    private MapDirection orientation;
    private IWorldMap map;

    public Animal(Vector2d initialPosition, IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    public void move(MoveDirection direction, IWorldMap map) {
        if (direction.equals(MoveDirection.LEFT)) {
            orientation = switch (orientation) {
                case NORTH -> MapDirection.WEST;
                case SOUTH -> MapDirection.EAST;
                case WEST -> MapDirection.SOUTH;
                case EAST -> MapDirection.NORTH;
            };
        } else if (direction.equals(MoveDirection.RIGHT)) {
            orientation = switch (orientation) {
                case NORTH -> MapDirection.EAST;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
                case EAST -> MapDirection.SOUTH;
            };
        } else {
            Vector2d movementVector = switch (orientation) {
                case NORTH -> new Vector2d(0, 1);
                case SOUTH -> new Vector2d(0, -1);
                case WEST -> new Vector2d(-1, 0);
                case EAST -> new Vector2d(1, 0);
            };
//            if(direction.equals(MoveDirection.FORWARD) && position.add(movementVector).precedes(MAP_TOP_RIGHT_CORNER)
//                && position.add(movementVector).follows(MAP_BOTTOM_LEFT_CORNER)){
//                position = position.add(movementVector);
//            }
//            else if(direction.equals(MoveDirection.BACKWARD) && position.subtract(movementVector).precedes(MAP_TOP_RIGHT_CORNER)
//                    && position.subtract(movementVector).follows(MAP_BOTTOM_LEFT_CORNER)){
//                position = position.subtract(movementVector);
//            }
            if (direction.equals(MoveDirection.FORWARD) && map.canMoveTo(position.add(movementVector))) {
                position = position.add(movementVector);
            } else if (direction.equals(MoveDirection.BACKWARD) && map.canMoveTo(position.subtract(movementVector))) {
                position = position.subtract(movementVector);
            }
        }
    }
}