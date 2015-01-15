import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;

public class Gen {

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
		printSetters();
	}

	Map<String, List<Method>> setters = new HashMap<String, List<Method>>();

	private void processClass(Class cls) {
		for (Method m : cls.getDeclaredMethods()) {
			if (m.isPublic() == false) {
				continue;
			}
			if (m.getName().matches("^setOn.*Listener$")) {
				//System.out.println("EVENT " + m);
			} else if (m.getName().startsWith("set") && m.getParameterCount() == 1) {
				String name = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);
				if (setters.get(name) == null) {
					setters.put(name, new ArrayList<Method>());
				}
				setters.get(name).add(m);
			}
		}
	}

	private void printSetters() {
		for (Map.Entry<String, List<Method>> e : setters.entrySet()) {
			String name = e.getKey();
			List<Method> methods = e.getValue();
			System.out.println("  public static Node " + name + 
					"(final " + methods.get(0).getParameterTypes()[0].getCanonicalName() + " val) { ");
			System.out.println("    return new Node(new SimpleSetter(val) {");
			System.out.println("      public void set(View v) {");
			for (Method m : methods) {
				String className = m.getDeclaringClass().getCanonicalName();
				System.out.println("        if (v instanceof " + className + ")");
				System.out.println("          (("  + className + ") v)." + m.getName() + "(val);");
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
