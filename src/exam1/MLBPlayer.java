package exam1;

import java.util.Scanner;

/**
 * Class that stores relevant information about a basketball player, including:
 * player's name, team, position of play, number of games played, number of
 * bats hit, number of runs scored, number of hits, number of doubles scored,
 * number of triples scored, number of home runs, number of total runs batted,
 * batting average and the fraction of times the player managed to reach base.
 */
public class MLBPlayer {

	private String name;
	private String team;
	private String position;
	private int nGames;
	private int atBats;
	private int runsScored;
	private int hits;
	private int doubles;
	private int triples;
	private int homeRuns;
	private double runsBatted;
	private double battingAverage;
	private double onBaseRatio;
	private double sluggingRatio;
	private double onBaseSluggingRatio;

	public MLBPlayer(String name, String team, String position, int nGames,
			int atBats, int runsScored, int hits, int doubles, int triples,
			int homeRuns, double runsBatted, double battingAverage,
			double onBaseRatio) {
		this.name = name;
		this.team = team;
		this.position = position;
		this.nGames = nGames;
		this.atBats = atBats;
		this.runsScored = runsScored;
		this.hits = hits;
		this.doubles = doubles;
		this.triples = triples;
		this.homeRuns = homeRuns;
		this.runsBatted = runsBatted;
		this.battingAverage = battingAverage;
		this.onBaseRatio = onBaseRatio;
		calcAll();
	}

	///////////////////////////////////////
	// Getters for all internal members  //
	///////////////////////////////////////

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public String getPosition() {
		return position;
	}

	public int getnGames() {
		return nGames;
	}

	public int getAtBats() {
		return atBats;
	}

	public int getRunsScored() {
		return runsScored;
	}

	public int getHits() {
		return hits;
	}

	public int getDoubles() {
		return doubles;
	}

	public int getTriples() {
		return triples;
	}

	public int getHomeRuns() {
		return homeRuns;
	}

	public double getRunsBatted() {
		return runsBatted;
	}

	public double getBattingAverage() {
		return battingAverage;
	}

	public double getOnBaseRatio() {
		return onBaseRatio;
	}

	public double getSluggingRatio() {
		return sluggingRatio;
	}

	public double getOnBaseSluggingRatio() {
		return onBaseSluggingRatio;
	}

	/**
	 * Internal method for calculating and storing the slugging ratio and the
	 * on-base slugging ratio of the player.
	 */
	private void calcAll() {
		double numerator = hits + 2*doubles + 3*triples + 4*homeRuns;
		this.sluggingRatio = numerator / atBats;
		this.onBaseSluggingRatio = sluggingRatio + onBaseRatio;
	}

	public static MLBPlayer fromString(String str) {
		Scanner sc = new Scanner(str);
		sc.useDelimiter("\t");

		String name = sc.next();
		String team = sc.next();
		String position = sc.next();
		int nGames = sc.nextInt();
		int atBats = sc.nextInt();
		int runsScored = sc.nextInt();
		int hits = sc.nextInt();
		int doubles = sc.nextInt();
		int triples = sc.nextInt();
		int homeRuns = sc.nextInt();
		double runsBatted = sc.nextInt();
		double battingAverage = sc.nextDouble();
		double onBaseRatio = sc.nextDouble();

		sc.close();

		MLBPlayer p = new MLBPlayer(name, team, position, nGames, atBats,
				runsScored, hits, doubles, triples, homeRuns, runsBatted,
				battingAverage, onBaseRatio);

		return p;
	}

	@Override
	public String toString() {
		return String.format(
				"%s who played for %s in the position %s. His/her stats are: [nGames=%s, atBats=%s, runsScored=%s, hits=%s, doubles=%s, triples=%s, homeRuns=%s, runsBatted=%.2f, battingAverage=%.2f, onBaseRatio=%.2f, sluggingRatio=%.2f, onBaseSluggingRatio=%.2f]",
				name, team, position, nGames, atBats, runsScored, hits,
				doubles, triples, homeRuns, runsBatted, battingAverage,
				onBaseRatio, sluggingRatio, onBaseSluggingRatio);
	}
	
	

}
