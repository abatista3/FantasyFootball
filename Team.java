import java.util.HashMap;

public class Team{
	private String name;
	private String owner;
	private String conference;
	private HashMap<Integer,Team> schedule;

	public Team(String n){
		name = n;
	}

	public Team(String n, String c, HashMap<Integer,Team> s){
		name = n;
		conference = c;
		schedule = s;
	}

	public String getTeamName(){ return this.name; }
	public String getTeamOwner(){ return this.owner; }
	public String getTeamConference(){ return this.conference; }
	public HashMap<Integer,Team> getTeamSchedule(){	return this.schedule; }

	public void setTeamName(String n){ this.name = n; }
	public void setTeamOwner(String o){ this.owner = o; }
	public void setTeamConference(String c){ this.conference = c; }
	public void setTeamSchedule(HashMap<Integer,Team> s){ this.schedule = s;}



}
