package ru.netology.conditionalproperty.Profile;

public class ProductionProfile implements SystemProfile{

    @Override
    public String getProfile() {
        return "Initiating production profile";
    }
}
