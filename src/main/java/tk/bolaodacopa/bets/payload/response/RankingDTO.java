package tk.bolaodacopa.bets.payload.response;

public interface RankingDTO {
/*	
	private String username;	
	private String name;	
	private Integer total;	
	private Integer correctmatches;	
	private Integer myrank;	
	
	public RankingDTO() {
		super();
	}

	public RankingDTO(String username, String name, Integer total, Integer correctmatches, Integer myrank) {
		super();
		this.username = username;
		this.name = name;
		this.total = total;
		this.correctmatches = correctmatches;
		this.myrank = myrank;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCorrectmatches() {
		return correctmatches;
	}

	public void setCorrectmatches(Integer correctmatches) {
		this.correctmatches = correctmatches;
	}

	public Integer getMyrank() {
		return myrank;
	}

	public void setMyrank(Integer myrank) {
		this.myrank = myrank;
	}
*/
	
	String getUsername();
	String getName();
	Integer getTotal();
	Integer getCorrectmatches();
	Integer getMyrank();

}
