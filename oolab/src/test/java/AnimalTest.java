import agh.ics.oop.Animal;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    void orientationTest() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.toString(), "(Wschód, (2, 2))");
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.toString(), "(Południe, (2, 2))");
        animal.move(MoveDirection.LEFT);
        assertEquals(animal.toString(), "(Wschód, (2, 2))");
    }

    @Test
    void positionTest() {
        Animal animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.toString(), "(Północ, (2, 3))");
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.toString(), "(Zachód, (3, 3))");
        for (int i = 0; i < 10; i++){
            animal.move(MoveDirection.FORWARD);
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
