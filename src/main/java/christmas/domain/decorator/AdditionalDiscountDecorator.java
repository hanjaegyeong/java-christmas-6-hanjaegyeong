package christmas.domain.decorator;

import christmas.domain.strategy.BasicDiscountStrategy;

public abstract class AdditionalDiscountDecorator implements BasicDiscountStrategy {
    // 추상클래스의 서브클래스에서 사용 가능하도록 protected
    protected BasicDiscountStrategy strategy;

    public AdditionalDiscountDecorator(BasicDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void applyDiscount() {
        strategy.applyDiscount();
    }
}
