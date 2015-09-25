package trikita.anvil;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.WeakHashMap;

public class CommonAttrs extends DSL {

	public interface SimpleItemSelectedListener {
		public void onItemSelected(AdapterView a, View v, int pos, long id);
	}

	//
	// Common attrs
	//
	
	// weight constants
	public final static int FILL = ViewGroup.LayoutParams.FILL_PARENT;
	public final static int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;
	public final static int WRAP = ViewGroup.LayoutParams.WRAP_CONTENT;

	// gravity constants
	public final static int TOP = Gravity.TOP;
	public final static int BOTTOM = Gravity.BOTTOM;
	public final static int LEFT = Gravity.LEFT;
	public final static int RIGHT = Gravity.RIGHT;
	public final static int CENTER_VERTICAL = Gravity.CENTER_VERTICAL;
	public final static int GROW_VERTICAL = Gravity.FILL_VERTICAL;
	public final static int CENTER_HORIZONTAL = Gravity.CENTER_HORIZONTAL;
	public final static int GROW_HORIZONTAL = Gravity.FILL_HORIZONTAL;
	public final static int CENTER = CENTER_VERTICAL|CENTER_HORIZONTAL;
	public final static int GROW = GROW_VERTICAL|GROW_HORIZONTAL;
	public final static int CLIP_VERTICAL = Gravity.CLIP_VERTICAL;
	public final static int CLIP_HORIZONTAL = Gravity.CLIP_HORIZONTAL;
	public final static int START = Gravity.START;
	public final static int END = Gravity.END;

	public static Resources R() {
		return Anvil.currentView().getResources();
	}

