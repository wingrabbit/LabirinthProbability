package probability.imitation;

/**
 *
 * @author i.epstein
 */
public class Probabilities {
    
    private final double stay;
    
    private final double left;
    
    private final double right;

    public Probabilities(double stay, double left, double right) {
        this.stay = stay;
        this.left = left;
        this.right = right;
    }

    public double getStay() {
        return stay;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }
    
}
