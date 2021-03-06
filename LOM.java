
import java.util.HashMap;
import java.util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class LOM{
	private static HashMap<String,Team> league = new HashMap<String,Team>();
	private static ArrayList<ArrayList<Team>> conferences = new ArrayList<ArrayList<Team>>();
	private static String teamNames[] = {
			"Kaep N Crunch",
			"The Seam",
			"ChiCheese",
			"SanBenitoCamela",
			"Weinchester United",
			"Project Mayhem",
			"Villegas",
			"Broco Semirecargado",
			"Iguanas",
			"Crudotes FC",
			"Massive DBags",
			"Mexihawks FC",
			"Peques",
			"The Multiple Scoregasms"};	


	public static void main(String args[]){
		String topEight[] = {
			"Project Mayhem",
			"Mexihawks FC",
			"Kaep N Crunch",
			"Peques",
			"The Seam",
			"Weinchester United",
			"Villegas",
			"ChiCheese"};
		
		String worstSix[] = {
			"Massive DBags",
			"Iguanas",
			"Crudotes FC",
			"The Multiple Scoregasms",
			"Broco Semirecargado",
			"SanBenitoCamela"};
		
		generateTeams();
		conferences = makeConferences(topEight, worstSix);
		printConferences(conferences);
		Schedule schedule = new Schedule();
		schedule.generateLeagueSchedule(league);
		//generateLeagueSchedule();
	}

	/*	Creates team objects for each of the league specified in the teamNames String array.
		Team objects are then added to the league hashmap
	*/
	public static void generateTeams(){
		for(int i = 0 ; i < teamNames.length ; i++){
			league.put(teamNames[i],new Team(teamNames[i]));
		}		
	}

	/*

	*/
	public static ArrayList<ArrayList<Team>> makeConferences(String[] best, String[] worst ){
		ArrayList<Team> top = new ArrayList<Team>();
		ArrayList<Team> bottom = new ArrayList<Team>();
		for( String teamName : teamNames ){
			if(Arrays.asList(best).contains(teamName)){top.add(league.get(teamName));}
			else{bottom.add(league.get(teamName));} 
		}		
		Collections.shuffle(top);
		Collections.shuffle(bottom);
		ArrayList<Team> Norte = new ArrayList<Team>();
		ArrayList<Team> Sur = new ArrayList<Team>();
		boolean b = true;
		for(int i = 0; i < top.size(); i++){
			if(b){
				Norte.add(top.get(i));
				if(i < bottom.size()){ Norte.add(bottom.get(i)); }
			}
			else{
				Sur.add(top.get(i));
				if(i < bottom.size()){ Sur.add(bottom.get(i)); }

			}
			b = !b;	
		}		
		ArrayList<ArrayList<Team>> c = new ArrayList<ArrayList<Team>>();
		c.add(Norte);
		c.add(Sur);
		for( Team t : Norte ){
			league.get(t.getTeamName()).setTeamConference("Zapoteco");
		}
		for( Team t : Sur ){
			league.get(t.getTeamName()).setTeamConference("Purepecha");
		}
		return c;
	}

	public static void printConferences(ArrayList<ArrayList<Team>> conferences){
		Integer i = new Integer(1);
		for(ArrayList<Team> conf : conferences){
			System.out.println("Conference " + i.toString());
			for( Team t : conf ){
				System.out.println(t.getTeamName());
			}
			i++;
			System.out.println();
		}
	}

/*
	public static HashMap<String,String> generateWeekMatchups(HashMap<String,HashMap<String,Integer>> teamsPrefs){
		HashMap<String,String> weekMatchups = new HashMap<String,String>();
		//generate week matchups
		Queue<String> q = new LinkedList<String>(Arrays.asList(teamNames));
		HashMap<String,Queue<String>> teamsToAsk = fillTeamsToAskQueue(teamsPrefs); // Each team has its queue sorted from highest priority to lowest.
		while(!q.isEmpty()){
			String team = q.poll();
			String rival = teamsToAsk.get(team).poll();
			if(rival!=null){
					if(weekMatchups.get(rival) == null){
						// compare if team has a matchup already
						if(weekMatchups.get(team) == null){
							weekMatchups.put(team,rival);
							weekMatchups.put(rival,team);
						}
						else{
							String currentRival = weekMatchups.get(team);
							if(teamsPrefs.get(team).get(currentRival) < teamsPrefs.get(team).get(rival)){
								weekMatchups.put(currentRival,null);
								weekMatchups.put(team,rival);
								weekMatchups.put(rival,team);
								q.add(currentRival);
							}
						}
					}
					else{
						String rivalsCurrentRival = weekMatchups.get(rival);
						// compare if team has a matchup already
						if(weekMatchups.get(team) == null){
							// just check if rival teams prefers team to its currentRival
							if(teamsPrefs.get(rival).get(rivalsCurrentRival) < teamsPrefs.get(rival).get(team)){
								weekMatchups.put(team,rival);
								weekMatchups.put(rivalsCurrentRival,null);
								weekMatchups.put(rival,team);
								q.add(rivalsCurrentRival);
							}
							else{
								q.add(team);
							}
						}
							else{ // both team and rival have matchups, see if they prefer each other
								String currentRival = weekMatchups.get(team);
								if(teamsPrefs.get(team).get(currentRival) < teamsPrefs.get(team).get(rival) && teamsPrefs.get(rival).get(rivalsCurrentRival) < teamsPrefs.get(rival).get(team)){
									weekMatchups.put(currentRival,null);
									weekMatchups.put(rivalsCurrentRival,null);
									weekMatchups.put(team,rival);
									weekMatchups.put(rival,team);
									q.add(currentRival);
									q.add(rivalsCurrentRival);
								}
							}
					}
				
			}
			else{
				if(weekMatchups.get(team) == null){
						teamsToAsk = fillTeamsToAskQueue(teamsPrefs);
						q.add(team);
					}
				}
			}
		return weekMatchups;
	}




	public static void printWeekMatchups(HashMap<String,String> weekMatchups, int week){
		Iterator<String> matchupKeyIterator = weekMatchups.keySet().iterator();
                //System.out.println("Week " + i);
                System.out.println("Week " + week);
                while(matchupKeyIterator.hasNext()){
                        String team = matchupKeyIterator.next();
                        String rival = weekMatchups.get(team);
                        System.out.println(team + " vs. " + rival);
               }
	}


	
	


	public static HashMap<String,Queue<String>> fillTeamsToAskQueue(HashMap<String, HashMap<String,Integer>> tp){
		HashMap<String,ArrayList<TeamPref>> teamsToAsk = new HashMap<String,ArrayList<TeamPref>>();
		HashMap<String,Queue<String>> sortedTeamsToAsk = new HashMap<String,Queue<String>>();
		Iterator<String> teamKeyIterator = tp.keySet().iterator();
		while(teamKeyIterator.hasNext()){
			String team = teamKeyIterator.next();
			ArrayList<TeamPref> teamRivalsPreferences = new ArrayList<TeamPref>();
			Iterator<String> rivalKeyIterator = tp.get(team).keySet().iterator();
			while(rivalKeyIterator.hasNext()){
				String rival = rivalKeyIterator.next();
				teamRivalsPreferences.add(new TeamPref(rival,tp.get(team).get(rival)));
			} 		
			teamsToAsk.put(team,teamRivalsPreferences);
		}

		// sort array list of each team
		teamKeyIterator = teamsToAsk.keySet().iterator();
		while(teamKeyIterator.hasNext()){
			String team = teamKeyIterator.next();
			Collections.sort(teamsToAsk.get(team));
		} 

		// add array list to queue
		teamKeyIterator = teamsToAsk.keySet().iterator();
		while(teamKeyIterator.hasNext()){
			String team = teamKeyIterator.next();
			Queue<String> q = new LinkedList<String>();
			for( TeamPref rival : teamsToAsk.get(team)){
				q.add(rival.getTeamName());
			}
			sortedTeamsToAsk.put(team,q);
		}
		return sortedTeamsToAsk;
	}
*/
}
