package com.ewm.registry;

import java.util.ArrayList;

public class GeneratorSettings {
    public static ArrayList<WorldInfo> info = new ArrayList<WorldInfo>();
    public static WorldInfo byName(String name){
        for (int i = 0; i < info.size(); i ++){
            if (info.get(i).name.equalsIgnoreCase(name))
                return info.get(i);
        }
        return null;
    }
}
