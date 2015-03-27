package trikita.anvil;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import static trikita.anvil.Render.*;

public class Props {
	//
	// Syntax sugar
	//
	
	// weight constants
	public final static int FILL = ViewGroup.LayoutParams.FILL_PARENT;
	public final static int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;
	public final static int WRAP = ViewGroup.LayoutParams.WRAP_CONTENT;

	// gravity constants
	public final static int TOP = 0x30;
	public final static int BOTTOM = 0x50;
	public final static int LEFT = 0x03;
	public final static int RIGHT = 0x05;
	public final static int CENTER_VERTICAL = 0x10;
	public final static int GROW_VERTICAL = 0x70;
	public final static int CENTER_HORIZONTAL = 0x01;
	public final static int GROW_HORIZONTAL = 0x07;
	public final static int CENTER = CENTER_VERTICAL|CENTER_HORIZONTAL;
	public final static int GROW = GROW_VERTICAL|GROW_HORIZONTAL;
	public final static int CLIP_VERTICAL = 0x80;
	public final static int CLIP_HORIZONTAL = 0x08;
	public final static int START = 0x00800003;
	public final static int END = 0x00800005;

	public static class LayoutNode implements AttrNode {
		int width;
		int height;
		float weight;
		int gravity;
		int column;
		int span;
		int[] margin = new int[4];
		
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

		public void apply(View v) {
			ViewGroup.LayoutParams p = v.getLayoutParams();
			p.width = width;
			p.height = height;
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
			v.setLayoutParams(p);
		}

		public int hashCode() {
			return this.width + this.height + Float.floatToIntBits(this.weight) +
				this.gravity + this.column + this.span +
				this.margin[0] + this.margin[1] + this.margin[2] + this.margin[3];
		}

		public boolean equals(Object obj) {
			if (getClass() == obj.getClass()) {
				LayoutNode n = (LayoutNode) obj;
				return this.width == n.width && this.height == n.height &&
					this.weight == n.weight && this.gravity == n.gravity &&
					this.column == n.column && this.span == n.span &&
					this.margin[0] == n.margin[0] && this.margin[1] == n.margin[1] &&
					this.margin[2] == n.margin[2] && this.margin[3] == n.margin[3];
			}
			return false;
		}
	}

	public static LayoutNode size(int w, int h) {
		return new LayoutNode(w, h);
	}

	public static AttrNode padding(int p) {
		return padding(p, p, p, p);
	}

	public static AttrNode padding(int horizontal, int vertical) {
		return padding(horizontal, vertical, horizontal, vertical);
	}

	public static AttrNode padding(final int left, final int top, final int right, final int bottom) {
		final List<Integer> params = new ArrayList<Integer>() {{
			add(left); add(top); add(right); add(bottom);
		}};
		return new SimpleAttrNode(params) {
			public void apply(View v) {
				v.setPadding(params.get(0), params.get(1), params.get(2), params.get(3));
			}
		};
	}

	public static AttrNode typeface(final String font) {
		return new SimpleAttrNode(font) {
			public void apply(View v) {
				if (v instanceof android.widget.TextView) {
					((android.widget.TextView) v)
						.setTypeface(Typeface.createFromAsset(v.getContext().getAssets(), font));
				}
			}
		};
	}

	//
	// Generated bindings
	//
}