	public static boolean isPortrait() {
		return R().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	public static float dip(float value) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
				R().getDisplayMetrics());
	}

	public static int dip(int value) {
		return Math.round(dip((float) value));
	}

	public static Void size(int w, int h) {
		return attr(LayoutSizeFunc.instance, new Integer[]{w, h});
	}

	private final static class LayoutSizeFunc implements AttrFunc<Integer[]> {
		private final static LayoutSizeFunc instance = new LayoutSizeFunc();
		public void apply(View v, Integer[] arg, Integer[] old) {
			ViewGroup.LayoutParams p = v.getLayoutParams();
			p.width = arg[0];
			p.height = arg[1];
			v.setLayoutParams(p);
		}
	}

	public static Void padding(int p) {
		return padding(p, p, p, p);
	}

	public static Void padding(int h, int v) {
		return padding(h, v, h, v);
	}

	public static Void padding(int l, int t, int r, int b) {
		return attr(PaddingFunc.instance, new Integer[]{l, r, r, b});
	}

	private final static class PaddingFunc implements AttrFunc<Integer[]> {
		private final static PaddingFunc instance = new PaddingFunc();
		public void apply(View v, Integer[] arg, Integer[] old) {
			v.setPadding(arg[0], arg[1], arg[2], arg[3]);
		}
	}

	public static Void margin(int w) {
		return margin(w, w, w, w);
	}

	public static Void margin(int h, int v) {
		return margin(h, v, h, v);
	}

	public static Void margin(int l, int t, int r, int b) {
		return attr(LayoutMarginFunc.instance, new Integer[]{l, t, r, b});
	}

	private final static class LayoutMarginFunc implements AttrFunc<Integer[]> {
		private final static LayoutMarginFunc instance = new LayoutMarginFunc();
		public void apply(View v, Integer[] arg, Integer[] old) {
			ViewGroup.LayoutParams p = v.getLayoutParams();
			if (p instanceof ViewGroup.MarginLayoutParams) {
				ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) p;
				mp.leftMargin = arg[0];
				mp.topMargin = arg[1];
				mp.rightMargin = arg[2];
				mp.bottomMargin = arg[3];
				v.setLayoutParams(mp);
			}
		}
	}

	public static Void weight(float w) {
		return attr(LayoutWeightFunc.instance, w);
	}

	private final static class LayoutWeightFunc implements AttrFunc<Float> {
		private final static LayoutWeightFunc instance = new LayoutWeightFunc();
		public void apply(View v, Float arg, Float old) {
			ViewGroup.LayoutParams p = v.getLayoutParams();
			if (p instanceof LinearLayout.LayoutParams) {
				((LinearLayout.LayoutParams) p).weight = arg;
				v.setLayoutParams(p);
			}
		}
	}

	public static Void layoutGravity(int g) {
		return attr(LayoutGravityFunc.instance, g);
	}

	private final static class LayoutGravityFunc implements AttrFunc<Integer> {
		private final static LayoutGravityFunc instance = new LayoutGravityFunc();
		public void apply(View v, Integer arg, Integer old) {
			ViewGroup.LayoutParams p = v.getLayoutParams();
			if (p instanceof LinearLayout.LayoutParams) {
				((LinearLayout.LayoutParams) p).gravity = arg;
				v.setLayoutParams(p);
			}
			if (p instanceof FrameLayout.LayoutParams) {
				((FrameLayout.LayoutParams) p).gravity = arg;
				v.setLayoutParams(p);
			}
		}
	}

	public static Void column(int c) {
		// TODO
		return null;
	}

	public static Void span(int span) {
		// TODO
		return null;
	}

	public static Void align(int verb) {
		// TODO
		return null;
	}

	public static Void align(int verb, int anchor) {
		// TODO
		return null;
	}

	public Void above(int anchor) {
		return align(RelativeLayout.ABOVE, anchor);
	}

	public Void alignBaseline(int anchor) {
		return align(RelativeLayout.ALIGN_BASELINE, anchor);
	}

	public Void alignBottom(int anchor) {
		return align(RelativeLayout.ALIGN_BOTTOM, anchor);
	}

	public Void alignEnd(int anchor) {
		return align(RelativeLayout.ALIGN_END, anchor);
	}

	public Void alignLeft(int anchor) {
		return align(RelativeLayout.ALIGN_LEFT, anchor);
	}

	public Void alignParentBottom() {
		return align(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
	}

	public Void alignParentEnd() {
		return align(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
	}

	public Void alignParentLeft() {
		return align(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
	}

	public Void alignParentRight() {
		return align(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
	}

	public Void alignParentStart() {
		return align(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
	}

	public Void alignParentTop() {
		return align(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	}

	public Void alignRight(int anchor) {
		return align(RelativeLayout.ALIGN_RIGHT, anchor);
	}

	public Void alignStart(int anchor) {
		return align(RelativeLayout.ALIGN_START, anchor);
	}

	public Void alignTop(int anchor) {
		return align(RelativeLayout.ALIGN_TOP, anchor);
	}

	public Void below(int anchor) {
		return align(RelativeLayout.BELOW, anchor);
	}

	public Void centerHorizontal() {
		return align(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	}

	public Void centerInParent() {
		return align(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
	}

	public Void centerVertical() {
		return align(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
	}

	public Void toEndOf(int anchor) {
		return align(RelativeLayout.END_OF, anchor);
	}

	public Void toLeftOf(int anchor) {
		return align(RelativeLayout.LEFT_OF, anchor);
	}

	public Void toRightOf(int anchor) {
		return align(RelativeLayout.RIGHT_OF, anchor);
	}

	public Void toStartOf(int anchor) {
		return align(RelativeLayout.START_OF, anchor);
	}

	public static Void typeface(String font) {
		return attr(TypefaceFunc.instance, font);
	}

	private final static class TypefaceFunc implements AttrFunc<String> {
		private final static TypefaceFunc instance = new TypefaceFunc();
		public void apply(View v, String font, String old) {
			if (v instanceof TextView) {
				((TextView) v).setTypeface(Typeface.createFromAsset(v.getContext().getAssets(), font));
			}
		}
	}

	public static Void visibility(boolean visible) {
		if (visible) {
			return trikita.anvil.v15.Attrs.visibility(View.VISIBLE);
		} else {
			return trikita.anvil.v15.Attrs.visibility(View.GONE);
		}
	}

	// -------------
	public static Void tag(int id, Object value) {
		return attr(TagFunc.instance, new Pair<Integer, Object>(id, value));
	}
	private final static class TagFunc implements AttrFunc<Pair<Integer, Object>> {
		private final static TagFunc instance = new TagFunc();
		public void apply(View v, Pair<Integer, Object> p, Pair<Integer, Object> q) {
			// TODO
		}
	}

	public static Void shadowLayer(float radius, float dx, float dy, int color) {
		return attr(ShadowLayerFunc.instance, new Number[]{radius, dx, dy, color});
	}
	private final static class ShadowLayerFunc implements AttrFunc<Number[]> {
		private final static ShadowLayerFunc instance = new ShadowLayerFunc();
		public void apply(View v, Number[] arg, Number[] old) {
			// TODO
		}
	}

	// Custom and simplified listeners

	private static Map<StringBuilder, String> hasUserInput =
		new WeakHashMap<StringBuilder, String>();

	public static Void text(final StringBuilder sb) {
		if (hasUserInput.containsKey(sb) == false) {
			hasUserInput.put(sb, sb.toString());
		}
		onTextChanged(new TextWatcher() {
			@Override
			public void	afterTextChanged(Editable s) {
				sb.replace(0, sb.length(), s.toString());
				Anvil.render();
			}
			@Override
			public void	beforeTextChanged(CharSequence s, int from, int n, int after) {}
			@Override
			public void	onTextChanged(CharSequence s, int from, int before, int n) {}
			@Override
			public int hashCode() { return sb.hashCode(); }
			@Override
			public boolean equals(Object o) { return sb.equals(o); }
		});
		if (sb.toString().equals(hasUserInput.get(sb)) == false) {
			trikita.anvil.v15.Attrs.text(sb.toString());
			hasUserInput.put(sb, sb.toString());
		}
		return null;
	}

	public static Void onTextChanged(TextWatcher w) {
		return attr(TextWatcherFunc.instance, w);
	}
	private final static class TextWatcherFunc implements AttrFunc<TextWatcher> {
		private final static TextWatcherFunc instance = new TextWatcherFunc();
		public void apply(final View v, final TextWatcher w, TextWatcher old) {
			if (v instanceof TextView) {
				TextView tv = (TextView) v;
				if (old != null) {
					tv.removeTextChangedListener(old);
				}
				tv.addTextChangedListener(new TextWatcher() {
					public void	afterTextChanged(Editable s) {
						w.afterTextChanged(s);
					}
					public void	beforeTextChanged(CharSequence s, int from, int n,
						int after) {
						w.beforeTextChanged(s, from, n, after);
					}
					public void	onTextChanged(CharSequence s,
						int from, int before, int n) {
						w.onTextChanged(s, from, before, n);
					}
					public int hashCode() { return w.hashCode(); }
					public boolean equals(Object o) { return w.equals(o); }
				});
			}
		}
	}

	public static Void onItemSelected(SimpleItemSelectedListener l) {
		return attr(ItemSelectedFunc.instance, l);
	}
	private final static class ItemSelectedFunc
			implements AttrFunc<SimpleItemSelectedListener> {
		private final static ItemSelectedFunc instance = new ItemSelectedFunc();
		public void apply(View v, final SimpleItemSelectedListener l,
				SimpleItemSelectedListener old) {
			AdapterView.OnItemSelectedListener wrapper =
				new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView a0, View a1, int a2, long a3) {
						l.onItemSelected(a0, a1, a2, a3);
						Anvil.render();
					}
					@Override
					public void onNothingSelected(AdapterView a0) {}
					@Override
					public int hashCode() { return l.hashCode(); }
					@Override
					public boolean equals(Object o) { return l.equals(o); }
				};
			if (v instanceof AdapterView) {
				((AdapterView) v).setOnItemSelectedListener(wrapper);
			} else if (v instanceof AutoCompleteTextView) {
				((AutoCompleteTextView) v).setOnItemSelectedListener(wrapper);
			}
		}
	}
}

