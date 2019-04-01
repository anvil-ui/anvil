package trikita.anvil.constraint.layout;

import java.util.Arrays;

final class ConstraintChain {
    public final int leftId;
    public final int leftSide;
    public final int rightId;
    public final int rightSide;
    public final int[] chainIds;
    public final float[] weights;
    public final int style;

    public ConstraintChain(int leftId, int leftSide, int rightId, int rightSide, int[] chainIds, float[] weights, int style) {
        this.leftId = leftId;
        this.leftSide = leftSide;
        this.rightId = rightId;
        this.rightSide = rightSide;
        this.chainIds = chainIds;
        this.weights = weights;
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstraintChain chain = (ConstraintChain) o;

        if (leftId != chain.leftId) return false;
        if (leftSide != chain.leftSide) return false;
        if (rightId != chain.rightId) return false;
        if (rightSide != chain.rightSide) return false;
        if (style != chain.style) return false;
        if (!Arrays.equals(chainIds, chain.chainIds)) return false;
        return Arrays.equals(weights, chain.weights);

    }

    @Override
    public int hashCode() {
        int result = leftId;
        result = 31 * result + leftSide;
        result = 31 * result + rightId;
        result = 31 * result + rightSide;
        result = 31 * result + Arrays.hashCode(chainIds);
        result = 31 * result + Arrays.hashCode(weights);
        result = 31 * result + style;
        return result;
    }
}
