package team.necro.game.common.reflection;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Reflections {

    /**
     * Get all classes within a package
     *
     * @param loader
     * @param packageName
     * @return
     * @throws Exception
     */
    public static List<Class<?>> getClassesFromPackage(ClassLoader loader, String packageName) throws Exception {
        List<Class<?>> classes = Lists.newArrayList();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith(packageName.concat("."))) {
                final Class<?> clazz = info.load();
                classes.add(clazz);
            }
        }
        return classes;
    }

    /**
     * Gives you a generic type back
     *
     * @param clazz
     * @param typeIndex
     * @return
     */
    public static Type getGenericType(Class<?> clazz, int typeIndex) {
        Type[] types =  ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
        return types[typeIndex];
    }


}
