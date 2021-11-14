package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
//        String[] a = {"f", "b", "r", "l"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        MapVisualiser mapState = new MapVisualiser(map);
        StringBuilder mapPic = new StringBuilder(mapState.draw(new Vector2d(0, 0), new Vector2d(10, 5)));
        System.out.println(mapPic);
    }
    public static Direction[] string_to_enum(String[] strings){
        Direction[] new_directions = new Direction[strings.length];
        int i = 0;
        for(String string: strings) {
            Direction result = switch (string) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> null;
            };
            new_directions[i] = result;
            i++;
        }
        return new_directions;
    }
    public static void run(Direction[] directions){
        for (Direction direction: directions){
            String message = switch (direction) {
                case FORWARD -> "Do przodu";
                case BACKWARD -> "Do tyłu";
                case RIGHT -> "W prawo";
                case LEFT -> "W lewo";
                default -> null;
            };
            if(message != null){
                System.out.println(message);
            }
        }
    }
}
