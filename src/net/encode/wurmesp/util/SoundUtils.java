/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.wurmonline.client.game.PlayerPosition
 *  com.wurmonline.client.renderer.cell.CellRenderable
 *  com.wurmonline.client.sound.FixedSoundSource
 *  com.wurmonline.client.sound.SoundSource
 */
package net.encode.wurmesp.util;

import com.wurmonline.client.game.PlayerPosition;
import com.wurmonline.client.renderer.cell.CellRenderable;
import com.wurmonline.client.sound.FixedSoundSource;
import com.wurmonline.client.sound.SoundSource;

public class SoundUtils {
    public static void playSound(String sound) {
        PlayerPosition pos = CellRenderable.world.getPlayer().getPos();
        CellRenderable.world.getSoundEngine().play(sound, (SoundSource)new FixedSoundSource(pos.getX(), pos.getY(), 2.0f), 1.0f, 5.0f, 1.0f, false, false);
    }
}

