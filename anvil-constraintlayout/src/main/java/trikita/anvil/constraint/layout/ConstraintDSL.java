package trikita.anvil.constraint.layout;

import android.view.View;

import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.constraintlayout.widget.Placeholder;

import java.util.LinkedList;

import trikita.anvil.Anvil;
import trikita.anvil.BaseDSL;

/**
 * DSL for creating views and settings their attributes.
 * It contains views and their setters from the library constraint-layout.
 */
public final class ConstraintDSL implements Anvil.AttributeSetter {
    static {
        Anvil.registerAttributeSetter(new ConstraintDSL());
    }

    public static BaseDSL.ViewClassResult constraintLayout() {
        return BaseDSL.v(ConstraintLayout.class);
    }

    public static Void constraintLayout(final Anvil.Renderable r) {
        return BaseDSL.v(ConstraintLayout.class, new Anvil.Renderable() {
            @Override
            public void view() {
                r.view();
                applyConstraints();
            }
        });
    }

    public static BaseDSL.ViewClassResult barrier() {
        return BaseDSL.v(Barrier.class);
    }

    public static Void barrier(Anvil.Renderable r) {
        return BaseDSL.v(Barrier.class, r);
    }

    public static BaseDSL.ViewClassResult placeholder() {
        return BaseDSL.v(Placeholder.class);
    }

    public static Void placeholder(Anvil.Renderable r) {
        return BaseDSL.v(Placeholder.class, r);
    }

    public static BaseDSL.ViewClassResult group() {
        return BaseDSL.v(Group.class);
    }

    public static Void group(Anvil.Renderable r) {
        return BaseDSL.v(Group.class, r);
    }

    public static BaseDSL.ViewClassResult guideline() {
        return BaseDSL.v(Guideline.class);
    }

    public static Void guideline(Anvil.Renderable r) {
        return BaseDSL.v(Guideline.class, r);
    }

    public static Void type(int arg) {
        return BaseDSL.attr("type", arg);
    }

    public static Void referencedIds(int[] arg) {
        return BaseDSL.attr("referencedIds", arg);
    }

    public static Void leftConstraintToLeft(int toId) {
        return BaseDSL.attr("leftConstraintToLeft", toId);
    }

    public static Void leftConstraintToRight(int toId) {
        return BaseDSL.attr("leftConstraintToRight", toId);
    }

    public static Void leftConstraintToTop(int toId) {
        return BaseDSL.attr("leftConstraintToTop", toId);
    }

    public static Void leftConstraintToBottom(int toId) {
        return BaseDSL.attr("leftConstraintToBottom", toId);
    }

    public static Void leftConstraintToParent() {
        return leftConstraintToLeft(ConstraintSet.PARENT_ID);
    }

    public static Void rightConstraintToRight(int toId) {
        return BaseDSL.attr("rightConstraintToRight", toId);
    }

    public static Void rightConstraintToLeft(int toId) {
        return BaseDSL.attr("rightConstraintToLeft", toId);
    }

    public static Void rightConstraintToTop(int toId) {
        return BaseDSL.attr("rightConstraintToTop", toId);
    }

    public static Void rightConstraintToBottom(int toId) {
        return BaseDSL.attr("rightConstraintToBottom", toId);
    }

    public static Void rightConstraintToParent() {
        return rightConstraintToRight(ConstraintSet.PARENT_ID);
    }

    public static Void topConstraintToRight(int toId) {
        return BaseDSL.attr("topConstraintToRight", toId);
    }

    public static Void topConstraintToLeft(int toId) {
        return BaseDSL.attr("topConstraintToLeft", toId);
    }

    public static Void topConstraintToTop(int toId) {
        return BaseDSL.attr("topConstraintToTop", toId);
    }

    public static Void topConstraintToBottom(int toId) {
        return BaseDSL.attr("topConstraintToBottom", toId);
    }

