package probability;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import probability.imitation.Coordinates;
import probability.imitation.Imitation;
import probability.imitation.Probabilities;

/**
 *
 * @author i.epstein
 */
public class Probability {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int size = 5;
        Probabilities vertical = new Probabilities(0.5, 0.2, 0.3);
        Probabilities horizontal = new Probabilities(0.4, 0.2, 0.4);
        
        System.out.println("");
        System.out.println("=======CALCULATION========");
        System.out.println("");
        
        double verticalProb = getProbability(size, vertical);           //50% - stay, 30% - up, 20% - down
        System.out.println("vertical: "+verticalProb);                  //0.23525000000000001
        double horizontalProb = getProbability(size, horizontal);       //40% - stay, 20% - left, 40% - right
        System.out.println("horizontal: "+horizontalProb);              //0.18944000000000005
        
        double overall = verticalProb*horizontalProb;                   //both happened
        System.out.println("overall: "+overall);                        //0.04456576000000002
        
        System.out.println("");
        System.out.println("=========IMITATION========");
        System.out.println("");
        
        int experiments = 50000000;
        int success = 0;
        int failed=0;
        
        for(int i=0; i<experiments; i++){
            Coordinates result = Imitation.field(size, vertical, horizontal);
            if((result.getX()==0) && (result.getY()==0)){
                success++;
            } else{
                failed++;
            }
        }
        double result = (double)success/experiments;
        
        System.out.println("Experiments: "+experiments);
        System.out.println("Success: "+success);        //2229623
        System.out.println("Failed: "+failed);          //47770377
        System.out.println("Result: "+result);          //0.04459246
        
        System.out.println("");
        System.out.println("Difference (theory - imitation): "
                +new DecimalFormat("#.##########").format(Math.abs(overall-result)));   //0,0000267
    }
    
    static double getProbability(int moves, Probabilities probabilities){
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
                    
                    tmp[j] = init[j]*probabilities.getStay();
                    tmp[j-1] = init[j]*probabilities.getLeft();
                    tmp[j+1] = init[j]*probabilities.getRight();
                    
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
