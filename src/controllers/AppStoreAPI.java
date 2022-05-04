package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.random;
import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI {

    public List<App> apps = new ArrayList<>();

    public boolean addApp(App app) {
        apps.add(app);
        return true;
    }

    public String listAllApps() {
        if (apps.isEmpty()) {
            return "No apps";
        } else {
            String listOfApps = "";
            for (int i = 0; i < apps.size(); i++) {
                listOfApps += i + ": " + apps.get(i) + "\n";  //adds all apps to one string and returns the string
            }
            return listOfApps;
        }
    }

    public String listSummaryOfAllApps() {
        String summary = "";
        for (int i = 0; i < apps.size(); i++) {
            summary += i + ": " + apps.get(i).appSummary() + "\n";

        }
        return summary;
    }


    public String listAllGameApps() {
        if (apps.isEmpty()) {
            return "No apps";
        } else {
            String listOfApps = "";
            for (int i = 0; i < apps.size(); i++) {
                if(apps.get(i) instanceof GameApp)
                    listOfApps += i + ": " + apps.get(i) + "\n";  //adds all apps to one string and returns the string
            } if(listOfApps.isEmpty())
                return "NO Game Apps";
            return listOfApps;
        }
    }

    public String listAllEducationApps() {
        if (apps.isEmpty()) {
            return "No apps";
        } else {
            String listOfApps = "";
            for (int i = 0; i < apps.size(); i++) {
                if(apps.get(i) instanceof EducationApp)
                    listOfApps += i + ": " + apps.get(i) + "\n";  //adds all apps to one string and returns the string
            } if(listOfApps.isEmpty())
                return "NO Education Apps";
            return listOfApps;
        }
    }


    public String listAllProductivityApps() {
        if (apps.isEmpty()) {
            return "No apps";
        } else {
            String listOfApps = "";
            for (int i = 0; i < apps.size(); i++) {
                if(apps.get(i) instanceof ProductivityApp)
                    listOfApps += i + ": " + apps.get(i) + "\n";  //adds all apps to one string and returns the string
            } if(listOfApps.isEmpty())
                return "NO Productivity Apps";
            return listOfApps;
        }
    }

    public String listAllAppsByName() {
        if (apps.isEmpty()) {
            return "No apps";
        } else {
            String listOfApps = "";
            for (int i = 0; i < apps.size(); i++) {
                listOfApps += i + ": " + apps.get(i).getAppName() + "\n";  //adds all apps to one string and returns the string
            }
            return listOfApps;
        }
    }


    public String listAllAppsAboveOrEqualAGivenStarRating(int star) {
        if (apps.isEmpty()) {
            return "No Apps";
        } else {
            String listOfApps = "";
            for (int i = 0; i < apps.size(); i++) {
                for (int j = 0; j < apps.get(i).getRatings().size(); j++)
                    if (apps.get(i).getRatings().get(j).getNumberOfStars() >= star) {
                        listOfApps += i + ": " + apps.get(i).getAppName() + "\n";
                    }

            }
            return listOfApps;

        }
    }

    public String listAllRecommendedApps() {
        String listOfRecommendedApps = "";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).isRecommendedApp() == true) {
                listOfRecommendedApps = i + "\n";
            }
        }
        return listOfRecommendedApps;
    }

    public String listAllAppsByChosenDeveloper(Developer developer) {
        if (apps.isEmpty()) {
            return "No apps by chosen Developer";
        } else {
            String listOfChosenDeveloper = "";
            for (int i = 0; i < apps.size(); i++) {
                if (apps.get(i).getDeveloper().equals(developer)) {
                    listOfChosenDeveloper = listOfChosenDeveloper + apps.get(i).getDeveloper() + "\n";
                }
            }
            return listOfChosenDeveloper;
        }
    }

    public int numberOfAppsByChosenDeveloper(Developer developer) {
        int counter = 0;
        for (int i = 0; i < apps.size(); i = i + 1) {
            if (apps.get(i).getDeveloper() == developer) {
                counter++;
            }
        }
        return counter;
    }

    public App deleteAppByIndex(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return apps.remove(indexToDelete);
        }
        return null;
    }

    public App randomApp(List<App> apps) {
        if (apps.isEmpty()) {
            return null;
        } else {
            return apps.get(ThreadLocalRandom.current().nextInt(apps.size()));
        }
    }

    // Method to simulate ratings (using the RatingUtility).
    // This will be called from the Driver (see skeleton code)

    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }




    //---------------------
    // Validation methods
    //---------------------
    // TODO UNCOMMENT THIS COMPlETED method as you start working through this class
    //---------------------

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }

    public boolean isValidAppName(String appname) {
        return ((appname.length() >= 0) && (appname.length() < 30));
    }

    //---------------------
    // Persistence methods
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED CODE block as you start working through this class
    //---------------------

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public String fileName() {
        return "apps.xml";
    }
}

