public class Matchup{
	private Team homeTeam;
	private float homeTeamScore;
	private Team visitorTeam;
	private float visitorTeamScore;

	Matchup(Team h, float hscore, Team v, float vscore){
		this.homeTeam = h;
		this.homeTeamScore = hscore;
		this.visitorTeam = v;
		this.visitorTeamScore = vscore;
	}

	public Team getHomeTeam(){ return this.homeTeam; }
	public Team getVisitorTeam(){ return this.visitorTeam; }
 	public float getHomeTeamScore(){ return this.homeTeamScore;}
	public float getVisitorTeamScore(){ return this.visitorTeamScore;}

	public void setHomeTeam(Team h){ this.homeTeam = h; }
	public void setVisitorTeam(Team v){ this.visitorTeam = v; }
 	public void setHomeTeamScore(float hscore ){ this.homeTeamScore = hscore;}
	public void setVisitorTeamScore(float vscore){ this.visitorTeamScore = vscore;}
}
