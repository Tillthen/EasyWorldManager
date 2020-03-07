package com.ewm.generator;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TreePopulator extends BlockPopulator {
    TreeType[] types = {
        TreeType.BIG_TREE, TreeType.TREE, TreeType.DARK_OAK, TreeType.JUNGLE, TreeType.ACACIA, TreeType.SMALL_JUNGLE
    };
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextBoolean()) {
            int amount = random.nextInt(20) + 1;
            for (int i = 1; i < amount; i++) {
                int X = random.nextInt(20);
                int Z = random.nextInt(20);
                int Y;
                for (Y = world.getMaxHeight() - 1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--) {
                    int treetype = random.nextInt(types.length);
                    world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), types[treetype]);
                }
            }
        }
    }
}
