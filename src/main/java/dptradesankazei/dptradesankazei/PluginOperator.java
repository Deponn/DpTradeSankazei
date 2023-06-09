package dptradesankazei.dptradesankazei;

import dptradesankazei.dptradesankazei.PropertiesAndConstant.Const;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PluginOperator {

    private int Prise;
    private final PlayersData playersData;
    public PluginOperator(PlayersData playersData){
        this.Prise = Const.InitialPrise;
        this.playersData = playersData;
    }

    public String initialize(){
        Prise = Const.InitialPrise;
        return playersData.initialize();
    }

    public String setLeader(String playerName){
        if(!playersData.isLeader(playerName)) {
            return playersData.setLeader(playerName);
        }
        return "すでにリーダーです。";
    }
    public void setPrise(int prise){
        Prise = prise;
    }

    public String Buy(String buyer, String merchandise){
        if(playersData.isLeader(buyer)){
            if(playersData.isInSameTeam(buyer,merchandise)){
                return "自分のチームに所属しているプレイヤーは買えません";
            }
            if(playersData.isInAnyBattleTeam(merchandise)){
                return "相手のチームに所属しているプレイヤーは買えません";
            }

            Player player = Bukkit.getPlayer(buyer);
            String teamName = playersData.getTeam(buyer);
            String command1 = "DpGiveMoney -team " + teamName + " -amount " + (-Prise);
            String command2 = "team join " + teamName + " " + merchandise;
            String command3 = "gamemode survival " + merchandise;
            String command4 = "tp " + merchandise + " " + buyer;
            if(player != null) {
                Bukkit.dispatchCommand(player, command1);
                Bukkit.dispatchCommand(player, command2);
                Bukkit.dispatchCommand(player, command3);
                Bukkit.dispatchCommand(player, command4);
            }
            Objects.requireNonNull(player).chat(buyer + "が" + merchandise + "を" + Prise + "円で購入しました。");
            return merchandise + "を" + Prise + "円で購入しました。";
        }else {
            return "このコマンドを使えるのはリーダーに指定されたプレイヤーのみです";
        }
    }
}
