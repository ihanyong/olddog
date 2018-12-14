package olddog.fraction;

import lombok.Getter;

/**
 * Fraction
 *
 * Immutable
 * @author yong.han
 * 2018/10/11
 */
@Getter
public class Fraction {
    private final long numerator;
    private final long denominator;

    public Fraction(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("分母不能为零");
        }
        long gcd = this.gcd(numerator, denominator);
        this.numerator = numerator/gcd;
        this.denominator = denominator/gcd;
    }

    public Fraction() {
        this(0, 1);
    }

    public Fraction add (Fraction augend) {
        return new Fraction(this.getNumerator()*augend.getDenominator() + augend.getNumerator()*this.getDenominator(), this.getDenominator()*augend.getDenominator());
    }
    public Fraction sub (Fraction subtrahend) {
        return new Fraction(this.getNumerator()*subtrahend.getDenominator() - subtrahend.getNumerator()*this.getDenominator(), this.getDenominator()*subtrahend.getDenominator());
    }

    private long gcd(long a, long b) {
        long mod = a % b;
        if (mod == 0) {
            return b;
        } else {
            return gcd(b, mod);
        }
    }


    @Override
    public String toString() {
        return "Fraction{" + numerator + "/" + denominator + '}';
    }
}
