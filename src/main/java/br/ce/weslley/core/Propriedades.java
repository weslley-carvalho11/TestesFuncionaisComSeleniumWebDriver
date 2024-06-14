package br.ce.weslley.core;

import sun.plugin.services.BrowserService;

public class Propriedades {

    public static boolean fechar_browser = true;
    public static Browsers browser = Browsers.FIREFOX;

    public enum Browsers {
        CHROME,
        FIREFOX
    }
}
