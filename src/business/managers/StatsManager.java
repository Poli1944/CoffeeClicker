package business.managers;

import business.entities.Stats;
import persistence.SQLStatsDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatsManager {

    private SQLGameDAO gameDAO;
    private SQLStatsDAO statsDAO;

    public StatsManager(SQLStatsDAO statsDAO, SQLGameDAO gameDAO) {
        this.statsDAO = statsDAO;
        this.gameDAO = gameDAO;
    }

    public Map<Long, Double> makeMap(List<Stats> statsList) {
        Map<Long, Double> chartData = new HashMap<>();

        if (statsList != null) {
            for (Stats s : statsList) {
                long minutes = s.getTime() / (1000 * 60);
                chartData.put(minutes, s.getCoffeCount());
            }
        }
        return chartData;
    }

    public ArrayList<Game> getFinishedGamesByUser(String username) {
        return gameDAO.getGamesByUser(username);
    }



}



