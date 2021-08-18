package team.necro.game.participant.pipeline;

import team.necro.game.participant.GameParticipant;

public interface GameParticipantHandler {

    GameParticipant handle(GameParticipant input);

}
