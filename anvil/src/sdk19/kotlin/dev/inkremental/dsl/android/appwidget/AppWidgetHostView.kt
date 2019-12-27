@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.appwidget

import android.appwidget.AppWidgetHostView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun appWidgetHostView(configure: AppWidgetHostViewScope.() -> Unit = {}) =
    v<AppWidgetHostView>(configure.bind(AppWidgetHostViewScope))
abstract class AppWidgetHostViewScope : FrameLayoutScope() {
  companion object : AppWidgetHostViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
