package net.morimori.ikisugioekaki.util;

public class IKSGMath {
    public static int differenceInt(int n1, int n2) {
        int r = n1 - n2;
        if (r < 0)
            r *= -1;
        return r;
    }

    public static int averageInt(int... n) {
        int r = 0;
        for (int a : n) {
            r += a;
        }
        r /= n.length;
        return r;
    }
}
