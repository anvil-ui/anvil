package trikita.example

import android.view.View;

import java.util.ArrayDeque;
import java.util.Deque;

import trikita.anvil.Nodes.*;

val stack = ArrayDeque<ViewNode>()

fun AttrNode.minus() = stack.peek().addAttrs(this)
fun AttrNode.plus() = stack.peek().addAttrs(this)

inline fun v<reified T: View>(f: () -> Unit): ViewNode {
	val node = ViewNode(javaClass<T>())
	if (!stack.empty) {
		stack.peek().addViews(node)
	}
	stack.push(node)
	f()
	return stack.pop()
}

