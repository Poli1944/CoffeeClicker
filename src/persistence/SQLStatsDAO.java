package persistence;

import business.entities.Config;
import business.entities.Stats;
import business.managers.StatsManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLStatsDAO {
    private final DatabaseConnector db;

    public SQLStatsDAO(Config config) {
        this.db = DatabaseConnector.getInstance(config);
    }

    public void saveStat(Stats stats, int gameId) {
        String query = "INSERT INTO stats (game_id, coffee_count, time_passed) VALUES (" + gameId + ", "
                + stats.getCoffeCount() + ", "
                + stats.getTime() + ")";
        db.insertQuery(query);
    }

    public List<Stats> getStatsByGame(int gameId) {
        List<Stats> statsList = new ArrayList<>();
        String query = "SELECT coffee_count, time_passed FROM stats WHERE game_id = " + gameId
                + " ORDER BY time_passed ASC";

        ResultSet rs = db.selectQuery(query);
        try {
            while (rs != null && rs.next()) {
                Stats s = new Stats(rs.getLong("time_passed"), rs.getDouble("coffee_count"));
                statsList.add(s);
            }
        } catch (SQLException e) {
            System.err.println("[SQLStatsDAO] Error getting stats: " + e.getMessage());
        }
        return statsList;
    }
}
