package repo.injection;

import repo.injection.annotations.Configuration;
import repo.injection.annotations.MyInject;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Injector class to analyze object fields by {@link MyInject} annotation
 * and filled them with corresponding value
 * @author andruha.tm
 * @version 1.0
 */
@Configuration(basePackages = {
				"repo.validators.impl.base",
				"repo.validators.impl.internet",
				"repo.validators.impl.mobile",
				"repo.validators.impl.tv",
				"repo.sorters.impl"
})
public class Injector {

	/**
	 * List of objects from packages
	 */
	private static List<Object> objectList;

	/**
	 * initializing list of objects from base packages of {@link Configuration}
	 */
	public Injector() {
		objectList = new ArrayList<>();
		if (this.getClass().isAnnotationPresent(Configuration.class)) {
			Configuration configuration = this.getClass().getAnnotation(Configuration.class);

			for (String packagePath : configuration.basePackages()) {
				packagePath = packagePath.replace(".", "/");

				try (DataInputStream dataInputStream = new DataInputStream((InputStream) Objects.requireNonNull(
								this.getClass().getClassLoader().getResource(packagePath)).getContent())) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
					String line = "";
					while ((line = bufferedReader.readLine()) != null) {
						if (line.endsWith(".class")) {
							String className = packagePath.replace("/", ".") + "."
											+ line.substring(0, line.length() - 6);
							System.out.println(className);
							objectList.add(Class.forName(className).getConstructor().newInstance());
						}
					}
				} catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException
								| ClassNotFoundException | IllegalAccessException e) {
					System.out.println("Class not found");
				}
			}
		}
	}

	/**
	 * dependency injection mthd
	 * @param o object to analyze for {@link MyInject annotation}
	 * @param <T> generic type of obj
	 * @return object with filled field
	 * @throws Exception while setting field value
	 */
	public static <T> T inject(final T o) throws IllegalAccessException {
		List<Object> classForInjection = new ArrayList<>();
		System.out.println(objectList);

		for (Field field : o.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(MyInject.class)) {
				field.setAccessible(true);
				if (field.getType().getName().contains("java.util.List")) {
					MyInject myInject = field.getAnnotation(MyInject.class);
					for (Object object : objectList) {
						if (object != null && myInject.targetType().isAssignableFrom(object.getClass())) {
							classForInjection.add(object);
						}
						field.set(o, classForInjection);
					}
				} else {
					for (Object object : objectList) {
						if (object != null && field.getType().isAssignableFrom(object.getClass())) {
							classForInjection.add(object);
						}
					}
					if (classForInjection.size() == 1) {
						field.set(o, classForInjection.get(0));
					} else {
						throw new RuntimeException("Instance for injecting is not defined");
					}
				}
			}
		}
		return o;
	}
}
