package probability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author i.epstein
 */
public class Probability {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double vertical = getProbability(5, 0.5, 0.3, 0.2);     //50% - stay, 30% - up, 20% - down
        System.out.println("vertical: "+vertical);
        double horizontal = getProbability(5, 0.4, 0.2, 0.4);   //40% - stay, 20% - left, 40% - right
        System.out.println("horizontal: "+horizontal);
        
        double overall = vertical*horizontal;                   //both happened
        System.out.println("overall: "+overall);
    }
    
    static double getProbability(int moves, double stayProb, double leftProb, double rightProb){
        double[] init = new double[moves*2+1];
        double[] empty = new double[moves*2+1];
        Arrays.fill(init, 0.0);
        Arrays.fill(empty, 0.0);
        
        init[moves] = 1.0;
        
        for(int i=0; i<moves; i++){
            List<double[]> calc = new ArrayList<double[]>();
            for(int j=1; j<init.length-1; j++){
                if(Double.compare(init[j], 0.0)!=0){
                    double[] tmp = Arrays.copyOf(empty, empty.length);
                    
                    tmp[j] = init[j]*stayProb;
                    tmp[j-1] = init[j]*leftProb;
                    tmp[j+1] = init[j]*rightProb;
                    
                    //System.out.println(Arrays.toString(tmp));
                    calc.add(tmp);
                }
            }
            init = sumLists(calc);
        }
        
        //System.out.println(Arrays.toString(init));
        return init[moves];
    }
    
    static double[] sumLists(List<double[]> lists){
        double[] result = new double[lists.get(0).length];
        Arrays.fill(result, 0.0);
        for(double[] list: lists){
            for(int i=0; i<list.length; i++){
                result[i]+=list[i];
            }
        }
        return result;
    }
    
}
