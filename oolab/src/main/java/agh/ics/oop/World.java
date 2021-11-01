package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
        Animal animal = new Animal();
        String[] input = {"f", "backward", "r", "f", "f", "das", "das", "l", "b"};
        MoveDirection[] directions = OptionsParser.parse(input);
        System.out.println(Arrays.toString(directions));
        for (MoveDirection direction : directions){
            animal.move(direction);
        }
        System.out.println(animal.toString());
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
