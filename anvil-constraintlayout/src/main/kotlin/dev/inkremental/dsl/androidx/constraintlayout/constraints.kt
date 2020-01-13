package dev.inkremental.dsl.androidx.constraintlayout

internal data class ConstraintSide(
    var startId: Int,
    val startSide: Int,
    val endId: Int,
    val endSide: Int
)

internal data class ConstraintCircle(
    var centerId: Int,
    val id: Int,
    val angle: Int,
    val radius: Int
)

internal data class ConstraintChain(
    val isHorizontal: Boolean,
    val leftId: Int,
    val leftSide: Int,
    val rightId: Int,
    val rightSide: Int,
    val chainIds: IntArray?,
    val weights: FloatArray?,
    val style: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConstraintChain

        if (isHorizontal != other.isHorizontal) return false
        if (leftId != other.leftId) return false
        if (leftSide != other.leftSide) return false
        if (rightId != other.rightId) return false
        if (rightSide != other.rightSide) return false
        if (chainIds != null) {
            if (other.chainIds == null) return false
            if (!chainIds.contentEquals(other.chainIds)) return false
        } else if (other.chainIds != null) return false
        if (weights != null) {
            if (other.weights == null) return false
            if (!weights.contentEquals(other.weights)) return false
        } else if (other.weights != null) return false
        if (style != other.style) return false

        return true
    }

    override fun hashCode(): Int {
        var result = leftId
        result = 31 * result + if(isHorizontal) 1 else 0
        result = 31 * result + leftSide
        result = 31 * result + rightId
        result = 31 * result + rightSide
        result = 31 * result + (chainIds?.contentHashCode() ?: 0)
        result = 31 * result + (weights?.contentHashCode() ?: 0)
        result = 31 * result + style
        return result
    }
}
