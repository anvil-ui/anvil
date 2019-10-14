package trikita.anvil.yoga.layout;

import android.view.View;
import android.view.ViewGroup;

import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import com.facebook.yoga.android.YogaLayout;

import trikita.anvil.Anvil;
import trikita.anvil.BaseDSL;

public class YogaDSL implements Anvil.AttributeSetter {

    static {
        Anvil.registerAttributeSetter(new YogaDSL());
    }

    public static Void yogaLayout(Anvil.Renderable r) {
        return BaseDSL.v(YogaLayout.class, r);
    }

    public static Void flexDirection(YogaFlexDirection direction) {
        return BaseDSL.attr("flexDirection", direction);
    }

    public static Void alignItems(YogaAlign align) {
        return BaseDSL.attr("alignItems", align);
    }

    public static Void alignContent(Float alignContent) {
        return BaseDSL.attr("alignContent", alignContent);
    }

    public static Void alignSelf(Float alignSelf) {
        return BaseDSL.attr("alignSelf", alignSelf);
    }

    public static Void aspectRatio(Float aspectRatio) {
        return BaseDSL.attr("aspectRatio", aspectRatio);
    }

    public static Void borderLeft(Float left) {
        return BaseDSL.attr("borderLeft", left);
    }

    public static Void borderRight(Float left) {
        return BaseDSL.attr("borderRight", left);
    }

    public static Void borderTop(Float left) {
        return BaseDSL.attr("borderTop", left);
    }

    public static Void borderBottom(Float left) {
        return BaseDSL.attr("borderBottom", left);
    }

    public static Void borderStart(Float left) {
        return BaseDSL.attr("borderStart", left);
    }

    public static Void borderEnd(Float left) {
        return BaseDSL.attr("borderEnd", left);
    }

    public static Void borderHorizontal(Float left) {
        return BaseDSL.attr("borderHorizontal", left);
    }

    public static Void borderVertical(Float left) {
        return BaseDSL.attr("borderVertical", left);
    }

    public static Void borderAll(Float left) {
        return BaseDSL.attr("borderAll", left);
    }

    public static Void marginAutoLeft() {
        return BaseDSL.attr("marginAutoLeft", YogaEdge.LEFT);
    }

    public static Void marginAutoRight() {
        return BaseDSL.attr("marginAutoRight", YogaEdge.RIGHT);
    }

    public static Void marginAutoTop() {
        return BaseDSL.attr("marginAutoTop", YogaEdge.TOP);
    }

    public static Void marginAutoBottom() {
        return BaseDSL.attr("marginAutoBottom", YogaEdge.BOTTOM);
    }

    public static Void marginAutoStart() {
        return BaseDSL.attr("marginAutoStart", YogaEdge.START);
    }

    public static Void marginAutoEnd() {
        return BaseDSL.attr("marginAutoEnd", YogaEdge.END);
    }

    public static Void marginAutoHorizontal() {
        return BaseDSL.attr("marginAutoHorizontal", YogaEdge.HORIZONTAL);
    }

    public static Void marginAutoVertical() {
        return BaseDSL.attr("marginAutoVertical", YogaEdge.VERTICAL);
    }

    public static Void marginAutoAll() {
        return BaseDSL.attr("marginAutoAll", YogaEdge.ALL);
    }

    public static Void direction(YogaDirection direction) {
        return BaseDSL.attr("direction", direction);
    }

    public static Void display(YogaDisplay display) {
        return BaseDSL.attr("display", display);
    }

    public static Void flexBasis(Float display) {
        return BaseDSL.attr("flexBasis", display);
    }

    public static Void flexDirection(Float display) {
        return BaseDSL.attr("flexDirection", display);
    }

    public static Void flexGrow(Float display) {
        return BaseDSL.attr("flexGrow", display);
    }

    public static Void flexShrink(Float display) {
        return BaseDSL.attr("flexShrink", display);
    }

    public static Void yogaHeight(Float display) {
        return BaseDSL.attr("yogaHeight", display);
    }

    public static Void yogaWidth(Float display) {
        return BaseDSL.attr("yogaWidth", display);
    }

    public static Void justifyContent(YogaJustify justify) {
        return BaseDSL.attr("justifyContent", justify);
    }

    public static Void marginLeft(Float margin) {
        return BaseDSL.attr("marginLeft", margin);
    }

    public static Void marginRight(Float margin) {
        return BaseDSL.attr("marginRight", margin);
    }

    public static Void marginTop(Float margin) {
        return BaseDSL.attr("marginTop", margin);
    }

