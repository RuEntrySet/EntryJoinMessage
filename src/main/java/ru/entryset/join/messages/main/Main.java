package ru.entryset.join.messages.main;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.entryset.api.configuration.Config;
import ru.entryset.api.tools.Messager;
import ru.entryset.join.messages.events.Events;

public class Main extends JavaPlugin {

    public static Config config;

    private static Main instance;

    public static Messager messager;

    public static Permission perms = null;

    @Override
    public void onEnable() {
        instance = this;
        config = new Config(this, "config.yml");
        messager = new Messager(config);
        registerEvents();
        setupPermissions();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    public static Main getInstance() {
        return Main.instance;
    }

    private void setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
    }


}
