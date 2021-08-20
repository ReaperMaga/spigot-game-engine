package team.necro.game.participant.pipeline;

import lombok.Getter;
import org.bukkit.entity.Player;
import team.necro.game.participant.GameParticipant;
import team.necro.game.participant.GameParticipantMode;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class GameParticipantPipeline {

    private Map<String, GameParticipantHandler> handlers;

    public GameParticipantPipeline() {
        this.handlers = new LinkedHashMap<>();
    }

    public GameParticipant flush(Player player) {
        GameParticipant participant = new GameParticipant(player, GameParticipantMode.PLAYER);
        for(GameParticipantHandler handler : this.handlers.values()) {
            participant = handler.handle(participant);
        }
        return participant;
    }

    public void registerHandler(String key, GameParticipantHandler handler) {
        this.handlers.put(key, handler);
    }

    public void unregisterHandler(String key) {
        if (existsHandler(key)) {
            this.handlers.remove(key);
        }
    }

    public boolean existsHandler(String key) {
        return this.handlers.containsKey(key);
    }

}
