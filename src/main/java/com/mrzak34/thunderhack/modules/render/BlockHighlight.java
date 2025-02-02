package com.mrzak34.thunderhack.modules.render;

import com.mrzak34.thunderhack.command.Command;
import com.mrzak34.thunderhack.events.PacketEvent;
import com.mrzak34.thunderhack.modules.Module;
import com.mrzak34.thunderhack.events.Render3DEvent;
import com.mrzak34.thunderhack.setting.*;
import com.mrzak34.thunderhack.util.Timer;
import com.mrzak34.thunderhack.util.render.RenderUtil;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockHighlight extends Module {
    private final Setting<Float> lineWidth = this.register(new Setting<Float>("LineWidth", 1.0f, 0.1f, 5.0f));
    public final Setting<ColorSetting> color = this.register(new Setting<>("Color", new ColorSetting(0x2250b4b4)));

    public BlockHighlight() {
        super("BlockHighlight", "подсвечивает блок на-который ты смотришь", Module.Category.RENDER);
    }

    @SubscribeEvent
    public void onRender3D(Render3DEvent event) {
        RayTraceResult ray = BlockHighlight.mc.objectMouseOver;
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = ray.getBlockPos();
            RenderUtil.drawBlockOutline(blockpos, color.getValue().getColorObject(), this.lineWidth.getValue(), false,0);
        }
    }
}

