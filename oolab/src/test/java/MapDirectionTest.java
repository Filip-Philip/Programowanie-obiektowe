import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    void testNext() {
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
    }

    @Test
    void testPrevious(){
        assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
    }


}
