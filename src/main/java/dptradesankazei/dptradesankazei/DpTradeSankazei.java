package dptradesankazei.dptradesankazei;

import dptradesankazei.dptradesankazei.Command.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DpTradeSankazei extends JavaPlugin{

    private boolean isEnabledPlugin;
    private PluginOperator op;

    @Override
    public void onEnable() {
        isEnabledPlugin = false;
        PlayersData playersData = new PlayersData();
        for (CmdName cmdName : CmdName.values()) {
            Objects.requireNonNull(this.getCommand(cmdName.getCmd())).setTabCompleter(new CommandSuggest(playersData));
        }
        op = new PluginOperator(playersData);
        getLogger().info("参加勢売買プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {
        getLogger().info("参加勢売買プラグインが無効になりました。");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        //コマンドの有効化無効化処理
        if (cmd.getName().equalsIgnoreCase(CmdName.EnableTrader.getCmd())) {
            isEnabledPlugin = true;
            if ((sender instanceof Player)) {
                sender.sendMessage("プラグイン有効化");
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase(CmdName.DisableTrader.getCmd())) {
            isEnabledPlugin = false;
            if ((sender instanceof Player)) {
                sender.sendMessage("プラグイン無効化");
            }
            return true;
        }
        if (isEnabledPlugin) {
            if (cmd.getName().equalsIgnoreCase(CmdName.Initialize.getCmd())) {
                String massage = op.initialize();
                if ((sender instanceof Player)) {
                    sender.sendMessage(massage);
                }
                return true;
            } else if (cmd.getName().equalsIgnoreCase(CmdName.Register.getCmd())) {
                ParserResister parser = ParserResister.parse(sender,args);
                if (!parser.isSuccess()) {
                    // パース失敗
                    return true;
                }
                String massage = op.setLeader(parser.PlayerName);
                if ((sender instanceof Player)) {
                    sender.sendMessage(massage);
                }
                return true;
            } else if (cmd.getName().equalsIgnoreCase(CmdName.SetPrise.getCmd())) {
                ParserSetPrise parser = ParserSetPrise.Parse(sender, args);
                if (!parser.isSuccess()) {
                    // パース失敗
                    return true;
                }
                op.setPrise(parser.Prise);
                if ((sender instanceof Player)) {
                    sender.sendMessage("参加勢の値段を" + parser.Prise + "に設定しました。");
                }
                return true;
            }else if (cmd.getName().equalsIgnoreCase(CmdName.Buy.getCmd())) {
                ParserBuy parser = ParserBuy.Parse(sender, args);
                if (!parser.isSuccess()) {
                    // パース失敗
                    return true;
                }
                if ((sender instanceof Player)) {
                    String  massage = op.Buy(sender.getName(), parser.PlayerName);
                    sender.sendMessage(massage);
                }
                return true;
            }
        }
        return true;
    }

}
