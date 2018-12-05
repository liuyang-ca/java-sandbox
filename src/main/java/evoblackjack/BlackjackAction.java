package evoblackjack;

// All of the action you can perform in blackjack
public enum BlackjackAction {

	STAND,
	HIT,
	DOUBLE,
	SPLIT;
	
	// Convert a given action to a string for printing tables.
	public String convertActionToString()
	{
		switch(this)
		{
			case STAND:
				return "S ";
			case HIT:
				return "H ";
			case SPLIT:
				return "SP";
			default:
				return "DD";			
		}
	}
	
}
