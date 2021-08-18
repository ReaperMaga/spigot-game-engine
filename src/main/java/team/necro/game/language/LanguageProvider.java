package team.necro.game.language;

public interface LanguageProvider<T> {

    String getPrefix();

    String getSentence(T player, String key, Object... params);

    void sendMessage(T player, String key, Object... params);

    void broadcastMessage(String key, Object... params);

    void registerSentence(String code, String key, String sentence);

}
