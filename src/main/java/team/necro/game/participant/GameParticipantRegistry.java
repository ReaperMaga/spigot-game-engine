package team.necro.game.participant;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.necro.game.Game;
import team.necro.game.event.GameParticipantRegisterEvent;
import team.necro.game.participant.pipeline.GameParticipantPipeline;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class GameParticipantRegistry {

    private Game game;
    private Map<UUID, GameParticipant> participants;
    private GameParticipantPipeline pipeline;

    public GameParticipantRegistry(Game game) {
        this.game = game;
        this.participants = new HashMap<>();
        this.pipeline = new GameParticipantPipeline();
    }

    public void registerParticipant(Player player) {
        if(!existsParticipant(player.getUniqueId())) {
            GameParticipant participant = pipeline.flush(player);
            registerParticipant(player.getUniqueId(), participant);
        }
    }

    private void registerParticipant(UUID uuid, GameParticipant participant) {
        this.participants.put(uuid, participant);
        Bukkit.getPluginManager().callEvent(new GameParticipantRegisterEvent(game, participant));
    }

    public boolean existsParticipant(UUID uuid) {
        return this.participants.containsKey(uuid);
    }

    public boolean unregisterParticipant(UUID uuid)  {
        if(!existsParticipant(uuid)) {
            return false;
        }
        Bukkit.getPluginManager().callEvent(new GameParticipantRegisterEvent(game, this.participants.get(uuid)));
        this.participants.remove(uuid);
        return true;
    }

    public <T extends GameParticipant> T getParticipant(UUID uuid) {
        return (T) this.participants.get(uuid);
    }
}
