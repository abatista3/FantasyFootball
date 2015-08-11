
import java.util.HashMap;
import java.util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class Schedule{
	private HashMap<Integer,ArrayList<Matchup>> schedule = new HashMap<Integer,ArrayList<Matchup>>();

	public Schedule(){
		for(Integer i = 1; i <= 13; i++){
			schedule.put(i,null);
		}
	}

	public void generateLeagueSchedule(HashMap<String,Team> league){
		ArrayList<Matchup> weekMatchups;
		ArrayList<String> leagueKeySet = new ArrayList<String>(league.keySet());
		Random r = new Random();
		Team[] homeTeams = new Team[league.size()/2];
		Team[] visitorTeams = new Team[league.size()/2];
		int h = 0;
		int v = 0;
		for(String teamName : leagueKeySet){
			if(h < league.size()/2){
				homeTeams[h] = league.get(teamName);
				h++;
			}
			else{
				visitorTeams[v] = league.get(teamName);
				v++;
			}
		}

		ArrayList<Integer> weeks = randomizeWeeks(13);
		// need to do n weeks
		for(int week = 0 ; week < 13 ; week++){
			weekMatchups  = new ArrayList<Matchup>();
			System.out.println(week);
			for(int i = 0; i < league.size()/2; i++){
				System.out.println(homeTeams[i].getTeamName() + "    vs   " + visitorTeams[i].getTeamName());
				weekMatchups.add(new Matchup(homeTeams[i],0,visitorTeams[i],0));
			}
			schedule.put(weeks.get(week), weekMatchups);
			Team[] all = rotateHomeVisitorTeams(homeTeams,visitorTeams);
			homeTeams = Arrays.copyOfRange(all, 0, league.size()/2);
			visitorTeams = Arrays.copyOfRange(all, league.size()/2, league.size());
		}
	}

	public Team[] rotateHomeVisitorTeams(Team[] ht, Team[] vt){
		Team lastHomeTeam = ht[ht.length-1];
		Team luckyTeam = ht[0];
		Team firstVisitorTeam = vt[0];
		
		for(int i = ht.length - 1; i >= 0; i--){
			if(i == 0){ht[i] = luckyTeam;}
			else if(i == 1){ ht[i] = firstVisitorTeam;}
			else{ ht[i] = ht[i-1];}
		}

		for(int i = 0; i < vt.length; i++){
			vt[i] = (i==vt.length-1 ? lastHomeTeam : vt[i+1]);
		}
		
		Team[] result = new Team[ht.length + vt.length];
		int i = 0;
		for(Team t : ht){
			result[i] = t;
			i++;
		}
		for(Team t: vt){
			result[i] = t;
			i++;
		}
		return result;
		
	}

	public ArrayList<Integer> randomizeWeeks(int numberOfWeeks){
		ArrayList<Integer> weeks = new ArrayList<Integer>();
		for(Integer i = 1; i <= (Integer) numberOfWeeks; i++){
			weeks.add(i);
		}

		Collections.shuffle(weeks);
		return weeks;
	}
	/* Previous code for reference while development is done
	public static HashMap<String,HashMap<String,Integer>> initializeSchedule(){
		HashMap<String,HashMap<String,Integer>> schedule = new HashMap<String,HashMap<String,Integer>>();
		for(String team : teamNames){
			HashMap<String,Integer> teamSchedule = new HashMap<String,Integer>();
			for(String rival : teamNames){
				Integer week = new Integer(0);
				if(!team.equals(rival)){
					teamSchedule.put(rival,week);
				}
			}
			schedule.put(team,teamSchedule);
		}
		return schedule;
	} 





	*/
}
