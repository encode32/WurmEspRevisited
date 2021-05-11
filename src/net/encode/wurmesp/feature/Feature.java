/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.wurmonline.client.game.World
 *  com.wurmonline.client.renderer.backend.Queue
 */
package net.encode.wurmesp.feature;

import com.wurmonline.client.game.World;
import com.wurmonline.client.renderer.backend.Queue;

public abstract class Feature {
    protected Queue queuePick;
    protected World world;
    public boolean first = true;

    public void setWorldQueue(World world, Queue queue) {
        this.world = world;
        this.queuePick = queue;
    }

    public abstract void refresh();

    public abstract void queue();
}

