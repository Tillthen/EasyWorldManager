package com.ewm.registry;


import org.bukkit.Material;

import java.util.ArrayList;

public class WorldInfo {
    public int height;
    public String name;
    public ArrayList<Material> materials;
    public String type;
    public double sharpness;
    public WorldInfo(int height, String name, ArrayList<Material> material, String type, double sharpness){
        this.height = height;
        this.name = name;
        this.materials = material;
        this.type = type;
        this.sharpness = sharpness;
    }
}
