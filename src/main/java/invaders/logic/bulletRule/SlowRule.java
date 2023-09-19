package invaders.logic.bulletRule;

import invaders.logic.bulletRule.BulletRule;

public class SlowRule implements BulletRule{
    @Override
    public double down(double y){
        return y + 1;
    }
}