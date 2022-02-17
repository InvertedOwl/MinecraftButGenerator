package invertedowl.randombutgenerator;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Challenge implements Listener {
    private ChallengeType type;
    private ChallengeResult<Player> result;
    private ChallengeResultType resultType;
    private Material block;
    private Entity entity;
    private Entity entity2;

    public static final Random random = new Random();

    public Challenge() {
        type = randomType();
        result = randomResult();



        if (type.equals(ChallengeType.STANDING)) {
            RandomButGenerator.instance.getServer().getPluginManager().registerEvents(this, RandomButGenerator.instance);
            Material block = Material.values()[random.nextInt(Material.values().length)];
            while (!block.isBlock()){
                block = Material.values()[random.nextInt(Material.values().length)];
            }
            this.block = block;
        }
        if (type.equals(ChallengeType.BREAKING)) {
            RandomButGenerator.instance.getServer().getPluginManager().registerEvents(this, RandomButGenerator.instance);
            Material block = Material.values()[random.nextInt(Material.values().length)];
            while (!block.isBlock()){
                block = Material.values()[random.nextInt(Material.values().length)];
            }
            this.block = block;
        }

    }

    public ChallengeType randomType() {
        return ChallengeType.values()[random.nextInt(ChallengeType.values().length)];
    }

    public ChallengeResult<Player> randomResult() {
        int randVal = random.nextInt(4);

        ChallengeResult<Player> result = null;

        if (randVal == 0) {
            result = p -> p.setHealth(0);
            resultType = ChallengeResultType.DIE;

        }
        if (randVal == 1) {
            result = p -> p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 2));
            resultType = ChallengeResultType.FLOAT;
        }
        if (randVal == 2) {
            result = p -> p.teleport(p.getLocation().add(0, 10, 0));
            resultType = ChallengeResultType.TELEPORT;
        }
        if (randVal == 3) {
            result = p -> {
                p.getInventory().addItem(new ItemStack(Material.DIAMOND, 64));
                p.getInventory().addItem(new ItemStack(Material.EMERALD, 64));
                p.getInventory().addItem(new ItemStack(Material.NETHERITE_BLOCK, 64));

            };
            resultType = ChallengeResultType.GET_REWARD;
        }

        return result;
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent moveEvent){
        if (type.equals(ChallengeType.STANDING)){
            if (moveEvent.getPlayer().getLocation().add(0, -0.1, 0).getBlock().getType().equals(block))
            result.result(moveEvent.getPlayer());
        }
    }

    @EventHandler
    public void playerBraekEvent(BlockBreakEvent event){
        if (type.equals(ChallengeType.BREAKING) && event.getBlock().getType().equals(block)){
            result.result(event.getPlayer());
        }
    }

    public Material getBlock() {
        return block;
    }

    public ChallengeType getType() {
        return type;
    }

    public ChallengeResultType getResultType() {
        return resultType;
    }

    public String getString() {
        String string = "Minecraft but, " + type + " " + resultType;

        try {
            if (type.equals(ChallengeType.STANDING))
                string = "Minecraft but, standing on " + ChatColor.GOLD + block.toString().toLowerCase().replace("_", " ") + ChatColor.RESET + " will make you " + ChatColor.GOLD + resultType.toString().toLowerCase().replace("_", " ");
            if (type.equals(ChallengeType.BREAKING))
                string = "Minecraft but, breaking " + ChatColor.GOLD + block.toString().toLowerCase().replace("_", " ") + ChatColor.RESET + " will make you " + ChatColor.GOLD + resultType.toString().toLowerCase().replace("_", " ");
        } catch (Exception e){
            string = e.toString();
        }


        return string;
    }
}