    public static Void marginBottom(Float margin) {
        return BaseDSL.attr("marginBottom", margin);
    }

    public static Void marginStart(Float margin) {
        return BaseDSL.attr("marginStart", margin);
    }

    public static Void marginEnd(Float margin) {
        return BaseDSL.attr("marginEnd", margin);
    }

    public static Void marginHorizontal(Float margin) {
        return BaseDSL.attr("marginHorizontal", margin);
    }

    public static Void marginVertical(Float margin) {
        return BaseDSL.attr("marginVertical", margin);
    }

    public static Void marginAll(Float margin) {
        return BaseDSL.attr("marginAll", margin);
    }

    public static Void paddingAll(Float margin) {
        return BaseDSL.attr("paddingAll", margin);
    }

    public static Void paddingLeft(Float margin) {
        return BaseDSL.attr("paddingLeft", margin);
    }

    public static Void paddingRight(Float margin) {
        return BaseDSL.attr("paddingRight", margin);
    }

    public static Void paddingTop(Float margin) {
        return BaseDSL.attr("paddingTop", margin);
    }

    public static Void paddingBottom(Float margin) {
        return BaseDSL.attr("paddingBottom", margin);
    }

    public static Void paddingStart(Float margin) {
        return BaseDSL.attr("paddingStart", margin);
    }

    public static Void paddingEnd(Float margin) {
        return BaseDSL.attr("paddingEnd", margin);
    }

    public static Void paddingHorizontal(Float margin) {
        return BaseDSL.attr("paddingHorizontal", margin);
    }

    public static Void paddingVertical(Float margin) {
        return BaseDSL.attr("paddingVertical", margin);
    }

    public static Void positionType(YogaPositionType positionType) {
        return BaseDSL.attr("positionType", positionType);
    }

    public static Void wrap(YogaWrap wrap) {
        return BaseDSL.attr("wrap", wrap);
    }

    public static Void flex(float weight) {
        return BaseDSL.attr("flex", weight);
    }

    public static Void flexBasisPercent(float percent) {
        return BaseDSL.attr("flexBasisPercent", percent);
    }

    public static Void heightPercent(float percent) {
        return BaseDSL.attr("heightPercent", percent);
    }

    public static Void marginLeftPercent(float percent) {
        return BaseDSL.attr("marginLeftPercent", percent);
    }

    public static Void marginRightPercent(float percent) {
        return BaseDSL.attr("marginRightPercent", percent);
    }

    public static Void marginTopPercent(float percent) {
        return BaseDSL.attr("marginTopPercent", percent);
    }

    public static Void marginBottomPercent(float percent) {
        return BaseDSL.attr("marginBottomPercent", percent);
    }

    public static Void marginStartPercent(float percent) {
        return BaseDSL.attr("marginStartPercent", percent);
    }

    public static Void marginEndPercent(float percent) {
        return BaseDSL.attr("marginEndPercent", percent);
    }

    public static Void marginHorizontalPercent(float percent) {
        return BaseDSL.attr("marginHorizontalPercent", percent);
    }

    public static Void marginVerticalPercent(float percent) {
        return BaseDSL.attr("marginVerticalPercent", percent);
    }

    public static Void marginAllPercent(float percent) {
        return BaseDSL.attr("marginAllPercent", percent);
    }

    public static Void paddingLeftPercent(float percent) {
        return BaseDSL.attr("paddingLeftPercent", percent);
    }

    public static Void paddingRightPercent(float percent) {
        return BaseDSL.attr("paddingRightPercent", percent);
    }

    public static Void paddingTopPercent(float percent) {
        return BaseDSL.attr("paddingTopPercent", percent);
    }

    public static Void paddingBottomPercent(float percent) {
        return BaseDSL.attr("paddingBottomPercent", percent);
    }

    public static Void paddingStartPercent(float percent) {
        return BaseDSL.attr("paddingStartPercent", percent);
    }

    public static Void paddingEndPercent(float percent) {
        return BaseDSL.attr("paddingEndPercent", percent);
    }

    public static Void paddingHorizontalPercent(float percent) {
        return BaseDSL.attr("paddingHorizontalPercent", percent);
    }

    public static Void paddingVerticalPercent(float percent) {
        return BaseDSL.attr("paddingVerticalPercent", percent);
    }

    public static Void paddingAllPercent(float percent) {
        return BaseDSL.attr("paddingAllPercent", percent);
    }

    public static Void positionLeftPercent(float percent) {
        return BaseDSL.attr("positionLeftPercent", percent);
    }

    public static Void positionRightPercent(float percent) {
        return BaseDSL.attr("positionRightPercent", percent);
    }

