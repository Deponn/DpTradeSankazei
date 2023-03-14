package dptradesankazei.dptradesankazei.Command;

import dptradesankazei.dptradesankazei.PlayersData;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ParserResister implements CmdParser {

    private final boolean isSuccess;//パース成功したかどうか
    public final String PlayerName;

    private ParserResister(boolean isSuccess, String playerName) {
        this.isSuccess = isSuccess;
        this.PlayerName = playerName;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    public static ParserResister parse(CommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        List<String> players = PlayersData.getPlayersList();
        for(String playerName : players){
            if(argsList.contains(playerName)){
                return new ParserResister(true,playerName);
            }
        }
        return new ParserResister(false,  null);
    }
}
