package controller;

import dao.ScoreDAO;
import model.GameScore;
import model.Question;
import service.MathService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private final ScoreDAO dao = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("leaderboard".equals(req.getParameter("action"))) {
            showLeaderboard(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("start".equals(action)) {
            startGame(req, resp);
        } else if ("submit".equals(action)) {
            submitAnswers(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    // Screen 1 -> Screen 2
    private void startGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = trim(req.getParameter("playerName"));
        if (name.isEmpty() || name.length() > 100) {
            req.setAttribute("error", "Please enter your name (1-100 characters).");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }
        req.getSession(true).setAttribute("playerName", name);
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    // Screen 2 -> Screen 3
    private void submitAnswers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String name = session == null ? null : (String) session.getAttribute("playerName");
        if (name == null) {
            req.setAttribute("error", "Game session expired. Please start a new game.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        List<Question> questions = new ArrayList<>();
        double[] answers = new double[4];
        String[] shown = new String[4];
        try {
            // Q1 - prime: number + 0/1
            int primeNo = parseInt(req, "primeNumber", "Q1: enter a whole number to test.");
            answers[0] = parseChoice(req, "primeAnswer", "Q1: choose your answer, 1 (Prime) or 0 (Not Prime).");
            shown[0] = String.valueOf((int) answers[0]);
            questions.add(new Question(Question.PRIME, primeNo, 0, null, 0, 2));

            // Q2 - modulus: two integers + answer
            int a = parseInt(req, "modA", "Q2: first value must be a whole number.");
            int b = parseInt(req, "modB", "Q2: second value must be a whole number.");
            if (b == 0) throw new IllegalArgumentException("Q2: the divisor must not be zero.");
            answers[1] = parseInt(req, "modAnswer", "Q2: your answer must be a whole number.");
            shown[1] = String.valueOf((int) answers[1]);
            questions.add(new Question(Question.MOD, a, b, null, 0, 2));

            // Q3, Q4 - trigonometry: degree + function + answer
            for (int i = 1; i <= 2; i++) {
                String q = "Q" + (i + 2) + ": ";
                double deg = parseDouble(req, "trig" + i + "Degree", q + "enter the angle in degrees (a number).");
                String func = trim(req.getParameter("trig" + i + "Func"));
                if (!func.equals("SIN") && !func.equals("COS") && !func.equals("TAN")) {
                    throw new IllegalArgumentException(q + "choose sin, cos or tan.");
                }
                if (func.equals("TAN") && Double.isNaN(MathService.calculateTan(deg))) {
                    throw new IllegalArgumentException(q + "tan is undefined for " + deg + "\u00B0. Choose another angle.");
                }
                answers[i + 1] = parseDouble(req, "trig" + i + "Answer", q + "enter your answer (a number, e.g. 0.87).");
                shown[i + 1] = trim(req.getParameter("trig" + i + "Answer"));
                questions.add(new Question(Question.TRIG, 0, 0, func, deg, 3));
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/game.jsp").forward(req, resp);
            return;
        }

        boolean[] correct = new boolean[4];
        List<String[]> details = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Question q = questions.get(i);
            correct[i] = MathService.isCorrect(q, answers[i]);
            details.add(new String[]{
                    q.getText(), shown[i], MathService.expectedText(q),
                    correct[i] ? "Correct" : "Wrong", String.valueOf(correct[i] ? q.getMarks() : 0)});
        }
        int totalScore = MathService.calculateScore(questions, correct);

        try {
            dao.save(new GameScore(name, totalScore, MathService.DIFFICULTY));
        } catch (SQLException e) {
            log("Could not save score", e);
            req.setAttribute("saveError", "Your score could not be saved to the database: " + e.getMessage());
        }

        req.setAttribute("score", totalScore);
        req.setAttribute("details", details);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    // Screen 4
    private void showLeaderboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("scores", dao.getTopScores(10));
        } catch (SQLException e) {
            log("Could not load leaderboard", e);
            req.setAttribute("error", "Could not load leaderboard: " + e.getMessage());
        }
        req.getRequestDispatcher("/leaderboard.jsp").forward(req, resp);
    }

    // ---------- helpers: throw IllegalArgumentException(message) on bad input ----------
    private int parseInt(HttpServletRequest req, String param, String msg) {
        try {
            return Integer.parseInt(trim(req.getParameter(param)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(msg);
        }
    }

    private double parseDouble(HttpServletRequest req, String param, String msg) {
        try {
            double v = Double.parseDouble(trim(req.getParameter(param)));
            if (!Double.isFinite(v)) throw new IllegalArgumentException(msg);
            return v;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(msg);
        }
    }

    private int parseChoice(HttpServletRequest req, String param, String msg) {
        String v = trim(req.getParameter(param));
        if (!v.equals("0") && !v.equals("1")) throw new IllegalArgumentException(msg);
        return Integer.parseInt(v);
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
