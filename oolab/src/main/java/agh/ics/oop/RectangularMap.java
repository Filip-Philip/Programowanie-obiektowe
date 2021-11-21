package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width, int height) {
        this.mapTopRightCorner = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.precedes(mapTopRightCorner)
                && position.follows(MAP_BOTTOM_LEFT_CORNER);
    }
}
