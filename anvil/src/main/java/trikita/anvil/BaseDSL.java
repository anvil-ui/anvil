package trikita.anvil;

import android.animation.Animator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class BaseDSL implements Anvil.AttributeSetter {

	static {
		Anvil.registerAttributeSetter(new BaseDSL());
	}

	//
	// Common attrs
	//
	
	// weight constants
	public final static int FILL = ViewGroup.LayoutParams.MATCH_PARENT;
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

	public static float sip(float value) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value,
				R().getDisplayMetrics());
	}

	public static int sip(int value) {
		return Math.round(sip((float) value));
	}

	public static Void size(int w, int h) {
		return attr("size", new AbstractMap.SimpleImmutableEntry<>(w, h));
	}
	public static Void padding(int p) {
		return padding(p, p, p, p);
	}
	public static Void padding(int h, int v) {
		return padding(h, v, h, v);
	}
	public static Void padding(int l, int t, int r, int b) {
		List<Integer> list = new ArrayList<>(4);
		list.add(l); list.add(t); list.add(r); list.add(b);
		return attr("padding", list);
	}

	public static Void margin(int w) {
		return margin(w, w, w, w);
	}
	public static Void margin(int h, int v) {
		return margin(h, v, h, v);
	}
	public static Void margin(int l, int t, int r, int b) {
		List<Integer> list = new ArrayList<>(4);
		list.add(l); list.add(t); list.add(r); list.add(b);
		return attr("margin", list);
	}

	public static Void weight(float w) {
		return attr("weight", w);
	}
	public static Void layoutGravity(int g) {
		return attr("layoutGravity", g);
	}

	public static Void align(int verb) {
		return align(verb, -1);
	}

	public static Void above(int anchor) {
		return align(RelativeLayout.ABOVE, anchor);
	}

	public static Void alignBaseline(int anchor) {
		return align(RelativeLayout.ALIGN_BASELINE, anchor);
	}

	public static Void alignBottom(int anchor) {
		return align(RelativeLayout.ALIGN_BOTTOM, anchor);
	}

	public static Void alignEnd(int anchor) {
		return align(RelativeLayout.ALIGN_END, anchor);
	}

	public static Void alignLeft(int anchor) {
		return align(RelativeLayout.ALIGN_LEFT, anchor);
	}

	public static Void alignParentBottom() {
		return align(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
	}

	public static Void alignParentEnd() {
		return align(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
	}

	public static Void alignParentLeft() {
		return align(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
	}

	public static Void alignParentRight() {
		return align(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
	}

	public static Void alignParentStart() {
		return align(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
	}

	public static Void alignParentTop() {
		return align(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	}

	public static Void alignRight(int anchor) {
		return align(RelativeLayout.ALIGN_RIGHT, anchor);
	}

	public static Void alignStart(int anchor) {
		return align(RelativeLayout.ALIGN_START, anchor);
	}

	public static Void alignTop(int anchor) {
		return align(RelativeLayout.ALIGN_TOP, anchor);
	}

	public static Void below(int anchor) {
		return align(RelativeLayout.BELOW, anchor);
	}

	public static Void centerHorizontal() {
		return align(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	}

	public static Void centerInParent() {
		return align(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
	}

	public static Void centerVertical() {
		return align(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
	}

	public static Void toEndOf(int anchor) {
		return align(RelativeLayout.END_OF, anchor);
	}

	public static Void toLeftOf(int anchor) {
		return align(RelativeLayout.LEFT_OF, anchor);
	}

	public static Void toRightOf(int anchor) {
		return align(RelativeLayout.RIGHT_OF, anchor);
	}

	public static Void toStartOf(int anchor) {
		return align(RelativeLayout.START_OF, anchor);
	}

	public static Void align(int verb, int anchor) {
		return attr("align", new AbstractMap.SimpleImmutableEntry<>(verb, anchor));
	}

	public static Void textSize(float size) {
		return attr("textSize", size);
	}

	public static Void typeface(String font) {
		return attr("typeface", font);
	}

	public static Void typeface(String font, int style) {
		return attr("styledTypeface", new AbstractMap.SimpleImmutableEntry<>(font, style));
	}

	public static Void compoundDrawables(Drawable l, Drawable t, Drawable r, Drawable b) {
		List<Drawable> list = new ArrayList<>(4);
		list.add(l); list.add(t); list.add(r); list.add(b);
		return attr("compoundDrawables", list);
	}

	public static Void compoundDrawablesWithIntrinsicBounds(Drawable l, Drawable t,
															Drawable r, Drawable b) {
		List<Drawable> list = new ArrayList<>(4);
		list.add(l); list.add(t); list.add(r); list.add(b);
		return attr("compoundDrawablesWithIntrinsicBounds", list);
	}

	public static Void compoundDrawablesWithIntrinsicBoundsResource(int l, int t,
																	int r, int b) {
		List<Integer> list = new ArrayList<>(4);
		list.add(l);
		list.add(t);
		list.add(r);
		list.add(b);
		return attr("compoundDrawablesWithIntrinsicBoundsResource", list);
	}

	public static Void visibility(boolean visible) {
		if (visible) {
			return trikita.anvil.DSL.visibility(View.VISIBLE);
		} else {
			return trikita.anvil.DSL.visibility(View.GONE);
		}
	}

	public static Void check(int id) {
		return attr("check", id);
	}

	public static Void tag(int id, Object value) {
		return attr("tag", new AbstractMap.SimpleImmutableEntry<>(id, value));
	}

	public static Void shadowLayer(float radius, float dx, float dy, int color) {
		List<Number> list = new ArrayList<>(4);
		list.add(radius); list.add(dx); list.add(dy); list.add(color);
		return attr("shadowLayer", list);
	}

	public interface SimpleTextWatcher {
		void onTextChanged(CharSequence s);
	}
	public interface SimpleSeekBarChangeListener {
		void onSeekBarChange(SeekBar s, int progress, boolean fromUser);
	}
	public interface SimpleItemSelectedListener {
		void onItemSelected(AdapterView a, View v, int pos, long id);
	}

	private final static Map<TextWatcherProxy, Void> TEXT_WATCHERS = new WeakHashMap<>();
	private static TextView CURRENT_INPUT_TEXT_VIEW = null;

	private static class TextWatcherProxy implements TextWatcher {
		private final TextView v;
		private TextWatcher watcher;
		private SimpleTextWatcher simpleWatcher;
		private String text = "";

		public TextWatcherProxy(TextView v) {
			this.v = v;
		}

		public TextWatcherProxy setImpl(TextWatcher w) {
			this.watcher = w;
			this.simpleWatcher = null;
			return this;
		}
		public TextWatcherProxy setImpl(SimpleTextWatcher w) {
			this.simpleWatcher = w;
			this.watcher = null;
			return this;
		}
		public boolean hasImpl(Object o) {
			if (o == null) {
				return false;
			}
			return o.equals(this.watcher) || o.equals(this.simpleWatcher);
		}
		public void afterTextChanged(Editable s) {
			if (this.watcher != null) {
				this.watcher.afterTextChanged(s);
			}
		}
		public void beforeTextChanged(CharSequence s, int from, int n, int after) {
			if (this.watcher != null) {
				this.watcher.beforeTextChanged(s, from, n, after);
			}
		}
		public void onTextChanged(CharSequence s, int from, int before, int n) {
			TextView old = CURRENT_INPUT_TEXT_VIEW;
			CURRENT_INPUT_TEXT_VIEW = this.v;
			if (this.text.equals(s.toString()) == false) {
				if (this.watcher != null) {
					this.watcher.onTextChanged(s, from, before, n);
				}
				if (this.simpleWatcher != null) {
					this.simpleWatcher.onTextChanged(s);
				}
				this.text = s.toString();

				Anvil.render();
			}
			CURRENT_INPUT_TEXT_VIEW = old;
		}
	}

	private final static class ItemSelectedWrapper implements AdapterView.OnItemSelectedListener {
		private final SimpleItemSelectedListener parent;
		private ItemSelectedWrapper(SimpleItemSelectedListener parent) { this.parent = parent; }
		public void onItemSelected(AdapterView a0, View a1, int a2, long a3) {
			parent.onItemSelected(a0, a1, a2, a3);
			Anvil.render();
		}
		public void onNothingSelected(AdapterView a0) {}
		public int hashCode() { return parent.hashCode(); }
		public boolean equals(Object o) { return parent.equals(o); }
	}

	private final static class SeekBarChangeWrapper implements SeekBar.OnSeekBarChangeListener {
		private final SimpleSeekBarChangeListener parent;
		private SeekBarChangeWrapper(SimpleSeekBarChangeListener parent) {
			this.parent = parent;
		}
		public void onProgressChanged(SeekBar a0, int a1, boolean a2) {
			parent.onSeekBarChange(a0, a1, a2);
			Anvil.render();
		}
		public void onStartTrackingTouch(SeekBar a0) {}
		public void onStopTrackingTouch(SeekBar a0) {}
		public int hashCode() { return parent.hashCode(); }
		public boolean equals(Object o) { return parent.equals(o); }
	}

	private final static class AnimatorPair {
		public Animator animator;
		public final boolean trigger;
		public AnimatorPair(Animator a, boolean trigger) {
			this.animator = a;
			this.trigger = trigger;
		}
		public int hashCode() {
			return trigger ? 0 : 1;
		}
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			AnimatorPair pair = (AnimatorPair) o;
			if (pair.trigger != this.trigger) {
				if (pair.animator != null && pair.animator.isRunning()) {
					pair.animator.cancel();
				}
				return false;
			}
			this.animator = pair.animator;
			return true;
		}
	}

	public static Void onItemSelected(SimpleItemSelectedListener l) { return attr("onItemSelected", l); }
	public static Void onSeekBarChange(SimpleSeekBarChangeListener l) { return attr("onSeekBarChange", l); }
	public static Void anim(boolean trigger, Animator a) { return attr("anim", new AnimatorPair(a, trigger)); }
	public static Void init(Runnable r) {
		return attr("init", r);
	}
	public static Void onTextChanged(SimpleTextWatcher w) { return attr("onTextChanged", w); }
	public static Void onTextChanged(TextWatcher w) { return attr("onTextChanged", w); }
	public static Void text(CharSequence arg) { return attr("text", arg); }

	public static final class ViewClassResult {}

	public static ViewClassResult v(Class<? extends View> c) {
		Anvil.currentMount().iterator.start(c, 0, null);
		return null;
	}

	public static ViewClassResult xml(int layoutId) {
		Anvil.currentMount().iterator.start(null, layoutId, null);
		return null;
	}

	private static Void end() {
		Anvil.currentMount().iterator.end();
		return null;
	}

	public static Void o(ViewClassResult c, Object ...args) { return end(); }

	public static Void v(Class<? extends View> c, Anvil.Renderable r) {
		v(c);
		r.view();
		return end();
	}

	public static Void xml(int layoutId, Anvil.Renderable r) {
		xml(layoutId);
		r.view();
		return end();
	}

	public static <T> Void attr(String name, T value) {
		Anvil.currentMount().iterator.attr(name, value);
		return null;
	}

	public static View withId(int id, Anvil.Renderable r) {
		View v = Anvil.currentView();
		if (v == null) {
			throw new RuntimeException("Anvil.currentView() is null");
		}
		// FIXME: how to mock this for tests?
		v = v.findViewById(id);
		if (v == null) {
			throw new RuntimeException("No view found for ID " + id);
		}
		return Anvil.mount(v, r);
	}

	@Override
	public boolean set(View v, String name, Object value, Object prevValue) {
		switch (name) {
			case "init":
				if (Anvil.get(v, "_initialized") == null) {
					Anvil.set(v, "_initialized", true);
					((Runnable) value).run();
				}
				return true;
			case "anim":
				AnimatorPair a = (AnimatorPair) value;
				if (a.trigger) {
					a.animator.setTarget(v);
					a.animator.start();
				}
				return true;
			case "onSeekBarChange":
				if (v instanceof SeekBar && value instanceof SimpleSeekBarChangeListener) {
					((SeekBar) v).setOnSeekBarChangeListener(
							new SeekBarChangeWrapper((SimpleSeekBarChangeListener) value));
					return true;
				}
				break;
			case "onItemSelected":
				if (v instanceof AdapterView && value instanceof SimpleItemSelectedListener) {
					((AdapterView) v).setOnItemSelectedListener(new ItemSelectedWrapper((SimpleItemSelectedListener) value));
					return true;
				} else if (v instanceof AutoCompleteTextView && value instanceof SimpleItemSelectedListener) {
					((AutoCompleteTextView) v).setOnItemSelectedListener(new ItemSelectedWrapper((SimpleItemSelectedListener) value));
					return true;
				}
				break;
			case "text":
				if (v instanceof TextView && value instanceof CharSequence) {
					if (v != CURRENT_INPUT_TEXT_VIEW) {
						((TextView) v).setText((CharSequence) value);
					}
					return true;
				} else if (v instanceof TextSwitcher && value instanceof CharSequence) {
					((TextSwitcher) v).setText((CharSequence) value);
					return true;
				}
				break;
			case "onTextChanged":
				if (v instanceof TextView && value instanceof SimpleTextWatcher) {
					TextView tv = (TextView) v;
					for (TextWatcherProxy proxy : TEXT_WATCHERS.keySet()) {
						if (proxy.hasImpl(prevValue)) {
							proxy.setImpl((SimpleTextWatcher) value);
							return true;
						}
					}
					TextWatcherProxy proxy = new TextWatcherProxy(tv).setImpl((SimpleTextWatcher) value);
					TEXT_WATCHERS.put(proxy, null);
					tv.addTextChangedListener(proxy);
					return true;
				}
				if (v instanceof TextView && value instanceof TextWatcher) {
					TextView tv = (TextView) v;
					for (TextWatcherProxy proxy : TEXT_WATCHERS.keySet()) {
						if (proxy.hasImpl(prevValue)) {
							proxy.setImpl((TextWatcher) value);
							return true;
						}
					}
					TextWatcherProxy proxy = new TextWatcherProxy(tv).setImpl((TextWatcher) value);
					TEXT_WATCHERS.put(proxy, null);
					tv.addTextChangedListener(proxy);
					return true;
				}
				break;
			case "tag":
				if (value instanceof AbstractMap.SimpleImmutableEntry) {
					AbstractMap.SimpleImmutableEntry p = (AbstractMap.SimpleImmutableEntry) value;
					v.setTag((Integer) p.getKey(), p.getValue());
					return true;
				}
				break;
			case "size":
				if (value instanceof AbstractMap.SimpleImmutableEntry) {
					AbstractMap.SimpleImmutableEntry arg = (AbstractMap.SimpleImmutableEntry) value;
					ViewGroup.LayoutParams p = v.getLayoutParams();
					p.width = (int) arg.getKey();
					p.height = (int) arg.getValue();
					v.setLayoutParams(p);
					return true;
				}
				break;
			case "check":
				if (v instanceof RadioGroup) {
					((RadioGroup) v).check((Integer) value);
					return true;
				}
				break;
			case "shadowLayer":
				if (v instanceof TextView && value instanceof List) {
					List<Number> arg = (List<Number>) value;
					((TextView) v).setShadowLayer(arg.get(0).floatValue(),
							arg.get(1).floatValue(), arg.get(2).floatValue(),
							arg.get(3).intValue());
					return true;
				}
				break;
			case "padding":
				if (value instanceof List) {
					List<Integer> arg = (List<Integer>) value;
					v.setPadding(arg.get(0), arg.get(1), arg.get(2), arg.get(3));
					return true;
				}
				break;
			case "margin":
				if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams && value instanceof List) {
					List<Integer> arg = (List<Integer>) value;
					ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
					mp.leftMargin = arg.get(0);
					mp.topMargin = arg.get(1);
					mp.rightMargin = arg.get(2);
					mp.bottomMargin = arg.get(3);
					v.setLayoutParams(mp);
					return true;
				}
				break;
			case "weight":
				if (v.getLayoutParams() instanceof LinearLayout.LayoutParams && value instanceof Float) {
					((LinearLayout.LayoutParams) v.getLayoutParams()).weight = (float) value;
					return true;
				}
				break;
			case "layoutGravity":
                if (v.getLayoutParams() instanceof LinearLayout.LayoutParams && value instanceof Integer) {
                    ((LinearLayout.LayoutParams) v.getLayoutParams()).gravity = (int) value;
                    return true;
                } else if (v.getLayoutParams() instanceof FrameLayout.LayoutParams && value instanceof Integer) {
                    ((FrameLayout.LayoutParams) v.getLayoutParams()).gravity = (int) value;
                    return true;
                }
                    break;
			case "align":
				if (v.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
					AbstractMap.SimpleImmutableEntry<Integer, Integer> e = (AbstractMap.SimpleImmutableEntry<Integer,Integer>) value;
					((RelativeLayout.LayoutParams) v.getLayoutParams()).addRule(e.getKey(), e.getValue());
					return true;
				}
				break;
			case "textSize":
				if (v instanceof TextView && value instanceof Float) {
					((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, (Float) value);
					return true;
				}
				break;
			case "typeface":
				if (v instanceof TextView && value instanceof String) {
					((TextView) v).setTypeface(Typeface.createFromAsset(v.getContext().getAssets(), (String) value));
					return true;
				}
				break;
			case "styledTypeface":
//	        Typeface resolvedTypeface = null;
//	        if( p.getKey() != null )
//	            resolvedTypeface = Typeface.createFromAsset(v.getContext().getAssets(), p.getKey());
//	        if (v instanceof TextView) {
//	            ((TextView) v).setTypeface(resolvedTypeface, p.getValue());
//	        }
				break;
			case "compoundDrawables":
//			if (v instanceof TextView) {
//				((TextView) v).setCompoundDrawables(arg.get(0), arg.get(1), arg.get(2), arg.get(3));
//			}
				break;
			case "compoundDrawablesWithIntrinsicBounds":
//			if (v instanceof TextView) {
//				((TextView) v).setCompoundDrawablesWithIntrinsicBounds(arg.get(0), arg.get(1),
//																	   arg.get(2), arg.get(3));
				break;
			case "compoundDrawablesWithIntrinsicBoundsResource":
//			if (v instanceof TextView) {
//				((TextView) v).setCompoundDrawablesWithIntrinsicBoundsResource(arg.get(0), arg.get(1),
//																	   arg.get(2), arg.get(3));
//                }
				break;
		}
		return false;
	}
}

