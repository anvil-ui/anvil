# Core principles

The main entry point to the library right now is `trikita.anvil.Anvil`
class and it's siblings in `anvil` module. It contains a bunch of
static properties and methods which work together to provide core
functionality.

***TODO***

# Code generation

The project has two Gradle plugins in `buildSrc`.

* `trikita.anvilgen` is the plugin which does heavylifting of DSL
generation for views. `AnvilGenPlugin` configures generator tasks
using parameters noted in corresponding extension, and `DSLGeneratorTask`
is a task which manages the process.

* `dev.inkremental.module` is the companion plugin which unifies accompanying
tasks of setting up publishing and basic configuration of the project.

Let's walk through the process of code generation.

1. Plugin checks the configuration of `anvilgen` extension. Depending on
mentioned type, it configures generator tasks either for Android SDK
or for some Android library. It collects dependencies, sets up classpath
and passes everything to the task.

2. Generator task builds module model via method called `createModel`.
It processes classpath, searching for Android `View` classes, then
collects all attributes, both setters and listeners, using some heuristics
to filter out unprocessable entities.

3. `dumpModel` function outputs filtered model to logs for everyone to
inspect it.

4. `renderModel` takes the model and generates the code. It outputs:
    * DSL function per view which defines new node in the tree.
    * Scope class per view which holds members putting attribute nodes
      to the view node.
    * Setter object with it's huge method, main purpose of which is to
      take tree attribute node and update actual view.
      
5. Attributes can be either setters or listeners. This separation is most
prominent in setter object, which generates code with corresponding
methods, namely `buildSetter` and `buildListener`.

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
