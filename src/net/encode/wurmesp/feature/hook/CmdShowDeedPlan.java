package net.encode.wurmesp.feature.hook;

import com.wurmonline.client.comm.SimpleServerConnectionClass;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import net.encode.wurmesp.WurmEspMod;

public class CmdShowDeedPlan
extends Hook {
    public CmdShowDeedPlan() {
        this.prepareHook("com.wurmonline.client.comm.SimpleServerConnectionClass", "reallyHandleCmdShowDeedPlan", "(Ljava/nio/ByteBuffer;)V", () -> (proxy, method, args) -> {
            if (WurmEspMod.deedsize) {
                ByteBuffer bb = (ByteBuffer)args[0];
                byte type = bb.get();
                switch (type) {
                    case 0: {
                        int qId = bb.getInt();
                        SimpleServerConnectionClass simpleServerConnectionClass = (SimpleServerConnectionClass)proxy;
                        Method readStringByteLengthMethod = simpleServerConnectionClass.getClass().getDeclaredMethod("readStringByteLength", ByteBuffer.class);
                        readStringByteLengthMethod.setAccessible(true);
                        Object[] readStringByteLengthArgs = new Object[]{bb};
                        String deedName = (String)readStringByteLengthMethod.invoke(simpleServerConnectionClass, readStringByteLengthArgs);
                        int tokenX = bb.getInt();
                        int tokenY = bb.getInt();
                        int startX = bb.getInt();
                        int startY = bb.getInt();
                        int endX = bb.getInt();
                        int endY = bb.getInt();
                        int perimSize = bb.getInt();
                        WurmEspMod.tilesHighlightManager.addData(startX, startY, endX, endY);
                        WurmEspMod.tileshighlight = true;
                    }
                }
            } else {
                method.invoke(proxy, args);
            }
            return null;
        });
        WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] SimpleServerConnectionClass.reallyHandleCmdShowDeedPlan hooked");
    }
}

