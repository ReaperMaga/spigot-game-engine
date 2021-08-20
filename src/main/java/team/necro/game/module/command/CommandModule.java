package team.necro.game.module.command;

import team.necro.game.Game;
import team.necro.game.module.GameModule;

public class CommandModule implements GameModule {


    @Override
    public void init(Game game) {

    }

    @Override
    public Class<? extends GameModule>[] getDependencies() {
        return new Class[0];
    }
}
