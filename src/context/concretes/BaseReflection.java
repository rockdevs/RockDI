package context.concretes;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;

import java.util.Set;
import java.util.regex.Pattern;

public class BaseReflection {

    private volatile static Reflections reflections;


    private BaseReflection(){};

    public static Reflections getReflections() {
        if(reflections==null){
            synchronized (BaseReflection.class) {
                if (reflections == null) {
                    reflections = new Reflections(ClasspathHelper.forJavaClassPath(),new TypeAnnotationsScanner(),new SubTypesScanner(),new FieldAnnotationsScanner());
                }
            }
        }
        return reflections;
    }


    public static Set<String> getRockXmlPaths() {
        reflections = new Reflections(ClasspathHelper.forJavaClassPath(),new ResourcesScanner());
        return reflections.getResources(Pattern.compile(".*rock\\.xml"));
    }
}
