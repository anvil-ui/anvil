package trikita.anvil.constraint.layout;

final class ConstraintSide {
    public final int startID;
    public final int startSide;
    public final int endID;
    public final int endSide;

    public ConstraintSide(int startID, int startSide, int endID, int endSide) {
        this.startID = startID;
        this.startSide = startSide;
        this.endID = endID;
        this.endSide = endSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstraintSide that = (ConstraintSide) o;

        if (startID != that.startID) return false;
        if (startSide != that.startSide) return false;
        if (endID != that.endID) return false;
        return endSide == that.endSide;

    }

    @Override
    public int hashCode() {
        int result = startID;
        result = 31 * result + startSide;
        result = 31 * result + endID;
        result = 31 * result + endSide;
        return result;
    }
}