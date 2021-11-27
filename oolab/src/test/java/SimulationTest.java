import agh.ics.oop.*;
import org.junit.jupiter.api.Test;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.RectangularMap;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    void movementTest() {
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(moves);
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertNotNull(map.objectAt(new Vector2d(2,0)));
        assertNotNull(map.objectAt(new Vector2d(3,5)));
        Animal testedAnimal1 = (Animal) map.objectAt(new Vector2d(2,0));
        Animal testedAnimal2 = (Animal) map.objectAt(new Vector2d(3,5));
        assertEquals(testedAnimal1.getPosition(), new Vector2d(2,0));
        assertEquals(testedAnimal2.getPosition(), new Vector2d(3,5));
    }
}
