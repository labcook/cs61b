package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private int postion;
    private double multiFactor;

    public AcceleratingSawToothGenerator(int period, double multiFactor) {
        this.period = period;
        this.multiFactor = multiFactor;
        this.state = 0;
        this.postion = 0;
    }

    @Override
    public double next() {
        state = (state + 1);
        postion = postion + 1;
        if (postion == period) {
            postion = 0;
            period *= multiFactor;
        }
        return 2.0 * (postion - period / 2) / period;
    }
}
