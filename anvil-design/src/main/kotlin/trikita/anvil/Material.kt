@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package trikita.anvil

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import com.google.android.material.animation.MotionSpec
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.google.android.material.circularreveal.CircularRevealGridLayout
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.google.android.material.circularreveal.CircularRevealRelativeLayout
import com.google.android.material.circularreveal.CircularRevealWidget
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.BaselineLayout
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.internal.FlowLayout
import com.google.android.material.internal.ForegroundLinearLayout
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.internal.ScrimInsetsFrameLayout
import com.google.android.material.internal.VisibilityAwareImageButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.resources.TextAppearance
import com.google.android.material.snackbar.SnackbarContentLayout
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.transformation.TransformationChildCard
import com.google.android.material.transformation.TransformationChildLayout
import kotlin.Any
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Float
import kotlin.Function
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun appBarLayout(configure: AppBarLayoutScope.() -> Unit = {}) =
    v<AppBarLayout>(configure.bind(AppBarLayoutScope))
abstract class AppBarLayoutScope {
  fun expanded(arg: Boolean): Unit = attr("expanded", arg)
  fun liftOnScroll(arg: Boolean): Unit = attr("liftOnScroll", arg)
  fun liftable(arg: Boolean): Unit = attr("liftable", arg)
  fun lifted(arg: Boolean): Unit = attr("lifted", arg)
  companion object : AppBarLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun collapsingToolbarLayout(configure: CollapsingToolbarLayoutScope.() -> Unit = {}) =
    v<CollapsingToolbarLayout>(configure.bind(CollapsingToolbarLayoutScope))
abstract class CollapsingToolbarLayoutScope {
  fun collapsedTitleGravity(arg: Int): Unit = attr("collapsedTitleGravity", arg)
  fun collapsedTitleTextAppearance(arg: Int): Unit = attr("collapsedTitleTextAppearance", arg)
  fun collapsedTitleTextColor(arg: ColorStateList): Unit = attr("collapsedTitleTextColor", arg)
  fun collapsedTitleTextColor(arg: Int): Unit = attr("collapsedTitleTextColor", arg)
  fun collapsedTitleTypeface(arg: Typeface?): Unit = attr("collapsedTitleTypeface", arg)
  fun contentScrim(arg: Drawable?): Unit = attr("contentScrim", arg)
  fun contentScrimColor(arg: Int): Unit = attr("contentScrimColor", arg)
  fun contentScrimResource(arg: Int): Unit = attr("contentScrimResource", arg)
  fun expandedTitleColor(arg: Int): Unit = attr("expandedTitleColor", arg)
  fun expandedTitleGravity(arg: Int): Unit = attr("expandedTitleGravity", arg)
  fun expandedTitleMarginBottom(arg: Int): Unit = attr("expandedTitleMarginBottom", arg)
  fun expandedTitleMarginEnd(arg: Int): Unit = attr("expandedTitleMarginEnd", arg)
  fun expandedTitleMarginStart(arg: Int): Unit = attr("expandedTitleMarginStart", arg)
  fun expandedTitleMarginTop(arg: Int): Unit = attr("expandedTitleMarginTop", arg)
  fun expandedTitleTextAppearance(arg: Int): Unit = attr("expandedTitleTextAppearance", arg)
  fun expandedTitleTextColor(arg: ColorStateList): Unit = attr("expandedTitleTextColor", arg)
  fun expandedTitleTypeface(arg: Typeface?): Unit = attr("expandedTitleTypeface", arg)
  fun scrimAnimationDuration(arg: Long): Unit = attr("scrimAnimationDuration", arg)
  fun scrimVisibleHeightTrigger(arg: Int): Unit = attr("scrimVisibleHeightTrigger", arg)
  fun scrimsShown(arg: Boolean): Unit = attr("scrimsShown", arg)
  fun statusBarScrim(arg: Drawable?): Unit = attr("statusBarScrim", arg)
  fun statusBarScrimColor(arg: Int): Unit = attr("statusBarScrimColor", arg)
  fun statusBarScrimResource(arg: Int): Unit = attr("statusBarScrimResource", arg)
  fun title(arg: CharSequence?): Unit = attr("title", arg)
  fun titleEnabled(arg: Boolean): Unit = attr("titleEnabled", arg)
  companion object : CollapsingToolbarLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun bottomAppBar(configure: BottomAppBarScope.() -> Unit = {}) =
    v<BottomAppBar>(configure.bind(BottomAppBarScope))
abstract class BottomAppBarScope {
  fun backgroundTint(arg: ColorStateList?): Unit = attr("backgroundTint", arg)
  fun cradleVerticalOffset(arg: Float): Unit = attr("cradleVerticalOffset", arg)
  fun fabAlignmentMode(arg: Int): Unit = attr("fabAlignmentMode", arg)
  fun fabCradleMargin(arg: Float): Unit = attr("fabCradleMargin", arg)
  fun fabCradleRoundedCornerRadius(arg: Float): Unit = attr("fabCradleRoundedCornerRadius", arg)
  fun hideOnScroll(arg: Boolean): Unit = attr("hideOnScroll", arg)
  companion object : BottomAppBarScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun bottomNavigationItemView(configure: BottomNavigationItemViewScope.() -> Unit = {}) =
    v<BottomNavigationItemView>(configure.bind(BottomNavigationItemViewScope))
abstract class BottomNavigationItemViewScope {
  companion object : BottomNavigationItemViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun bottomNavigationMenuView(configure: BottomNavigationMenuViewScope.() -> Unit = {}) =
    v<BottomNavigationMenuView>(configure.bind(BottomNavigationMenuViewScope))
abstract class BottomNavigationMenuViewScope {
  companion object : BottomNavigationMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun bottomNavigationView(configure: BottomNavigationViewScope.() -> Unit = {}) =
    v<BottomNavigationView>(configure.bind(BottomNavigationViewScope))
abstract class BottomNavigationViewScope {
  fun itemBackground(arg: Drawable?): Unit = attr("itemBackground", arg)
  fun itemBackgroundResource(arg: Int): Unit = attr("itemBackgroundResource", arg)
  fun itemHorizontalTranslationEnabled(arg: Boolean): Unit =
      attr("itemHorizontalTranslationEnabled", arg)
  fun itemIconSize(arg: Int): Unit = attr("itemIconSize", arg)
  fun itemIconSizeRes(arg: Int): Unit = attr("itemIconSizeRes", arg)
  fun itemIconTintList(arg: ColorStateList?): Unit = attr("itemIconTintList", arg)
  fun itemTextAppearanceActive(arg: Int): Unit = attr("itemTextAppearanceActive", arg)
  fun itemTextAppearanceInactive(arg: Int): Unit = attr("itemTextAppearanceInactive", arg)
  fun itemTextColor(arg: ColorStateList?): Unit = attr("itemTextColor", arg)
  fun labelVisibilityMode(arg: Int): Unit = attr("labelVisibilityMode", arg)
  fun onNavigationItemReselected(arg: ((arg0: MenuItem) -> Unit)?): Unit =
      attr("onNavigationItemReselected", arg)
  fun onNavigationItemSelected(arg: ((arg0: MenuItem) -> Boolean)?): Unit =
      attr("onNavigationItemSelected", arg)
  fun selectedItemId(arg: Int): Unit = attr("selectedItemId", arg)
  companion object : BottomNavigationViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun materialButton(configure: MaterialButtonScope.() -> Unit = {}) =
    v<MaterialButton>(configure.bind(MaterialButtonScope))
abstract class MaterialButtonScope {
  fun cornerRadius(arg: Int): Unit = attr("cornerRadius", arg)
  fun cornerRadiusResource(arg: Int): Unit = attr("cornerRadiusResource", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun iconGravity(arg: Int): Unit = attr("iconGravity", arg)
  fun iconPadding(arg: Int): Unit = attr("iconPadding", arg)
  fun iconResource(arg: Int): Unit = attr("iconResource", arg)
  fun iconSize(arg: Int): Unit = attr("iconSize", arg)
  fun iconTint(arg: ColorStateList?): Unit = attr("iconTint", arg)
  fun iconTintMode(arg: PorterDuff.Mode): Unit = attr("iconTintMode", arg)
  fun iconTintResource(arg: Int): Unit = attr("iconTintResource", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColorResource(arg: Int): Unit = attr("rippleColorResource", arg)
  fun strokeColor(arg: ColorStateList?): Unit = attr("strokeColor", arg)
  fun strokeColorResource(arg: Int): Unit = attr("strokeColorResource", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  fun strokeWidthResource(arg: Int): Unit = attr("strokeWidthResource", arg)
  companion object : MaterialButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun materialCardView(configure: MaterialCardViewScope.() -> Unit = {}) =
    v<MaterialCardView>(configure.bind(MaterialCardViewScope))
abstract class MaterialCardViewScope {
  fun strokeColor(arg: Int): Unit = attr("strokeColor", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  companion object : MaterialCardViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun chip(configure: ChipScope.() -> Unit = {}) = v<Chip>(configure.bind(ChipScope))
abstract class ChipScope {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checkableResource(arg: Int): Unit = attr("checkableResource", arg)
  fun checkedIcon(arg: Drawable?): Unit = attr("checkedIcon", arg)
  fun checkedIconResource(arg: Int): Unit = attr("checkedIconResource", arg)
  fun checkedIconVisible(arg: Boolean): Unit = attr("checkedIconVisible", arg)
  fun checkedIconVisible(arg: Int): Unit = attr("checkedIconVisible", arg)
  fun chipBackgroundColor(arg: ColorStateList?): Unit = attr("chipBackgroundColor", arg)
  fun chipBackgroundColorResource(arg: Int): Unit = attr("chipBackgroundColorResource", arg)
  fun chipCornerRadius(arg: Float): Unit = attr("chipCornerRadius", arg)
  fun chipCornerRadiusResource(arg: Int): Unit = attr("chipCornerRadiusResource", arg)
  fun chipDrawable(arg: ChipDrawable): Unit = attr("chipDrawable", arg)
  fun chipEndPadding(arg: Float): Unit = attr("chipEndPadding", arg)
  fun chipEndPaddingResource(arg: Int): Unit = attr("chipEndPaddingResource", arg)
  fun chipIcon(arg: Drawable?): Unit = attr("chipIcon", arg)
  fun chipIconResource(arg: Int): Unit = attr("chipIconResource", arg)
  fun chipIconSize(arg: Float): Unit = attr("chipIconSize", arg)
  fun chipIconSizeResource(arg: Int): Unit = attr("chipIconSizeResource", arg)
  fun chipIconTint(arg: ColorStateList?): Unit = attr("chipIconTint", arg)
  fun chipIconTintResource(arg: Int): Unit = attr("chipIconTintResource", arg)
  fun chipIconVisible(arg: Boolean): Unit = attr("chipIconVisible", arg)
  fun chipIconVisible(arg: Int): Unit = attr("chipIconVisible", arg)
  fun chipMinHeight(arg: Float): Unit = attr("chipMinHeight", arg)
  fun chipMinHeightResource(arg: Int): Unit = attr("chipMinHeightResource", arg)
  fun chipStartPadding(arg: Float): Unit = attr("chipStartPadding", arg)
  fun chipStartPaddingResource(arg: Int): Unit = attr("chipStartPaddingResource", arg)
  fun chipStrokeColor(arg: ColorStateList?): Unit = attr("chipStrokeColor", arg)
  fun chipStrokeColorResource(arg: Int): Unit = attr("chipStrokeColorResource", arg)
  fun chipStrokeWidth(arg: Float): Unit = attr("chipStrokeWidth", arg)
  fun chipStrokeWidthResource(arg: Int): Unit = attr("chipStrokeWidthResource", arg)
  fun closeIcon(arg: Drawable?): Unit = attr("closeIcon", arg)
  fun closeIconContentDescription(arg: CharSequence?): Unit = attr("closeIconContentDescription",
      arg)
  fun closeIconEndPadding(arg: Float): Unit = attr("closeIconEndPadding", arg)
  fun closeIconEndPaddingResource(arg: Int): Unit = attr("closeIconEndPaddingResource", arg)
  fun closeIconResource(arg: Int): Unit = attr("closeIconResource", arg)
  fun closeIconSize(arg: Float): Unit = attr("closeIconSize", arg)
  fun closeIconSizeResource(arg: Int): Unit = attr("closeIconSizeResource", arg)
  fun closeIconStartPadding(arg: Float): Unit = attr("closeIconStartPadding", arg)
  fun closeIconStartPaddingResource(arg: Int): Unit = attr("closeIconStartPaddingResource", arg)
  fun closeIconTint(arg: ColorStateList?): Unit = attr("closeIconTint", arg)
  fun closeIconTintResource(arg: Int): Unit = attr("closeIconTintResource", arg)
  fun closeIconVisible(arg: Boolean): Unit = attr("closeIconVisible", arg)
  fun closeIconVisible(arg: Int): Unit = attr("closeIconVisible", arg)
  fun hideMotionSpec(arg: MotionSpec?): Unit = attr("hideMotionSpec", arg)
  fun hideMotionSpecResource(arg: Int): Unit = attr("hideMotionSpecResource", arg)
  fun iconEndPadding(arg: Float): Unit = attr("iconEndPadding", arg)
  fun iconEndPaddingResource(arg: Int): Unit = attr("iconEndPaddingResource", arg)
  fun iconStartPadding(arg: Float): Unit = attr("iconStartPadding", arg)
  fun iconStartPaddingResource(arg: Int): Unit = attr("iconStartPaddingResource", arg)
  fun onCloseIconClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onCloseIconClick", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColorResource(arg: Int): Unit = attr("rippleColorResource", arg)
  fun showMotionSpec(arg: MotionSpec?): Unit = attr("showMotionSpec", arg)
  fun showMotionSpecResource(arg: Int): Unit = attr("showMotionSpecResource", arg)
  fun textAppearance(arg: TextAppearance?): Unit = attr("textAppearance", arg)
  fun textAppearanceResource(arg: Int): Unit = attr("textAppearanceResource", arg)
  fun textEndPadding(arg: Float): Unit = attr("textEndPadding", arg)
  fun textEndPaddingResource(arg: Int): Unit = attr("textEndPaddingResource", arg)
  fun textStartPadding(arg: Float): Unit = attr("textStartPadding", arg)
  fun textStartPaddingResource(arg: Int): Unit = attr("textStartPaddingResource", arg)
  companion object : ChipScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun chipGroup(configure: ChipGroupScope.() -> Unit = {}) =
    v<ChipGroup>(configure.bind(ChipGroupScope))
abstract class ChipGroupScope : FlowLayoutScope() {
  fun chipSpacing(arg: Int): Unit = attr("chipSpacing", arg)
  fun chipSpacingHorizontal(arg: Int): Unit = attr("chipSpacingHorizontal", arg)
  fun chipSpacingHorizontalResource(arg: Int): Unit = attr("chipSpacingHorizontalResource", arg)
  fun chipSpacingResource(arg: Int): Unit = attr("chipSpacingResource", arg)
  fun chipSpacingVertical(arg: Int): Unit = attr("chipSpacingVertical", arg)
  fun chipSpacingVerticalResource(arg: Int): Unit = attr("chipSpacingVerticalResource", arg)
  fun onCheckedChange(arg: ((arg0: ChipGroup, arg1: Int) -> Unit)?): Unit = attr("onCheckedChange",
      arg)
  fun singleLine(arg: Int): Unit = attr("singleLine", arg)
  fun singleSelection(arg: Boolean): Unit = attr("singleSelection", arg)
  fun singleSelection(arg: Int): Unit = attr("singleSelection", arg)
  companion object : ChipGroupScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun circularRevealFrameLayout(configure: CircularRevealFrameLayoutScope.() -> Unit = {}) =
    v<CircularRevealFrameLayout>(configure.bind(CircularRevealFrameLayoutScope))
abstract class CircularRevealFrameLayoutScope {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealFrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun circularRevealGridLayout(configure: CircularRevealGridLayoutScope.() -> Unit = {}) =
    v<CircularRevealGridLayout>(configure.bind(CircularRevealGridLayoutScope))
abstract class CircularRevealGridLayoutScope {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealGridLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun circularRevealLinearLayout(configure: CircularRevealLinearLayoutScope.() -> Unit = {}) =
    v<CircularRevealLinearLayout>(configure.bind(CircularRevealLinearLayoutScope))
abstract class CircularRevealLinearLayoutScope {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealLinearLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun circularRevealRelativeLayout(configure: CircularRevealRelativeLayoutScope.() -> Unit = {}) =
    v<CircularRevealRelativeLayout>(configure.bind(CircularRevealRelativeLayoutScope))
abstract class CircularRevealRelativeLayoutScope {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealRelativeLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun circularRevealCardView(configure: CircularRevealCardViewScope.() -> Unit = {}) =
    v<CircularRevealCardView>(configure.bind(CircularRevealCardViewScope))
abstract class CircularRevealCardViewScope {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealCardViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun floatingActionButton(configure: FloatingActionButtonScope.() -> Unit = {}) =
    v<FloatingActionButton>(configure.bind(FloatingActionButtonScope))
abstract class FloatingActionButtonScope : VisibilityAwareImageButtonScope() {
  fun compatElevation(arg: Float): Unit = attr("compatElevation", arg)
  fun compatElevationResource(arg: Int): Unit = attr("compatElevationResource", arg)
  fun compatHoveredFocusedTranslationZ(arg: Float): Unit = attr("compatHoveredFocusedTranslationZ",
      arg)
  fun compatHoveredFocusedTranslationZResource(arg: Int): Unit =
      attr("compatHoveredFocusedTranslationZResource", arg)
  fun compatPressedTranslationZ(arg: Float): Unit = attr("compatPressedTranslationZ", arg)
  fun compatPressedTranslationZResource(arg: Int): Unit = attr("compatPressedTranslationZResource",
      arg)
  fun customSize(arg: Int): Unit = attr("customSize", arg)
  fun expanded(arg: Boolean): Unit = attr("expanded", arg)
  fun expandedComponentIdHint(arg: Int): Unit = attr("expandedComponentIdHint", arg)
  fun hideMotionSpec(arg: MotionSpec): Unit = attr("hideMotionSpec", arg)
  fun hideMotionSpecResource(arg: Int): Unit = attr("hideMotionSpecResource", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColor(arg: Int): Unit = attr("rippleColor", arg)
  fun showMotionSpec(arg: MotionSpec): Unit = attr("showMotionSpec", arg)
  fun showMotionSpecResource(arg: Int): Unit = attr("showMotionSpecResource", arg)
  fun size(arg: Int): Unit = attr("size", arg)
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportImageTintList(arg: ColorStateList?): Unit = attr("supportImageTintList", arg)
  fun supportImageTintMode(arg: PorterDuff.Mode?): Unit = attr("supportImageTintMode", arg)
  fun useCompatPadding(arg: Boolean): Unit = attr("useCompatPadding", arg)
  companion object : FloatingActionButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun baselineLayout(configure: BaselineLayoutScope.() -> Unit = {}) =
    v<BaselineLayout>(configure.bind(BaselineLayoutScope))
abstract class BaselineLayoutScope {
  companion object : BaselineLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun checkableImageButton(configure: CheckableImageButtonScope.() -> Unit = {}) =
    v<CheckableImageButton>(configure.bind(CheckableImageButtonScope))
abstract class CheckableImageButtonScope {
  companion object : CheckableImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun flowLayout(configure: FlowLayoutScope.() -> Unit = {}) =
    v<FlowLayout>(configure.bind(FlowLayoutScope))
abstract class FlowLayoutScope {
  fun singleLine(arg: Boolean): Unit = attr("singleLine", arg)
  companion object : FlowLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun foregroundLinearLayout(configure: ForegroundLinearLayoutScope.() -> Unit = {}) =
    v<ForegroundLinearLayout>(configure.bind(ForegroundLinearLayoutScope))
abstract class ForegroundLinearLayoutScope {
  companion object : ForegroundLinearLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun navigationMenuItemView(configure: NavigationMenuItemViewScope.() -> Unit = {}) =
    v<NavigationMenuItemView>(configure.bind(NavigationMenuItemViewScope))
abstract class NavigationMenuItemViewScope : ForegroundLinearLayoutScope() {
  fun horizontalPadding(arg: Int): Unit = attr("horizontalPadding", arg)
  fun needsEmptyIcon(arg: Boolean): Unit = attr("needsEmptyIcon", arg)
  fun textAppearance(arg: Int): Unit = attr("textAppearance", arg)
  companion object : NavigationMenuItemViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun navigationMenuView(configure: NavigationMenuViewScope.() -> Unit = {}) =
    v<NavigationMenuView>(configure.bind(NavigationMenuViewScope))
abstract class NavigationMenuViewScope {
  companion object : NavigationMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun scrimInsetsFrameLayout(configure: ScrimInsetsFrameLayoutScope.() -> Unit = {}) =
    v<ScrimInsetsFrameLayout>(configure.bind(ScrimInsetsFrameLayoutScope))
abstract class ScrimInsetsFrameLayoutScope {
  companion object : ScrimInsetsFrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun visibilityAwareImageButton(configure: VisibilityAwareImageButtonScope.() -> Unit = {}) =
    v<VisibilityAwareImageButton>(configure.bind(VisibilityAwareImageButtonScope))
abstract class VisibilityAwareImageButtonScope {
  companion object : VisibilityAwareImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun navigationView(configure: NavigationViewScope.() -> Unit = {}) =
    v<NavigationView>(configure.bind(NavigationViewScope))
abstract class NavigationViewScope : ScrimInsetsFrameLayoutScope() {
  fun checkedItem(arg: MenuItem): Unit = attr("checkedItem", arg)
  fun checkedItem(arg: Int): Unit = attr("checkedItem", arg)
  fun itemBackground(arg: Drawable?): Unit = attr("itemBackground", arg)
  fun itemBackgroundResource(arg: Int): Unit = attr("itemBackgroundResource", arg)
  fun itemHorizontalPadding(arg: Int): Unit = attr("itemHorizontalPadding", arg)
  fun itemHorizontalPaddingResource(arg: Int): Unit = attr("itemHorizontalPaddingResource", arg)
  fun itemIconPadding(arg: Int): Unit = attr("itemIconPadding", arg)
  fun itemIconPaddingResource(arg: Int): Unit = attr("itemIconPaddingResource", arg)
  fun itemIconTintList(arg: ColorStateList?): Unit = attr("itemIconTintList", arg)
  fun itemTextAppearance(arg: Int): Unit = attr("itemTextAppearance", arg)
  fun itemTextColor(arg: ColorStateList?): Unit = attr("itemTextColor", arg)
  fun navigationItemSelectedListener(arg: NavigationView.OnNavigationItemSelectedListener?): Unit =
      attr("navigationItemSelectedListener", arg)
  companion object : NavigationViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun snackbarContentLayout(configure: SnackbarContentLayoutScope.() -> Unit = {}) =
    v<SnackbarContentLayout>(configure.bind(SnackbarContentLayoutScope))
abstract class SnackbarContentLayoutScope {
  companion object : SnackbarContentLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun tabItem(configure: TabItemScope.() -> Unit = {}) = v<TabItem>(configure.bind(TabItemScope))
abstract class TabItemScope {
  companion object : TabItemScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun tabLayout(configure: TabLayoutScope.() -> Unit = {}) =
    v<TabLayout>(configure.bind(TabLayoutScope))
abstract class TabLayoutScope {
  fun inlineLabel(arg: Boolean): Unit = attr("inlineLabel", arg)
  fun inlineLabelResource(arg: Int): Unit = attr("inlineLabelResource", arg)
  fun selectedTabIndicator(arg: Drawable?): Unit = attr("selectedTabIndicator", arg)
  fun selectedTabIndicator(arg: Int): Unit = attr("selectedTabIndicator", arg)
  fun selectedTabIndicatorColor(arg: Int): Unit = attr("selectedTabIndicatorColor", arg)
  fun selectedTabIndicatorGravity(arg: Int): Unit = attr("selectedTabIndicatorGravity", arg)
  fun tabGravity(arg: Int): Unit = attr("tabGravity", arg)
  fun tabIconTint(arg: ColorStateList?): Unit = attr("tabIconTint", arg)
  fun tabIconTintResource(arg: Int): Unit = attr("tabIconTintResource", arg)
  fun tabIndicatorFullWidth(arg: Boolean): Unit = attr("tabIndicatorFullWidth", arg)
  fun tabMode(arg: Int): Unit = attr("tabMode", arg)
  fun tabRippleColor(arg: ColorStateList): Unit = attr("tabRippleColor", arg)
  fun tabRippleColorResource(arg: Int): Unit = attr("tabRippleColorResource", arg)
  fun tabTextColors(arg: ColorStateList?): Unit = attr("tabTextColors", arg)
  fun unboundedRipple(arg: Boolean): Unit = attr("unboundedRipple", arg)
  fun unboundedRippleResource(arg: Int): Unit = attr("unboundedRippleResource", arg)
  companion object : TabLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun textInputEditText(configure: TextInputEditTextScope.() -> Unit = {}) =
    v<TextInputEditText>(configure.bind(TextInputEditTextScope))
abstract class TextInputEditTextScope {
  companion object : TextInputEditTextScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun textInputLayout(configure: TextInputLayoutScope.() -> Unit = {}) =
    v<TextInputLayout>(configure.bind(TextInputLayoutScope))
abstract class TextInputLayoutScope {
  fun boxBackgroundColor(arg: Int): Unit = attr("boxBackgroundColor", arg)
  fun boxBackgroundColorResource(arg: Int): Unit = attr("boxBackgroundColorResource", arg)
  fun boxBackgroundMode(arg: Int): Unit = attr("boxBackgroundMode", arg)
  fun boxStrokeColor(arg: Int): Unit = attr("boxStrokeColor", arg)
  fun counterEnabled(arg: Boolean): Unit = attr("counterEnabled", arg)
  fun counterMaxLength(arg: Int): Unit = attr("counterMaxLength", arg)
  fun defaultHintTextColor(arg: ColorStateList?): Unit = attr("defaultHintTextColor", arg)
  fun error(arg: CharSequence?): Unit = attr("error", arg)
  fun errorEnabled(arg: Boolean): Unit = attr("errorEnabled", arg)
  fun errorTextAppearance(arg: Int): Unit = attr("errorTextAppearance", arg)
  fun errorTextColor(arg: ColorStateList?): Unit = attr("errorTextColor", arg)
  fun helperText(arg: CharSequence?): Unit = attr("helperText", arg)
  fun helperTextColor(arg: ColorStateList?): Unit = attr("helperTextColor", arg)
  fun helperTextEnabled(arg: Boolean): Unit = attr("helperTextEnabled", arg)
  fun helperTextTextAppearance(arg: Int): Unit = attr("helperTextTextAppearance", arg)
  fun hint(arg: CharSequence?): Unit = attr("hint", arg)
  fun hintAnimationEnabled(arg: Boolean): Unit = attr("hintAnimationEnabled", arg)
  fun hintEnabled(arg: Boolean): Unit = attr("hintEnabled", arg)
  fun hintTextAppearance(arg: Int): Unit = attr("hintTextAppearance", arg)
  fun passwordVisibilityToggleContentDescription(arg: CharSequence?): Unit =
      attr("passwordVisibilityToggleContentDescription", arg)
  fun passwordVisibilityToggleContentDescription(arg: Int): Unit =
      attr("passwordVisibilityToggleContentDescription", arg)
  fun passwordVisibilityToggleDrawable(arg: Drawable?): Unit =
      attr("passwordVisibilityToggleDrawable", arg)
  fun passwordVisibilityToggleDrawable(arg: Int): Unit = attr("passwordVisibilityToggleDrawable",
      arg)
  fun passwordVisibilityToggleEnabled(arg: Boolean): Unit = attr("passwordVisibilityToggleEnabled",
      arg)
  fun passwordVisibilityToggleTintList(arg: ColorStateList?): Unit =
      attr("passwordVisibilityToggleTintList", arg)
  fun passwordVisibilityToggleTintMode(arg: PorterDuff.Mode?): Unit =
      attr("passwordVisibilityToggleTintMode", arg)
  fun textInputAccessibilityDelegate(arg: TextInputLayout.AccessibilityDelegate): Unit =
      attr("textInputAccessibilityDelegate", arg)
  fun typeface(arg: Typeface?): Unit = attr("typeface", arg)
  companion object : TextInputLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun transformationChildCard(configure: TransformationChildCardScope.() -> Unit = {}) =
    v<TransformationChildCard>(configure.bind(TransformationChildCardScope))
abstract class TransformationChildCardScope : CircularRevealCardViewScope() {
  companion object : TransformationChildCardScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

fun transformationChildLayout(configure: TransformationChildLayoutScope.() -> Unit = {}) =
    v<TransformationChildLayout>(configure.bind(TransformationChildLayoutScope))
abstract class TransformationChildLayoutScope : CircularRevealFrameLayoutScope() {
  companion object : TransformationChildLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(MaterialDslSetter)
    }
  }
}

/**
 * DSL for creating views and settings their attributes.
 * This file has been generated by
 * {@code gradle generateMaterialDSL}
 * It contains views and their setters from the library material.
 * Please, don't edit it manually unless for debugging.
 */
object MaterialSetter : Anvil.AttributeSetter<Any?> {
  override fun set(
    v: View,
    name: String,
    arg: Any?,
    old: Any?
  ): Boolean = when (name) {
    "expanded" -> when {
      v is AppBarLayout && arg is Boolean -> {
        v.setExpanded(arg)
        true
      }
      v is FloatingActionButton && arg is Boolean -> {
        v.setExpanded(arg)
        true
      }
      else -> false
    }
    "liftOnScroll" -> when {
      v is AppBarLayout && arg is Boolean -> {
        v.setLiftOnScroll(arg)
        true
      }
      else -> false
    }
    "liftable" -> when {
      v is AppBarLayout && arg is Boolean -> {
        v.setLiftable(arg)
        true
      }
      else -> false
    }
    "lifted" -> when {
      v is AppBarLayout && arg is Boolean -> {
        v.setLifted(arg)
        true
      }
      else -> false
    }
    "collapsedTitleGravity" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setCollapsedTitleGravity(arg)
        true
      }
      else -> false
    }
    "collapsedTitleTextAppearance" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setCollapsedTitleTextAppearance(arg)
        true
      }
      else -> false
    }
    "collapsedTitleTextColor" -> when {
      v is CollapsingToolbarLayout && arg is ColorStateList -> {
        v.setCollapsedTitleTextColor(arg)
        true
      }
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setCollapsedTitleTextColor(arg)
        true
      }
      else -> false
    }
    "collapsedTitleTypeface" -> when {
      v is CollapsingToolbarLayout && arg is Typeface? -> {
        v.setCollapsedTitleTypeface(arg as Typeface)
        true
      }
      else -> false
    }
    "contentScrim" -> when {
      v is CollapsingToolbarLayout && arg is Drawable? -> {
        v.setContentScrim(arg as Drawable)
        true
      }
      else -> false
    }
    "contentScrimColor" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setContentScrimColor(arg)
        true
      }
      else -> false
    }
    "contentScrimResource" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setContentScrimResource(arg)
        true
      }
      else -> false
    }
    "expandedTitleColor" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleColor(arg)
        true
      }
      else -> false
    }
    "expandedTitleGravity" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleGravity(arg)
        true
      }
      else -> false
    }
    "expandedTitleMarginBottom" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleMarginBottom(arg)
        true
      }
      else -> false
    }
    "expandedTitleMarginEnd" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleMarginEnd(arg)
        true
      }
      else -> false
    }
    "expandedTitleMarginStart" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleMarginStart(arg)
        true
      }
      else -> false
    }
    "expandedTitleMarginTop" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleMarginTop(arg)
        true
      }
      else -> false
    }
    "expandedTitleTextAppearance" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setExpandedTitleTextAppearance(arg)
        true
      }
      else -> false
    }
    "expandedTitleTextColor" -> when {
      v is CollapsingToolbarLayout && arg is ColorStateList -> {
        v.setExpandedTitleTextColor(arg)
        true
      }
      else -> false
    }
    "expandedTitleTypeface" -> when {
      v is CollapsingToolbarLayout && arg is Typeface? -> {
        v.setExpandedTitleTypeface(arg as Typeface)
        true
      }
      else -> false
    }
    "scrimAnimationDuration" -> when {
      v is CollapsingToolbarLayout && arg is Long -> {
        v.setScrimAnimationDuration(arg)
        true
      }
      else -> false
    }
    "scrimVisibleHeightTrigger" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setScrimVisibleHeightTrigger(arg)
        true
      }
      else -> false
    }
    "scrimsShown" -> when {
      v is CollapsingToolbarLayout && arg is Boolean -> {
        v.setScrimsShown(arg)
        true
      }
      else -> false
    }
    "statusBarScrim" -> when {
      v is CollapsingToolbarLayout && arg is Drawable? -> {
        v.setStatusBarScrim(arg as Drawable)
        true
      }
      else -> false
    }
    "statusBarScrimColor" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setStatusBarScrimColor(arg)
        true
      }
      else -> false
    }
    "statusBarScrimResource" -> when {
      v is CollapsingToolbarLayout && arg is Int -> {
        v.setStatusBarScrimResource(arg)
        true
      }
      else -> false
    }
    "title" -> when {
      v is CollapsingToolbarLayout && arg is CharSequence? -> {
        v.setTitle(arg as CharSequence)
        true
      }
      else -> false
    }
    "titleEnabled" -> when {
      v is CollapsingToolbarLayout && arg is Boolean -> {
        v.setTitleEnabled(arg)
        true
      }
      else -> false
    }
    "backgroundTint" -> when {
      v is BottomAppBar && arg is ColorStateList? -> {
        v.setBackgroundTint(arg as ColorStateList)
        true
      }
      else -> false
    }
    "cradleVerticalOffset" -> when {
      v is BottomAppBar && arg is Float -> {
        v.setCradleVerticalOffset(arg)
        true
      }
      else -> false
    }
    "fabAlignmentMode" -> when {
      v is BottomAppBar && arg is Int -> {
        v.setFabAlignmentMode(arg)
        true
      }
      else -> false
    }
    "fabCradleMargin" -> when {
      v is BottomAppBar && arg is Float -> {
        v.setFabCradleMargin(arg)
        true
      }
      else -> false
    }
    "fabCradleRoundedCornerRadius" -> when {
      v is BottomAppBar && arg is Float -> {
        v.setFabCradleRoundedCornerRadius(arg)
        true
      }
      else -> false
    }
    "hideOnScroll" -> when {
      v is BottomAppBar && arg is Boolean -> {
        v.setHideOnScroll(arg)
        true
      }
      else -> false
    }
    "itemBackground" -> when {
      v is BottomNavigationView && arg is Drawable? -> {
        v.setItemBackground(arg as Drawable)
        true
      }
      v is NavigationView && arg is Drawable? -> {
        v.setItemBackground(arg as Drawable)
        true
      }
      else -> false
    }
    "itemBackgroundResource" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setItemBackgroundResource(arg)
        true
      }
      v is NavigationView && arg is Int -> {
        v.setItemBackgroundResource(arg)
        true
      }
      else -> false
    }
    "itemHorizontalTranslationEnabled" -> when {
      v is BottomNavigationView && arg is Boolean -> {
        v.setItemHorizontalTranslationEnabled(arg)
        true
      }
      else -> false
    }
    "itemIconSize" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setItemIconSize(arg)
        true
      }
      else -> false
    }
    "itemIconSizeRes" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setItemIconSizeRes(arg)
        true
      }
      else -> false
    }
    "itemIconTintList" -> when {
      v is BottomNavigationView && arg is ColorStateList? -> {
        v.setItemIconTintList(arg as ColorStateList)
        true
      }
      v is NavigationView && arg is ColorStateList? -> {
        v.setItemIconTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "itemTextAppearanceActive" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setItemTextAppearanceActive(arg)
        true
      }
      else -> false
    }
    "itemTextAppearanceInactive" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setItemTextAppearanceInactive(arg)
        true
      }
      else -> false
    }
    "itemTextColor" -> when {
      v is BottomNavigationView && arg is ColorStateList? -> {
        v.setItemTextColor(arg as ColorStateList)
        true
      }
      v is NavigationView && arg is ColorStateList? -> {
        v.setItemTextColor(arg as ColorStateList)
        true
      }
      else -> false
    }
    "labelVisibilityMode" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setLabelVisibilityMode(arg)
        true
      }
      else -> false
    }
    "onNavigationItemReselected" -> when {
      v is BottomNavigationView -> when {
        arg == null -> {
          v.setOnNavigationItemReselectedListener(null as?
              BottomNavigationView.OnNavigationItemReselectedListener?)
          true
        }
        arg is Function<*> -> {
          arg as ((arg0: MenuItem) -> Unit)?
          v.setOnNavigationItemReselectedListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onNavigationItemSelected" -> when {
      v is BottomNavigationView -> when {
        arg == null -> {
          v.setOnNavigationItemSelectedListener(null as?
              BottomNavigationView.OnNavigationItemSelectedListener?)
          true
        }
        arg is Function<*> -> {
          arg as ((arg0: MenuItem) -> Boolean)?
          v.setOnNavigationItemSelectedListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "selectedItemId" -> when {
      v is BottomNavigationView && arg is Int -> {
        v.setSelectedItemId(arg)
        true
      }
      else -> false
    }
    "cornerRadius" -> when {
      v is MaterialButton && arg is Int -> {
        v.setCornerRadius(arg)
        true
      }
      else -> false
    }
    "cornerRadiusResource" -> when {
      v is MaterialButton && arg is Int -> {
        v.setCornerRadiusResource(arg)
        true
      }
      else -> false
    }
    "icon" -> when {
      v is MaterialButton && arg is Drawable -> {
        v.setIcon(arg)
        true
      }
      else -> false
    }
    "iconGravity" -> when {
      v is MaterialButton && arg is Int -> {
        v.setIconGravity(arg)
        true
      }
      else -> false
    }
    "iconPadding" -> when {
      v is MaterialButton && arg is Int -> {
        v.setIconPadding(arg)
        true
      }
      else -> false
    }
    "iconResource" -> when {
      v is MaterialButton && arg is Int -> {
        v.setIconResource(arg)
        true
      }
      else -> false
    }
    "iconSize" -> when {
      v is MaterialButton && arg is Int -> {
        v.setIconSize(arg)
        true
      }
      else -> false
    }
    "iconTint" -> when {
      v is MaterialButton && arg is ColorStateList? -> {
        v.setIconTint(arg as ColorStateList)
        true
      }
      else -> false
    }
    "iconTintMode" -> when {
      v is MaterialButton && arg is PorterDuff.Mode -> {
        v.setIconTintMode(arg)
        true
      }
      else -> false
    }
    "iconTintResource" -> when {
      v is MaterialButton && arg is Int -> {
        v.setIconTintResource(arg)
        true
      }
      else -> false
    }
    "rippleColor" -> when {
      v is MaterialButton && arg is ColorStateList? -> {
        v.setRippleColor(arg as ColorStateList)
        true
      }
      v is Chip && arg is ColorStateList? -> {
        v.setRippleColor(arg as ColorStateList)
        true
      }
      v is FloatingActionButton && arg is ColorStateList? -> {
        v.setRippleColor(arg as ColorStateList)
        true
      }
      v is FloatingActionButton && arg is Int -> {
        v.setRippleColor(arg)
        true
      }
      else -> false
    }
    "rippleColorResource" -> when {
      v is MaterialButton && arg is Int -> {
        v.setRippleColorResource(arg)
        true
      }
      v is Chip && arg is Int -> {
        v.setRippleColorResource(arg)
        true
      }
      else -> false
    }
    "strokeColor" -> when {
      v is MaterialButton && arg is ColorStateList? -> {
        v.setStrokeColor(arg as ColorStateList)
        true
      }
      v is MaterialCardView && arg is Int -> {
        v.setStrokeColor(arg)
        true
      }
      else -> false
    }
    "strokeColorResource" -> when {
      v is MaterialButton && arg is Int -> {
        v.setStrokeColorResource(arg)
        true
      }
      else -> false
    }
    "strokeWidth" -> when {
      v is MaterialButton && arg is Int -> {
        v.setStrokeWidth(arg)
        true
      }
      v is MaterialCardView && arg is Int -> {
        v.setStrokeWidth(arg)
        true
      }
      else -> false
    }
    "strokeWidthResource" -> when {
      v is MaterialButton && arg is Int -> {
        v.setStrokeWidthResource(arg)
        true
      }
      else -> false
    }
    "checkable" -> when {
      v is Chip && arg is Boolean -> {
        v.setCheckable(arg)
        true
      }
      else -> false
    }
    "checkableResource" -> when {
      v is Chip && arg is Int -> {
        v.setCheckableResource(arg)
        true
      }
      else -> false
    }
    "checkedIcon" -> when {
      v is Chip && arg is Drawable? -> {
        v.setCheckedIcon(arg as Drawable)
        true
      }
      else -> false
    }
    "checkedIconResource" -> when {
      v is Chip && arg is Int -> {
        v.setCheckedIconResource(arg)
        true
      }
      else -> false
    }
    "checkedIconVisible" -> when {
      v is Chip && arg is Boolean -> {
        v.setCheckedIconVisible(arg)
        true
      }
      v is Chip && arg is Int -> {
        v.setCheckedIconVisible(arg)
        true
      }
      else -> false
    }
    "chipBackgroundColor" -> when {
      v is Chip && arg is ColorStateList? -> {
        v.setChipBackgroundColor(arg as ColorStateList)
        true
      }
      else -> false
    }
    "chipBackgroundColorResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipBackgroundColorResource(arg)
        true
      }
      else -> false
    }
    "chipCornerRadius" -> when {
      v is Chip && arg is Float -> {
        v.setChipCornerRadius(arg)
        true
      }
      else -> false
    }
    "chipCornerRadiusResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipCornerRadiusResource(arg)
        true
      }
      else -> false
    }
    "chipDrawable" -> when {
      v is Chip && arg is ChipDrawable -> {
        v.setChipDrawable(arg)
        true
      }
      else -> false
    }
    "chipEndPadding" -> when {
      v is Chip && arg is Float -> {
        v.setChipEndPadding(arg)
        true
      }
      else -> false
    }
    "chipEndPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipEndPaddingResource(arg)
        true
      }
      else -> false
    }
    "chipIcon" -> when {
      v is Chip && arg is Drawable? -> {
        v.setChipIcon(arg as Drawable)
        true
      }
      else -> false
    }
    "chipIconResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipIconResource(arg)
        true
      }
      else -> false
    }
    "chipIconSize" -> when {
      v is Chip && arg is Float -> {
        v.setChipIconSize(arg)
        true
      }
      else -> false
    }
    "chipIconSizeResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipIconSizeResource(arg)
        true
      }
      else -> false
    }
    "chipIconTint" -> when {
      v is Chip && arg is ColorStateList? -> {
        v.setChipIconTint(arg as ColorStateList)
        true
      }
      else -> false
    }
    "chipIconTintResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipIconTintResource(arg)
        true
      }
      else -> false
    }
    "chipIconVisible" -> when {
      v is Chip && arg is Boolean -> {
        v.setChipIconVisible(arg)
        true
      }
      v is Chip && arg is Int -> {
        v.setChipIconVisible(arg)
        true
      }
      else -> false
    }
    "chipMinHeight" -> when {
      v is Chip && arg is Float -> {
        v.setChipMinHeight(arg)
        true
      }
      else -> false
    }
    "chipMinHeightResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipMinHeightResource(arg)
        true
      }
      else -> false
    }
    "chipStartPadding" -> when {
      v is Chip && arg is Float -> {
        v.setChipStartPadding(arg)
        true
      }
      else -> false
    }
    "chipStartPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipStartPaddingResource(arg)
        true
      }
      else -> false
    }
    "chipStrokeColor" -> when {
      v is Chip && arg is ColorStateList? -> {
        v.setChipStrokeColor(arg as ColorStateList)
        true
      }
      else -> false
    }
    "chipStrokeColorResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipStrokeColorResource(arg)
        true
      }
      else -> false
    }
    "chipStrokeWidth" -> when {
      v is Chip && arg is Float -> {
        v.setChipStrokeWidth(arg)
        true
      }
      else -> false
    }
    "chipStrokeWidthResource" -> when {
      v is Chip && arg is Int -> {
        v.setChipStrokeWidthResource(arg)
        true
      }
      else -> false
    }
    "closeIcon" -> when {
      v is Chip && arg is Drawable? -> {
        v.setCloseIcon(arg as Drawable)
        true
      }
      else -> false
    }
    "closeIconContentDescription" -> when {
      v is Chip && arg is CharSequence? -> {
        v.setCloseIconContentDescription(arg as CharSequence)
        true
      }
      else -> false
    }
    "closeIconEndPadding" -> when {
      v is Chip && arg is Float -> {
        v.setCloseIconEndPadding(arg)
        true
      }
      else -> false
    }
    "closeIconEndPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setCloseIconEndPaddingResource(arg)
        true
      }
      else -> false
    }
    "closeIconResource" -> when {
      v is Chip && arg is Int -> {
        v.setCloseIconResource(arg)
        true
      }
      else -> false
    }
    "closeIconSize" -> when {
      v is Chip && arg is Float -> {
        v.setCloseIconSize(arg)
        true
      }
      else -> false
    }
    "closeIconSizeResource" -> when {
      v is Chip && arg is Int -> {
        v.setCloseIconSizeResource(arg)
        true
      }
      else -> false
    }
    "closeIconStartPadding" -> when {
      v is Chip && arg is Float -> {
        v.setCloseIconStartPadding(arg)
        true
      }
      else -> false
    }
    "closeIconStartPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setCloseIconStartPaddingResource(arg)
        true
      }
      else -> false
    }
    "closeIconTint" -> when {
      v is Chip && arg is ColorStateList? -> {
        v.setCloseIconTint(arg as ColorStateList)
        true
      }
      else -> false
    }
    "closeIconTintResource" -> when {
      v is Chip && arg is Int -> {
        v.setCloseIconTintResource(arg)
        true
      }
      else -> false
    }
    "closeIconVisible" -> when {
      v is Chip && arg is Boolean -> {
        v.setCloseIconVisible(arg)
        true
      }
      v is Chip && arg is Int -> {
        v.setCloseIconVisible(arg)
        true
      }
      else -> false
    }
    "hideMotionSpec" -> when {
      v is Chip && arg is MotionSpec? -> {
        v.setHideMotionSpec(arg as MotionSpec)
        true
      }
      v is FloatingActionButton && arg is MotionSpec -> {
        v.setHideMotionSpec(arg)
        true
      }
      else -> false
    }
    "hideMotionSpecResource" -> when {
      v is Chip && arg is Int -> {
        v.setHideMotionSpecResource(arg)
        true
      }
      v is FloatingActionButton && arg is Int -> {
        v.setHideMotionSpecResource(arg)
        true
      }
      else -> false
    }
    "iconEndPadding" -> when {
      v is Chip && arg is Float -> {
        v.setIconEndPadding(arg)
        true
      }
      else -> false
    }
    "iconEndPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setIconEndPaddingResource(arg)
        true
      }
      else -> false
    }
    "iconStartPadding" -> when {
      v is Chip && arg is Float -> {
        v.setIconStartPadding(arg)
        true
      }
      else -> false
    }
    "iconStartPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setIconStartPaddingResource(arg)
        true
      }
      else -> false
    }
    "onCloseIconClick" -> when {
      v is Chip -> when {
        arg == null -> {
          v.setOnCloseIconClickListener(null as? View.OnClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as ((arg0: View) -> Unit)?
          v.setOnCloseIconClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "showMotionSpec" -> when {
      v is Chip && arg is MotionSpec? -> {
        v.setShowMotionSpec(arg as MotionSpec)
        true
      }
      v is FloatingActionButton && arg is MotionSpec -> {
        v.setShowMotionSpec(arg)
        true
      }
      else -> false
    }
    "showMotionSpecResource" -> when {
      v is Chip && arg is Int -> {
        v.setShowMotionSpecResource(arg)
        true
      }
      v is FloatingActionButton && arg is Int -> {
        v.setShowMotionSpecResource(arg)
        true
      }
      else -> false
    }
    "textAppearance" -> when {
      v is Chip && arg is TextAppearance? -> {
        v.setTextAppearance(arg as TextAppearance)
        true
      }
      v is NavigationMenuItemView && arg is Int -> {
        v.setTextAppearance(arg)
        true
      }
      else -> false
    }
    "textAppearanceResource" -> when {
      v is Chip && arg is Int -> {
        v.setTextAppearanceResource(arg)
        true
      }
      else -> false
    }
    "textEndPadding" -> when {
      v is Chip && arg is Float -> {
        v.setTextEndPadding(arg)
        true
      }
      else -> false
    }
    "textEndPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setTextEndPaddingResource(arg)
        true
      }
      else -> false
    }
    "textStartPadding" -> when {
      v is Chip && arg is Float -> {
        v.setTextStartPadding(arg)
        true
      }
      else -> false
    }
    "textStartPaddingResource" -> when {
      v is Chip && arg is Int -> {
        v.setTextStartPaddingResource(arg)
        true
      }
      else -> false
    }
    "chipSpacing" -> when {
      v is ChipGroup && arg is Int -> {
        v.setChipSpacing(arg)
        true
      }
      else -> false
    }
    "chipSpacingHorizontal" -> when {
      v is ChipGroup && arg is Int -> {
        v.setChipSpacingHorizontal(arg)
        true
      }
      else -> false
    }
    "chipSpacingHorizontalResource" -> when {
      v is ChipGroup && arg is Int -> {
        v.setChipSpacingHorizontalResource(arg)
        true
      }
      else -> false
    }
    "chipSpacingResource" -> when {
      v is ChipGroup && arg is Int -> {
        v.setChipSpacingResource(arg)
        true
      }
      else -> false
    }
    "chipSpacingVertical" -> when {
      v is ChipGroup && arg is Int -> {
        v.setChipSpacingVertical(arg)
        true
      }
      else -> false
    }
    "chipSpacingVerticalResource" -> when {
      v is ChipGroup && arg is Int -> {
        v.setChipSpacingVerticalResource(arg)
        true
      }
      else -> false
    }
    "onCheckedChange" -> when {
      v is ChipGroup -> when {
        arg == null -> {
          v.setOnCheckedChangeListener(null as? ChipGroup.OnCheckedChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as ((arg0: ChipGroup, arg1: Int) -> Unit)?
          v.setOnCheckedChangeListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "singleLine" -> when {
      v is FlowLayout && arg is Boolean -> {
        v.setSingleLine(arg)
        true
      }
      v is ChipGroup && arg is Int -> {
        v.setSingleLine(arg)
        true
      }
      else -> false
    }
    "singleSelection" -> when {
      v is ChipGroup && arg is Boolean -> {
        v.setSingleSelection(arg)
        true
      }
      v is ChipGroup && arg is Int -> {
        v.setSingleSelection(arg)
        true
      }
      else -> false
    }
    "circularRevealOverlayDrawable" -> when {
      v is CircularRevealFrameLayout && arg is Drawable? -> {
        v.setCircularRevealOverlayDrawable(arg as Drawable)
        true
      }
      v is CircularRevealGridLayout && arg is Drawable? -> {
        v.setCircularRevealOverlayDrawable(arg as Drawable)
        true
      }
      v is CircularRevealLinearLayout && arg is Drawable? -> {
        v.setCircularRevealOverlayDrawable(arg as Drawable)
        true
      }
      v is CircularRevealRelativeLayout && arg is Drawable? -> {
        v.setCircularRevealOverlayDrawable(arg as Drawable)
        true
      }
      v is CircularRevealCardView && arg is Drawable? -> {
        v.setCircularRevealOverlayDrawable(arg as Drawable)
        true
      }
      else -> false
    }
    "circularRevealScrimColor" -> when {
      v is CircularRevealFrameLayout && arg is Int -> {
        v.setCircularRevealScrimColor(arg)
        true
      }
      v is CircularRevealGridLayout && arg is Int -> {
        v.setCircularRevealScrimColor(arg)
        true
      }
      v is CircularRevealLinearLayout && arg is Int -> {
        v.setCircularRevealScrimColor(arg)
        true
      }
      v is CircularRevealRelativeLayout && arg is Int -> {
        v.setCircularRevealScrimColor(arg)
        true
      }
      v is CircularRevealCardView && arg is Int -> {
        v.setCircularRevealScrimColor(arg)
        true
      }
      else -> false
    }
    "revealInfo" -> when {
      v is CircularRevealFrameLayout && arg is CircularRevealWidget.RevealInfo? -> {
        v.setRevealInfo(arg as CircularRevealWidget.RevealInfo)
        true
      }
      v is CircularRevealGridLayout && arg is CircularRevealWidget.RevealInfo? -> {
        v.setRevealInfo(arg as CircularRevealWidget.RevealInfo)
        true
      }
      v is CircularRevealLinearLayout && arg is CircularRevealWidget.RevealInfo? -> {
        v.setRevealInfo(arg as CircularRevealWidget.RevealInfo)
        true
      }
      v is CircularRevealRelativeLayout && arg is CircularRevealWidget.RevealInfo? -> {
        v.setRevealInfo(arg as CircularRevealWidget.RevealInfo)
        true
      }
      v is CircularRevealCardView && arg is CircularRevealWidget.RevealInfo? -> {
        v.setRevealInfo(arg as CircularRevealWidget.RevealInfo)
        true
      }
      else -> false
    }
    "compatElevation" -> when {
      v is FloatingActionButton && arg is Float -> {
        v.setCompatElevation(arg)
        true
      }
      else -> false
    }
    "compatElevationResource" -> when {
      v is FloatingActionButton && arg is Int -> {
        v.setCompatElevationResource(arg)
        true
      }
      else -> false
    }
    "compatHoveredFocusedTranslationZ" -> when {
      v is FloatingActionButton && arg is Float -> {
        v.setCompatHoveredFocusedTranslationZ(arg)
        true
      }
      else -> false
    }
    "compatHoveredFocusedTranslationZResource" -> when {
      v is FloatingActionButton && arg is Int -> {
        v.setCompatHoveredFocusedTranslationZResource(arg)
        true
      }
      else -> false
    }
    "compatPressedTranslationZ" -> when {
      v is FloatingActionButton && arg is Float -> {
        v.setCompatPressedTranslationZ(arg)
        true
      }
      else -> false
    }
    "compatPressedTranslationZResource" -> when {
      v is FloatingActionButton && arg is Int -> {
        v.setCompatPressedTranslationZResource(arg)
        true
      }
      else -> false
    }
    "customSize" -> when {
      v is FloatingActionButton && arg is Int -> {
        v.setCustomSize(arg)
        true
      }
      else -> false
    }
    "expandedComponentIdHint" -> when {
      v is FloatingActionButton && arg is Int -> {
        v.setExpandedComponentIdHint(arg)
        true
      }
      else -> false
    }
    "size" -> when {
      v is FloatingActionButton && arg is Int -> {
        v.setSize(arg)
        true
      }
      else -> false
    }
    "supportBackgroundTintList" -> when {
      v is FloatingActionButton && arg is ColorStateList? -> {
        v.setSupportBackgroundTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "supportBackgroundTintMode" -> when {
      v is FloatingActionButton && arg is PorterDuff.Mode? -> {
        v.setSupportBackgroundTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "supportImageTintList" -> when {
      v is FloatingActionButton && arg is ColorStateList? -> {
        v.setSupportImageTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "supportImageTintMode" -> when {
      v is FloatingActionButton && arg is PorterDuff.Mode? -> {
        v.setSupportImageTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "useCompatPadding" -> when {
      v is FloatingActionButton && arg is Boolean -> {
        v.setUseCompatPadding(arg)
        true
      }
      else -> false
    }
    "horizontalPadding" -> when {
      v is NavigationMenuItemView && arg is Int -> {
        v.setHorizontalPadding(arg)
        true
      }
      else -> false
    }
    "needsEmptyIcon" -> when {
      v is NavigationMenuItemView && arg is Boolean -> {
        v.setNeedsEmptyIcon(arg)
        true
      }
      else -> false
    }
    "checkedItem" -> when {
      v is NavigationView && arg is MenuItem -> {
        v.setCheckedItem(arg)
        true
      }
      v is NavigationView && arg is Int -> {
        v.setCheckedItem(arg)
        true
      }
      else -> false
    }
    "itemHorizontalPadding" -> when {
      v is NavigationView && arg is Int -> {
        v.setItemHorizontalPadding(arg)
        true
      }
      else -> false
    }
    "itemHorizontalPaddingResource" -> when {
      v is NavigationView && arg is Int -> {
        v.setItemHorizontalPaddingResource(arg)
        true
      }
      else -> false
    }
    "itemIconPadding" -> when {
      v is NavigationView && arg is Int -> {
        v.setItemIconPadding(arg)
        true
      }
      else -> false
    }
    "itemIconPaddingResource" -> when {
      v is NavigationView && arg is Int -> {
        v.setItemIconPaddingResource(arg)
        true
      }
      else -> false
    }
    "itemTextAppearance" -> when {
      v is NavigationView && arg is Int -> {
        v.setItemTextAppearance(arg)
        true
      }
      else -> false
    }
    "navigationItemSelectedListener" -> when {
      v is NavigationView && arg is NavigationView.OnNavigationItemSelectedListener? -> {
        v.setNavigationItemSelectedListener(arg as NavigationView.OnNavigationItemSelectedListener)
        true
      }
      else -> false
    }
    "inlineLabel" -> when {
      v is TabLayout && arg is Boolean -> {
        v.setInlineLabel(arg)
        true
      }
      else -> false
    }
    "inlineLabelResource" -> when {
      v is TabLayout && arg is Int -> {
        v.setInlineLabelResource(arg)
        true
      }
      else -> false
    }
    "selectedTabIndicator" -> when {
      v is TabLayout && arg is Drawable? -> {
        v.setSelectedTabIndicator(arg as Drawable)
        true
      }
      v is TabLayout && arg is Int -> {
        v.setSelectedTabIndicator(arg)
        true
      }
      else -> false
    }
    "selectedTabIndicatorColor" -> when {
      v is TabLayout && arg is Int -> {
        v.setSelectedTabIndicatorColor(arg)
        true
      }
      else -> false
    }
    "selectedTabIndicatorGravity" -> when {
      v is TabLayout && arg is Int -> {
        v.setSelectedTabIndicatorGravity(arg)
        true
      }
      else -> false
    }
    "tabGravity" -> when {
      v is TabLayout && arg is Int -> {
        v.setTabGravity(arg)
        true
      }
      else -> false
    }
    "tabIconTint" -> when {
      v is TabLayout && arg is ColorStateList? -> {
        v.setTabIconTint(arg as ColorStateList)
        true
      }
      else -> false
    }
    "tabIconTintResource" -> when {
      v is TabLayout && arg is Int -> {
        v.setTabIconTintResource(arg)
        true
      }
      else -> false
    }
    "tabIndicatorFullWidth" -> when {
      v is TabLayout && arg is Boolean -> {
        v.setTabIndicatorFullWidth(arg)
        true
      }
      else -> false
    }
    "tabMode" -> when {
      v is TabLayout && arg is Int -> {
        v.setTabMode(arg)
        true
      }
      else -> false
    }
    "tabRippleColor" -> when {
      v is TabLayout && arg is ColorStateList -> {
        v.setTabRippleColor(arg)
        true
      }
      else -> false
    }
    "tabRippleColorResource" -> when {
      v is TabLayout && arg is Int -> {
        v.setTabRippleColorResource(arg)
        true
      }
      else -> false
    }
    "tabTextColors" -> when {
      v is TabLayout && arg is ColorStateList? -> {
        v.setTabTextColors(arg as ColorStateList)
        true
      }
      else -> false
    }
    "unboundedRipple" -> when {
      v is TabLayout && arg is Boolean -> {
        v.setUnboundedRipple(arg)
        true
      }
      else -> false
    }
    "unboundedRippleResource" -> when {
      v is TabLayout && arg is Int -> {
        v.setUnboundedRippleResource(arg)
        true
      }
      else -> false
    }
    "boxBackgroundColor" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setBoxBackgroundColor(arg)
        true
      }
      else -> false
    }
    "boxBackgroundColorResource" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setBoxBackgroundColorResource(arg)
        true
      }
      else -> false
    }
    "boxBackgroundMode" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setBoxBackgroundMode(arg)
        true
      }
      else -> false
    }
    "boxStrokeColor" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setBoxStrokeColor(arg)
        true
      }
      else -> false
    }
    "counterEnabled" -> when {
      v is TextInputLayout && arg is Boolean -> {
        v.setCounterEnabled(arg)
        true
      }
      else -> false
    }
    "counterMaxLength" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setCounterMaxLength(arg)
        true
      }
      else -> false
    }
    "defaultHintTextColor" -> when {
      v is TextInputLayout && arg is ColorStateList? -> {
        v.setDefaultHintTextColor(arg as ColorStateList)
        true
      }
      else -> false
    }
    "error" -> when {
      v is TextInputLayout && arg is CharSequence? -> {
        v.setError(arg as CharSequence)
        true
      }
      else -> false
    }
    "errorEnabled" -> when {
      v is TextInputLayout && arg is Boolean -> {
        v.setErrorEnabled(arg)
        true
      }
      else -> false
    }
    "errorTextAppearance" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setErrorTextAppearance(arg)
        true
      }
      else -> false
    }
    "errorTextColor" -> when {
      v is TextInputLayout && arg is ColorStateList? -> {
        v.setErrorTextColor(arg as ColorStateList)
        true
      }
      else -> false
    }
    "helperText" -> when {
      v is TextInputLayout && arg is CharSequence? -> {
        v.setHelperText(arg as CharSequence)
        true
      }
      else -> false
    }
    "helperTextColor" -> when {
      v is TextInputLayout && arg is ColorStateList? -> {
        v.setHelperTextColor(arg as ColorStateList)
        true
      }
      else -> false
    }
    "helperTextEnabled" -> when {
      v is TextInputLayout && arg is Boolean -> {
        v.setHelperTextEnabled(arg)
        true
      }
      else -> false
    }
    "helperTextTextAppearance" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setHelperTextTextAppearance(arg)
        true
      }
      else -> false
    }
    "hint" -> when {
      v is TextInputLayout && arg is CharSequence? -> {
        v.setHint(arg as CharSequence)
        true
      }
      else -> false
    }
    "hintAnimationEnabled" -> when {
      v is TextInputLayout && arg is Boolean -> {
        v.setHintAnimationEnabled(arg)
        true
      }
      else -> false
    }
    "hintEnabled" -> when {
      v is TextInputLayout && arg is Boolean -> {
        v.setHintEnabled(arg)
        true
      }
      else -> false
    }
    "hintTextAppearance" -> when {
      v is TextInputLayout && arg is Int -> {
        v.setHintTextAppearance(arg)
        true
      }
      else -> false
    }
    "passwordVisibilityToggleContentDescription" -> when {
      v is TextInputLayout && arg is CharSequence? -> {
        v.setPasswordVisibilityToggleContentDescription(arg as CharSequence)
        true
      }
      v is TextInputLayout && arg is Int -> {
        v.setPasswordVisibilityToggleContentDescription(arg)
        true
      }
      else -> false
    }
    "passwordVisibilityToggleDrawable" -> when {
      v is TextInputLayout && arg is Drawable? -> {
        v.setPasswordVisibilityToggleDrawable(arg as Drawable)
        true
      }
      v is TextInputLayout && arg is Int -> {
        v.setPasswordVisibilityToggleDrawable(arg)
        true
      }
      else -> false
    }
    "passwordVisibilityToggleEnabled" -> when {
      v is TextInputLayout && arg is Boolean -> {
        v.setPasswordVisibilityToggleEnabled(arg)
        true
      }
      else -> false
    }
    "passwordVisibilityToggleTintList" -> when {
      v is TextInputLayout && arg is ColorStateList? -> {
        v.setPasswordVisibilityToggleTintList(arg as ColorStateList)
        true
      }
      else -> false
    }
    "passwordVisibilityToggleTintMode" -> when {
      v is TextInputLayout && arg is PorterDuff.Mode? -> {
        v.setPasswordVisibilityToggleTintMode(arg as PorterDuff.Mode)
        true
      }
      else -> false
    }
    "textInputAccessibilityDelegate" -> when {
      v is TextInputLayout && arg is TextInputLayout.AccessibilityDelegate -> {
        v.setTextInputAccessibilityDelegate(arg)
        true
      }
      else -> false
    }
    "typeface" -> when {
      v is TextInputLayout && arg is Typeface? -> {
        v.setTypeface(arg as Typeface)
        true
      }
      else -> false
    }
    else -> false
  }
}
