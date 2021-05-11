package net.encode.wurmesp.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.encode.wurmesp.feature.hook.Hook;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;

public class ReUtils {
    @SuppressWarnings("unchecked")
	public static <T> T getField(Object proxy, String fieldname) {
        Class<?> cls = proxy.getClass();
        Object returnedObject = null;
        try {
            Field field = ReflectionUtil.getField(cls, (String)fieldname);
            returnedObject = ReflectionUtil.getPrivateField((Object)proxy, (Field)field);
        }
        catch (ClassCastException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException ex) {
            Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (T)returnedObject;
    }
}

