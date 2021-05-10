package DVM;

public class DVM
{
    public DVM(int ID, double Address)
    {
        this.ID =  ID;
        this.Address =  ID;
    }

    int ID; // 메세지 보낼 타겟 아이디

    double Address; // 실제 주소

    boolean available = false; // 요청이 왔을때 재고 존재 여부
}