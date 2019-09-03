package trikita.anvil.constraint.layout;

final class CircleConstraint {
    public int centerId;
    public final int id;
    public final int angle;
    public final int radius;

    public CircleConstraint(int centerId, int id, int angle, int radius) {
        this.centerId = centerId;
        this.id = id;
        this.angle = angle;
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CircleConstraint that = (CircleConstraint) o;

        if (centerId != that.centerId) return false;
        if (id != that.id) return false;
        if (angle != that.angle) return false;
        return radius == that.radius;

    }

    @Override
    public int hashCode() {
        int result = centerId;
        result = 31 * result + id;
        result = 31 * result + angle;
        result = 31 * result + radius;
        return result;
    }
}