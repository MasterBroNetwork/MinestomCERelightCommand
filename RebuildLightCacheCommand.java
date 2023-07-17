package your.package.name.here;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.condition.CommandCondition;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.network.packet.client.ClientPacketsHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RebuildLightCacheCommand extends Command {
    public RebuildLightCacheCommand() {
        super("rebuildlightcache", "relight");
        addSyntax(this::execute);
        setCondition(Conditions::playerOnly);
    }

    private void execute(@NotNull CommandSender commandSender, @NotNull CommandContext commandContext) {
        Player player = (Player) commandSender;
        Instance currentPlayerInstance = player.getInstance();

        player.sendMessage(Component.text("Light cache has been rebuilt.", NamedTextColor.YELLOW));
        //LightingChunk.relight(currentPlayerInstance, currentPlayerInstance.getChunks());
        for(Chunk chunk : currentPlayerInstance.getChunks()) {
            LightingChunk lightingChunk = (LightingChunk) chunk;
            lightingChunk.sendLighting();
        }

        System.out.println(player.getUsername() + " rebuilt the light cache.");
    }
}
