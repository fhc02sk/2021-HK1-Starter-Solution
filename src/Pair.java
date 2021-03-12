import java.util.Objects;

public class Pair <F, S>{
    private F firstValue;
    private S secondValue;

    public Pair(F firstValue, S secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public F getFirstValue() {
        return firstValue;
    }

    public S getSecondValue() {
        return secondValue;
    }

    @Override
    public String toString() {
        return firstValue + "-" + secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
