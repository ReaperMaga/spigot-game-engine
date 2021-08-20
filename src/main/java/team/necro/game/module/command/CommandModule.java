package team.necro.game.module.command;

import team.necro.game.Game;
import team.necro.game.common.command.exception.mapper.CommandExceptionMapper;
import team.necro.game.module.GameModule;
import team.necro.game.module.command.mapper.DefaultGameExceptionMapper;

public class CommandModule implements GameModule {

    private CommandExceptionMapper exceptionMapper;

    public CommandModule() {
        this.exceptionMapper = new DefaultGameExceptionMapper();
    }

    @Override
    public void init(Game game) {

    }

    @Override
    public Class<? extends GameModule>[] getDependencies() {
        return new Class[0];
    }
}
