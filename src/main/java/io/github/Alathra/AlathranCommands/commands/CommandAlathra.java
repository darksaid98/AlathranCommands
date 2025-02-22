package io.github.Alathra.AlathranCommands.commands;

import io.github.Alathra.AlathranCommands.AlathranCommands;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import com.github.milkdrinkers.colorparser.ColorParser;
import io.github.Alathra.AlathranCommands.utils.ConfigUtil;
import org.jetbrains.annotations.Nullable;

public class CommandAlathra implements ConfigUtil {
    final private String prefix = "[ACMDS]";
    private final AlathranCommands alathraCommands;

    CommandAlathra(AlathranCommands pl) {
        alathraCommands = pl;
        alathraCommands.saveDefaultConfig();

        new CommandAPICommand("alathracommands")
                .withSubcommands(
                    new CommandAPICommand("reload")
                        .withPermission(CommandPermission.fromString("alathrancommands.reload"))
                        .executes((sender, args) -> {
                            alathraCommands.saveDefaultConfig();
                            alathraCommands.reloadConfig();
                            sender.sendMessage(ColorParser.of(prefix + " <green>config has been reloaded!").build());
                        })/*, // TODO Re-enable when implementation is finished
                    new CommandAPICommand("schedule")
                        .withPermission(CommandPermission.fromString("alathrancommands.schedule"))
                        .withSubcommands(
                            CommandStop.registerStopCommand(),
                            CommandStop.registerRestartCommand(),
                            CommandStop.registerCancelCommand()
                        )*/

                )
                .register();
    }

    public @Nullable String getMsg(String path) {
        return alathraCommands.getConfig().getString(path);
    }
}
