package team.necro.game.language.impl;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.necro.game.Game;
import team.necro.game.language.Language;
import team.necro.game.language.LanguageProvider;
import team.necro.game.participant.GameParticipant;

public class FileLanguageAdapter implements LanguageProvider<Player> {

    private Game game;
    private Language selected;
    private FileLanguageRepository repository;
    private String prefix;

    public FileLanguageAdapter(Game game, String languageCode) {
        this.repository = new FileLanguageRepository(game);
        this.game = game;

        String code = languageCode;
        if(!repository.exists(code)) {
            this.selected = new Language(code, Maps.newHashMap());
            repository.create(code, selected);
        } else {
            this.selected = repository.find(languageCode);
        }
        if(!selected.getSentences().containsKey("prefix")) {
           registerSentence(languageCode, "prefix", "§8[§cGame§8] §7");
        }
    }

    @Override
    public String getPrefix() {
        return selected.getSentences().get("prefix");
    }

    @Override
    public String getSentence(Player player, String key, Object... params) {
        String lowered = key.toLowerCase();
        if(!selected.getSentences().containsKey(lowered)) {
            selected.getSentences().put(lowered, "No message found");
            repository.update(selected.getCode(), selected);
        }
        return String.format(selected.getSentences().get(lowered), params);
    }

    @Override
    public void sendMessage(Player player, String key, Object... params) {
        player.sendMessage(getPrefix()+getSentence(player, key, params));
    }

    @Override
    public void broadcastMessage(String key, Object... params) {
        for(GameParticipant participant : game.getParticipantRegistry().getParticipants().values()) {
            sendMessage(participant.getPlayer(), key, params);
        }
    }

    @Override
    public void registerSentence(String code, String key, String sentence) {
        Language language = repository.find(code.toLowerCase());
        language.getSentences().put(key.toLowerCase(), sentence);
        repository.update(language.getCode(), language);
    }
}
