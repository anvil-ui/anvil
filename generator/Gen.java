import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Gen {

	private static class Pair<A, B> {
		public final A first;
		public final B second;
		public Pair(A a, B b) {
			this.first = a;
			this.second = b;
		}
		public int hashCode() {
			return first.hashCode() ^ second.hashCode();
		}
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != getClass()) {
				return false;
			}
			return this.first.equals(((Pair) obj).first) &&
				this.second.equals(((Pair) obj).second);
		}
	}

	public Gen(File f) throws IOException, ClassNotFoundException {
		JarFile jar = new JarFile(f);
		URL jarUrl = new URL("jar", "","file:" + f.getAbsolutePath()+"!/");
		URLClassLoader loader = new URLClassLoader(new URL[] {jarUrl},
				getClass().getClassLoader());

		Class<?> viewClass = loader.loadClass("android.view.View");

    for (JarEntry e : Collections.list(jar.entries())) {
			if (e.getName().endsWith(".class")) {
				String className = e.getName().replace(".class", "").replace("/", ".");

				// Skip inner classes
				if (className.contains("$")) {
					continue;
				}

				Class<?> cls = loader.loadClass(className);
				if (viewClass.isAssignableFrom(cls)) {
					processClass(cls);
				}
			}
		}
		printListeners();
		printSetters();
	}

	Map<Pair<String, Class>, List<Method>> setters = new HashMap<Pair<String, Class>, List<Method>>();
	Map<Pair<String, Class>, List<Method>> listeners = new HashMap<Pair<String, Class>, List<Method>>();

	private void processClass(Class cls) {
		for (Method m : cls.getDeclaredMethods()) {
			if (!Modifier.isPublic(m.getModifiers()) || m.isSynthetic() || m.isBridge()) {
				continue;
			}
			if (m.getName().matches("^setOn.*Listener$")) {
				String name = m.getName();
				name = name.substring(5, name.length() - 8);
				Pair<String, Class> pair = new Pair<String, Class>(name, m.getParameterTypes()[0]);
				if (listeners.get(pair) == null) {
					listeners.put(pair, new ArrayList<Method>());
				}
				listeners.get(pair).add(m);
			} else if (m.getName().startsWith("set") && m.getParameterCount() == 1) {
				String name = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);
				Pair<String, Class> pair = new Pair<String, Class>(name, m.getParameterTypes()[0]);
				if (setters.get(pair) == null) {
					setters.put(pair, new ArrayList<Method>());
				}
				setters.get(pair).add(m);
			}
		}
	}

	private void printListeners() {
		for (Map.Entry<Pair<String, Class>, List<Method>> e : listeners.entrySet()) {
			String name = e.getKey().first;
			Class listenerClass = e.getKey().second;
			List<Method> methods = e.getValue();
			System.out.println("  public static Node on" + name + 
					"(final " + listenerClass.getCanonicalName() + " listener) {");
			System.out.println("    return new Node(new SimpleSetter(listener) {");
			System.out.println("      public void set(View v) {");
			for (Method m : methods) {
				String className = m.getDeclaringClass().getCanonicalName();
				System.out.println("        if (v instanceof " + className + ")");
				System.out.println("          (("  + className + ") v)." + m.getName() + 
						"(new " + listenerClass.getCanonicalName() + "() {");
				for (Method lm : listenerClass.getDeclaredMethods()) {
					String args = "";
					String vars = "";
					Class[] params = lm.getParameterTypes();
					for (int i = 0; i < params.length; i++) {
						args = args + (i != 0 ? ", " : "") +
							params[i].getCanonicalName() + " a" + i;
						vars = vars + (i != 0 ? ", " : "") + "a" + i;
					}
					String returnClass = lm.getReturnType().getCanonicalName();
					System.out.println("            public " + returnClass + " " +
							lm.getName() + "(" + args + ") {");
					if (returnClass != "void") {
						System.out.println("              " + returnClass +
								" r = listener." + lm.getName() + "(" + vars + ");");
						System.out.println("              render();");
						System.out.println("              return r;");
					} else {
						System.out.println("              listener." + lm.getName() + "(" + vars + ");");
						System.out.println("              render();");
					}
					System.out.println("            }");
				}
				System.out.println("        });");
			}
			System.out.println("      }");
			System.out.println("    });");
			System.out.println("  }");
		}
	}

	private void printSetters() throws IOException {
		for (Map.Entry<Pair<String, Class>, List<Method>> e : setters.entrySet()) {
			String name = e.getKey().first;
			Class paramType = e.getKey().second;
			List<Method> methods = e.getValue();
			System.out.println("  public static Node " + name + 
					"(final " + paramType.getCanonicalName() + " val) {");
			System.out.println("    return new Node(new SimpleSetter(val) {");
			System.out.println("      public void set(View v) {");
			for (Method m : methods) {
				String className = m.getDeclaringClass().getCanonicalName();
				File f = new File(className + "__" + m.getName() + "__" + paramType.getCanonicalName());
				if (f.exists()) {
					String text = new Scanner(f).useDelimiter("\\A").next();
					System.out.println("        " + text.replace("\n", "\n        "));
				} else {
					System.out.println("        if (v instanceof " + className + ")");
					System.out.println("          (("  + className + ") v)." + m.getName() + "(val);");
				}
			}
			System.out.println("      }");
			System.out.println("    });");
			System.out.println("  }");
		}
	}

	public static void main(String[] args) {
		try {
			new Gen(new File(args[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
