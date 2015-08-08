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
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
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
					if (n != null) {
						n.apply(v);
					}
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
	 * A helper for setting a custom font from the assets
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

	public static AttrNode ref(final RefSet refs, final String name) {
		final List<Object> params = new ArrayList<Object>() {{
			add(refs); add(name);
		}};

		return new SimpleAttrNode(params) {
			public void apply(View v) {
				refs.set(name, v);
			}
		};
	}

	/**
	 * A helper for toggling view visibility
	 * @param visible view visibility flag
	 * @return visibility attribute node
	 */
	public static AttrNode visibility(final boolean visible) {
		return new SimpleAttrNode(visible) {
			public void apply(View v) {
				v.setVisibility(visible ? View.VISIBLE : View.GONE);
			}
		};
	}

	/**
	 * A helper for setting custom tags to the view
	 * @param id tag ID
	 * @param value tag object value
	 * @return tag attribute node
	 */
	public static AttrNode tag(final int id, final Object value) {
		final Pair<Integer, Object> tagPair = new Pair<>(id, value);
		return new SimpleAttrNode(tagPair) {
			public void apply(View v) {
				v.setTag(id, value);
			}
		};
	}

	/**
	 * A helper for setting a shadow layer to the TextView
	 * @param radius blur radius
	 * @param dx x axis offset
	 * @param dy y axis offset
	 * @param color shadow color
	 */
	public static AttrNode shadowLayer(final float radius, final float dx,
			final float dy, final int color) {
		final List<Number> params = new ArrayList<Number>() {{
			add(radius); add(dx); add(dy); add(color);
		}};

		return new SimpleAttrNode(params) {
			public void apply(View v) {
				if (v instanceof TextView) {
					((TextView) v).setShadowLayer(radius, dx, dy, color);
				}
			}
		};
	}

	/**
	 * A helper to listen to TextView changes
	 * @param w Text watcher
	 * @return text watcher attribute node
	 */
	public static AttrNode onTextChanged(final TextWatcher w) {
		return new SimpleAttrNode(w) {
			private int tagId = System.identityHashCode(this.getClass());

			public void apply(View v) {
				if (v instanceof TextView) {
					TextView tv = (TextView) v;
					Object tag = v.getTag(tagId);
					if (tag instanceof TextWatcher) {
						tv.removeTextChangedListener((TextWatcher) tag);
					}
					TextWatcher watcher = new TextWatcher() {
						@Override
						public void afterTextChanged(Editable s) {
							w.afterTextChanged(s);
							Anvil.render();
						}
						@Override
						public void beforeTextChanged(CharSequence s, int from, int n, int after) {
							w.beforeTextChanged(s, from, n, after);
						}
						@Override
						public void onTextChanged(CharSequence s, int from, int before, int count) {
							w.onTextChanged(s, from, before, count);
						}
					};

					tv.setTag(tagId, watcher);
					tv.addTextChangedListener(watcher);
				}
			}
		};
	}

	public interface SimpleItemSelectedListener {
		public void onItemSelected(AdapterView a, View v, int pos, long id);
	}

	public static Nodes.AttrNode onItemSelected(final SimpleItemSelectedListener l) {
		return new SimpleAttrNode(l) {
			public void apply(View v) {
				if (v instanceof AdapterView) {
					((AdapterView) v).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						public void onItemSelected(AdapterView a0, View a1, int a2, long a3) {
							l.onItemSelected(a0, a1, a2, a3);
							Anvil.render();
						}
						public void onNothingSelected(AdapterView a0) {}
					});
				}
				if (v instanceof AutoCompleteTextView) {
					((AutoCompleteTextView) v).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						public void onItemSelected(AdapterView a0, View a1, int a2, long a3) {
							l.onItemSelected(a0, a1, a2, a3);
							Anvil.render();
						}
						public void onNothingSelected(AdapterView a0) {}
					});
				}
			}
		};
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

	/**
	 * A callback interface to be executed whenever a real view is constructed
	 * from the virtual layout and is attached to the parent view group, so that
	 * the real view can be manually configured.
	 */
	public interface ConfigListener {
		public void onConfig(View v);
	}

	/**
	 * A helper to register a listener where you can configure your real view
	 * after it's added to the layout. It can be used to modify view properties
	 * that are not configurable via Anvil helpers. You may keep a reference to
	 * your View instance and reuse it later. Configuration step is helpful for
	 * TextureViews, SurfaceViews etc.
	 * @param listener config listener
	 * @return attribute node that calls config listener whenever the real view
	 * is created
	 */
	public static AttrNode config(final ConfigListener listener) {
		return new SimpleAttrNode(null) {
			private ViewTreeObserver observer(View v) {
				return ((ViewGroup) v.getParent()).getViewTreeObserver();
			}
			public void apply(final View v) {
				observer(v).addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						listener.onConfig(v);
						observer(v).removeGlobalOnLayoutListener(this);
						Anvil.render();
					}
				});
			}
		};
	}
}
