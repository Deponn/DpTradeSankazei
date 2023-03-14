package dptradesankazei.dptradesankazei;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class PlayersData {

    private Map<String,String> LeaderMap;


    public PlayersData(){
        this.LeaderMap = new HashMap<>();
    }

    public String initialize(){
        this.LeaderMap = new HashMap<>();
        return "初期化しました";
    }

    public String setLeader(String playerName){
        Team team = getJoiningTeam(playerName);
        if( team != null) {
            LeaderMap.put(playerName, team.getName());
            return "リーダーを" + playerName + "に設定しました。";
        }else {
            return "チームに所属しているプレイヤーを指定してください。";
        }
    }

    public boolean isLeader(String playerName){
        return LeaderMap.containsKey(playerName);
    }

    public String getTeam(String playerName){
        return LeaderMap.get(playerName);
    }

    public boolean isInAnyBattleTeam(String playerName) {
        Team team = getJoiningTeam(playerName);
        if( team != null) {
            String teamName = team.getName();
            for (String battleTeamName : LeaderMap.values()) {
                if (teamName.equals(battleTeamName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInSameTeam(String playerName1, String playerName2) {
        Team team1 = getJoiningTeam(playerName1);
        Team team2 = getJoiningTeam(playerName2);
        if( team1 != null) {
            if(team2 != null) {
                String teamName1 = team1.getName();
                String teamName2 = team2.getName();
                return teamName1.equals(teamName2);
            }
        }
        return false;
    }

    public static List<String> getPlayersList(){
        List<String> playerList = new ArrayList<>();
        List<World> worlds = Bukkit.getWorlds();
        for(World world : worlds){
            List<Player> players = world.getPlayers();
            for (Player player: players){
                playerList.add(player.getName());
            }
        }
        return playerList;
    }

    //プレイヤーがチームに所属しているか判定すべてのチームのすべてのメンバーと照合し一致する名前があったらそのチームを返す。
    public static Team getJoiningTeam(String playerName) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = Objects.requireNonNull(scoreboardManager).getMainScoreboard();
        Object[] teamObjects = scoreboard.getTeams().toArray();
        for (Object teamObj : teamObjects) {
            Team team = (Team) teamObj;
            if (team.hasEntry(playerName)) {
                return team;
            }
        }
        return null;
    }
}
