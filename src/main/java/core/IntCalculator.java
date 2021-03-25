package core;

public class IntCalculator implements Calculator<Integer> {

    @Override
    public Integer add(Integer t1, Integer t2) {
        return t1 + t2;
    }

    @Override
    public Integer subtract(Integer t1, Integer t2) {
        return t1 - t2;
    }

    @Override
    public Integer multiply(Integer t1, Integer t2) {
        return t1 * t2;
    }

    @Override
    public Integer divide(Integer t1, Integer t2) {
        return t1 / t2;
    }
}
