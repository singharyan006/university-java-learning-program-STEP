package model;

import java.util.Arrays;

public final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || evolutionStages == null || evolutionStages.length == 0 || maxLifespan <= 0 || habitat == null) {
            throw new IllegalArgumentException("Invalid species data");
        }
        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return Arrays.copyOf(evolutionStages, evolutionStages.length); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() {
        return "PetSpecies{" + "speciesName='" + speciesName + '\'' + ", habitat='" + habitat + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PetSpecies)) return false;
        PetSpecies other = (PetSpecies) obj;
        return speciesName.equals(other.speciesName) && habitat.equals(other.habitat);
    }

    @Override
    public int hashCode() {
        return speciesName.hashCode() + habitat.hashCode();
    }
}