    public static Void positionTopPercent(float percent) {
        return BaseDSL.attr("positionTopPercent", percent);
    }

    public static Void positionBottomPercent(float percent) {
        return BaseDSL.attr("positionBottomPercent", percent);
    }

    public static Void positionStartPercent(float percent) {
        return BaseDSL.attr("positionStartPercent", percent);
    }

    public static Void positionEndPercent(float percent) {
        return BaseDSL.attr("positionEndPercent", percent);
    }

    public static Void positionHorizontalPercent(float percent) {
        return BaseDSL.attr("positionHorizontalPercent", percent);
    }

    public static Void positionVerticalPercent(float percent) {
        return BaseDSL.attr("positionVerticalPercent", percent);
    }

    public static Void positionAllPercent(float percent) {
        return BaseDSL.attr("positionAllPercent", percent);
    }

    public static Void widthPercent(float percent) {
        return BaseDSL.attr("widthPercent", percent);
    }

    public static Void maxWidthPercent(float percent) {
        return BaseDSL.attr("maxWidthPercent", percent);
    }

    public static Void maxHeightPercent(float percent) {
        return BaseDSL.attr("maxHeightPercent", percent);
    }

    public static Void minHeightPercent(float percent) {
        return BaseDSL.attr("minHeightPercent", percent);
    }

