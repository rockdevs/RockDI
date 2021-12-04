package context.concretes;

import annotation.RockDI;
import context.abstracts.Checker;
import context.abstracts.RockExecutor;
import exceptions.concretes.CannotFindRockXmlFileException;
import exceptions.concretes.ManyRockApplicationAnnotationException;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractRockContext implements RockExecutor , Checker {


    protected final Reflections reflections = BaseReflection.getReflections();

    protected final Set<String> rockXmlPaths = BaseReflection.getRockXmlPaths();

    private boolean  isEnableRockDI = true;

    Map<Class<?>, Object> instancesPoolWithType =  new HashMap<>();

    Set<Class<?>> types = new HashSet<>();

    protected AbstractRockContext(){};

    @Override
    public final void  execute() throws Exception {
        if(isEnableRockDI()){
            check();
            findAllClassTypes();
            injectAllInstances();
        }else {
            System.err.print("WARNING! ");
            System.out.println("RockDI is not enable.");
        }
    }

    @Override
    public void check() throws Exception {
        if(rockXmlPaths.isEmpty()) throw new CannotFindRockXmlFileException();
    }

    private boolean isEnableRockDI() throws ManyRockApplicationAnnotationException {
        for (Class<?> c: reflections.getTypesAnnotatedWith(RockDI.class)){
            RockDI application =  c.getAnnotation(RockDI.class);
            isEnableRockDI = application.enable();
        }
        return isEnableRockDI;
    }
    protected abstract void findAllClassTypes();
    protected abstract void injectAllInstances();
}
