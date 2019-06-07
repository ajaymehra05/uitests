package config;

import logger.ILogger;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import java.net.URL;

public class ConfigManager implements ILogger {

    private static ConfigManager configManager;
    PropertiesConfiguration defaultConfig = null;
    CompositeConfiguration config = new CompositeConfiguration();

    private ConfigManager() {
        try {
            LOG.info("Configurations are loaded from config.properties ");
            URL configFile = ConfigManager.class.getClassLoader().getResource("/config.properties");
            if(configFile == null) {
                configFile = ConfigManager.class.getClassLoader().getResource("config.properties");
                if(configFile == null){
                    throw new RuntimeException("could not load config.properties");
                }
            }

            defaultConfig = new PropertiesConfiguration(configFile);

            defaultConfig.setAutoSave(true);
            config.addConfiguration(defaultConfig);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    //Get a singleton instance of {@link ConfigManager}
    public static ConfigManager getInstance() {
        if (configManager == null) {
            synchronized (ConfigManager.class) {
                if (configManager == null) {
                    configManager = new ConfigManager();
                }
            }
        }
        return configManager;
    }

    public String getString(String key) {
        String val = System.getProperty(key);
        return !StringUtils.isEmpty(val)? val : config.getString(key);
    }

    public Integer getInt(String key) {
        String val = System.getProperty(key);
        return !StringUtils.isEmpty(val)? Integer.parseInt(val.trim()) : config.getInt(key);

    }

}


