import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Main {

    private static String BOT_NAME = "KukuBot";
    private static String BOT_TOKEN = "764785281:AAGV9G4iq3s62RDuwZ5S1g1ktorXnPNK0D4" /* your bot's token here */;

    private static String PROXY_HOST = System.getenv("PROXY_HOST");
    private static Integer PROXY_PORT = Integer.parseInt(System.getenv("PROXY_PORT"));
    private static  String PROXY_USER = System.getenv("PROXY_USER");
    private static String PROXY_PASS = System.getenv("PROXY_PASS");

    public static void main(String[] args) {
        try {
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(PROXY_USER, PROXY_PASS.toCharArray());
                }
            });

            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            TelegramBot bot = new TelegramBot(BOT_TOKEN, BOT_NAME, botOptions);

            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
