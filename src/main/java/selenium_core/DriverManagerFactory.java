package selenium_core;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String type) throws Exception {
        DriverManager driverManager;
        type = type.toUpperCase();

        switch (type){
            case "CHROME":{
                driverManager = new ChromeDriverManager();
            }
            break;
            case "FIREFOX":{
                driverManager = new FirefoxDriverManager();
            }
            break;
            default: throw new Exception("Browser: "+type+" is not supported!");
//            default: driverManager = new ChromeDriverManager();
        }
        return driverManager;

    }

}
