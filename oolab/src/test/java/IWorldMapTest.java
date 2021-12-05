import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class IWorldMapTest {
    @Test
    void canMoveToTest() {
        IWorldMap recMap = new RectangularMap(4, 4);
        IWorldMap grassField = new GrassField(2);
        Animal animalRecMap1 = new Animal(new Vector2d(1, 1), recMap);
        Animal animalRecMap2 = new Animal(new Vector2d(1, 2), recMap);
        Animal animalGrassField1 = new Animal(new Vector2d(1, 100), grassField);
        Animal animalgrassField2 = new Animal(new Vector2d(1, 101), grassField);
        Vector2d positionTested = new Vector2d(1, 1);
        assertEquals(recMap.canMoveTo(positionTested), false);
        positionTested = new Vector2d(1, 3);
        assertEquals(recMap.canMoveTo(positionTested), true);
        positionTested = new Vector2d(1, 5);
        assertEquals(recMap.canMoveTo(positionTested), false);
        positionTested = new Vector2d(1, 100);
        assertEquals(grassField.canMoveTo(positionTested), false);
        positionTested = new Vector2d(347, 145);
        assertEquals(grassField.canMoveTo(positionTested), true);
    }

    @Test
    void placeTest() {
        IWorldMap recMap = new RectangularMap(4, 4);
        IWorldMap grassField = new GrassField(4);
        Vector2d positionTested = new Vector2d(1,1);
        Animal animalRecMap1 = new Animal(positionTested, recMap);
        assertEquals(animalRecMap1.getPosition(), positionTested);
        Animal animalGrassField1 = new Animal(positionTested, grassField);
        assertEquals(animalGrassField1.getPosition(), positionTested);
        assertThrows(IllegalArgumentException.class, () -> {
           Animal animalRecMap2 = new Animal(positionTested, recMap); 
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Animal animalGrassField2 = new Animal(positionTested, grassField);
        });
    }

    @Test
    void isOccupiedTest() {
        IWorldMap recMap = new RectangularMap(4, 4);
        IWorldMap grassField = new GrassField(2);
        Animal animalRecMap1 = new Animal(new Vector2d(1, 1), recMap);
        Animal animalGrassField1 = new Animal(new Vector2d(1, 1), grassField);
        Vector2d positionTested = new Vector2d(1,1);
        assertEquals(recMap.isOccupied(positionTested), true);
        positionTested = new Vector2d(1,2);
        assertEquals(recMap.isOccupied(positionTested), false);
        positionTested = new Vector2d(1,1);
        assertEquals(grassField.isOccupied(positionTested), true);
        positionTested = new Vector2d(1,2);
        assertEquals(grassField.isOccupied(positionTested), false);
    }

    @Test
    void objectAtTest() {
        IWorldMap recMap = new RectangularMap(4, 4);
        IWorldMap grassField = new GrassField(2);
        Animal animalRecMap1 = new Animal(new Vector2d(1, 1), recMap);
        Animal animalGrassField1 = new Animal(new Vector2d(1, 1), grassField);
        Vector2d positionTested = new Vector2d(1,1);
        assertEquals(recMap.objectAt(positionTested), animalRecMap1);
        positionTested = new Vector2d(1,2);
        assertEquals(recMap.objectAt(positionTested), null);
        positionTested = new Vector2d(1,1);
        assertEquals(grassField.objectAt(positionTested), animalGrassField1);
        positionTested = new Vector2d(1,2);
        assertEquals(grassField.objectAt(positionTested), null);
    }
}
