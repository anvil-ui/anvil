# Declarative layouts in Java

[![Join the chat at https://gitter.im/zserge/android-virtual-layout](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/zserge/android-virtual-layout?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Using this approach you can declare your layouts like:

```java
	v(LinearLayout.class,
		orientation(LinearLayout.VERTICAL),

		v(TextView.class,
			text(someText)),

		v(Button.class,
			text("Click me"),
			onClick(myListener)));
```

You can use variables, expression and all the power of Java language.

This approach uses no hacks, no code generation or reflection. This also
behaves like in reactive programming - if a variable referred in the layout is
changed - the UI will be adjusted automatically (in most common cases).

For more details see my blog posts:

- http://zserge.com/blog/android-mvx.html
- http://zserge.com/blog/anvil-1.html
