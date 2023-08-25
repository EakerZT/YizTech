package xyz.eaker.yiztech.api.menu;

import net.minecraft.network.FriendlyByteBuf;
import xyz.eaker.yiztech.api.capacity.IMachineStatus;
import xyz.eaker.yiztech.api.definition.ProgressBar;

public class ProgressBarComponent extends BaseSyncComponent {
    private final ProgressBar progressBar;
    private final IMachineStatus machineStatus;
    private int state;
    private int progress;
    private int duration;

    public ProgressBarComponent(int x, int y, ProgressBar progressBar, IMachineStatus machineStatus) {
        super(x, y, progressBar.width, progressBar.height);
        this.progressBar = progressBar;
        this.machineStatus = machineStatus;
    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    @Override
    public void read(FriendlyByteBuf packet) {
        this.state = packet.readVarInt();
        this.progress = packet.readInt();
        this.duration = packet.readInt();
    }

    @Override
    public void write(FriendlyByteBuf packet) {
        packet.writeVarInt(this.state);
        packet.writeInt(this.progress);
        packet.writeInt(this.duration);
    }

    @Override
    public boolean isChanged() {
        var s = this.machineStatus.machineState();
        var p = this.machineStatus.processProgress();
        var d = this.machineStatus.processDuration();
        if (s == this.state && p == this.progress && d == this.duration) {
            return false;
        }
        this.state = s;
        this.progress = p;
        this.duration = d;
        return true;
    }
}
