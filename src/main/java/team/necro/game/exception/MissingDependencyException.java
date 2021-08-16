package team.necro.game.exception;

import team.necro.game.module.GameModule;

public class MissingDependencyException extends Exception{

    public MissingDependencyException(GameModule module, String dependency) {
        super("Module " + module.getClass().getSimpleName() + " has missing dependency: (" + dependency + ")");
    }
}
