package nim;

public class Record {
	private int occur, win;
	private double winRate;
	
	public Record()
	{
		occur = 0;
		win = 0;
		winRate = 0;
	}
	
	public void addRecord(boolean isWin)
	{
		occur++;
		
		if(isWin)
			win++;
		
		if(win != 0)
		{
			winRate = win/occur;
		}
		else
		{
			winRate = 0;
		}
	}
	
	public int getOccur()
	{
		return occur;
	}
	
	public int getWin()
	{
		return win;
	}
	
	public double getWinRate()
	{
		return winRate;
	}
}
