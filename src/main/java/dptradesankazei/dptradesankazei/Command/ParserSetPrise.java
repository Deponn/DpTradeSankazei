package dptradesankazei.dptradesankazei.Command;

import org.bukkit.command.CommandSender;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.List;

public class ParserSetPrise implements CmdParser{

    private final boolean isSuccess;//パース成功したかどうか
    public final Integer Prise;//相手のチーム名

    private ParserSetPrise(boolean isSuccess, Integer prise){
        this.isSuccess = isSuccess;
        this.Prise = prise;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    public static ParserSetPrise Parse(CommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        try {
            int prise = Integer.parseInt(argsList.get(0));
            return new ParserSetPrise(true,prise);
        }catch (Exception e) {
            sender.sendMessage(e.getMessage());
            return new ParserSetPrise(false, null);
        }
    }
}
