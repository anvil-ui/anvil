package trikita.anvil;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

/**
 * <p>
 * This is a helper class that contains definitions of the virtual layout nodes.
 * </p>
 * <p>
 * This class also provides a helper factory method {@code v(...)} that creates a virtual layout for the given layout hierarchy and state:
 * </p>
 * <pre>
 * {@code
 * v(LinearLayout.class,
 *   orientation(LinearLayout.VERTICAL),
 *
 *   v(TextView.class,
 *     text(someStringVariable)),
 *
 *   v(Button.class,
 *     text(R.string.btn_text),
 *     onClick(this::someButtonClickHandler)));
 * }
 * </pre>
 * <p>
 * There are two types of nodes - view nodes (e.g. nodes that will be rendered
 * as some View object) and attribute nodes (e.g. nodes that will modify the
 * view attribute, like text, color, some event listener etc).
 * </p>
 * <p>
 * This class does a little trick to achieve some type safety of the nodes.
 * Which means that you can include attribute nodes into any kind of a view
 * node, but node into the attribute node, so this code is incorrect and will
 * result in a compiler error:
 * </p>
 * <pre>
 * {@code
 * text("Hello",
 *   backgroundColor(Color.WHITE));
 * }
 * </pre>
 * <p>
 * This also means that can can include view nodes into any kind of a ViewGroup
 * node, but not into the attribute node or a regular view node, so this code
 * is incorrect and will result in a compiler error:
 * </p>
 * <pre>
 * {@code
 * // View inside a view, not a viewgroup:
 * v(TextView.class,
 *   v(Button.class));
 * // View inside an attribute:
 * text("Hello",
 *   v(TextView.class));
 * }
 * </pre>
 */
public class Nodes {

	/**
	 * <p>
	 * A general virtual layout node type. Parametrized type T is a View or
	 * ViewGroup. One should not use this interface directly, instead ViewNode
	 * and AttrNode classes should be used.
	 * </p>
	 * <p>
	 * T here is "View" if node is applicable to views and viewgroups
	 * </p>
	 * <p>
	 * T here is "ViewGroup" if node is applicable to viewgroups only
	 * </p>
	 * <p>
	 * Then {@code v(Class<T>, INode<? super T>...)} will:
	 * </p>
	 * <ul>
	 * <li>accept View class and INode&lt;View&gt; nodes</li>
	 * <li>accept ViewGroup class and INode&lt;View&gt; nodes</li>
	 * <li>accept ViewGroup class and INode&lt;ViewGroup&gt; nodes</li>
	 * <li>not accept View class and INode&lt;ViewGroup&gt; nodes (ViewGroup is not super of View)</li>
	 * </ul>
	 */
	public interface INode<T> {}

	/** A view node. Contains a list of child view nodes and attributes nodes for
	 * the current view. Nested view nodes are allowed to be used with viewgroups
	 * only
	 */
	public static class ViewNode implements INode<ViewGroup> {
		Class<? extends View> viewClass;
		List<ViewNode> children = new ArrayList<ViewNode>();
		List<AttrNode> attrs = new ArrayList<AttrNode>();

		public ViewNode(Class<? extends View> c) {
			this.viewClass = c;
		}

		public ViewNode addViews(Collection<ViewNode> c) {
			this.children.addAll(c);
			return this;
		}

		public ViewNode addViews(ViewNode ...nodes) {
			for (ViewNode n : nodes) {
				this.children.add(n);
			}
			return this;
		}

		public ViewNode addAttrs(Collection<AttrNode> c) {
			this.attrs.addAll(c);
			return this;
		}

		public ViewNode addAttrs(AttrNode ...nodes) {
			for (AttrNode n : nodes) {
				this.attrs.add(n);
			}
			return this;
		}
	}

	/**
	 * Attribute node. Used to modify some attribute of a view. Attribute nodes
	 * can be used with both views and viewgroups.
	 */
	public static interface AttrNode extends INode<View> {
		void apply(View v);
	}

	/**
	 * <p>
	 * A factory method to return a virtual layout from the given view node, its
	 * attributes and child view nodes.
	 * </p>
	 * <p>
	 * v() method for viewgroups accepts both attributes and child view nodes.
	 * </p>
	 * <p>
	 * v() method for views accepts attributes only
	 * </p>
	 * @param <T> a type of the view class (TextView, FrameLayout etc)
	 * @param c view class (TextView.class, FrameLayout.class etc)
	 * @param args child nodes (attribute or view nodes)
	 * @return A virtual layout view node
	 */
	public static <T extends View> ViewNode v(Class<T> c, INode<? super T> ...args) {
		ViewNode node = new ViewNode(c);
		for (INode<?> n : args) {
			if (n instanceof ViewNode) {
				node.children.add((ViewNode) n);
			} else if (n instanceof AttrNode) {
				node.attrs.add((AttrNode) n);
			}
		}
		return node;
	}
}
