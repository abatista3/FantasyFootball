public class TeamPref implements Comparable{
	private Integer preference;
	private String team;

	public TeamPref(String s, Integer i){
		preference = i;
		team = s;
	}

	public Integer getPreference(){ return preference; }
	public String getTeamName(){ return team; }

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return ((TeamPref)arg0).getPreference() - this.preference;
	}
	
}
