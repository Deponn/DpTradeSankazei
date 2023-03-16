package dptradesankazei.dptradesankazei.Command;

public enum CmdName {

    EnableTrader("DpEnableTraderPlugin"),
    DisableTrader("DpDisableTraderPlugin"),
    Initialize("DpInitializeTrader"),
    Register("DpRegister"),
    SetPrise("DpSetPrise"),
    Buy("Buy");

    private final String Command;

    // コンストラクタを定義
    CmdName(String Command) {
        this.Command = Command;
    }

    // メソッド
    public String getCmd() {
        return this.Command;
    }
}

