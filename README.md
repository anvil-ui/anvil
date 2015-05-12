# Minimalistic library for reactive Android UI

[![Join the chat at https://gitter.im/zserge/anvil](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/zserge/anvil?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Anvil is a tiny library to simplify android UI development. The name stands for
"android virtual layout".

## The concept for "virtual layout"

Virtual layout is an Android analogy of the virtual DOM in the web.

Instead of inflating and processing the real views, Anvil works with the
virtual tree of nodes which represents the structure of the real layout. These
virtual view nodes are very lightweight and can be
added/removed/compared/iterated faster than the real view hierarchy.

This allows to compare two virtual layout trees on every rendering cycle and
update the views and the attributes that have been changed since last
rendering.

Also, the data flow is now unidirectional. E.g. in your event listeners
(`onClick, onTouch, ...`) you modify your model data. You don't modify your
views at that point. Now, when the model data is changed (from the user
interaction or from the background services) - your UI will be re-rendered and
view properties will be adjusted according to the new model data.

## What about my XMLs, fragments, activities?

Since Anvil manages the view hierarchy for you - you can mix XMLs and Anvil
layouts. But Anvil layout declaration is compact and readable, don't be
scared:

```java
	v(LinearLayout.class,
		orientation(LinearLayout.VERTICAL),

		v(TextView.class,
			text(someText)),

		v(Button.class,
			text("Click me"),
			onClick(myListener)));
```

Good thing is that you now have data-binding! In the example above `onClick()`
binds a click listener to the button. Also a text view will be updated
automatically every time the app changes the value of `someText` variable.

The view hierarchy is normally created inside a Renderable object, which can be
a View, an activity or a fragment, so you may still use those. For convenience,
there is a class `RenderableView` which simplifies some routine work:

```java
// Hosting activity
public class MyActivity extends Activity {
	public void onCreate(Bundle b) {
		super.onCreate(b);
		// you can still inflate MyCoolView from XML, so you may use Anvil
		// only for some parts of your app
		setContentView(new MyCoolView(this));
	}
}

//
// Custom view that updates itself
//
public class MyCoolView extends RenderableView {
	private int mNumberOfClicks = 0;

	public MyCoolView(Context c) {
		super(c);
	}

	public ViewNode view() {
		return
			v(LinearLayout.class,
				orientation(LinearLayout.VERTICAL),
				v(TextView.class,
					text("Clicked " + mNumberOfClicks + " times"),
				v(Button.class,
					onClick((v) => mNumberOfClicks++)));
	}
}
```

Inside the `view()` method you can use variables, expressions and all the power
of Java language. You can put your color constants, dimensions etc into some
theme class, or use them from XML resources as before.

## Documentation

A user-friendly documentation is still in progress, but feel free to help with it - let me know what questions you have about Anvil, what articles about it your would like to read, what issues you met when you first started using Anvil.

JavaDocs can be found [here][1]

For the full list of available generators to bind data and listenters check these classes:

* [API level 10 (Android 2.3)][2]
* [API level 15 (Android 4.0)][3]

Also, for more details see my blog posts about Anvil:

- http://zserge.com/blog/android-mvx.html
- http://zserge.com/blog/anvil-1.html
- http://zserge.com/blog/anvil-2.html
- http://zserge.com/blog/anvil-kotlin.html

1: https://oss.sonatype.org/service/local/repositories/releases/archive/co/trikita/anvil/0.0.6/anvil-0.0.6-javadoc.jar/!/index.html).
3: https://oss.sonatype.org/service/local/repositories/releases/archive/co/trikita/anvil/0.0.6/anvil-0.0.6-javadoc.jar/!/trikita/anvil/v10/Attrs.html
3: https://oss.sonatype.org/service/local/repositories/releases/archive/co/trikita/anvil/0.0.6/anvil-0.0.6-javadoc.jar/!/trikita/anvil/v15/Attrs.html
