package probability.immitation;

import java.util.Random;

/**
 *
 * @author i.epstein
 */
public class Immitation {
    
    public static Coordinates field(int moves, Probabilities vertical, Probabilities horizontal){
        Coordinates coordinates = new Coordinates(0, 0);
        Random rnd = new Random();
        for(int i=0;i<moves;i++){
            double next = rnd.nextDouble();
            
            if(Double.compare(next, vertical.getLeft())<0){coordinates.setY(coordinates.getY()-1);}                             //go down
            else if(Double.compare(next, (vertical.getLeft()+vertical.getRight()))<1){coordinates.setY(coordinates.getY()+1);}    //go up
            
            next = rnd.nextDouble();
            
            if(Double.compare(next, horizontal.getLeft())<0){coordinates.setX(coordinates.getX()-1);}                               //go left
            else if(Double.compare(next, (horizontal.getLeft()+horizontal.getRight()))<1){coordinates.setX(coordinates.getX()+1);}    //go right
        }
        
        return coordinates;
    }
    
}
