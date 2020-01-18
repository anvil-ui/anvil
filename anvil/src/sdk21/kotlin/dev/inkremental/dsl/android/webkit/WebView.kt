@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.webkit

import android.webkit.DownloadListener
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.widget.AbsoluteLayoutScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun webView(configure: WebViewScope.() -> Unit = {}) = v<WebView>(configure.bind(WebViewScope))
abstract class WebViewScope : AbsoluteLayoutScope() {
  fun downloadListener(arg: DownloadListener): Unit = attr("downloadListener", arg)
  fun findListener(arg: WebView.FindListener): Unit = attr("findListener", arg)
  fun horizontalScrollbarOverlay(arg: Boolean): Unit = attr("horizontalScrollbarOverlay", arg)
  fun initialScale(arg: Int): Unit = attr("initialScale", arg)
  fun networkAvailable(arg: Boolean): Unit = attr("networkAvailable", arg)
  fun verticalScrollbarOverlay(arg: Boolean): Unit = attr("verticalScrollbarOverlay", arg)
  fun webChromeClient(arg: WebChromeClient): Unit = attr("webChromeClient", arg)
  fun webViewClient(arg: WebViewClient): Unit = attr("webViewClient", arg)
  companion object : WebViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
