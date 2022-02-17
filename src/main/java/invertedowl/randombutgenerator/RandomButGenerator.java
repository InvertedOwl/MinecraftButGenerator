package invertedowl.randombutgenerator;

import invertedowl.randombutgenerator.commands.NewChallenges;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomButGenerator extends JavaPlugin {
    public static RandomButGenerator instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.getCommand("NewChallenges").setExecutor(new NewChallenges());
    }

    @Override
    public void onDisable() {

    }
}
