package dptradesankazei.dptradesankazei.Command;

import dptradesankazei.dptradesankazei.PlayersData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandSuggest implements TabCompleter{

    private final PlayersData playersData;

    public CommandSuggest(PlayersData playersData){
        this.playersData = playersData;
    }


    /**
     * コマンドのTAB補完候補を返す
     *
     * @param sender コマンド送信者
     * @param args   引数
     * @return コマンド補完候補
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase(CmdName.Register.getCmd())) {
            return suggest_Resister(sender, args);
        }else if (command.getName().equalsIgnoreCase(CmdName.SetPrise.getCmd())){
            return suggest_SetPrise(sender, args);
        }else if (command.getName().equalsIgnoreCase(CmdName.Buy.getCmd())){
            return suggest_Buy(sender, args);
        }else {
            return new ArrayList<>();
        }
    }
    /**
     * リーダー登録
     *
     * @param sender コマンド送信者
     * @param args   引数
     * @return コマンド補完候補
     */
    private List<String> suggest_Resister(CommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        List<String> suggest = new ArrayList<>();
        if (argsList.size() == 1) {
            List<String> players = PlayersData.getPlayersList();
            for(String playerName : players){
                if(!playersData.isLeader(playerName)){
                    suggest.add(playerName);
                }
            }
        }
        return suggest;
    }
    /**
     * 値段決定
     *
     * @param sender コマンド送信者
     * @param args   引数
     * @return コマンド補完候補
     */
    private List<String> suggest_SetPrise(CommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        List<String> suggest = new ArrayList<>();
        if (argsList.size() == 1) {
            return Arrays.asList("-1000","0","1000","5000");
        }
        return suggest;
    }
    /**
     * 買う
     *
     * @param sender コマンド送信者
     * @param args   引数
     * @return コマンド補完候補
     */
    private List<String> suggest_Buy(CommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        List<String> suggest = new ArrayList<>();
        if (argsList.size() == 1) {
            List<String> players = PlayersData.getPlayersList();
            for(String playerName : players){
                if(!playersData.isInAnyBattleTeam(playerName)){
                    suggest.add(playerName);
                }
            }
        }
        return suggest;
    }
}
