package models;

import utils.Utilities;

public class EducationApp extends App{
    private int level;

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost,int level) {
        super(developer, appName, appSize, appVersion, appCost);

        if(Utilities.validRange(level,1,10)){
            this.level = level;

        }else this.level = 0;


    }

    @Override
    public boolean isRecommendedApp() {
        if(super.getAppCost() >0.99 && super.calculateRating() >=3.5 && level >=3){
            return true;
        }else return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "EducationApp{" +
                "level=" + level +
                '}';
    }
}
