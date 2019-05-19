package trikita.anvil;

import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class PropertySetter implements Anvil.AttributeSetter {

    private final Map<Class, Class> WRAP = new HashMap<Class, Class>() {{
        put(int.class, Integer.class);
        put(long.class, Long.class);
        put(double.class, Double.class);
        put(float.class, Float.class);
        put(boolean.class, Boolean.class);
        put(char.class, Character.class);
        put(byte.class, Byte.class);
        put(void.class, Void.class);
        put(short.class, Short.class);

        put(Integer.class, int.class);
        put(Long.class, long.class);
        put(Double.class, double.class);
        put(Float.class, float.class);
        put(Boolean.class, boolean.class);
        put(Character.class, char.class);
        put(Byte.class, byte.class);
        put(Void.class, void.class);
        put(Short.class, short.class);
    }};

    private boolean assignable(Class<?> a, Class<?> b) {
        if (a == null) {
            return false;
        }
        if (b == null) {
            return !(a.isPrimitive());
        }
        if (b.isPrimitive() && !a.isPrimitive()) {
            b = WRAP.get(b);
            if (b == null) {
                return false;
            }
        }
        if (a.isPrimitive() && !b.isPrimitive()) {
            b = WRAP.get(b);
            if (b == null) {
                return false;
            }
        }
        if (b.equals(a)) {
            return true;
        }
        if (b.isPrimitive()) {
            if (a.isPrimitive() == false) {
                return false;
            }
            if (Integer.TYPE.equals(b)) {
                return Long.TYPE.equals(a) || Float.TYPE.equals(a) || Double.TYPE.equals(a);
            }
            if (Long.TYPE.equals(b)) {
                return Float.TYPE.equals(a) || Double.TYPE.equals(a);
            }
            if (Boolean.TYPE.equals(b)) {
                return false;
            }
            if (Double.TYPE.equals(b)) {
                return false;
            }
            if (Float.TYPE.equals(b)) {
                return Double.TYPE.equals(a);
            }
            if (Character.TYPE.equals(b)) {
                return Integer.TYPE.equals(a) || Long.TYPE.equals(a) ||
                        Float.TYPE.equals(a) || Double.TYPE.equals(a);
            }
            if (Short.TYPE.equals(b)) {
                return Integer.TYPE.equals(a) || Long.TYPE.equals(a) ||
                        Float.TYPE.equals(a) || Double.TYPE.equals(a);
            }
            if (Byte.TYPE.equals(b)) {
                return Short.TYPE.equals(a) || Integer.TYPE.equals(a) ||
                        Long.TYPE.equals(a) || Float.TYPE.equals(a) ||
                        Double.TYPE.equals(a);
            }
            return false;
        }
        return a.isAssignableFrom(b);
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String setter = "set" + name;
        String listener = "set" + name + "Listener";
        Class<?> cls = v.getClass();
        Log.d("REFLECT", "set attr " + name + " for view " + v.getClass().getSimpleName() + " with value " + value + " oldValue " + prevValue);
        while (cls != null) {
            try {
                for (Method m : cls.getDeclaredMethods()) {
                    if ((m.getName().equals(setter) || m.getName().equals(listener)) &&
                            m.getParameterTypes().length == 1) {
                        Class arg = m.getParameterTypes()[0];
                        if ((value == null && !arg.isPrimitive()) || (value != null && assignable(arg, value.getClass()))) {
                            m.invoke(v, value);
                            return true;
                        }
                    }
                }
                cls = cls.getSuperclass();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