    public static Void topConstraintToParent() {
        return topConstraintToTop(ConstraintSet.PARENT_ID);
    }

    public static Void bottomConstraintToRight(int toId) {
        return BaseDSL.attr("bottomConstraintToRight", toId);
    }

    public static Void bottomConstraintToLeft(int toId) {
        return BaseDSL.attr("bottomConstraintToLeft", toId);
    }

    public static Void bottomConstraintToTop(int toId) {
        return BaseDSL.attr("bottomConstraintToTop", toId);
    }

    public static Void bottomConstraintToBottom(int toId) {
        return BaseDSL.attr("bottomConstraintToBottom", toId);
    }

    public static Void bottomConstraintToParent() {
        return bottomConstraintToBottom(ConstraintSet.PARENT_ID);
    }

    public static Void chain(int leftId, int leftSide, int rightId, int rightSide, int[] chainIds, float[] weights, int style) {
        return BaseDSL.attr("chain", new ConstraintChain(leftId, leftSide, rightId, rightSide, chainIds, weights, style));
    }

    public static Void circleConstraint(int id, int angle, int radius) {
        return BaseDSL.attr("circleConstraint", new CircleConstraint(0, id, angle, radius));
    }

    public static Void id(int id) {
        return BaseDSL.attr("idForConstraint", id);
    }

    public static Void applyConstraints() {
        return BaseDSL.attr("apply", true);
    }

    public static Void orientation(int orientation) {
        return BaseDSL.attr("orientation", orientation);
    }

    public static Void guideBegin(int margin) {
        return BaseDSL.attr("guideBegin", margin);
    }

    public static Void guideEnd(int margin) {
        return BaseDSL.attr("guideEnd", margin);
    }

    public static Void guidelinePercent(float ratio) {
        return BaseDSL.attr("guidelinePercent", ratio);
    }

    public static Void contentId(int contentId) {
        return BaseDSL.attr("contentId", contentId);
    }

    public final int CONSTRAINTS_KEY = 1 + 2 << 24;
    public final int CHAIN_KEY = 1 + 3 << 24;
    public final int CIRCLE_KEY = 1 + 4 << 24;

