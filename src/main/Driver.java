package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                case 2 ->  runAppStoreMenu();
                case 3 ->  runReportsMenu();
                case 4 -> searchAppsBySpecificCriteria();
                case 5-> System.out.println(appStoreAPI.listAllAppsByName());
                case 6 -> System.out.println(appStoreAPI.listAllRecommendedApps());
                case 7 -> System.out.println(appStoreAPI.randomApp(appStoreAPI.apps));
              //  case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }

    private int appMenu() {
        System.out.println("""
                 -------App Menu-------
                |   1) Add an app                              |
                |   0) RETURN to main menu                     |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runAppStoreMenu() {
        int option = appMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appMenu();
        }
    }


    //public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
    private void addApp() {

        int developer = ScannerInput.validNextInt("Enter the developers index");
        String appName  = ScannerInput.validNextLine("Please enter the app name: ");
        double appSize = ScannerInput.validNextDouble("Please enter the app size: ");
        double appVersion = ScannerInput.validNextDouble("Please enter the app Version");
        double appCost = ScannerInput.validNextDouble("Please enter the app cost");

        appStoreAPI.addApp(new App(developerAPI.getDevelopers().get(developer), appName, appSize, appVersion, appCost) {

            @Override
            public boolean isRecommendedApp() {
                return false;
            }
        });
    }




    private int ReportsMenu() {
        System.out.println("""
                 -------Reports Menu-------
                |   1) List all apps              |
                |   2) List summary of apps       |
                |   3) List all game apps         |
                |   4) List all education app     |
                |   5) List all productivity      |
                |   6) List by rating             |
                |   7) List by developer          |
                |   8) Delete Apps                |
                |   0) RETURN to main menu        |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runReportsMenu() {
        int option = ReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(appStoreAPI.listAllApps());
                case 2 -> System.out.println(appStoreAPI.listSummaryOfAllApps());
                case 3 -> System.out.println(appStoreAPI.listAllGameApps());
                case 4 -> System.out.println(appStoreAPI.listAllEducationApps());
                case 5 -> System.out.println(appStoreAPI.listAllProductivityApps());
                case 6 -> listAllAppsAboveOrEqualAGivenStarRating();
                case 7 -> listAllAppsByChosenDeveloper();
                case 8 -> deleteAppByIndex();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = ReportsMenu();
        }
    }

    private int deleteAppByIndex() {
        int indextodelete  = ScannerInput.validNextInt("Please enter the app Index: ");
        if (appStoreAPI.deleteAppByIndex(indextodelete) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
        return indextodelete;
    }
    public void listAllAppsAboveOrEqualAGivenStarRating(){

    }

    public void listAllAppsByChosenDeveloper(){

    }




    //--------------------------------------------------
    // TODO UNCOMMENT THIS CODE as you start working through this class
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps of that rating or above)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            // TODO Search methods below
            // case 1 -> searchAppsByName();
            // case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
            // case 3 -> searchAppsEqualOrAboveAStarRating();
            // default -> System.out.println("Invalid option");
        }
    }

    //--------------------------------------------------
    // TODO UNCOMMENT THIS COMPLETED CODE as you start working through this class
    //--------------------------------------------------
    //private void simulateRatings() {
        //if (appStoreAPI.numberOfApps() > 0) {
        //    System.out.println("Simulating ratings...");
          //  appStoreAPI.simulateRatings();
            //System.out.println(appStoreAPI.listSummaryOfAllApps());
        //} else {
          //  System.out.println("No apps");
        //}
    //}



    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        // TODO try-catch to save the developers to XML file
        // TODO try-catch to save the apps in the store to XML file
    }

    private void loadAllData() {
        // TODO try-catch to load the developers from XML file
        // TODO try-catch to load the apps in the store from XML file
    }

}
