package trikita.anvil;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Utils {

    Map<Class, Integer> createdViews = new HashMap<>();
    Map<String, Integer> changedAttrs = new HashMap<>();

    public Utils() {
        Anvil.registerAttributeSetter(new Anvil.AttributeSetter() {
            public boolean set(View v, String name, Object value, Object prevValue) {
                changedAttrs.put(name, !changedAttrs.containsKey(name) ? 1 : (changedAttrs.get(name) + 1));
                return false;
            }
        });
        Anvil.registerViewFactory(new Anvil.ViewFactory() {
            public View fromClass(Context c, Class<? extends View> v) {
                createdViews.put(v, !createdViews.containsKey(v) ? 1 : (createdViews.get(v) + 1));
                return null;
            }
            public View fromXml(ViewGroup parent, int xmlId) {
                return null;
            }
        });
    }

//    public View fromId(View v, int viewId) {
//        if (v instanceof MockLayout) {
//            for (View child : ((MockLayout) v).children) {
//                if (child.getId() == viewId) {
//                    return child;
//                }
//            }
//        }
//        return null;
//    }

    Anvil.Renderable empty = new Anvil.Renderable() {
        public void view() {}
    };

    MockLayout container;

    @Before
    public void setUp() {
        changedAttrs.clear();
        createdViews.clear();
        container = new MockLayout(getContext());
    }

    @After
    public void tearDown() {
        Anvil.unmount(container);
    }


    public Context getContext() {
        return null;
    }

    public static class MockView extends View {
        private int id = 0;
        private Object tag;
        private String text;
        public MockView(Context c) { super(c); }
        public void setId(int id) { this.id = id; }
        public int getId() { return this.id; }
        public void setText(String text) { this.text = text; }
        public String getText() { return this.text; }
        public Object getTag() { return tag; }
        public void setTag(Object tag) { this.tag = tag; }
        public String toString() { return "MockView$"+this.hashCode(); }
    }

    public static class MockLayout extends FrameLayout {
        private List<View> children = new ArrayList<>();

        private int id;
        private Object tag;
        private String text;

        public MockLayout(Context c) {
            super(c);
        }

        public int getChildCount() {
            return children.size();
        }

        public View getChildAt(int index) {
            return children.get(index);
        }

        public int indexOfChild(View v) {
            return children.indexOf(v);
        }

        public void addView(View v, int index) {
            this.children.add(index, v);
            super.addView(v, index);
        }

        public void removeView(View v) {
            this.children.remove(v);
            super.removeView(v);
        }

        public void removeViews(int start, int count) {
            if (count > 0) {
                this.children.subList(start, start+count).clear();
            }
            super.removeViews(start, count);
        }

        public void setId(int id) { this.id = id; }
        public int getId() { return this.id; }
        public Object getTag() { return tag; }
        public void setTag(Object tag) { this.tag = tag; }
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public String toString() { return "MockLayout$"+this.hashCode(); }
    }
}
