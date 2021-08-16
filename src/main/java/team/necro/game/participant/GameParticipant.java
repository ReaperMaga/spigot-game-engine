package team.necro.game.participant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameParticipant {

    @Nullable
    private Player player;
    private GameParticipantMode mode;


}
