
# Anvil - reactive views for Android

[![Join the chat at https://gitter.im/zserge/anvil](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/zserge/anvil?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Build Status](https://travis-ci.org/zserge/anvil.svg?branch=master)](https://travis-ci.org/zserge/anvil)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23193-brightgreen.svg)](http://androidweekly.net/issues/issue-193)

<br/>

<div>
<img align="left" src="https://raw.githubusercontent.com/zserge/anvil/master/logo/ic_launcher.png" alt="logo" width="96px" height="96px" />
<p>
Anvil is a small Java library for creating reactive user interfaces. Originally inspired by <a href="https://facebook.github.io/react/">React</a>, it suits well as a view layer for <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel">MVVM</a> or <a href="http://redux.js.org/">Redux</a> design patterns.
</p>
</div>

<br/>

## Features

* Super small (4 hand-written classes + 1 generated class)
* Easy to learn (top-level API is only 5 functions)
* Fast (uses no reflection¹)
* Efficient (views are updated lazily, if the data change didn't affect the view - it remains untouched)
* Easy to read declarative syntax
* Java 8 and Kotlin-friendly, but supports Java 6 as well
* XML layouts are supported, too

¹Reflection is still used to inflate views once (standard XML inflater does the
same thing, so no performance loss here).

## Installation

``` gradle
// build.gradle
repositories {
    jcenter()
}
dependencies {
    compile 'co.trikita:anvil-sdk15:0.5.0'
}
```
Anvil comes in multiple builds for different minimal SDK versions:

* anvil-sdk15 (ICS, 99.7% of devices)
* anvil-sdk19 (Kitkat, 94.3% of devices)
* anvil-sdk21 (Lollipop, 82.3% of devices)

API levels 16, 17, 18, 22 or 23 are not added because they had very few
UI-related methods added.

## Examples

Normally you would write your layouts in XMLs, then get your views by their
IDs and set their listeners, finally you would observe your data and modify
view properties as your data changes.

Anvil simplifies most of this boring routine.

First, add a static import that makes it much easier to write your view:

``` java
import static trikita.anvil.DSL.*;
```

Next, declare your layout, assign event listeners and bind data:

``` Java
public int ticktock = 0;
public void onCreate(Bundle b) {
    super.onCreate(b);
    setContentView(new RenderableView(this) {
        @Override
        public void view() {
            linearLayout(() -> {
                size(MATCH, MATCH);
                padding(dip(8));
                orientation(LinearLayout.VERTICAL);

                textView(() -> {
                    size(MATCH, WRAP);
                    text("Tick-tock: " + ticktock);
                });

                button(() -> {
                    size(MATCH, WRAP);
                    text("Close");
                    // Finish current activity when the button is clicked
                    onClick(v -> finish());
                });
            });
        }
    });
}
```

Here we've created a very simple reactive view and added it to our Activity.
We've declared our layout (a LinearLayout with a TextView inside). We've defined
styles (width, height, orientation and padding). We've set OnClickListener to 
the button. We've also bound a variable "ticktock" to the text property inside a TextView.

Next, let's update your views as your data changes:

``` java
ticktock++;
Anvil.render();
```

At this point your layout will be updated and the TextView will contain text
"Tick-Tock: 1" instead of "Tick-Tock: 0". However the only actual view method
being called would be setText().

You should know that there is no need to call Anvil.render() inside your event
listeners, it's already triggered after each UI listener call:

``` java
public void view() {
    linearLayout(() -> {
        textView(() -> {
            text("Clicks: " + numClicks);
        });
        button(() -> {
            text("Click me");
            onClick(v -> {
                numClicks++; // text view will be updated automatically
            });
        });
    });
}
```

## Made with Anvil

[![Talalarmo](https://raw.githubusercontent.com/trikita/talalarmo/master/src/main/res/drawable-xhdpi/ic_launcher.png)](https://github.com/trikita/talalarmo)
[![Slide](https://raw.githubusercontent.com/trikita/slide/master/src/main/res/mipmap-xxhdpi/ic_launcher.png)](https://github.com/trikita/slide)
[![Spot](https://i.imgur.com/Al3sh3Q.png)](https://play.google.com/store/apps/details?id=trikita.spot)
[![Quilt](https://i.imgur.com/rqI02l0.png)](https://play.google.com/store/apps/details?id=trikita.quilt)

You may find more Anvil examples for Java 6, Java 8 and Kotlin at

- [Anvil simple demos in Kotlin](https://github.com/zserge/anvil-kotlin-demos)
- [Anvil more advanced examples in Java or Kotlin](https://github.com/zserge/anvil-examples)

## How it works

No magic. When a renderable object is being constructed there are 3 types of
operations: push view, modify some attribute of the current view, and 
pop view. If you're familiar with
[incremental DOM](http://google.github.io/incremental-dom/#about) - Anvil
follows the same approach.

Pushing a view adds it as a child to the parent view from the top of
the stack. Attribute modification simply sets the given property to
the current view on top of the stack. Pop unwinds the stack.

When you mount this layout (assuming the `name` is "John"):

``` java
linearLayout(() -> {
    textView(() -> {
        text("Hello " + name);
    });
});
```

It does the following sequence of actions:

```
Push LinearLayout (adding it to the root view)
Push TextView (adding it to the linear layout)
Attr text "Hello John" (calling setText of the TextView)
Pop
Pop
```

The only trick is that these actions are cached into a so called "virtual layout" -
a tree-like structure matching the actual layout of views and their properties.

So when you call `Anvil.render()` next time it compares the sequence of
actions with the cache and skips property values if they haven't change. Which means on the next
`Anvil.render()` call _the views will remain untouched_. This caching technique
makes render a very quick operation (having a layout of 100 views, 10
attributes each you can do about 4000 render cycles per second!).

Now, if you modify the `name` from "John" to "Jane" and call Anvil.render() it will do the following:

```
Push LinearLayout (noop)
Push TextView (noop)
Attr text "Hello Jane" (comparing with "Hello John" from the pervious render,
  noticing the difference and calling setText("Hello Jane") to the TextView)
Pop
Pop
```

So if you modify one of the variables "bound" to some of the attributes - the
cache will be missed and attribute will be updated.

For all event listeners a "proxy" is generated, which delegates its
method calls to your actual event listener, but calls `Anvil.render()` after each
method. This is useful, because most of your data models are modified when
the user interacts with the UI, so you write less code without calling
`Anvil.render()` from every listener. Remember, no-op renders are very fast.

## Supported languages and API levels

Anvil is written in Java 7, but its API is designed to use lambdas as well, so
in modern times it's recommended to use Anvil with Java8/Retrolambda or Kotlin.

Syntax is a bit different for each language, but it's very intuitive anyway.

Java 6 without lambdas:

``` java
public void view() {
    o (linearLayout(),
      orientation(LinearLayout.VERTICAL),

        o (textView(),
          text("...")),
        
        o (button(),
          text("..."),
          onClick(myListener)));
}
```

Java 8 + RetroLambda:

``` java
public void view() {
    linearLayout(() -> {
        orientation(LinearLayout.VERTICAL);
        textView(() -> {
            text("....");
        });
        button(() -> {
            text(".....");
            onClick(v -> {
                ....
            });
        });
    });
}
```

Kotlin:

``` kotlin
public override fun view() {
    linearLayout {
        orientation(LinearLayout.VERTICAL)
        textView {
            text("....")
        }
        button {
            text("....")
            onClick { v ->
                ...
            }
        }
    }
}
```

Anvil library contains only a few classes to work with the virtual layout, but most
of the DSL (domain-specific language describing how to create views/layouts and set
their attributes) is generated from `android.jar`.

## API

Here's a list of classes and methods you need to know to work with Anvil like a pro:

* `Anvil.Renderable` - functional interface that one should implement to
    describe layout structure, style and data bindings.

* `Anvil.mount(View, Anvil.Renderable)` - mounts renderable layout into a
    View or a ViewGroup

* `Anvil.unmount(View)` - unmounts renderable layout from the View
    removing all its child views if it's a ViewGroup

* `Anvil.render()` - starts a new render cycle updating all mounted views

* `Anvil.currentView(): View` - returns the view which is currently being
    rendered. Useful in some very rare cases and only inside the Renderable's
    method `view()` to get access to the real view and modifying it manually.

* `RenderableView` - a most typical implementation of Anvil.Renderable.
    Extending this class and overriding its method `view()` allows to create
    self-contained reactive components that are mounted and unmounted
    automatically.

* `RenderableAdapter` - extending this class and overriding its `getCount()`,
    `getItem(int)` and `view(int)` allows to create lists where each item is a
    standalone reactive renderable object.

* `RenderableAdapter.withItems(list, cb(index, item))` - a shortcut to create
    simple adapters for the given list of items. `cb` is a lambda that describes
    the layout and bindings of a certain list item at the given index.

## DSL

The bottom part of the iceberg is Anvil DSL.

DSL consists of a few handwritten property setters, but most of it is
generated from java classes in the android SDK.

See a full list of the DSL methods for each API level [here](https://github.com/zserge/anvil/blob/master/DSL.md).

### Property setters

The setters are named as the view methods from Android SDK, but without the
"set" prefix. E.g. "text(s)" instead of "setText(s)", "backgroundDrawable(d)"
instead of "setBackgroundDrawable(d)" and so on.

### Event listeners

Event listeners also have names from the Android SDK, but without the "set"
prefix and the "Listener" suffix, e.g. "onClick" instead of "setOnClickListener".

### Handwritten bindings

For LayoutParams the bindings can't be generated easily, so it was faster to write them manually:

* `size(width, height)` - set width and height. Special constants like `WRAP`, `FILL`
  and `MATCH` are available.
* `dip(x)` - returns the value in pixels for the dimension in density-independent
  pixels. Often used with size, padding or margin properties.
* `margin(m)`, `margin(h, v)`, `margin(l, t, r, b)` - set view margin for all 4
  sides, for horizontal/vertical dimensions or for each side individually.
* `weight(w)` - modifies view layout weight.
* `layoutGravity(g)` - modifies layout gravity of a view. Common constants like
  `START`, `END`, `CENTER`, `CENTER_VERTICAL`, etc. are available.
* `align(verb, anchor)`, `align(verb)` - base functions for relative layout params.
* `above(id)`, `alignBaseline(id)`, `alignBottom(id)`, `alignEnd(id)`, `alignLeft(id)`, `alignParentBottom()`,
  `alignParentEnd()`, `alignParentLeft()`, `alignParentRight()`, `alignParentStart()`, `alignParentTop()`,
  `alignRight(id)`, `alignTop(id)`, `below(id)`, `centerHorizontal()`, `centerVertical()`, `centerInParent()`,
  `toEndOf(id)`, `toLeftOf(id)`, `toRightOf(id)`, `toStartOf(id)` - all possible settings for relative layout params

A few bindings have been  written for other use cases which we find useful:

* `init(Runnable)` - executes a runnable once, useful to initialize custom views (see also `Anvil.currentView()`).
* `R()` - returns a `Resources` object associated with the current view. Useful for
  multiple screen support (sizes, dpi, orientation etc).
* `isPortrait()` - returns true if a screen is portrait-oriented. Useful for
    tweaking layouts for different orientations.
* `typeface(font)` - loads font from assets by its file name and sets the typeface to
    a TextView
* `padding(p)`, `padding(h, v)`, `padding(l, t, r, b)` - set view padding for all 4
    sides, for horizontal/vertical dimensions or for each side individually.
* `visibility(flag)` - sets the visibility property to `View.VISIBLE` or `View.GONE` depending on
    the `flag` boolean value
* `shadowLayer(radius, dx, dy, color)` - sets shadow layer of a TextView
* `onTextChanged(textWatcher)` - binds a text watcher to an `EditText`. No
    `Anvil.render()` is called in this case, because you're likely to get an infinite
    recursion.
* `text(StringBuilder)` - binds a string builder to the edit text, so when you
    change its contents - the edit text is changed, and if you type something
    manually - the string builder gets modified. So far it's the only two-way
    data binding, becayse TextWatcher is a complicated beast.
* `onItemSelected(lambda)` - accepts a functional interface to handle a `Spinner`
    events. `onNothingSelected()` method is omitted, because it's rarely used anyway.

If a binding you need is not in the list - please, check [issue #27](https://github.com/zserge/anvil/issues/27) and report it there.

A special case for animations is added:

* `anim(trigger, Animator)` - starts animation when `trigger` is true, cancels it
    when the `trigger` becomes false.

Finally, a few low-level DSL functions are there, which you would no need unless you want to write your own property setters or custom view builders:

* `v(class, attrs...)` - pushes view, applies attributes, doesn't pop the view.
* `o()`, `x()` - names that look like bullets, actually pop the view. These are used in Java 6 syntax.
* `v(class, renderable)` - pushes the view, applies the renderable to fulfil
    attributes and child views, pops the view. This is used in Java 8 and Kotlin
    syntax.
* `attr(func, value)` - checks the cache for the given value of the given
    property setter function. Often used to create your own property setter
    binding.

## XML layouts

If you're migrating an existing project to Anvil or if you prefer to keep your
view layouts declared in XML - you can do so:

``` kotlin
public override fun view() {
    // A designer gave us an XML with some fancy layout:
    // a viewgroup with a button and a progress bar in it
    xml(R.layout.my_layout) {
        backgroundColor(Settings.bgColor) // will modify root layout view color

        withId(R.id.my_button) {
            // button state may depend on some variable
            enabled(isMyButtonEnabled)
                // button click listener can be attached
                onClick { v ->
                    ...
                }
        }

        withId(R.id.my_progress_bar) {
            visible(isMyProgressBarShown)
            progress(someProgressValue)
        }
    }
}
```

Here `xml()` creates a view node, much like `frameLayout()` or `textView()`,
except for it uses an XML file to inflate the views, not the direct view class
constructor. Much like view "builders", `xml()` takes a renderable lambda as a
parameter and uses that to modify the created view.

Any attribute setters will affect the root view from the XML layout.

If you want to modify attributes of the child views from the XML - you should
use `withId()` to assign a renderable to a view with the given ID. You may not
follow the hierarchy of the child views, e.g. all your `withId()` calls may be
nested as the views are nested in the XML, or may be direct children of the
`xml()` renderable. `xml()` calls can be nested, too.

`xml()` also allows you to create views that can not be inflated from Java code
but only from XML (such as hostozintal progress bars) or any other views where
AttributeSet must be given or the views that rely on the `onFinishInflate`
method to be called.

`withId()` call is not limited to XML views, it can be used for views declared
in code that have IDs. So if you have a custom viewgroup that creates its child
views automatically and assigns some IDs to them - you can still modify their
properties using `withId()`. Also, if any views are created inside the
`withId()` or `xml()` - they will be appeneded to the view group:

``` java
v(MyComponent.java, () -> {
    withId(R.id.child_view, () -> {
        // R.id.child_view was implicitely created in MyComponent's constructor
        // but we still can modify it here
    });

    textView(() -> {
        // This textView will be appeneded to MyComponent layout
    });
});
```

## License

Code is distributed under MIT license, feel free to use it in your proprietary
projects as well.

