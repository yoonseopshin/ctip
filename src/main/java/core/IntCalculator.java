package core;

public final class IntCalculator implements Calculator<Integer> {

    @Override
    public Integer add(final Integer t1, final Integer t2) {
        return t1 + t2;
    }

    @Override
    public Integer subtract(final Integer t1, final Integer t2) {
        return t1 - t2;
    }

    @Override
    public Integer multiply(final Integer t1, final Integer t2) {
        return t1 * t2;
    }

    @Override
    public Integer divide(final Integer t1, final Integer t2) {
        return t1 / t2;
    }
}
