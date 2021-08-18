package team.necro.game.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import team.necro.game.Game;
import team.necro.game.participant.GameParticipant;

@Getter
@AllArgsConstructor
public class GameParticipantUnRegisterEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Game game;
    private GameParticipant participant;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
