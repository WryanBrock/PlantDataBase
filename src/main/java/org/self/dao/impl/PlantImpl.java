package org.self.dao.impl;


import org.self.dao.DAO;
import org.self.model.Plant;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlantImpl extends DAO<Plant> {

    private static final Logger LOGGER = Logger.getLogger(PlantImpl.class.getName());
    private static final String INSERT = "INSERT INTO plant(name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color) VALUES (?,?,?,?,?,?,?,?);";
    private static final String GET_ALL = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant";
    private static final String GET_ONE = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant WHERE id = ?";
    private static final String DELETE = "DELETE FROM plant WHERE id = ?";
    private static final String UPDATE = "UPDATE plant SET name = ?, height_cm = ?, is_herb = ?, is_pern = ?, flower_time = ?, sprout_time = ?, min_sum_hr = ?, flower_color = ? WHERE id = ?";
    private static final String LOOKUP = "SELECT id FROM plant WHERE name = ?";
    private static final String PERENNIAL = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant WHERE is_pern = true";
    private static final String COLORS = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant ORDER BY flower_color";
    private static final String HEIGHTS = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant ORDER BY height_cm";
    private static final String SPROUTS = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant ORDER BY sprout_time";
    private static final String FLOWERS = "SELECT id, name, height_cm, is_herb, is_pern, flower_time, sprout_time, min_sum_hr, flower_color FROM plant ORDER BY flower_time";

    public PlantImpl(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public Plant findById(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPlant(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching plant by ID: " + id, e);
        }
        return null;
    }

    @Override
    public ArrayList<Plant> findAll() {
        ArrayList<Plant> plants = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                plants.add(mapResultSetToPlant(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching all plants", e);
        }
        return plants;
    }

    @Override
    public Plant update(Plant data) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            statement.setString(1, data.getName());
            statement.setInt(2, data.getHeight_cm());
            statement.setBoolean(3, data.is_herb());
            statement.setBoolean(4, data.is_pern());
            statement.setString(5, data.getFlower_time());
            statement.setString(6, data.getSprout_time());
            statement.setInt(7, data.getMinSum_hr());
            statement.setString(8, data.getFlower_color());
            statement.setInt(9, data.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return data;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating plant: " + data, e);
        }
        return null;
    }

    @Override
    public Plant create(Plant data) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, data.getName());
            statement.setInt(2, data.getHeight_cm());
            statement.setBoolean(3, data.is_herb());
            statement.setBoolean(4, data.is_pern());
            statement.setString(5, data.getFlower_time());
            statement.setString(6, data.getSprout_time());
            statement.setInt(7, data.getMinSum_hr());
            statement.setString(8, data.getFlower_color());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        data.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return data;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating plant: " + data, e);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting plant with ID: " + id, e);
        }
    }

    private Plant mapResultSetToPlant(ResultSet resultSet) throws SQLException {
        Plant plant = new Plant();
        plant.setId(resultSet.getInt("id"));
        plant.setName(resultSet.getString("name"));
        plant.setHeight_cm(resultSet.getInt("height_cm"));
        plant.setIs_herb(resultSet.getBoolean("is_herb"));
        plant.setIs_pern(resultSet.getBoolean("is_pern"));
        plant.setFlower_time(resultSet.getString("flower_time"));
        plant.setSprout_time(resultSet.getString("sprout_Time"));
        plant.setMinSum_hr(resultSet.getInt("min_sum_hr"));
        plant.setFlower_color(resultSet.getString("flower_color"));
        return plant;
    }
    public int lookUp(String name) {
        try (PreparedStatement statement = this.connection.prepareStatement(LOOKUP)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Assuming the lookup returns an integer ID or count
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error looking up plant with name: " + name, e);
        }
        return -1; // Return -1 to indicate not found or an error
    }
    public ArrayList<Plant> allPere() {
        ArrayList<Plant> plants = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(PERENNIAL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                plants.add(mapResultSetToPlant(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching perennial plants", e);
        }
        return plants;
    }
    public ArrayList<Plant> sortByColor() {
        ArrayList<Plant> plants = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(COLORS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                plants.add(mapResultSetToPlant(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching perennial plants", e);
        }
        return plants;
    }

    public ArrayList<Plant> sortByHeight() {
        ArrayList<Plant> plants = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(HEIGHTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                plants.add(mapResultSetToPlant(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching perennial plants", e);
        }
        return plants;
    }

    public ArrayList<Plant> sproutTimes() {
        ArrayList<Plant> plants = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SPROUTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                plants.add(mapResultSetToPlant(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching perennial plants", e);
        }
        return plants;
    }

    public ArrayList<Plant> FlowerTimes() {
        ArrayList<Plant> plants = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(FLOWERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                plants.add(mapResultSetToPlant(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching perennial plants", e);
        }
        return plants;
    }
}