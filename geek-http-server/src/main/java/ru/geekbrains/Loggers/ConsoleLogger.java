package ru.geekbrains.Loggers;

public class ConsoleLogger implements Logger{
    @Override
    public void info(String infoMsg){
        System.out.println(infoMsg);
    }
}
