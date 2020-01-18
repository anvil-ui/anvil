@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.constraintlayout.widget

import androidx.constraintlayout.widget.Group
import dev.inkremental.bind
import dev.inkremental.v

fun group(configure: GroupScope.() -> Unit = {}) =
    v<Group>(configure.bind(GroupScope))
abstract class GroupScope : ConstraintHelperScope() {
    companion object : GroupScope()
}
