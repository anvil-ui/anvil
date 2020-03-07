package dev.inkremental.meta.gradle

import org.gradle.api.NamedDomainObjectContainer

internal fun <T: Any> NamedDomainObjectContainer<T>.maybeCreate(name: String, configureAction: T.() -> Unit): T =
    findByName(name) ?: create(name, configureAction)
