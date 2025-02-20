package org.self.model;

public class Plant {

    private int id;
    private String name;
    private int height_cm;
    private boolean is_herb;
    private boolean is_pern;
    private String flower_time;
    private String sprout_time;
    private int minSum_hr;
    private String flower_color;

    public Plant() {
    }
    public Plant( String name, int height_cm, boolean is_herb, boolean is_pern, String flower_time, String sprout_time, int minSum_hr, String flower_color) {

        this.name = name;
        this.height_cm = height_cm;
        this.is_herb = is_herb;
        this.is_pern = is_pern;
        this.flower_time = flower_time;
        this.sprout_time = sprout_time;
        this.minSum_hr = minSum_hr;
        this.flower_color = flower_color;
    }
    public Plant(int id, String name, int height_cm, boolean is_herb, boolean is_pern, String flower_time, String sprout_time, int minSum_hr, String flower_color) {
        this.id = id;
        this.name = name;
        this.height_cm = height_cm;
        this.is_herb = is_herb;
        this.is_pern = is_pern;
        this.flower_time = flower_time;
        this.sprout_time = sprout_time;
        this.minSum_hr = minSum_hr;
        this.flower_color = flower_color;
    }

    @Override
    public String toString() {
        return "Plant{" +
                id +
                ", " + name  +
                ", " + height_cm +
                ", " + flower_color +
                ", " + is_herb +
                ", " + is_pern +
                ", " + flower_time +
                ", " + sprout_time +
                ", " + minSum_hr +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight_cm() {
        return height_cm;
    }

    public void setHeight_cm(int height_cm) {
        this.height_cm = height_cm;
    }

    public boolean is_herb() {
        return is_herb;
    }

    public void setIs_herb(boolean is_herb) {
        this.is_herb = is_herb;
    }

    public boolean is_pern() {
        return is_pern;
    }

    public void setIs_pern(boolean is_pern) {
        this.is_pern = is_pern;
    }

    public String getFlower_time() {
        return flower_time;
    }

    public String getFlower_color() {
        return flower_color;
    }

    public void setFlower_color(String flower_color) {
        this.flower_color = flower_color;
    }

    public void setFlower_time(String flower_time) {
        this.flower_time = flower_time;
    }

    public String getSprout_time() {
        return sprout_time;
    }

    public void setSprout_time(String sprout_time) {
        this.sprout_time = sprout_time;
    }

    public int getMinSum_hr() {
        return minSum_hr;
    }

    public void setMinSum_hr(int minSum_hr) {
        this.minSum_hr = minSum_hr;
    }
}