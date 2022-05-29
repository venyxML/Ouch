package io.github.venyml.owie;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Owie extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Prepare for pain (Owie initializing...)");
        this.getCommand("ow").setExecutor(new CommandControl());
        Bukkit.getPluginManager().registerEvents(new CommandControl(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("No more pain (Owie disabling...)");
    }
}

class CommandControl implements CommandExecutor, Listener {

    public static boolean run = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            if(args[0].equalsIgnoreCase("start")){
                if(args.length == 1){
                    run = true;
                    return true;
                }
            } else if(args[0].equalsIgnoreCase("stop")){
                run = false;
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if(run && e.getEntity() instanceof Player){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + getRandomMessage());
        }
    }

    private String getRandomMessage(){
        String[] quotes = {"Ow!", "Owie!", "Ouch:(", "p a i n", "oof", "yikes...", "that looked like it hurt",
        "someone needs a doctor i think?", "Eesh", "oop", "zoinks", "bonk /neg"};

        return quotes[(new Random()).nextInt(quotes.length)];
    }

}