package trikita.anvil;

/**
 * Helper for attribute node containing only one value. Applies changes to the
 * view only when the value has been changed since last rendering cycle.
 */
public abstract class SimpleAttrNode<T> implements Nodes.AttrNode {
	protected final T value;

	public SimpleAttrNode(T value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return value == null ? value.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass() &&
			(this.value == null
			 && ((SimpleAttrNode) obj).value == null
			 || this.value.equals(((SimpleAttrNode) obj).value));
	}
}

