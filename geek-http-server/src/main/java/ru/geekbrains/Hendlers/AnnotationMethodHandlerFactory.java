package ru.geekbrains.Hendlers;

import org.reflections.Reflections;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.serializers.ResponseSerializer;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnnotationMethodHandlerFactory {
    public static MethodHandler create(SocketService socketService, ResponseSerializer serializer, Config config){

        Reflections reflections = new Reflections("ru.geekbrains.Hendlers");
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));

        MethodHandler previousMethodHandler = null;

        classes.sort(Comparator.comparingInt(AnnotationMethodHandlerFactory::getOrder).reversed());
        try {

            for (Class<?> clazz : classes) {
               Constructor<?> constructor = clazz.getConstructor(String.class, MethodHandler.class, SocketService.class, ResponseSerializer.class, Config.class);
               previousMethodHandler = (MethodHandler) constructor.newInstance(getMethod(clazz), previousMethodHandler, socketService, serializer, config);
            }
           } catch (Exception e) {
               throw new RuntimeException(e);
           }

        return previousMethodHandler;
    }

    private static int getOrder(Class<?> clazz){
        return clazz.getAnnotation(Handler.class).order();
    }

    private static String getMethod(Class<?> clazz){
        return clazz.getAnnotation(Handler.class).method();
    }
}
