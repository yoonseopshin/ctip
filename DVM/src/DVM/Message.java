package DVM;

public class Message
{
	int ID = -1;
	int Type = -1;
	int intData = -1;
	boolean boolData = false;
	
	Message(int ID, int Type)
	{
		this.ID = ID;
		this.Type = Type;
	}
	
	void intInput(int input)
	{
		this.intData = input;
	}
	
	void boolInput(boolean input)
	{
		this.boolData = input;
	}
}
