package dptradesankazei.dptradesankazei.Command;

import dptradesankazei.dptradesankazei.PlayersData;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ParserBuy implements CmdParser{

    private final boolean isSuccess;//パース成功したかどうか
    public final String PlayerName;

    private ParserBuy(boolean isSuccess, String playerName) {
        this.isSuccess = isSuccess;
        this.PlayerName = playerName;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    public static ParserBuy Parse(CommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        List<String> players = PlayersData.getPlayersList();
        for(String playerName : players){
            if(argsList.contains(playerName)){
                return new ParserBuy(true,playerName);
            }
        }
        return new ParserBuy(false,  null);
    }


}
