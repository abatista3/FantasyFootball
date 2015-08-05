
import java.util.HashMap;
import java.util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class TeamsPreferences{

	private HashMap<Team,HashMap<Team,Integer>> teamsPreferences = new HashMap<Team,HashMap<Team,Integer>>();

	public HashMap<Team,HashMap<Team,Integer>> getTeamsPreferences(){ return teamsPreferences; }
	
	/* Method that gets as input a league (teams) and returns the preferences that each team has to play against each other
	*  The preferences are generated randomly, but an offset is added to teams that are on the same conference since teams on
	*  the same conference must play against each other.
	*/
	public HashMap<Team,HashMap<Team,Integer>> createTeamRandomPreferences(HashMap<String,Team> league ){
		HashMap<Team,HashMap<Team,Integer>> teamsPrefs = new HashMap<Team,HashMap<Team,Integer>>();
		Random r = new Random();
		int offset = 10; // offset so that teams prefere to play teams of same conference
		Iterator<String> teamKeyIterator = league.keySet().iterator();
		while(teamKeyIterator.hasNext()){
			Team team = league.get(teamKeyIterator.next());
			HashMap<Team,Integer> teamPref = new HashMap<Team,Integer>();
			Iterator<String> rivalKeyIterator = league.keySet().iterator();
			while(rivalKeyIterator.hasNext()){
				Team rival = league.get(rivalKeyIterator.next());
				if(!team.equals(rival)){
					teamPref.put(rival,(team.getTeamConference().equals(rival.getTeamConference()) ? new Integer(r.nextInt(8) + offset) : new Integer(r.nextInt(8))));
					//if(team.getTeamConference().equals(rival.getTeamConference())){
					//	teamPref.put(rival,new Integer(r.nextInt(8)+ offset));
					//}
					//else{
					//	teamPref.put(rival,new Integer(r.nextInt(8)));
					//}
				}
			}
			teamsPrefs.put(team,teamPref);
		}
		teamsPreferences = teamsPrefs;
		return teamsPrefs;
	}

	/* Method that prints the provided team name and the preferences he has to play against his rivals.
	*  Mainly used for troubleshooting purposes
	*/
	public void printTeamPreferences(Team team){
		Iterator<Team> teamIterator = teamsPreferences.get(team).keySet().iterator();
		System.out.println(team.getTeamName());
		while(teamIterator.hasNext()){
			Team rival = teamIterator.next();
			Integer pref = teamsPreferences.get(team).get(rival);
			System.out.println("	" + rival.getTeamName() + " " + pref);
		}
	}

	/* Method that prints all of the teams preferences to play their rivals.
	*  Mainly used for troubleshooting purposes
	*/
	public void printTeamsPreferences(){
		Iterator<Team> teamIterator = teamsPreferences.keySet().iterator();
		while(teamIterator.hasNext()){
			Team team = teamIterator.next();
			printTeamPreferences(team);
		}

	}	

	public void cleanTeamsPrefs(HashMap<Team,Team> weekMatchups){
		Iterator<Team> teamIterator = teamsPreferences.keySet().iterator();
		while(teamIterator.hasNext()){
			Team team = teamIterator.next();
			teamsPreferences.get(team).remove(weekMatchups.get(team));
		}
	}

	public ArrayList<Team> sortTeamPreferences(Team t){
		HashMap<Team,Integer> teamPreferences = teamsPreferences.get(t);
		ArrayList<Team> rivals = new ArrayList<Team>(teamPreferences.keySet());
		// bubble sort
		boolean b = true;
		while(b){
			b = false;
			for(int i = 0; i < rivals.size(); i++){
				if(i+1 != rivals.size()){
					if(teamPreferences.get(rivals.get(i)) < teamPreferences.get(rivals.get(i+1))){
						Team tmp = rivals.get(i);
						rivals.set(i,rivals.get(i+1));
						rivals.set(i+1,tmp);
						b = true;
					}
				}
			}
		}
		return rivals;
		
	}

}
