package Logic;

public class Message
{
    int ID = -1;
    int Type = -1;
    int xAdress = -1;
    int yAdress = -1;
    int Title = -1;
    int C_Number = -1;

    boolean boolData = false;

    void setmsg(int id, int type, int data)
    {
        ID = id;
        Type = type;
        if(Type == 1 ||Type == 6)
            Title = data;
    }
    void setmsg(int id, int type, boolean data)
    {
        ID = id;
        Type = type;
        if(Type == 2 ||Type == 7)
            boolData = data;
    }
    void setmsg(int id, int type, int intdata, boolean booldata)
    {
        ID = id;
        Type = type;
        Title = intdata;
        boolData = booldata;
    }
    void setmsg(int id, int type, int data1, int data2)
    {
        ID = id;
        Type = type;
        if(Type == 3)
        {
            Title = data1;
            C_Number = data2;
        }
        else if(Type == 5)
        {
            xAdress = data1;
            xAdress = data2;
        }
    }
}

