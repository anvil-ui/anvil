package trikita.anvil;

import android.widget.RelativeLayout;
import android.util.Pair;
import java.util.List;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TableRow;
import android.view.View;

/** Attribute node that adjusts the LayoutParams of the view */
public class LayoutNode implements Nodes.AttrNode {
	int width;
	int height;
	float weight;
	int gravity;
	int column;
	int span;
	int[] margin = new int[4];
	List<Pair<Integer,Integer>> rules = new ArrayList<>();

	public LayoutNode() {
		this(Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	public LayoutNode(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public LayoutNode weight(float w) {
		this.weight = w;
		return this;
	}

	public LayoutNode margin(int m) {
		return margin(m, m, m, m);
	}

	public LayoutNode margin(int horizontal, int vertical) {
		return margin(horizontal, vertical, horizontal, vertical);
	}

	public LayoutNode margin(int l, int t, int r, int b) {
		this.margin[0] = l;
		this.margin[1] = t;
		this.margin[2] = r;
		this.margin[3] = b;
		return this;
	}

	public LayoutNode gravity(int g) {
		this.gravity = g;
		return this;
	}

	public LayoutNode column(int column) {
		this.column = column;
		return this;
	}

	public LayoutNode span(int span) {
		this.span = span;
		return this;
	}

	public LayoutNode align(int verb) {
		return this.align(verb, -1);
	}

	public LayoutNode align(int verb, int anchor) {
		this.rules.add(new Pair<>(verb, anchor));
		return this;
	}

	public LayoutNode above(int anchor) {
		return align(RelativeLayout.ABOVE, anchor);
	}

	public LayoutNode alignBaseline(int anchor) {
		return align(RelativeLayout.ALIGN_BASELINE, anchor);
	}

	public LayoutNode alignBottom(int anchor) {
		return align(RelativeLayout.ALIGN_BOTTOM, anchor);
	}

	public LayoutNode alignEnd(int anchor) {
		return align(RelativeLayout.ALIGN_END, anchor);
	}

	public LayoutNode alignLeft(int anchor) {
		return align(RelativeLayout.ALIGN_LEFT, anchor);
	}

	public LayoutNode alignParentBottom() {
		return align(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
	}

	public LayoutNode alignParentEnd() {
		return align(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
	}

	public LayoutNode alignParentLeft() {
		return align(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
	}

	public LayoutNode alignParentRight() {
		return align(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
	}

	public LayoutNode alignParentStart() {
		return align(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
	}

	public LayoutNode alignParentTop() {
		return align(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	}

	public LayoutNode alignRight(int anchor) {
		return align(RelativeLayout.ALIGN_RIGHT, anchor);
	}

	public LayoutNode alignStart(int anchor) {
		return align(RelativeLayout.ALIGN_START, anchor);
	}

	public LayoutNode alignTop(int anchor) {
		return align(RelativeLayout.ALIGN_TOP, anchor);
	}

	public LayoutNode below(int anchor) {
		return align(RelativeLayout.BELOW, anchor);
	}

	public LayoutNode centerHorizontal() {
		return align(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	}

	public LayoutNode centerInParent() {
		return align(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
	}

	public LayoutNode centerVertical() {
		return align(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
	}

	public LayoutNode toEndOf(int anchor) {
		return align(RelativeLayout.END_OF, anchor);
	}

	public LayoutNode toLeftOf(int anchor) {
		return align(RelativeLayout.LEFT_OF, anchor);
	}

	public LayoutNode toRightOf(int anchor) {
		return align(RelativeLayout.RIGHT_OF, anchor);
	}

	public LayoutNode toStartOf(int anchor) {
		return align(RelativeLayout.START_OF, anchor);
	}

	public void apply(View v) {
		ViewGroup.LayoutParams p = v.getLayoutParams();
		if (width != Integer.MIN_VALUE) {
			p.width = width;
		}
		if (height != Integer.MIN_VALUE) {
			p.height = height;
		}
		if (p instanceof ViewGroup.MarginLayoutParams) {
			((ViewGroup.MarginLayoutParams) p).leftMargin = this.margin[0];
			((ViewGroup.MarginLayoutParams) p).topMargin = this.margin[1];
			((ViewGroup.MarginLayoutParams) p).rightMargin = this.margin[2];
			((ViewGroup.MarginLayoutParams) p).bottomMargin = this.margin[3];
		}
		if (p instanceof LinearLayout.LayoutParams) {
			((LinearLayout.LayoutParams) p).weight = this.weight;
			((LinearLayout.LayoutParams) p).gravity = this.gravity;
		}
		if (p instanceof FrameLayout.LayoutParams) {
			((FrameLayout.LayoutParams) p).gravity = this.gravity;
		}
		if (p instanceof TableRow.LayoutParams) {
			((TableRow.LayoutParams) p).column = this.column;
			((TableRow.LayoutParams) p).span = this.span;
		}
		if (p instanceof RelativeLayout.LayoutParams) {
			for (Pair<Integer, Integer> rule : rules) {
				((RelativeLayout.LayoutParams) p).addRule(rule.first, rule.second);
			}
		}
		v.setLayoutParams(p);
	}

	public int hashCode() {
		return this.width + this.height + Float.floatToIntBits(this.weight) +
			this.gravity + this.column + this.span +
			this.margin[0] + this.margin[1] + this.margin[2] + this.margin[3] +
			rules.hashCode();
	}

	public boolean equals(Object obj) {
		if (getClass() == obj.getClass()) {
			LayoutNode n = (LayoutNode) obj;
			return this.width == n.width && this.height == n.height &&
				this.weight == n.weight && this.gravity == n.gravity &&
				this.column == n.column && this.span == n.span &&
				this.margin[0] == n.margin[0] && this.margin[1] == n.margin[1] &&
				this.margin[2] == n.margin[2] && this.margin[3] == n.margin[3] &&
				rules.equals(n.rules);
		}
		return false;
	}
}

