package invaders.logic;
import invaders.logic.BulletRule;

public class SlowRule implements BulletRule{
    @Override
    public double down(double y){
        return y + 1;
    }
}