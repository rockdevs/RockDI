package context.concretes;

import annotation.Injection;
import annotation.RockField;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class AnnotatedContext extends AbstractRockContext {

    public AnnotatedContext() {}

    @Override
    protected void findAllClassTypes() {
        super.types = reflections.getTypesAnnotatedWith(RockField.class);
        for (Class<?> type:super.types){
            if(!type.isInterface()){
                try {
                    super.instancesPoolWithType.putIfAbsent(type,type.getConstructor().newInstance());
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    protected void injectAllInstances() {
        for (Class<?> type:super.types){
            Arrays.stream(type.getDeclaredFields()).forEach((field)->{
                if(field.isAnnotationPresent(Injection.class)){
                    try {
                        //Burani metod icine alib recursive elemek lazimdi Cunki new Consumer isdeyerekTipi deyil instanceni isdiyir
                        field.set(super.instancesPoolWithType.get(type),super.instancesPoolWithType.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
