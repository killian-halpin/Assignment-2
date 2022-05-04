package models;

import utils.Utilities;

public class GameApp extends App{
    private boolean isMultiplayer;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost,boolean isMultiplayer) {
        super(developer, appName, appSize, appVersion, appCost);

        this.isMultiplayer = isMultiplayer;
    }

    @Override
    public boolean isRecommendedApp() {
        if(isMultiplayer == true && super.calculateRating() > 4.0);
        return true;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    public String appSummary() {
        return super.appSummary();
    }

    @Override
    public String toString() {
        return "GameApp{" +
                "isMultiplayer=" + isMultiplayer +
                '}';
    }
}
