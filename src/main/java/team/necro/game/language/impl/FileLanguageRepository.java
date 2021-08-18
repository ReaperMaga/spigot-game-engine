package team.necro.game.language.impl;

import lombok.Getter;
import team.necro.game.Game;
import team.necro.game.common.repository.file.AbstractGsonCrudRepository;
import team.necro.game.language.Language;

import java.io.File;

@Getter
public class FileLanguageRepository extends AbstractGsonCrudRepository<Language> {

    public FileLanguageRepository(Game game) {
        super(game.getBootstrap().getDirectory().concat(File.separator).concat("languages").concat(File.separator), Language.class);
    }


}
