package invaders.logic.bulletRule;

import invaders.logic.bulletRule.BulletRule;

public class FastRule implements BulletRule{
    @Override
    public double down(double y){
        return y + 2;
    }
}