@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package trikita.anvil

import android.app.SearchableInfo
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.PopupWindow
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.ExpandedMenuView
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.ActionBarContainer
import androidx.appcompat.widget.ActionBarOverlayLayout
import androidx.appcompat.widget.ActionMenuView
import androidx.appcompat.widget.ActivityChooserView
import androidx.appcompat.widget.AlertDialogLayout
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.appcompat.widget.ButtonBarLayout
import androidx.appcompat.widget.ContentFrameLayout
import androidx.appcompat.widget.DialogTitle
import androidx.appcompat.widget.FitWindowsFrameLayout
import androidx.appcompat.widget.FitWindowsLinearLayout
import androidx.appcompat.widget.FitWindowsViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.ScrollingTabContainerView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.ViewStubCompat
import androidx.core.text.PrecomputedTextCompat
import androidx.core.view.ActionProvider
import androidx.cursoradapter.widget.CursorAdapter
import kotlin.Any
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Float
import kotlin.Function
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun actionMenuItemView(configure: ActionMenuItemViewScope.() -> Unit = {}) =
    v<ActionMenuItemView>(configure.bind(ActionMenuItemViewScope))
abstract class ActionMenuItemViewScope : AppCompatTextViewScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  fun expandedFormat(arg: Boolean): Unit = attr("expandedFormat", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun itemInvoker(arg: MenuBuilder.ItemInvoker): Unit = attr("itemInvoker", arg)
  fun popupCallback(arg: ActionMenuItemView.PopupCallback): Unit = attr("popupCallback", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  companion object : ActionMenuItemViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun expandedMenuView(configure: ExpandedMenuViewScope.() -> Unit = {}) =
    v<ExpandedMenuView>(configure.bind(ExpandedMenuViewScope))
abstract class ExpandedMenuViewScope : ListViewScope() {
  companion object : ExpandedMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun listMenuItemView(configure: ListMenuItemViewScope.() -> Unit = {}) =
    v<ListMenuItemView>(configure.bind(ListMenuItemViewScope))
abstract class ListMenuItemViewScope : LinearLayoutScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  fun forceShowIcon(arg: Boolean): Unit = attr("forceShowIcon", arg)
  fun groupDividerEnabled(arg: Boolean): Unit = attr("groupDividerEnabled", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  companion object : ListMenuItemViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun actionBarContainer(configure: ActionBarContainerScope.() -> Unit = {}) =
    v<ActionBarContainer>(configure.bind(ActionBarContainerScope))
abstract class ActionBarContainerScope : FrameLayoutScope() {
  fun primaryBackground(arg: Drawable): Unit = attr("primaryBackground", arg)
  fun splitBackground(arg: Drawable): Unit = attr("splitBackground", arg)
  fun stackedBackground(arg: Drawable): Unit = attr("stackedBackground", arg)
  fun tabContainer(arg: ScrollingTabContainerView): Unit = attr("tabContainer", arg)
  fun transitioning(arg: Boolean): Unit = attr("transitioning", arg)
  companion object : ActionBarContainerScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun actionBarOverlayLayout(configure: ActionBarOverlayLayoutScope.() -> Unit = {}) =
    v<ActionBarOverlayLayout>(configure.bind(ActionBarOverlayLayoutScope))
abstract class ActionBarOverlayLayoutScope : ViewGroupScope() {
  fun actionBarHideOffset(arg: Int): Unit = attr("actionBarHideOffset", arg)
  fun actionBarVisibilityCallback(arg: ActionBarOverlayLayout.ActionBarVisibilityCallback): Unit =
      attr("actionBarVisibilityCallback", arg)
  fun hasNonEmbeddedTabs(arg: Boolean): Unit = attr("hasNonEmbeddedTabs", arg)
  fun hideOnContentScrollEnabled(arg: Boolean): Unit = attr("hideOnContentScrollEnabled", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun icon(arg: Int): Unit = attr("icon", arg)
  fun logo(arg: Int): Unit = attr("logo", arg)
  fun overlayMode(arg: Boolean): Unit = attr("overlayMode", arg)
  fun showingForActionMode(arg: Boolean): Unit = attr("showingForActionMode", arg)
  fun uiOptions(arg: Int): Unit = attr("uiOptions", arg)
  fun windowCallback(arg: Window.Callback): Unit = attr("windowCallback", arg)
  fun windowTitle(arg: CharSequence): Unit = attr("windowTitle", arg)
  companion object : ActionBarOverlayLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun actionMenuView(configure: ActionMenuViewScope.() -> Unit = {}) =
    v<ActionMenuView>(configure.bind(ActionMenuViewScope))
abstract class ActionMenuViewScope : LinearLayoutCompatScope() {
  fun expandedActionViewsExclusive(arg: Boolean): Unit = attr("expandedActionViewsExclusive", arg)
  fun onMenuItemClick(arg: ((arg0: MenuItem) -> Boolean)?): Unit = attr("onMenuItemClick", arg)
  fun overflowIcon(arg: Drawable?): Unit = attr("overflowIcon", arg)
  fun overflowReserved(arg: Boolean): Unit = attr("overflowReserved", arg)
  fun popupTheme(arg: Int): Unit = attr("popupTheme", arg)
  companion object : ActionMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun activityChooserView(configure: ActivityChooserViewScope.() -> Unit = {}) =
    v<ActivityChooserView>(configure.bind(ActivityChooserViewScope))
abstract class ActivityChooserViewScope : ViewGroupScope() {
  fun defaultActionButtonContentDescription(arg: Int): Unit =
      attr("defaultActionButtonContentDescription", arg)
  fun expandActivityOverflowButtonContentDescription(arg: Int): Unit =
      attr("expandActivityOverflowButtonContentDescription", arg)
  fun expandActivityOverflowButtonDrawable(arg: Drawable): Unit =
      attr("expandActivityOverflowButtonDrawable", arg)
  fun initialActivityCount(arg: Int): Unit = attr("initialActivityCount", arg)
  fun onDismiss(arg: (() -> Unit)?): Unit = attr("onDismiss", arg)
  fun provider(arg: ActionProvider): Unit = attr("provider", arg)
  companion object : ActivityChooserViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun alertDialogLayout(configure: AlertDialogLayoutScope.() -> Unit = {}) =
    v<AlertDialogLayout>(configure.bind(AlertDialogLayoutScope))
abstract class AlertDialogLayoutScope : LinearLayoutCompatScope() {
  companion object : AlertDialogLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatAutoCompleteTextView(configure: AppCompatAutoCompleteTextViewScope.() -> Unit = {}) =
    v<AppCompatAutoCompleteTextView>(configure.bind(AppCompatAutoCompleteTextViewScope))
abstract class AppCompatAutoCompleteTextViewScope : AutoCompleteTextViewScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  companion object : AppCompatAutoCompleteTextViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatButton(configure: AppCompatButtonScope.() -> Unit = {}) =
    v<AppCompatButton>(configure.bind(AppCompatButtonScope))
abstract class AppCompatButtonScope : ButtonScope() {
  fun supportAllCaps(arg: Boolean): Unit = attr("supportAllCaps", arg)
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  companion object : AppCompatButtonScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatCheckBox(configure: AppCompatCheckBoxScope.() -> Unit = {}) =
    v<AppCompatCheckBox>(configure.bind(AppCompatCheckBoxScope))
abstract class AppCompatCheckBoxScope : CheckBoxScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportButtonTintList(arg: ColorStateList?): Unit = attr("supportButtonTintList", arg)
  fun supportButtonTintMode(arg: PorterDuff.Mode?): Unit = attr("supportButtonTintMode", arg)
  companion object : AppCompatCheckBoxScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatCheckedTextView(configure: AppCompatCheckedTextViewScope.() -> Unit = {}) =
    v<AppCompatCheckedTextView>(configure.bind(AppCompatCheckedTextViewScope))
abstract class AppCompatCheckedTextViewScope : CheckedTextViewScope() {
  companion object : AppCompatCheckedTextViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatEditText(configure: AppCompatEditTextScope.() -> Unit = {}) =
    v<AppCompatEditText>(configure.bind(AppCompatEditTextScope))
abstract class AppCompatEditTextScope : EditTextScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  companion object : AppCompatEditTextScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatImageButton(configure: AppCompatImageButtonScope.() -> Unit = {}) =
    v<AppCompatImageButton>(configure.bind(AppCompatImageButtonScope))
abstract class AppCompatImageButtonScope : ImageButtonScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportImageTintList(arg: ColorStateList?): Unit = attr("supportImageTintList", arg)
  fun supportImageTintMode(arg: PorterDuff.Mode?): Unit = attr("supportImageTintMode", arg)
  companion object : AppCompatImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatImageView(configure: AppCompatImageViewScope.() -> Unit = {}) =
    v<AppCompatImageView>(configure.bind(AppCompatImageViewScope))
abstract class AppCompatImageViewScope : ImageViewScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportImageTintList(arg: ColorStateList?): Unit = attr("supportImageTintList", arg)
  fun supportImageTintMode(arg: PorterDuff.Mode?): Unit = attr("supportImageTintMode", arg)
  companion object : AppCompatImageViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatMultiAutoCompleteTextView(configure: AppCompatMultiAutoCompleteTextViewScope.() -> Unit
    = {}) =
    v<AppCompatMultiAutoCompleteTextView>(configure.bind(AppCompatMultiAutoCompleteTextViewScope))
abstract class AppCompatMultiAutoCompleteTextViewScope : MultiAutoCompleteTextViewScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  companion object : AppCompatMultiAutoCompleteTextViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatRadioButton(configure: AppCompatRadioButtonScope.() -> Unit = {}) =
    v<AppCompatRadioButton>(configure.bind(AppCompatRadioButtonScope))
abstract class AppCompatRadioButtonScope : RadioButtonScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportButtonTintList(arg: ColorStateList?): Unit = attr("supportButtonTintList", arg)
  fun supportButtonTintMode(arg: PorterDuff.Mode?): Unit = attr("supportButtonTintMode", arg)
  companion object : AppCompatRadioButtonScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatRatingBar(configure: AppCompatRatingBarScope.() -> Unit = {}) =
    v<AppCompatRatingBar>(configure.bind(AppCompatRatingBarScope))
abstract class AppCompatRatingBarScope : RatingBarScope() {
  companion object : AppCompatRatingBarScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatSeekBar(configure: AppCompatSeekBarScope.() -> Unit = {}) =
    v<AppCompatSeekBar>(configure.bind(AppCompatSeekBarScope))
abstract class AppCompatSeekBarScope : SeekBarScope() {
  companion object : AppCompatSeekBarScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatSpinner(configure: AppCompatSpinnerScope.() -> Unit = {}) =
    v<AppCompatSpinner>(configure.bind(AppCompatSpinnerScope))
abstract class AppCompatSpinnerScope : SpinnerScope() {
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  companion object : AppCompatSpinnerScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatTextView(configure: AppCompatTextViewScope.() -> Unit = {}) =
    v<AppCompatTextView>(configure.bind(AppCompatTextViewScope))
abstract class AppCompatTextViewScope : TextViewScope() {
  fun precomputedText(arg: PrecomputedTextCompat): Unit = attr("precomputedText", arg)
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportCompoundDrawablesTintList(arg: ColorStateList?): Unit =
      attr("supportCompoundDrawablesTintList", arg)
  fun supportCompoundDrawablesTintMode(arg: PorterDuff.Mode?): Unit =
      attr("supportCompoundDrawablesTintMode", arg)
  fun textMetricsParamsCompat(arg: PrecomputedTextCompat.Params): Unit =
      attr("textMetricsParamsCompat", arg)
  companion object : AppCompatTextViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun appCompatToggleButton(configure: AppCompatToggleButtonScope.() -> Unit = {}) =
    v<AppCompatToggleButton>(configure.bind(AppCompatToggleButtonScope))
abstract class AppCompatToggleButtonScope : ToggleButtonScope() {
  companion object : AppCompatToggleButtonScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun buttonBarLayout(configure: ButtonBarLayoutScope.() -> Unit = {}) =
    v<ButtonBarLayout>(configure.bind(ButtonBarLayoutScope))
abstract class ButtonBarLayoutScope : LinearLayoutScope() {
  fun allowStacking(arg: Boolean): Unit = attr("allowStacking", arg)
  companion object : ButtonBarLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun contentFrameLayout(configure: ContentFrameLayoutScope.() -> Unit = {}) =
    v<ContentFrameLayout>(configure.bind(ContentFrameLayoutScope))
abstract class ContentFrameLayoutScope : FrameLayoutScope() {
  companion object : ContentFrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun dialogTitle(configure: DialogTitleScope.() -> Unit = {}) =
    v<DialogTitle>(configure.bind(DialogTitleScope))
abstract class DialogTitleScope : AppCompatTextViewScope() {
  companion object : DialogTitleScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun fitWindowsFrameLayout(configure: FitWindowsFrameLayoutScope.() -> Unit = {}) =
    v<FitWindowsFrameLayout>(configure.bind(FitWindowsFrameLayoutScope))
abstract class FitWindowsFrameLayoutScope : FrameLayoutScope() {
  fun onFitSystemWindows(arg: ((arg0: Rect) -> Unit)?): Unit = attr("onFitSystemWindows", arg)
  companion object : FitWindowsFrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun fitWindowsLinearLayout(configure: FitWindowsLinearLayoutScope.() -> Unit = {}) =
    v<FitWindowsLinearLayout>(configure.bind(FitWindowsLinearLayoutScope))
abstract class FitWindowsLinearLayoutScope : LinearLayoutScope() {
  fun onFitSystemWindows(arg: ((arg0: Rect) -> Unit)?): Unit = attr("onFitSystemWindows", arg)
  companion object : FitWindowsLinearLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun linearLayoutCompat(configure: LinearLayoutCompatScope.() -> Unit = {}) =
    v<LinearLayoutCompat>(configure.bind(LinearLayoutCompatScope))
abstract class LinearLayoutCompatScope : ViewGroupScope() {
  fun baselineAligned(arg: Boolean): Unit = attr("baselineAligned", arg)
  fun baselineAlignedChildIndex(arg: Int): Unit = attr("baselineAlignedChildIndex", arg)
  fun dividerDrawable(arg: Drawable): Unit = attr("dividerDrawable", arg)
  fun dividerPadding(arg: Int): Unit = attr("dividerPadding", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalGravity(arg: Int): Unit = attr("horizontalGravity", arg)
  fun measureWithLargestChildEnabled(arg: Boolean): Unit = attr("measureWithLargestChildEnabled",
      arg)
  fun orientation(arg: Int): Unit = attr("orientation", arg)
  fun showDividers(arg: Int): Unit = attr("showDividers", arg)
  fun verticalGravity(arg: Int): Unit = attr("verticalGravity", arg)
  fun weightSum(arg: Float): Unit = attr("weightSum", arg)
  companion object : LinearLayoutCompatScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun scrollingTabContainerView(configure: ScrollingTabContainerViewScope.() -> Unit = {}) =
    v<ScrollingTabContainerView>(configure.bind(ScrollingTabContainerViewScope))
abstract class ScrollingTabContainerViewScope : HorizontalScrollViewScope() {
  fun allowCollapse(arg: Boolean): Unit = attr("allowCollapse", arg)
  fun contentHeight(arg: Int): Unit = attr("contentHeight", arg)
  fun tabSelected(arg: Int): Unit = attr("tabSelected", arg)
  companion object : ScrollingTabContainerViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun searchView(configure: SearchViewScope.() -> Unit = {}) =
    v<SearchView>(configure.bind(SearchViewScope))
abstract class SearchViewScope : LinearLayoutCompatScope() {
  fun appSearchData(arg: Bundle): Unit = attr("appSearchData", arg)
  fun iconified(arg: Boolean): Unit = attr("iconified", arg)
  fun iconifiedByDefault(arg: Boolean): Unit = attr("iconifiedByDefault", arg)
  fun imeOptions(arg: Int): Unit = attr("imeOptions", arg)
  fun inputType(arg: Int): Unit = attr("inputType", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun onClose(arg: (() -> Boolean)?): Unit = attr("onClose", arg)
  fun onQueryTextFocusChange(arg: ((arg0: View, arg1: Boolean) -> Unit)?): Unit =
      attr("onQueryTextFocusChange", arg)
  fun onQueryText(arg: SearchView.OnQueryTextListener?): Unit = attr("onQueryText", arg)
  fun onSearchClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onSearchClick", arg)
  fun onSuggestion(arg: SearchView.OnSuggestionListener?): Unit = attr("onSuggestion", arg)
  fun queryHint(arg: CharSequence?): Unit = attr("queryHint", arg)
  fun queryRefinementEnabled(arg: Boolean): Unit = attr("queryRefinementEnabled", arg)
  fun searchableInfo(arg: SearchableInfo): Unit = attr("searchableInfo", arg)
  fun submitButtonEnabled(arg: Boolean): Unit = attr("submitButtonEnabled", arg)
  fun suggestionsAdapter(arg: CursorAdapter): Unit = attr("suggestionsAdapter", arg)
  companion object : SearchViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun switchCompat(configure: SwitchCompatScope.() -> Unit = {}) =
    v<SwitchCompat>(configure.bind(SwitchCompatScope))
abstract class SwitchCompatScope : CompoundButtonScope() {
  fun showText(arg: Boolean): Unit = attr("showText", arg)
  fun splitTrack(arg: Boolean): Unit = attr("splitTrack", arg)
  fun switchMinWidth(arg: Int): Unit = attr("switchMinWidth", arg)
  fun switchPadding(arg: Int): Unit = attr("switchPadding", arg)
  fun switchTypeface(arg: Typeface): Unit = attr("switchTypeface", arg)
  fun textOff(arg: CharSequence): Unit = attr("textOff", arg)
  fun textOn(arg: CharSequence): Unit = attr("textOn", arg)
  fun thumbDrawable(arg: Drawable): Unit = attr("thumbDrawable", arg)
  fun thumbResource(arg: Int): Unit = attr("thumbResource", arg)
  fun thumbTextPadding(arg: Int): Unit = attr("thumbTextPadding", arg)
  fun thumbTintList(arg: ColorStateList?): Unit = attr("thumbTintList", arg)
  fun thumbTintMode(arg: PorterDuff.Mode?): Unit = attr("thumbTintMode", arg)
  fun trackDrawable(arg: Drawable): Unit = attr("trackDrawable", arg)
  fun trackResource(arg: Int): Unit = attr("trackResource", arg)
  fun trackTintList(arg: ColorStateList?): Unit = attr("trackTintList", arg)
  fun trackTintMode(arg: PorterDuff.Mode?): Unit = attr("trackTintMode", arg)
  companion object : SwitchCompatScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun toolbar(configure: ToolbarScope.() -> Unit = {}) = v<Toolbar>(configure.bind(ToolbarScope))
abstract class ToolbarScope : ViewGroupScope() {
  fun collapseContentDescription(arg: Int): Unit = attr("collapseContentDescription", arg)
  fun collapseContentDescription(arg: CharSequence?): Unit = attr("collapseContentDescription", arg)
  fun collapseIcon(arg: Drawable?): Unit = attr("collapseIcon", arg)
  fun collapseIcon(arg: Int): Unit = attr("collapseIcon", arg)
  fun collapsible(arg: Boolean): Unit = attr("collapsible", arg)
  fun contentInsetEndWithActions(arg: Int): Unit = attr("contentInsetEndWithActions", arg)
  fun contentInsetStartWithNavigation(arg: Int): Unit = attr("contentInsetStartWithNavigation", arg)
  fun logo(arg: Int): Unit = attr("logo", arg)
  fun logo(arg: Drawable): Unit = attr("logo", arg)
  fun logoDescription(arg: Int): Unit = attr("logoDescription", arg)
  fun logoDescription(arg: CharSequence): Unit = attr("logoDescription", arg)
  fun navigationContentDescription(arg: Int): Unit = attr("navigationContentDescription", arg)
  fun navigationContentDescription(arg: CharSequence?): Unit = attr("navigationContentDescription",
      arg)
  fun navigationIcon(arg: Drawable?): Unit = attr("navigationIcon", arg)
  fun navigationIcon(arg: Int): Unit = attr("navigationIcon", arg)
  fun navigationOnClickListener(arg: View.OnClickListener): Unit = attr("navigationOnClickListener",
      arg)
  fun onMenuItemClick(arg: ((arg0: MenuItem) -> Boolean)?): Unit = attr("onMenuItemClick", arg)
  fun overflowIcon(arg: Drawable?): Unit = attr("overflowIcon", arg)
  fun popupTheme(arg: Int): Unit = attr("popupTheme", arg)
  fun subtitle(arg: Int): Unit = attr("subtitle", arg)
  fun subtitle(arg: CharSequence): Unit = attr("subtitle", arg)
  fun subtitleTextColor(arg: ColorStateList): Unit = attr("subtitleTextColor", arg)
  fun subtitleTextColor(arg: Int): Unit = attr("subtitleTextColor", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  fun title(arg: Int): Unit = attr("title", arg)
  fun titleMarginBottom(arg: Int): Unit = attr("titleMarginBottom", arg)
  fun titleMarginEnd(arg: Int): Unit = attr("titleMarginEnd", arg)
  fun titleMarginStart(arg: Int): Unit = attr("titleMarginStart", arg)
  fun titleMarginTop(arg: Int): Unit = attr("titleMarginTop", arg)
  fun titleTextColor(arg: ColorStateList): Unit = attr("titleTextColor", arg)
  fun titleTextColor(arg: Int): Unit = attr("titleTextColor", arg)
  companion object : ToolbarScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

fun viewStubCompat(configure: ViewStubCompatScope.() -> Unit = {}) =
    v<ViewStubCompat>(configure.bind(ViewStubCompatScope))
abstract class ViewStubCompatScope : ViewScope() {
  fun inflatedId(arg: Int): Unit = attr("inflatedId", arg)
  fun layoutInflater(arg: LayoutInflater): Unit = attr("layoutInflater", arg)
  fun layoutResource(arg: Int): Unit = attr("layoutResource", arg)
  fun onInflate(arg: ((arg0: ViewStubCompat, arg1: View) -> Unit)?): Unit = attr("onInflate", arg)
  companion object : ViewStubCompatScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)}
  }
}

/**
 * DSL for creating views and settings their attributes.
 * This file has been generated by
 * {@code gradle generateAppCompatv7DSL}
 * It contains views and their setters from the library appcompat-v7.
 * Please, don't edit it manually unless for debugging.
 */
object AppCompatv7Setter : Anvil.AttributeSetter<Any?> {
  init {
    Anvil.registerAttributeSetter(this)
    Anvil.registerAttributeSetter(AppcompatV7DslSetter)
  }

  override fun set(
    v: View,
    name: String,
    arg: Any?,
    old: Any?
  ): Boolean = when (name) {
    "checkable" -> when {
      v is ActionMenuItemView && arg is Boolean -> {
        v.setCheckable(arg)
        true
      }
      v is ListMenuItemView && arg is Boolean -> {
        v.setCheckable(arg)
        true
      }
      else -> false
    }
    "checked" -> when {
      v is ActionMenuItemView && arg is Boolean -> {
        v.setChecked(arg)
        true
      }
      v is ListMenuItemView && arg is Boolean -> {
        v.setChecked(arg)
        true
      }
      else -> false
    }
    "expandedFormat" -> when {
      v is ActionMenuItemView && arg is Boolean -> {
        v.setExpandedFormat(arg)
        true
      }
      else -> false
    }
    "icon" -> when {
      v is ActionMenuItemView && arg is Drawable -> {
        v.setIcon(arg)
        true
      }
      v is ListMenuItemView && arg is Drawable -> {
        v.setIcon(arg)
        true
      }
      v is ActionBarOverlayLayout && arg is Drawable -> {
        v.setIcon(arg)
        true
      }
      v is ActionBarOverlayLayout && arg is Int -> {
        v.setIcon(arg)
        true
      }
      else -> false
    }
    "itemInvoker" -> when {
      v is ActionMenuItemView && arg is MenuBuilder.ItemInvoker -> {
        v.setItemInvoker(arg)
        true
      }
      else -> false
    }
    "popupCallback" -> when {
      v is ActionMenuItemView && arg is ActionMenuItemView.PopupCallback -> {
        v.setPopupCallback(arg)
        true
      }
      else -> false
    }
    "title" -> when {
      v is ActionMenuItemView && arg is CharSequence -> {
        v.setTitle(arg)
        true
      }
      v is ListMenuItemView && arg is CharSequence -> {
        v.setTitle(arg)
        true
      }
      v is Toolbar && arg is CharSequence -> {
        v.setTitle(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setTitle(arg)
        true
      }
      else -> false
    }
    "forceShowIcon" -> when {
      v is ListMenuItemView && arg is Boolean -> {
        v.setForceShowIcon(arg)
        true
      }
      else -> false
    }
    "groupDividerEnabled" -> when {
      v is ListMenuItemView && arg is Boolean -> {
        v.setGroupDividerEnabled(arg)
        true
      }
      else -> false
    }
    "primaryBackground" -> when {
      v is ActionBarContainer && arg is Drawable -> {
        v.setPrimaryBackground(arg)
        true
      }
      else -> false
    }
    "splitBackground" -> when {
      v is ActionBarContainer && arg is Drawable -> {
        v.setSplitBackground(arg)
        true
      }
      else -> false
    }
    "stackedBackground" -> when {
      v is ActionBarContainer && arg is Drawable -> {
        v.setStackedBackground(arg)
        true
      }
      else -> false
    }
    "tabContainer" -> when {
      v is ActionBarContainer && arg is ScrollingTabContainerView -> {
        v.setTabContainer(arg)
        true
      }
      else -> false
    }
    "transitioning" -> when {
      v is ActionBarContainer && arg is Boolean -> {
        v.setTransitioning(arg)
        true
      }
      else -> false
    }
    "actionBarHideOffset" -> when {
      v is ActionBarOverlayLayout && arg is Int -> {
        v.setActionBarHideOffset(arg)
        true
      }
      else -> false
    }
    "actionBarVisibilityCallback" -> when {
      v is ActionBarOverlayLayout && arg is ActionBarOverlayLayout.ActionBarVisibilityCallback -> {
        v.setActionBarVisibilityCallback(arg)
        true
      }
      else -> false
    }
    "hasNonEmbeddedTabs" -> when {
      v is ActionBarOverlayLayout && arg is Boolean -> {
        v.setHasNonEmbeddedTabs(arg)
        true
      }
      else -> false
    }
    "hideOnContentScrollEnabled" -> when {
      v is ActionBarOverlayLayout && arg is Boolean -> {
        v.setHideOnContentScrollEnabled(arg)
        true
      }
      else -> false
    }
    "logo" -> when {
      v is Toolbar && arg is Drawable -> {
        v.setLogo(arg)
        true
      }
      v is ActionBarOverlayLayout && arg is Int -> {
        v.setLogo(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setLogo(arg)
        true
      }
      else -> false
    }
    "overlayMode" -> when {
      v is ActionBarOverlayLayout && arg is Boolean -> {
        v.setOverlayMode(arg)
        true
      }
      else -> false
    }
    "showingForActionMode" -> when {
      v is ActionBarOverlayLayout && arg is Boolean -> {
        v.setShowingForActionMode(arg)
        true
      }
      else -> false
    }
    "uiOptions" -> when {
      v is ActionBarOverlayLayout && arg is Int -> {
        v.setUiOptions(arg)
        true
      }
      else -> false
    }
    "windowCallback" -> when {
      v is ActionBarOverlayLayout && arg is Window.Callback -> {
        v.setWindowCallback(arg)
        true
      }
      else -> false
    }
    "windowTitle" -> when {
      v is ActionBarOverlayLayout && arg is CharSequence -> {
        v.setWindowTitle(arg)
        true
      }
      else -> false
    }
    "expandedActionViewsExclusive" -> when {
      v is ActionMenuView && arg is Boolean -> {
        v.setExpandedActionViewsExclusive(arg)
        true
      }
      else -> false
    }
    "onMenuItemClick" -> when {
      v is ActionMenuView -> when {
        arg == null -> {
          v.setOnMenuItemClickListener(null as? ActionMenuView.OnMenuItemClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: MenuItem) -> Boolean
          v.setOnMenuItemClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      v is Toolbar -> when {
        arg == null -> {
          v.setOnMenuItemClickListener(null as? Toolbar.OnMenuItemClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: MenuItem) -> Boolean
          v.setOnMenuItemClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "overflowIcon" -> when {
      v is ActionMenuView && (arg == null || arg is Drawable) -> {
        v.setOverflowIcon(arg as Drawable)
        true
      }
      v is Toolbar && (arg == null || arg is Drawable) -> {
        v.setOverflowIcon(arg as Drawable)
        true
      }
      else -> false
    }
    "overflowReserved" -> when {
      v is ActionMenuView && arg is Boolean -> {
        v.setOverflowReserved(arg)
        true
      }
      else -> false
    }
    "popupTheme" -> when {
      v is ActionMenuView && arg is Int -> {
        v.setPopupTheme(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setPopupTheme(arg)
        true
      }
      else -> false
    }
    "defaultActionButtonContentDescription" -> when {
      v is ActivityChooserView && arg is Int -> {
        v.setDefaultActionButtonContentDescription(arg)
        true
      }
      else -> false
    }
    "expandActivityOverflowButtonContentDescription" -> when {
      v is ActivityChooserView && arg is Int -> {
        v.setExpandActivityOverflowButtonContentDescription(arg)
        true
      }
      else -> false
    }
    "expandActivityOverflowButtonDrawable" -> when {
      v is ActivityChooserView && arg is Drawable -> {
        v.setExpandActivityOverflowButtonDrawable(arg)
        true
      }
      else -> false
    }
    "initialActivityCount" -> when {
      v is ActivityChooserView && arg is Int -> {
        v.setInitialActivityCount(arg)
        true
      }
      else -> false
    }
    "onDismiss" -> when {
      v is ActivityChooserView -> when {
        arg == null -> {
          v.setOnDismissListener(null as? PopupWindow.OnDismissListener?)
          true
        }
        arg is Function<*> -> {
          arg as () -> Unit
          v.setOnDismissListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "provider" -> when {
      v is ActivityChooserView && arg is ActionProvider -> {
        v.setProvider(arg)
        true
      }
      else -> false
    }
    "supportBackgroundTintList" -> when {
      v is AppCompatAutoCompleteTextView && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatButton && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatCheckBox && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatEditText && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatImageButton && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatImageView && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatMultiAutoCompleteTextView && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatRadioButton && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatSpinner && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      v is AppCompatTextView && (arg == null || arg is ColorStateList) -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "supportBackgroundTintMode" -> when {
      v is AppCompatAutoCompleteTextView && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatButton && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatCheckBox && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatEditText && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatImageButton && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatImageView && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatMultiAutoCompleteTextView && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatRadioButton && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatSpinner && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatTextView && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "supportAllCaps" -> when {
      v is AppCompatButton && arg is Boolean -> {
        v.setSupportAllCaps(arg)
        true
      }
      else -> false
    }
    "supportButtonTintList" -> when {
      v is AppCompatCheckBox && (arg == null || arg is ColorStateList) -> {
        v.setSupportButtonTintList(arg as ColorStateList)
        true
      }
      v is AppCompatRadioButton && (arg == null || arg is ColorStateList) -> {
        v.setSupportButtonTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "supportButtonTintMode" -> when {
      v is AppCompatCheckBox && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportButtonTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatRadioButton && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportButtonTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "supportImageTintList" -> when {
      v is AppCompatImageButton && (arg == null || arg is ColorStateList) -> {
        v.setSupportImageTintList(arg as ColorStateList)
        true
      }
      v is AppCompatImageView && (arg == null || arg is ColorStateList) -> {
        v.setSupportImageTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "supportImageTintMode" -> when {
      v is AppCompatImageButton && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportImageTintMode(arg as PorterDuff.Mode)
        true
      }
      v is AppCompatImageView && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportImageTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "precomputedText" -> when {
      v is AppCompatTextView && arg is PrecomputedTextCompat -> {
        v.setPrecomputedText(arg)
        true
      }
      else -> false
    }
    "supportCompoundDrawablesTintList" -> when {
      v is AppCompatTextView && (arg == null || arg is ColorStateList) -> {
        v.setSupportCompoundDrawablesTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "supportCompoundDrawablesTintMode" -> when {
      v is AppCompatTextView && (arg == null || arg is PorterDuff.Mode) -> {
        v.setSupportCompoundDrawablesTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "textMetricsParamsCompat" -> when {
      v is AppCompatTextView && arg is PrecomputedTextCompat.Params -> {
        v.setTextMetricsParamsCompat(arg)
        true
      }
      else -> false
    }
    "allowStacking" -> when {
      v is ButtonBarLayout && arg is Boolean -> {
        v.setAllowStacking(arg)
        true
      }
      else -> false
    }
    "onFitSystemWindows" -> when {
      v is FitWindowsFrameLayout -> when {
        arg == null -> {
          v.setOnFitSystemWindowsListener(null as? FitWindowsViewGroup.OnFitSystemWindowsListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: Rect) -> Unit
          v.setOnFitSystemWindowsListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      v is FitWindowsLinearLayout -> when {
        arg == null -> {
          v.setOnFitSystemWindowsListener(null as? FitWindowsViewGroup.OnFitSystemWindowsListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: Rect) -> Unit
          v.setOnFitSystemWindowsListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "baselineAligned" -> when {
      v is LinearLayoutCompat && arg is Boolean -> {
        v.setBaselineAligned(arg)
        true
      }
      else -> false
    }
    "baselineAlignedChildIndex" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setBaselineAlignedChildIndex(arg)
        true
      }
      else -> false
    }
    "dividerDrawable" -> when {
      v is LinearLayoutCompat && arg is Drawable -> {
        v.setDividerDrawable(arg)
        true
      }
      else -> false
    }
    "dividerPadding" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setDividerPadding(arg)
        true
      }
      else -> false
    }
    "gravity" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setGravity(arg)
        true
      }
      else -> false
    }
    "horizontalGravity" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setHorizontalGravity(arg)
        true
      }
      else -> false
    }
    "measureWithLargestChildEnabled" -> when {
      v is LinearLayoutCompat && arg is Boolean -> {
        v.setMeasureWithLargestChildEnabled(arg)
        true
      }
      else -> false
    }
    "orientation" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setOrientation(arg)
        true
      }
      else -> false
    }
    "showDividers" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setShowDividers(arg)
        true
      }
      else -> false
    }
    "verticalGravity" -> when {
      v is LinearLayoutCompat && arg is Int -> {
        v.setVerticalGravity(arg)
        true
      }
      else -> false
    }
    "weightSum" -> when {
      v is LinearLayoutCompat && arg is Float -> {
        v.setWeightSum(arg)
        true
      }
      else -> false
    }
    "allowCollapse" -> when {
      v is ScrollingTabContainerView && arg is Boolean -> {
        v.setAllowCollapse(arg)
        true
      }
      else -> false
    }
    "contentHeight" -> when {
      v is ScrollingTabContainerView && arg is Int -> {
        v.setContentHeight(arg)
        true
      }
      else -> false
    }
    "tabSelected" -> when {
      v is ScrollingTabContainerView && arg is Int -> {
        v.setTabSelected(arg)
        true
      }
      else -> false
    }
    "appSearchData" -> when {
      v is SearchView && arg is Bundle -> {
        v.setAppSearchData(arg)
        true
      }
      else -> false
    }
    "iconified" -> when {
      v is SearchView && arg is Boolean -> {
        v.setIconified(arg)
        true
      }
      else -> false
    }
    "iconifiedByDefault" -> when {
      v is SearchView && arg is Boolean -> {
        v.setIconifiedByDefault(arg)
        true
      }
      else -> false
    }
    "imeOptions" -> when {
      v is SearchView && arg is Int -> {
        v.setImeOptions(arg)
        true
      }
      else -> false
    }
    "inputType" -> when {
      v is SearchView && arg is Int -> {
        v.setInputType(arg)
        true
      }
      else -> false
    }
    "maxWidth" -> when {
      v is SearchView && arg is Int -> {
        v.setMaxWidth(arg)
        true
      }
      else -> false
    }
    "onClose" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnCloseListener(null as? SearchView.OnCloseListener?)
          true
        }
        arg is Function<*> -> {
          arg as () -> Boolean
          v.setOnCloseListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onQueryTextFocusChange" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnQueryTextFocusChangeListener(null as? View.OnFocusChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: View, arg1: Boolean) -> Unit
          v.setOnQueryTextFocusChangeListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onQueryText" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnQueryTextListener(null as? SearchView.OnQueryTextListener?)
          true
        }
        arg is SearchView.OnQueryTextListener -> {
          v.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(arg0: String): Boolean =
                arg.onQueryTextChange(arg0).also { Anvil.render() }

            override fun onQueryTextSubmit(arg0: String): Boolean =
                arg.onQueryTextSubmit(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "onSearchClick" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnSearchClickListener(null as? View.OnClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: View) -> Unit
          v.setOnSearchClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onSuggestion" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnSuggestionListener(null as? SearchView.OnSuggestionListener?)
          true
        }
        arg is SearchView.OnSuggestionListener -> {
          v.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionClick(arg0: Int): Boolean = arg.onSuggestionClick(arg0).also {
                Anvil.render() }

            override fun onSuggestionSelect(arg0: Int): Boolean =
                arg.onSuggestionSelect(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "queryHint" -> when {
      v is SearchView && (arg == null || arg is CharSequence) -> {
        v.setQueryHint(arg as CharSequence)
        true
      }
      else -> false
    }
    "queryRefinementEnabled" -> when {
      v is SearchView && arg is Boolean -> {
        v.setQueryRefinementEnabled(arg)
        true
      }
      else -> false
    }
    "searchableInfo" -> when {
      v is SearchView && arg is SearchableInfo -> {
        v.setSearchableInfo(arg)
        true
      }
      else -> false
    }
    "submitButtonEnabled" -> when {
      v is SearchView && arg is Boolean -> {
        v.setSubmitButtonEnabled(arg)
        true
      }
      else -> false
    }
    "suggestionsAdapter" -> when {
      v is SearchView && arg is CursorAdapter -> {
        v.setSuggestionsAdapter(arg)
        true
      }
      else -> false
    }
    "showText" -> when {
      v is SwitchCompat && arg is Boolean -> {
        v.setShowText(arg)
        true
      }
      else -> false
    }
    "splitTrack" -> when {
      v is SwitchCompat && arg is Boolean -> {
        v.setSplitTrack(arg)
        true
      }
      else -> false
    }
    "switchMinWidth" -> when {
      v is SwitchCompat && arg is Int -> {
        v.setSwitchMinWidth(arg)
        true
      }
      else -> false
    }
    "switchPadding" -> when {
      v is SwitchCompat && arg is Int -> {
        v.setSwitchPadding(arg)
        true
      }
      else -> false
    }
    "switchTypeface" -> when {
      v is SwitchCompat && arg is Typeface -> {
        v.setSwitchTypeface(arg)
        true
      }
      else -> false
    }
    "textOff" -> when {
      v is SwitchCompat && arg is CharSequence -> {
        v.setTextOff(arg)
        true
      }
      else -> false
    }
    "textOn" -> when {
      v is SwitchCompat && arg is CharSequence -> {
        v.setTextOn(arg)
        true
      }
      else -> false
    }
    "thumbDrawable" -> when {
      v is SwitchCompat && arg is Drawable -> {
        v.setThumbDrawable(arg)
        true
      }
      else -> false
    }
    "thumbResource" -> when {
      v is SwitchCompat && arg is Int -> {
        v.setThumbResource(arg)
        true
      }
      else -> false
    }
    "thumbTextPadding" -> when {
      v is SwitchCompat && arg is Int -> {
        v.setThumbTextPadding(arg)
        true
      }
      else -> false
    }
    "thumbTintList" -> when {
      v is SwitchCompat && (arg == null || arg is ColorStateList) -> {
        v.setThumbTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "thumbTintMode" -> when {
      v is SwitchCompat && (arg == null || arg is PorterDuff.Mode) -> {
        v.setThumbTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "trackDrawable" -> when {
      v is SwitchCompat && arg is Drawable -> {
        v.setTrackDrawable(arg)
        true
      }
      else -> false
    }
    "trackResource" -> when {
      v is SwitchCompat && arg is Int -> {
        v.setTrackResource(arg)
        true
      }
      else -> false
    }
    "trackTintList" -> when {
      v is SwitchCompat && (arg == null || arg is ColorStateList) -> {
        v.setTrackTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "trackTintMode" -> when {
      v is SwitchCompat && (arg == null || arg is PorterDuff.Mode) -> {
        v.setTrackTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "collapseContentDescription" -> when {
      v is Toolbar && (arg == null || arg is CharSequence) -> {
        v.setCollapseContentDescription(arg as CharSequence)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setCollapseContentDescription(arg)
        true
      }
      else -> false
    }
    "collapseIcon" -> when {
      v is Toolbar && (arg == null || arg is Drawable) -> {
        v.setCollapseIcon(arg as Drawable)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setCollapseIcon(arg)
        true
      }
      else -> false
    }
    "collapsible" -> when {
      v is Toolbar && arg is Boolean -> {
        v.setCollapsible(arg)
        true
      }
      else -> false
    }
    "contentInsetEndWithActions" -> when {
      v is Toolbar && arg is Int -> {
        v.setContentInsetEndWithActions(arg)
        true
      }
      else -> false
    }
    "contentInsetStartWithNavigation" -> when {
      v is Toolbar && arg is Int -> {
        v.setContentInsetStartWithNavigation(arg)
        true
      }
      else -> false
    }
    "logoDescription" -> when {
      v is Toolbar && arg is CharSequence -> {
        v.setLogoDescription(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setLogoDescription(arg)
        true
      }
      else -> false
    }
    "navigationContentDescription" -> when {
      v is Toolbar && (arg == null || arg is CharSequence) -> {
        v.setNavigationContentDescription(arg as CharSequence)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setNavigationContentDescription(arg)
        true
      }
      else -> false
    }
    "navigationIcon" -> when {
      v is Toolbar && (arg == null || arg is Drawable) -> {
        v.setNavigationIcon(arg as Drawable)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setNavigationIcon(arg)
        true
      }
      else -> false
    }
    "navigationOnClickListener" -> when {
      v is Toolbar && arg is View.OnClickListener -> {
        v.setNavigationOnClickListener(arg)
        true
      }
      else -> false
    }
    "subtitle" -> when {
      v is Toolbar && arg is CharSequence -> {
        v.setSubtitle(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setSubtitle(arg)
        true
      }
      else -> false
    }
    "subtitleTextColor" -> when {
      v is Toolbar && arg is ColorStateList -> {
        v.setSubtitleTextColor(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setSubtitleTextColor(arg)
        true
      }
      else -> false
    }
    "titleMarginBottom" -> when {
      v is Toolbar && arg is Int -> {
        v.setTitleMarginBottom(arg)
        true
      }
      else -> false
    }
    "titleMarginEnd" -> when {
      v is Toolbar && arg is Int -> {
        v.setTitleMarginEnd(arg)
        true
      }
      else -> false
    }
    "titleMarginStart" -> when {
      v is Toolbar && arg is Int -> {
        v.setTitleMarginStart(arg)
        true
      }
      else -> false
    }
    "titleMarginTop" -> when {
      v is Toolbar && arg is Int -> {
        v.setTitleMarginTop(arg)
        true
      }
      else -> false
    }
    "titleTextColor" -> when {
      v is Toolbar && arg is ColorStateList -> {
        v.setTitleTextColor(arg)
        true
      }
      v is Toolbar && arg is Int -> {
        v.setTitleTextColor(arg)
        true
      }
      else -> false
    }
    "inflatedId" -> when {
      v is ViewStubCompat && arg is Int -> {
        v.setInflatedId(arg)
        true
      }
      else -> false
    }
    "layoutInflater" -> when {
      v is ViewStubCompat && arg is LayoutInflater -> {
        v.setLayoutInflater(arg)
        true
      }
      else -> false
    }
    "layoutResource" -> when {
      v is ViewStubCompat && arg is Int -> {
        v.setLayoutResource(arg)
        true
      }
      else -> false
    }
    "onInflate" -> when {
      v is ViewStubCompat -> when {
        arg == null -> {
          v.setOnInflateListener(null as? ViewStubCompat.OnInflateListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: ViewStubCompat, arg1: View) -> Unit
          v.setOnInflateListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    else -> false
  }
}
