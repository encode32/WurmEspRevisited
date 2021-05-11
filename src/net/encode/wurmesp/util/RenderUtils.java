package net.encode.wurmesp.util;

import com.wurmonline.client.renderer.PickRenderer;
import com.wurmonline.client.renderer.PickRenderer.CustomPickOutlineRender;
import com.wurmonline.client.renderer.backend.IndexBuffer;
import com.wurmonline.client.renderer.backend.Primitive;
import com.wurmonline.client.renderer.backend.Queue;
import com.wurmonline.client.renderer.backend.RenderState;
import com.wurmonline.client.renderer.backend.VertexBuffer;

import net.encode.wurmesp.WurmEspMod;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class RenderUtils {
    public static void renderPrimitiveLines(int numvertex, float[] vertexdata, int[] indexdata, Queue queue, float[] color) {
        VertexBuffer _vBuffer = VertexBuffer.create((VertexBuffer.Usage)VertexBuffer.Usage.PICK, (int)numvertex, (boolean)true, (boolean)false, (boolean)false, (boolean)false, (boolean)false, (int)0, (int)0, (boolean)false, (boolean)true);
        FloatBuffer vdata = _vBuffer.lock();
        vdata.put(vertexdata);
        _vBuffer.unlock();
        IndexBuffer _iBuffer = IndexBuffer.create((int)indexdata.length, (boolean)false, (boolean)true);
        IntBuffer idata = _iBuffer.lock();
        idata.put(indexdata);
        _iBuffer.unlock();
        
        PickRenderer tmp1257_1254 = WurmEspMod._pickRenderer;
		CustomPickOutlineRender customPickOutline = tmp1257_1254.new CustomPickOutlineRender();

		RenderState renderStateOutline = new RenderState();
		renderStateOutline.alphaval = 0.5F;
		renderStateOutline.twosided = false;
		renderStateOutline.depthtest = Primitive.TestFunc.LESS;
		renderStateOutline.depthwrite = false;
		renderStateOutline.blendmode = Primitive.BlendMode.ALPHABLEND;
		renderStateOutline.customstate = customPickOutline;
        
        Primitive p = queue.reservePrimitive();
        p.copyStateFrom(renderStateOutline);
        p.vertex = _vBuffer;
        p.index = _iBuffer;
        p.num = _iBuffer.getNumIndex() / 2;
        p.type = Primitive.Type.LINES;
        p.nolight = true;
        p.nofog = true;
        p.texture[0] = null;
        p.setColor(color[0], color[1], color[2], color[3]);
        queue.queue(p, null);
    }
}

