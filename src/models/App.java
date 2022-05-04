package models;

import utils.Utilities;

import java.util.List;

public abstract class App {

    private Developer developer;
    private String appName = "No app name";
    private double appSize = 0;
    private double appVersion = 1.0;
    private double appCost = 0.0;
    private List<Rating> ratings;

    public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        this.developer = developer;
        this.appName = appName;


        if(Utilities.validRange(appSize,1,1000)){
            this.appSize = appSize;
        }else{
            this.appSize = 0;
        }


        if(Utilities.greaterThanOrEqualTo(appVersion,1.0)){
            this.appVersion = appVersion;
        }else{
            this.appVersion = 1.0;
        }

        if(Utilities.greaterThanOrEqualTo(appCost,0)){
            this.appCost = appCost;
        }else{
            this.appCost = 0;
        }


    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public double getAppSize() {
        return appSize;
    }

    public void setAppSize(double appSize) {
        this.appSize = appSize;
    }

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }

    public double getAppCost() {
        return appCost;
    }

    public void setAppCost(double appCost) {
        this.appCost = appCost;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public abstract boolean isRecommendedApp();

    public boolean addRating(Rating rating) {
      ratings.add(rating);
      return true;
    }


    public String listRating() {
        if (ratings.isEmpty()) {
            return "No ratings added";
        } else {
            String listRatings = "";
            for (int i = 0; i < ratings.size(); i++) {
                listRatings += i + ": " + ratings.get(i) + "\n";  //adds all ratings to one string and returns the string
            }
            return listRatings;
        }
    }

    public int calculateRating(){
        int average = 0;
        for(int i = 0;i < ratings.size(); i = i +1){
            int averageStars = ratings.get(i).getNumberOfStars() / ratings.size();
            average = averageStars;

        }
        return average;
    }

    public String appSummary(){
        return appName + " " + appVersion+ " " + developer + " "+ appCost + " " + " " +  calculateRating();
    }

    @Override
    public String toString() {
        return "App{" +
                "developer=" + developer +
                ", appName='" + appName + '\'' +
                ", appSize=" + appSize +
                ", appVersion=" + appVersion +
                ", appCost=" + appCost +
                ", ratings=" + ratings +
                '}';
    }
}
