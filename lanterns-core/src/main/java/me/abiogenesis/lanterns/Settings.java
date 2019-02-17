package me.abiogenesis.lanterns;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Settings {

    public static final String SETTINGS_RESOURCE = "/me/abiogenesis/lanterns/settings.properties";

    public static final String CARDS_PER_LANTERN_PROPERTY = "numberOfCardsPerLantern";
    public static final String FAVOR_TOKENS_PROPERTY = "numberOfFavorTokens";

    public final int numberOfCardsPerLantern;
    public final int numberOfFavorTokens;

    public Settings(Properties properties) {
        numberOfCardsPerLantern = Integer.parseInt(properties.getProperty(CARDS_PER_LANTERN_PROPERTY));
        numberOfFavorTokens = Integer.parseInt(properties.getProperty(FAVOR_TOKENS_PROPERTY));
    }

    private static class LazyHolder {

        static final Settings INSTANCE;

        static {
            Properties properties = new Properties();
            try (InputStream resourceStream = Settings.class.getResourceAsStream(SETTINGS_RESOURCE)) {
                properties.load(resourceStream);
            } catch (IOException e) {
                throw new ExceptionInInitializerError(e);
            }
            INSTANCE = new Settings(properties);
        }
    }

    public static Settings getDefaults() {
        return LazyHolder.INSTANCE;
    }
}
