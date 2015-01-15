# Declarative layouts in Java

Using this approach you can declare your layouts like:

	v(LinearLayout.class,
		orientation(LinearLayout.VERTICAL),
		v(TextView.class,
			text("Hello")),
		v(Button.class,
			text("Click me"),
			onClick(myListener)));

You can use variables, expression and all the power of Java language.

This approach uses no hacks, no code generation or reflection. This also
behaves like in reactive programming - if a variable referred in the layout is
changed - the UI will be adjusted automatically (in most common cases).

For more details see my blog post - http://zserge.com/blog/android-mvx.html