    public boolean set(View v, String name, final Object arg, final Object old) {
        switch (name) {
            case "allowsGoneWidget":
                if (v instanceof Barrier && arg instanceof Boolean) {
                    ((Barrier) v).setAllowsGoneWidget((boolean) arg);
                    return true;
                }
                break;
            case "constraintSet":
                if (v instanceof ConstraintLayout && arg instanceof ConstraintSet) {
                    ((ConstraintLayout) v).setConstraintSet((ConstraintSet) arg);
                    return true;
                }
                break;
            case "leftConstraintToLeft":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.LEFT, (Integer) arg, ConstraintSet.LEFT);
                    return true;
                }
                break;
            case "leftConstraintToRight":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.LEFT, (Integer) arg, ConstraintSet.RIGHT);
                    return true;
                }
                break;
            case "leftConstraintToTop":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.LEFT, (Integer) arg, ConstraintSet.TOP);
                    return true;
                }
                break;
            case "leftConstraintToBottom":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.LEFT, (Integer) arg, ConstraintSet.BOTTOM);
                    return true;
                }
                break;
            case "rightConstraintToLeft":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.RIGHT, (Integer) arg, ConstraintSet.LEFT);
                    return true;
                }
                break;
            case "rightConstraintToRight":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.RIGHT, (Integer) arg, ConstraintSet.RIGHT);
                    return true;
                }
                break;
            case "rightConstraintToTop":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.RIGHT, (Integer) arg, ConstraintSet.TOP);
                    return true;
                }
                break;
            case "rightConstraintToBottom":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.RIGHT, (Integer) arg, ConstraintSet.BOTTOM);
                    return true;
                }
                break;
            case "topConstraintToLeft":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.TOP, (Integer) arg, ConstraintSet.LEFT);
                    return true;
                }
                break;
            case "topConstraintToRight":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.TOP, (Integer) arg, ConstraintSet.RIGHT);
                    return true;
                }
                break;
            case "topConstraintToTop":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.TOP, (Integer) arg, ConstraintSet.TOP);
                    return true;
                }
                break;
            case "topConstraintToBottom":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.TOP, (Integer) arg, ConstraintSet.BOTTOM);
                    return true;
                }
                break;
            case "bottomConstraintToLeft":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.BOTTOM, (Integer) arg, ConstraintSet.LEFT);
                    return true;
                }
                break;
            case "bottomConstraintToRight":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.BOTTOM, (Integer) arg, ConstraintSet.RIGHT);
                    return true;
                }
                break;
            case "bottomConstraintToTop":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.BOTTOM, (Integer) arg, ConstraintSet.TOP);
                    return true;
                }
                break;
            case "bottomConstraintToBottom":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof Integer) {
                    addConstraint(((ConstraintLayout) v.getParent()), v.getId(), ConstraintSet.BOTTOM, (Integer) arg, ConstraintSet.BOTTOM);
                    return true;
                }
                break;
            case "chain":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof ConstraintChain) {
                    ConstraintLayout cl = ((ConstraintLayout) v.getParent());
                    LinkedList<ConstraintChain> chainsList = (LinkedList<ConstraintChain>) cl.getTag(CHAIN_KEY);
                    if (chainsList == null) {
                        chainsList = new LinkedList<>();
                    }
                    chainsList.add((ConstraintChain) arg);
                    cl.setTag(CHAIN_KEY, chainsList);
                    return true;
                }
                break;
            case "circleConstraint":
                if (v.getParent() instanceof ConstraintLayout && arg instanceof CircleConstraint) {
                    ConstraintLayout cl = ((ConstraintLayout) v.getParent());
                    LinkedList<CircleConstraint> circlesList = (LinkedList<CircleConstraint>) cl.getTag(CIRCLE_KEY);
                    if (circlesList == null) {
                        circlesList = new LinkedList<>();
                    }
                    CircleConstraint arg2 = (CircleConstraint) arg;
                    arg2.centerId = v.getId();
                    circlesList.add(arg2);
                    cl.setTag(CIRCLE_KEY, circlesList);
                    return true;
                }
                break;
            case "apply":
                if (v instanceof ConstraintLayout && arg instanceof Boolean) {
                    ConstraintLayout cl = ((ConstraintLayout) v);
                    LinkedList<ConstraintSide> constraintSideList = (LinkedList<ConstraintSide>) cl.getTag(CONSTRAINTS_KEY);
                    LinkedList<ConstraintChain> chainsList = (LinkedList<ConstraintChain>) cl.getTag(CHAIN_KEY);
                    LinkedList<CircleConstraint> circlesList = (LinkedList<CircleConstraint>) cl.getTag(CIRCLE_KEY);

                    if ((constraintSideList == null || constraintSideList.size() == 0)
                            && (chainsList == null || chainsList.size() == 0)
                            && (circlesList == null || circlesList.size() == 0)) {
                        return false;
                    }

                    ConstraintSet cs1 = new ConstraintSet();
                    cs1.clone(cl);

                    if (constraintSideList != null && constraintSideList.size() > 0) {
                        for (int i = 0; i < constraintSideList.size(); i++) {
                            ConstraintSide side = constraintSideList.get(i);
                            cs1.connect(side.startID, side.startSide, side.endID, side.endSide);
                        }
                        constraintSideList.clear();
                    }

                    if (chainsList != null && chainsList.size() > 0) {
                        for (int i = 0; i < chainsList.size(); i++) {
                            ConstraintChain chain = chainsList.get(i);
                            cs1.createHorizontalChain(chain.leftId, chain.leftSide, chain.rightId, chain.rightSide, chain.chainIds, chain.weights, chain.style);
                        }
                        chainsList.clear();
                    }

                    if (circlesList != null && circlesList.size() > 0) {
                        for (int i = 0; i < circlesList.size(); i++) {
                            CircleConstraint circleConstraint = circlesList.get(i);
                            cs1.constrainCircle(circleConstraint.id, circleConstraint.centerId, circleConstraint.radius, circleConstraint.angle);
                        }
                        circlesList.clear();
                    }

                    cs1.applyTo(cl);
                    return false;
                }
                break;
            case "maxHeight":
                if (v instanceof ConstraintLayout && arg instanceof Integer) {
                    ((ConstraintLayout) v).setMaxHeight((int) arg);
                    return true;
                }
                break;
            case "maxWidth":
                if (v instanceof ConstraintLayout && arg instanceof Integer) {
                    ((ConstraintLayout) v).setMaxWidth((int) arg);
                    return true;
                }
                break;
            case "minHeight":
                if (v instanceof ConstraintLayout && arg instanceof Integer) {
                    ((ConstraintLayout) v).setMinHeight((int) arg);
                    return true;
                }
                break;
            case "minWidth":
                if (v instanceof ConstraintLayout && arg instanceof Integer) {
                    ((ConstraintLayout) v).setMinWidth((int) arg);
                    return true;
                }
                break;
            case "optimizationLevel":
                if (v instanceof ConstraintLayout && arg instanceof Integer) {
                    ((ConstraintLayout) v).setOptimizationLevel((int) arg);
                    return true;
                }
                break;
            case "referencedIds":
                if (v instanceof Barrier && arg instanceof int[]) {
                    ((Barrier) v).setReferencedIds((int[]) arg);
                    return true;
                }
                if (v instanceof Group && arg instanceof int[]) {
                    ((Group) v).setReferencedIds((int[]) arg);
                    return true;
                }
                break;
            case "type":
                if (v instanceof Barrier && arg instanceof Integer) {
                    ((Barrier) v).setType((int) arg);
                    return true;
                }
                break;
            case "idForConstraint":
                if (arg instanceof Integer) {
                    v.setId((Integer) arg);
                    if (v.getParent() instanceof ConstraintLayout) {
                        ((ConstraintLayout) v.getParent()).onViewAdded(v);
                    }
                    return true;
                }
                break;
            case "orientation":
                if (v instanceof Guideline && arg instanceof Integer) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                    params.orientation = (Integer) arg;
                    v.setLayoutParams(params);
                    return true;
                }
                break;
            case "guideBegin":
                if (v instanceof Guideline && arg instanceof Integer) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                    params.guideBegin = (Integer) arg;
                    v.setLayoutParams(params);
                    return true;
                }
                break;
            case "guideEnd":
                if (v instanceof Guideline && arg instanceof Integer) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                    params.guideEnd = (Integer) arg;
                    v.setLayoutParams(params);
                    return true;
                }
                break;
            case "guidelinePercent":
                if (v instanceof Guideline && arg instanceof Float) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                    params.guidePercent = (Float) arg;
                    v.setLayoutParams(params);
                    return true;
                }
                break;
            case "contentId":
                if (v instanceof Placeholder && arg instanceof Integer) {
                    ((Placeholder) v).setContentId((int) arg);
                    return true;
                }
                break;
        }
        return false;
    }

    private void addConstraint(ConstraintLayout cl, int fromId, int fromSide, int toId, int endSide) {
        LinkedList<ConstraintSide> constraintSideList = (LinkedList<ConstraintSide>) cl.getTag(CONSTRAINTS_KEY);
        if (constraintSideList == null) {
            constraintSideList = new LinkedList<>();
            cl.setTag(CONSTRAINTS_KEY, constraintSideList);
        }
        constraintSideList.add(
                new ConstraintSide(fromId, fromSide, toId, endSide));
    }
}
