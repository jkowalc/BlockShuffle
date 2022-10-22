package pl.speedriders.blockshuffle;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import pl.speedriders.blockshuffle.Utils;
public class MaterialRandomizer {
    enum MaterialType {
        COLORS
    }
    private HashMap<MaterialType, List<Material>> possibleMaterials;
    private void loadPossibleMaterials(MaterialType type) {
        if(possibleMaterials.containsKey(type)) {
            return;
        }
        switch(type) {
            case COLORS:
                possibleMaterials.put(type, Arrays.asList(
                        Material.RED_TERRACOTTA,
                        Material.WHITE_TERRACOTTA,
                        Material.YELLOW_TERRACOTTA
                ));
        }
    }

    public Material generateRandomMaterial(MaterialType type) {
        loadPossibleMaterials(type);
        return Utils.random(possibleMaterials.get(type));
    }
}