    public static Void minWidthPercent(float percent) {
        return BaseDSL.attr("minWidthPercent", percent);
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        switch (name) {
            case "flexDirection":
                if (v instanceof YogaLayout && value instanceof YogaFlexDirection) {
                    ((YogaLayout) v).getYogaNode().setFlexDirection((YogaFlexDirection) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "alignItems":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaAlign) {
                    ((YogaLayout) v).getYogaNode().setAlignItems((YogaAlign) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "alignContent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setAlignContent(YogaAlign.fromInt(Math.round((Float) value)));
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "alignSelf":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setAlignSelf(YogaAlign.fromInt(Math.round((Float) value)));
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "aspectRatio":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setAspectRatio((Float) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "borderLeft":
                if (setBorder(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "borderRight":
                if (setBorder(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "borderTop":
                if (setBorder(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "borderBottom":
                if (setBorder(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "borderStart":
                if (setBorder(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "borderEnd":
                if (setBorder(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "borderHorizontal":
                if (setBorder(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "borderVertical":
                if (setBorder(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "borderAll":
                if (setBorder(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "marginAutoLeft":
            case "marginAutoStart":
            case "marginAutoRight":
            case "marginAutoTop":
            case "marginAutoBottom":
            case "marginAutoEnd":
            case "marginAutoHorizontal":
            case "marginAutoVertical":
            case "marginAutoAll":
                if (setMarginAuto(v, value)) {
                    return true;
                }
                break;
            case "direction":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaDirection) {
                    ((YogaLayout) v).getYogaNode().setDirection((YogaDirection) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "display":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaDisplay) {
                    ((YogaLayout) v).getYogaNode().setDisplay((YogaDisplay) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "flexBasis":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setFlexBasis((Float) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "flexGrow":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setFlexGrow((Float) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "flexShrink":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setFlexShrink((Float) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "yogaHeight":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setHeight((Float) value);
                    return true;
                }
                break;
            case "yogaWidth":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setWidth((Float) value);
                    return true;
                }
                break;
            case "flex":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v.getParent()).getYogaNodeForView(v).setFlex((Float) value);
                    return true;
                }
                break;
            case "justifyContent":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaJustify) {
                    ((YogaLayout) v).getYogaNode().setJustifyContent((YogaJustify) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "marginLeft":
                if (setMargin(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "marginStart":
                if (setMargin(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "marginRight":
                if (setMargin(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "marginTop":
                if (setMargin(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "marginBottom":
                if (setMargin(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "marginEnd":
                if (setMargin(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "marginHorizontal":
                if (setMargin(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "marginVertical":
                if (setMargin(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "marginAll":
                if (setMargin(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "paddingLeft":
                if (setPadding(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "paddingStart":
                if (setPadding(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "paddingRight":
                if (setPadding(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "paddingTop":
                if (setPadding(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "paddingBottom":
                if (setPadding(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "paddingEnd":
                if (setPadding(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "paddingHorizontal":
                if (setPadding(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "paddingVertical":
                if (setPadding(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "paddingAll":
                if (setPadding(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "positionLeft":
                if (setPosition(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "positionStart":
                if (setPosition(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "positionRight":
                if (setPosition(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "positionTop":
                if (setPosition(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "positionBottom":
                if (setPosition(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "positionEnd":
                if (setPosition(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "positionHorizontal":
                if (setPosition(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "positionVertical":
                if (setPosition(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "positionAll":
                if (setPosition(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "positionType":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaPositionType) {
                    ((YogaLayout) v).getYogaNode().setPositionType((YogaPositionType) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "wrap":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaWrap) {
                    ((YogaLayout) v).getYogaNode().setWrap((YogaWrap) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "flexBasisPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setFlexBasisPercent((Float) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "heightPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setHeightPercent((Float) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "marginLeftPercent":
                if (setMarginPercent(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "marginRightPercent":
                if (setMarginPercent(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "marginTopPercent":
                if (setMarginPercent(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "marginBottomPercent":
                if (setMarginPercent(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "marginStartPercent":
                if (setMarginPercent(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "marginEndPercent":
                if (setMarginPercent(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "marginHorizontalPercent":
                if (setMarginPercent(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "marginVerticalPercent":
                if (setMarginPercent(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "marginAllPercent":
                if (setMarginPercent(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "positionLeftPercent":
                if (setPositionPercent(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "positionRightPercent":
                if (setPositionPercent(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "positionTopPercent":
                if (setPositionPercent(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "positionBottomPercent":
                if (setPositionPercent(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "positionStartPercent":
                if (setPositionPercent(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "positionEndPercent":
                if (setPositionPercent(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "positionHorizontalPercent":
                if (setPositionPercent(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "positionVerticalPercent":
                if (setPositionPercent(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "positionAllPercent":
                if (setPositionPercent(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "paddingLeftPercent":
                if (setPaddingPercent(v, value, YogaEdge.LEFT)) {
                    return true;
                }
                break;
            case "paddingRightPercent":
                if (setPaddingPercent(v, value, YogaEdge.RIGHT)) {
                    return true;
                }
                break;
            case "paddingTopPercent":
                if (setPaddingPercent(v, value, YogaEdge.TOP)) {
                    return true;
                }
                break;
            case "paddingBottomPercent":
                if (setPaddingPercent(v, value, YogaEdge.BOTTOM)) {
                    return true;
                }
                break;
            case "paddingStartPercent":
                if (setPaddingPercent(v, value, YogaEdge.START)) {
                    return true;
                }
                break;
            case "paddingEndPercent":
                if (setPaddingPercent(v, value, YogaEdge.END)) {
                    return true;
                }
                break;
            case "paddingHorizontalPercent":
                if (setPaddingPercent(v, value, YogaEdge.HORIZONTAL)) {
                    return true;
                }
                break;
            case "paddingVerticalPercent":
                if (setPaddingPercent(v, value, YogaEdge.VERTICAL)) {
                    return true;
                }
                break;
            case "paddingAllPercent":
                if (setPaddingPercent(v, value, YogaEdge.ALL)) {
                    return true;
                }
                break;
            case "widthPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setWidthPercent((Float) value);
                    return true;
                }
                break;
            case "minWidthPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setMinWidthPercent((Float) value);
                    return true;
                }
                break;
            case "minHeightPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setMinHeightPercent((Float) value);
                    return true;
                }
                break;
            case "maxWidthPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setMaxWidthPercent((Float) value);
                    return true;
                }
                break;
            case "maxHeightPercent":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v).getYogaNode().setMaxHeightPercent((Float) value);
                    return true;
                }
                break;
        }
        return false;
    }

    private boolean setBorder(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setBorder(edge, (Float) value);
            return true;
        }
        return false;
    }

    private boolean setMarginAuto(View v, Object value) {
        if (v.getParent() instanceof YogaLayout && value instanceof YogaEdge) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setMarginAuto((YogaEdge) value);
            return true;
        }
        return false;
    }

    private boolean setMarginPercent(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setMarginPercent(edge, (Float) value);
            return true;
        }
        return false;
    }

    private boolean setPaddingPercent(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setPaddingPercent(edge, (Float) value);
            return true;
        }
        return false;
    }

    private boolean setPositionPercent(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setPositionPercent(edge, (Float) value);
            return true;
        }
        return false;
    }

    private boolean setMargin(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setMargin(edge, (Float) value);
            return true;
        }
        return false;
    }

    private boolean setPadding(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setPadding(edge, (Float) value);
            return true;
        }
        return false;
    }

    private boolean setPosition(View v, Object value, YogaEdge edge) {
        if (v.getParent() instanceof YogaLayout && value instanceof Float) {
            ((YogaLayout) v.getParent()).getYogaNodeForView(v).setPosition(edge, (Float) value);
            return true;
        }
        return false;
    }
}
