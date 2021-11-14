import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    void orientationTest() {
        IWorldMap map = new RectangularMap(4,4);
        Animal animal = new Animal(new Vector2d(2,2), map);
        animal.move(MoveDirection.RIGHT, map);
        assertEquals(animal.toString(), "(Wschód, (2, 2))");
        animal.move(MoveDirection.RIGHT, map);
        assertEquals(animal.toString(), "(Południe, (2, 2))");
        animal.move(MoveDirection.LEFT, map);
        assertEquals(animal.toString(), "(Wschód, (2, 2))");
    }

    @Test
    void positionTest() {
        IWorldMap map = new RectangularMap(4,4);
        Animal animal = new Animal(new Vector2d(2,2), map);
        animal.move(MoveDirection.FORWARD, map);
        assertEquals(animal.toString(), "(Północ, (2, 3))");
        animal.move(MoveDirection.LEFT, map);
        animal.move(MoveDirection.BACKWARD, map);
        assertEquals(animal.toString(), "(Zachód, (3, 3))");
        for (int i = 0; i < 10; i++){
            animal.move(MoveDirection.FORWARD, map);
        }
        assertEquals(animal.toString(), "(Zachód, (0, 3))");
    }

    @Test
    void parserTest() {
        String[] input = {"b", "forward", "asdas", "l", "right"};
        MoveDirection[] parserOutput = OptionsParser.parse(input);
        MoveDirection[] correctOutput = {MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.LEFT,
                                        MoveDirection.RIGHT};
        assertArrayEquals(parserOutput, correctOutput);
    }
}
