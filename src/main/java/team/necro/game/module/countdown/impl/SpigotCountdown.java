package team.necro.game.module.countdown.impl;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import team.necro.game.Game;
import team.necro.game.module.countdown.Countdown;
import team.necro.game.module.countdown.CountdownListener;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SpigotCountdown implements Countdown {

    private final long delay;
    private final long ticks;


    private long remaining;

    @Nullable
    private BukkitTask task;


    private List<CountdownListener> listeners;

    public SpigotCountdown(long delay, long ticks) {
        this.delay = delay;
        this.ticks = ticks;
        this.listeners = new ArrayList<>();
    }

    public void addListener(CountdownListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void start(Game game) {
        for(CountdownListener listener : listeners) {
            listener.onStart();
        }

        task = new BukkitRunnable() {
            @Override
            public void run() {
                if(remaining <= 0) {
                    stop();
                    for(CountdownListener listener : listeners) {
                        listener.onEnd();
                    }
                } else {
                    boolean decrement = true;
                    for(CountdownListener listener : listeners) {
                        if(!listener.onCall()) {
                            decrement = false;
                        }
                    }
                    if(decrement) {
                        remaining--;
                    }

                }
            }
        }.runTaskTimer(game.getPlugin(), delay, ticks);
    }

    @Override
    public void stop() {
        if(isRunning()) {
            task.cancel();
            task = null;
            for(CountdownListener listener : listeners) {
                listener.onStop();
            }
        }
    }


    @Override
    public boolean isRunning() {
        return task != null;
    }
}
