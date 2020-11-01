package net.morimori.ikisugioekaki.pallet;

import net.morimori.ikisugioekaki.Main;
import net.morimori.ikisugioekaki.util.Pos;

import java.awt.*;
import java.awt.event.InputEvent;

public class PalletTool {
    public static void selectEraser() {
        select(Main.windowx + 655, Main.windowy + 615);
    }

    public static void selectSize(int size) {
        select(Main.windowx + 814 + (22 * (size - 1)), Main.windowy + 663);
    }

    public static void selectAllClear() {
        select(Main.windowx + 980, Main.windowy + 635);
        sleep(200);
        select(Main.windowx + 462, Main.windowy + 421);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void select(Pos pos) {
        select(Main.windowx + pos.getX(), Main.windowy + pos.getY());
    }

    private static void select(int x, int y) {
        try {
            Robot r = new Robot();
            r.mouseMove(x, y);
            r.delay(10);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.delay(10);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
