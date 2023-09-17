package invaders.logic;
import invaders.logic.BulletRule;

public class FastRule implements BulletRule{
    @Override
    public double down(double y){
        return y + 2;
    }
}