package trikita.anvil;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a utility class with some handy attribute generators. It servers as
 * a base class for the generated {@code Attrs} classes for various API levels.
 */
public class BaseAttrs extends Nodes {

	/**
	 * <p>
	 * Returns a compound attribute node. Helpful for common widget style
	 * definitions, e.g:
	 * </p>
	 * <pre>
	 * {@code
	 * public AttrNode headerTextStyle() {
	 *   return attrs(
	 *     textColor(Color.BLACK),
	 *     textSize(24),
	 *     gravity(CENTER)
	 *   );
	 * }
	 * ...
	 * v(TextView.class,
	 *   headerTextStyle(),
	 *   text("Hello"));
	 * }
	 * </pre>
	 * <p>
	 * This is useful for constant nodes. For the attributes that change a lot
	 * better use chaining view node API ({@code addAttrs})
	 * </p>
	 * @param nodes list of attribute nodes
	 * @return a compound node for the given attributes list
	 */
	public static AttrNode attrs(final AttrNode ...nodes) {
		return new SimpleAttrNode(new ArrayList<AttrNode>(Arrays.asList(nodes))) {
			public void apply(View v) {
				for (AttrNode n : nodes) {
					n.apply(v);
				}
			}
		};
	}

	//
	// Some helpers for view resources
	//
	
	/**
	 * Returns resources associated with the component currently being rendered
	 * @return current component resources
	 */
	public static Resources R() {
		return Anvil.getCurrentRenderable().getRootView().getResources();
	}
	
	/** 
	 * Returns current screen orientation
	 * @return true if screen is in portrait orientation, false otherwise
	 */
	public static boolean isPortrait() {
		return R().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	/** 
	 * Converts dip to pixels for the current screen density
	 * @param value density-independent value
	 * @return value in pixels for the given density-independent value
	 */
	public static float dip(float value) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
				R().getDisplayMetrics());
	}

	/**
	 * Converts dip to pixels for the current screen settings. Useful to avoid
	 * casts from float to int in some methods
	 * @param value density-independent value
	 * @return value in pixels for the given density-independent value
	 */
	public static int dip(int value) {
		return Math.round(dip((float) value));
	}

	/**
	 * Returns attribute value in pixels
	 * @param attr attribute ID
	 * @return attribute value in pixels
	 */
	public static int attrPx(int attr) {
		TypedValue tv = attr(attr);
		return Math.round(R().getDimension(tv.resourceId));
	}

	/**
	 * Returns attribute value as a TypedValue
	 * @param attr attribute ID
	 * @return attribute value as a TypedValue
	 */
	public static TypedValue attr(int attr) {
		TypedValue tv = new TypedValue();
		Anvil.getCurrentRenderable().getRootView()
			.getContext().getTheme().resolveAttribute(attr, tv, true);
		return tv;
	}

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

	/**
	 * Creates a new LayoutParam generator chain
	 * @param w width
	 * @param h height
	 * @return layout node
	 */
	public static LayoutNode size(int w, int h) {
		return new LayoutNode(w, h);
	}

	/**
	 * Creates a new LayoutParam generator chain with default width/height
	 * @return layout node
	 */
	public static LayoutNode size() {
		return new LayoutNode();
	}

	/**
	 * A helper for padding when each side padding is the same
	 * @param p padding
	 * @return padding node
	 */
	public static AttrNode padding(int p) {
		return padding(p, p, p, p);
	}

	/**
	 * A helper for padding when padding is the same for each dimension
	 * @param horizontal horizontal padding
	 * @param vertical vertical padding
	 * @return padding node
	 */
	public static AttrNode padding(int horizontal, int vertical) {
		return padding(horizontal, vertical, horizontal, vertical);
	}

	/**
	 * A generic helper for padding
	 * @param left left padding
	 * @param top top padding
	 * @param right right padding
	 * @param bottom bottom padding
	 * @return padding node
	 */
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

	/**
	 * A helper for settings custom font from the assets
	 * @param font font path inside the assets directory
	 * @return typeface attribute node
	 */
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

	private static class TextWatchAttr extends SimpleAttrNode<TextWatcher> {
		private WeakReference<TextView> viewRef;

		public TextWatchAttr(TextWatcher value) {
			super(value);
		}

		public int hashCode() {
			return super.hashCode();
		}

		public boolean equals(Object obj) {
			boolean res = super.equals(obj);
			// We know that if the view has the same class, but different value - 
			// apply() will be called for the new value holder.
			// Which means it's a good place to cleanup the previous value holder
			if (!res && obj instanceof TextWatchAttr) {
				((TextWatchAttr) obj).cleanup();
			}
			return res;
		}

		@Override
		public void apply(View v) {
			if (v instanceof TextView) {
				this.viewRef = new WeakReference<>((TextView) v);
				((TextView) v).addTextChangedListener(this.value);
			}
		}

		private void cleanup() {
			TextView v = this.viewRef.get();
			if (v != null) {
				v.removeTextChangedListener(this.value);
			}
		}
	}

	/**
	 * A helper to listen to TextView changes
	 * @param w Text watcher
	 * @return text watcher attribute node
	 */
	public static AttrNode onTextChanged(final TextWatcher w) {
		return new TextWatchAttr(w);
	}

	/**
	 * A simplified TextWatcher interface when you only need to know the text
	 * being entered
	 */
	public static interface SimpleTextWatcher {
		public void onTextChanged(String s);
	}

	/**
	 * A helper to listen to TextView changes with simplified interface
	 * @param w Simple text watcher with one method (handy for Java 8)
	 * @return text watcher attribute node
	 */
	public static AttrNode onTextChanged(final SimpleTextWatcher w) {
		return onTextChanged(new TextWatcher() {
			@Override
			public void	afterTextChanged(Editable s) {
				w.onTextChanged(s.toString());
			}
			@Override
			public void	beforeTextChanged(CharSequence s, int from, int n, int after) {}
			@Override
			public void	onTextChanged(CharSequence s, int from, int before, int count) {}
		});
	}
}
