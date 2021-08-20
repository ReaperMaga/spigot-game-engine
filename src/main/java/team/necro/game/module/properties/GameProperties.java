package team.necro.game.module.properties;

import lombok.Data;

@Data
public class GameProperties {

    private boolean allowPvP;
    private boolean allowHunger;

    private boolean allowBlockBreak;
    private boolean allowBlockPlace;

    private boolean allowInteract;
    private boolean allowEntityInteract;

    private boolean allowDamage;
    private boolean allowEntityDamage;

    private boolean allowInventoryClick;

    private boolean allowWeather;
    private boolean allowLeavesDecay;
    private boolean allowLiquidPhysics;
}
