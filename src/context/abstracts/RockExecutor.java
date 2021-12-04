package context.abstracts;


import exceptions.concretes.RockException;

import java.lang.reflect.InvocationTargetException;

public interface RockExecutor {
    void execute() throws RockException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, Exception;
}
