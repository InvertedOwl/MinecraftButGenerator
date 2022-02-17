package invertedowl.randombutgenerator.commands;

import invertedowl.randombutgenerator.Challenge;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NewChallenges implements CommandExecutor {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("NewChallenges")){
            if (sender instanceof Player){
                if (args.length > 0){
                    for (int i = 0; i < Integer.parseInt(args[0]) - 1; i++) {
                        Challenge challenge = new Challenge();
                        sender.sendMessage(challenge.getString());
                    }
                }

                Challenge challenge = new Challenge();
                sender.sendMessage(challenge.getString());
            }
        }
        return false;
    }
}
