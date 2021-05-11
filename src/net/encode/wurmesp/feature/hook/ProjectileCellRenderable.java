package net.encode.wurmesp.feature.hook;

import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import net.encode.wurmesp.WurmEspMod;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;

public class ProjectileCellRenderable {
    public ProjectileCellRenderable() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ctWurmArrow = classPool.getCtClass("com.wurmonline.client.renderer.cell.ProjectileCellRenderable");
            CtMethod m = CtNewMethod.make((String)"public void initialize() { return; }", (CtClass)ctWurmArrow);
            ctWurmArrow.addMethod(m);
            WurmEspMod.logger.log(Level.INFO, "[WurmEspMod] Added method initialize on ProjectileCellRenderable");
        }
        catch (CannotCompileException | NotFoundException ex) {
            Logger.getLogger(ProjectileCellRenderable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

