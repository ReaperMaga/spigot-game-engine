package team.necro.game.module.command.impl;

import de.liquiddev.command.CommandFailException;
import de.liquiddev.command.adapter.bukkit.Arguments;
import de.liquiddev.command.adapter.bukkit.PlayerCommand;
import org.bukkit.entity.Player;

public class TestCommand extends PlayerCommand {

    public TestCommand() {
        super("TestCommand", "test", "<test>");
    }

    @Override
    protected void onCommand(Player player, Arguments arguments) throws CommandFailException {

    }
}
