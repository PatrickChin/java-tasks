package exam1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Class analysing a URL containing tab separated data on the hitting statistics
 * for all MLB players from the year 2001.
 * It will count the total number of players, find the player with the most home 
 * runs, and for each team, find the number of players with at least 10 at-bats,
 * the player with the highest slugging percentage, and the player with the highest
 * slugging percentage.
 */
public class MLBAnalysis {

	/**
	 * Sub-class that stores the team-wise analysis data,
	 */
	class TeamAnalysis {

		/** Team analysed */
		String team;

		/** Number of players with at least 10 bats. */
		int n10bats;

		/** The best slugger */
		MLBPlayer bestSlugger;

		/** The best on-base slugger */
		MLBPlayer bestOBSlugger;

		/** Simple constructor initalizing member values */
		TeamAnalysis(String team, int n10bats, MLBPlayer bestSlugger, MLBPlayer bestOBSlugger) {
			this.team = team;
			this.n10bats = n10bats;
			this.bestSlugger = bestSlugger;
			this.bestOBSlugger = bestOBSlugger;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("For team ").append(team).append("\n");
			builder.append("The number of players with at least 10 bats is ").append(n10bats).append("\n");
			builder.append("The best slugger on this team was ").append(bestSlugger).append("\n");
			builder.append("The best on-base slugger on this team was ").append(bestOBSlugger).append("\n");
			return builder.toString();
		}

		
	}

	/** URL from which data was read */
	private String url;

	/** List of all parsed MBLPlayer objects */
	private ArrayList<MLBPlayer> players = new ArrayList<>();

	/** Hash map of players grouped by team and using the team as a key */
	private HashMap<String,ArrayList<MLBPlayer>> teamPlayerMap = new HashMap<>();

	/** Hash map of TeamAnalysis objects for each team, using the team as a key */
	private HashMap<String,TeamAnalysis> teamAnalysisMap = new HashMap<>();

	/** Number of players analysed */
	private int nPlayers;

	/** Player with the most home runs */
	private MLBPlayer playerMostHomeruns;

	/**
	 * Constructor reading data from url and parsing into an array list of MLBPlayers, 
	 * then grouping by team and carrying out the analysis.
	 */
	public MLBAnalysis(String url) throws IOException {
		this.url = url;

		// Use a try-with-resources block to read from url and close streams
		try (LineNumberReader br =
				new LineNumberReader(new InputStreamReader(new URL(url).openStream()))) {

			for (String line = br.readLine();
					line != null;
					line = br.readLine()) {

				// Ignore enpty lines
				if (line.isEmpty()) {
					continue;
				}

				// Try and parse the line and skip if the line is not in
				// the expected format.
				try {
					players.add(MLBPlayer.fromString(line));
				} catch (InputMismatchException e) {
					System.out.println( "Line number " + br.getLineNumber() +
							" from the url:\n\t" + url +
							"\ncould not be parsed. Skipping...\n");
				}
			}

		}

		nPlayers = players.size();

		// Create hash map grouping players by team
		groupByTeam();

		// Analyse and store all data
		analyse();
	}

	private void analyse() {

		int maxHomeRuns = 0;
		for (MLBPlayer p : players) {
			if (p.getHomeRuns() > maxHomeRuns) {
				maxHomeRuns = p.getHomeRuns();
				playerMostHomeruns = p;
			}
		}

		// For each team carry out analysis
		for (Map.Entry<String,ArrayList<MLBPlayer>> e : teamPlayerMap.entrySet()) {
			String team = e.getKey();
			ArrayList<MLBPlayer> teamMembers = e.getValue();

			int n10bats = 0;
			double bestSluggingRatio = Double.NEGATIVE_INFINITY;
			double bestOBSluggingRatio = Double.NEGATIVE_INFINITY;
			MLBPlayer bestSlugger = null;
			MLBPlayer bestOBSlugger = null;
			for (MLBPlayer p : teamMembers) {

				// Ignore players with less than 10 bats
				if (p.getAtBats() < 10) {
					continue;
				}
				// Count total number of players with more than 10 bats
				n10bats++;

				// Find best slugger
				double sluggingRatio = p.getSluggingRatio();
				if (sluggingRatio > bestSluggingRatio) {
					bestSluggingRatio = sluggingRatio;
					bestSlugger = p;
				}

				// Find best on base slugger
				double onBaseSluggingRatio = p.getOnBaseSluggingRatio();
				if (onBaseSluggingRatio > bestOBSluggingRatio) {
					bestOBSluggingRatio = onBaseSluggingRatio;
					bestOBSlugger = p;
				}

			}

			TeamAnalysis teamAnalysis = new TeamAnalysis(team, n10bats, bestSlugger, bestOBSlugger);
			teamAnalysisMap.put(team, teamAnalysis);
		}
	}


	private void groupByTeam() {
		teamPlayerMap = new HashMap<>();

		// Iterate through all players
		for (MLBPlayer p : players) {
			String key = p.getTeam();
			if (!teamPlayerMap.containsKey(key)) {
				// Initalise ArrayList of members
				teamPlayerMap.put(key, new ArrayList<>());
			}

			// Add player to list pointed to by key
			teamPlayerMap.get(key).add(p);
		}
	}


	@Override
	public String toString() {
		StringBuilder sb =  new StringBuilder();
		sb.append("Results from analysing URL: ").append(url).append("\n");
		sb.append("Number of players analysed: ").append(nPlayers).append("\n");
		sb.append("Player who hit the most home runs: ").append(playerMostHomeruns).append("\n\n");
		sb.append("Team-wise analysis:\n");

		for (TeamAnalysis ta : teamAnalysisMap.values()) {
			sb.append(ta).append("\n");
		}

		// for (TeamAnalysis
		return sb.toString();
	}

}
