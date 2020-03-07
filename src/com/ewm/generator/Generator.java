package com.ewm.generator;


import com.ewm.registry.GeneratorSettings;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.OctaveGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Generator extends ChunkGenerator {

    @Override
    public List<BlockPopulator> getDefaultPopulators(World w){
        BlockPopulator[] populators = {new TreePopulator()};
        return Arrays.asList(populators);
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biomeGrid) {
        ChunkData result = createChunkData(world);
        Random rand = new Random(world.getSeed());

        /* Default settings */
        String type = "simplex";
        int height = 150;
        double sharpness = 1.5;
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(Material.GRASS); materials.add(Material.COBBLESTONE); materials.add(Material.STONE); materials.add(Material.GRASS);

        if (GeneratorSettings.byName(world.getName()) != null) {
            type = GeneratorSettings.byName(world.getName()).type;
            height = GeneratorSettings.byName(world.getName()).height;
            materials = GeneratorSettings.byName(world.getName()).materials;
            sharpness = GeneratorSettings.byName(world.getName()).sharpness;
        }
        OctaveGenerator g = null;
        if (type.equalsIgnoreCase("perlin")) {
            g = new PerlinOctaveGenerator(rand, 100);
        } else if (type.equalsIgnoreCase("simplex")){
            g = new SimplexOctaveGenerator(rand, 100);
        } else {
            g = new SimplexOctaveGenerator(rand, 100);
        }
        if (g != null)
            g.setScale(1 / 64.0);

        if (type.equalsIgnoreCase("flat")) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    result.setBlock(x, 0, z, Material.BEDROCK);
                    for (int y = 0; y < height; y ++) {
                        int index = rand.nextInt(materials.size());
                        result.setBlock(x, y, z, materials.get(index));
                    }
                }
            }
        } else if (type.equalsIgnoreCase("simplex") || type.equalsIgnoreCase("perlin")){
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    result.setBlock(x, 0, z, Material.BEDROCK);
                    result.setBlock(x, 1, z, Material.STATIONARY_WATER);
                    double noise = g.noise(x + chunkX * 16, z + chunkZ * 16, 0.5, 0.001) * 26;
                    for (int y = 1; y <= height + noise * sharpness; y++) {
                        int index = rand.nextInt(materials.size());
                        result.setBlock(x, y, z, materials.get(index));
                    }
                }
            }
        }
        return result;
    }
}
