package dao;

import model.GameScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {

    /** INSERT using PreparedStatement. */
    public void save(GameScore s) throws SQLException {
        String sql = "INSERT INTO game_scores (player_name, score, difficulty, played_at) VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getPlayerName());
            ps.setInt(2, s.getScore());
            ps.setString(3, s.getDifficulty());
            ps.setTimestamp(4, Timestamp.valueOf(s.getPlayedAt()));
            ps.executeUpdate();
        }
    }

    /** Top scores: highest first; earlier time wins ties. */
    public List<GameScore> getTopScores(int limit) throws SQLException {
        String sql = "SELECT id, player_name, score, difficulty, played_at "
                   + "FROM game_scores ORDER BY score DESC, played_at ASC LIMIT ?";
        List<GameScore> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GameScore g = new GameScore();
                    g.setId(rs.getInt("id"));
                    g.setPlayerName(rs.getString("player_name"));
                    g.setScore(rs.getInt("score"));
                    g.setDifficulty(rs.getString("difficulty"));
                    g.setPlayedAt(rs.getTimestamp("played_at").toLocalDateTime());
                    list.add(g);
                }
            }
        }
        return list;
    }
}
