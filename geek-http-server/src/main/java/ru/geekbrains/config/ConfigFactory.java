package ru.geekbrains.config;

public class ConfigFactory {
    public static Config create(String[] args, String fileName){
        if (args.length == 2){
            return new ConfigFromCli(args);
        }else {
            return new ConfigFromFile(fileName);
        }
    }
}
