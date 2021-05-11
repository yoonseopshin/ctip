package Logic;

class Message
{
    private int ID = -1;
    private int Type = -1;
    private int intData = -1;
    private boolean boolData = false;

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


