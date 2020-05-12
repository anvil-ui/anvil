---
layout: page
title: Code generation
parent: Under the hood
nav_order: 2
---

# Code generation

***TODO rewrite this describing new meta. Stuff below is outdated.***

The project has two Gradle plugins in `buildSrc`.

* `dev.inkremental.gen` (`dev.inkremental.meta.gradle.InkrementalGenPlugin`)
is the plugin which does heavylifting of DSL generation for views. It configures
generator tasks using parameters noted in corresponding extension,
and `DSLGeneratorTask` is a task which manages the process.

* `dev.inkremental.module` (`dev.inkremental.meta.gradle.InkrementalModulePlugin`)
is the companion plugin which unifies accompanying tasks of setting up
publishing and basic configuration of the project.

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

