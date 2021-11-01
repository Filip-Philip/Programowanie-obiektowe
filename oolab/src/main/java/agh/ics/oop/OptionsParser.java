package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        int count = 0;
        for (String arg : args){
            if (arg.equals("f") || arg.equals("forward") || arg.equals("b") || arg.equals("backward")
                    || arg.equals("r") || arg.equals("right") || arg.equals("l") || arg.equals("left")){
                count++;
            }
        }
        MoveDirection[] directions = new MoveDirection[count];
        count = 0;
        for (String arg : args){
            if (arg.equals("f") || arg.equals("forward")){
                directions[count] = MoveDirection.FORWARD;
                count++;
            }
            else if (arg.equals("b") || arg.equals("backward")){
                directions[count] = MoveDirection.BACKWARD;
                count++;
            }
            else if (arg.equals("r") || arg.equals("right")){
                directions[count] = MoveDirection.RIGHT;
                count++;
            }
            else if (arg.equals("l") || arg.equals("left")){
                directions[count] = MoveDirection.LEFT;
                count++;
            }
        }
        return directions;
    }
}
