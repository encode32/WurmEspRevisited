package net.encode.wurmesp.feature.hook;

import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.classhooks.InvocationHandlerFactory;

public class Hook {
    public void prepareHook(String path, String methodName, String descriptor, InvocationHandlerFactory invocationHandlerFactory) {
        HookManager.getInstance().registerHook(path, methodName, descriptor, invocationHandlerFactory);
    }
}

