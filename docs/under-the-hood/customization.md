---
layout: page
title: Customization
parent: Under the hood
nav_order: 3
---

# Customization

There are two ways to influence generated code: **quirks** and **custom
code**.

In cases where heuristics filtering out unusable classes or attributes
don't help, one can set up **quirks**. This is a simple dictionary,
which maps qualified names of views to nested dictionary. It in turn
allows for disabling or renaming generated scope or disable generation
of specific attribute. You can check [anvil/build.gradle] for the example
of how this could be used.

As mentioned earlier, the main parts of generated code are scopes and
setter. Both parts can be influenced in some way.

Customization of scopes can be done via extension functions. This keeps
type-safety guaranties while allowing to utilize Kotlin powers.

One can also create custom setter to process attributes added via
extensions. You can use `anvilgen` extension to point machinery to your
setter so it would be initialized along with generated one.
