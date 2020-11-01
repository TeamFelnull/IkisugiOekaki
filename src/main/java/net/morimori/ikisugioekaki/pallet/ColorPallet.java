package net.morimori.ikisugioekaki.pallet;

import net.morimori.ikisugioekaki.util.ColorUtil;
import net.morimori.ikisugioekaki.util.Pos;

import java.util.ArrayList;
import java.util.List;

public class ColorPallet {
    public static List<ColorPallet> colorPallets = new ArrayList<>();

    private final Pos position;
    private final int color;

    public ColorPallet(Pos pos, int color) {
        this.position = pos;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Pos getPosition() {
        return position;
    }

    public static void init() {
        colorPallets.add(new ColorPallet(new Pos(695, 610), 2234389));
     //   colorPallets.add(new ColorPallet(new Pos(714, 610), 16777215));
        colorPallets.add(new ColorPallet(new Pos(740, 595), 15073298));
        colorPallets.add(new ColorPallet(new Pos(755, 595), 16094720));
        colorPallets.add(new ColorPallet(new Pos(769, 595), 16773120));
        colorPallets.add(new ColorPallet(new Pos(740, 626), 6890460));
        colorPallets.add(new ColorPallet(new Pos(755, 626), 16711680));
        colorPallets.add(new ColorPallet(new Pos(769, 626), 255));
        colorPallets.add(new ColorPallet(new Pos(802, 595), 16076935));
        colorPallets.add(new ColorPallet(new Pos(817, 595), 16430195));
        colorPallets.add(new ColorPallet(new Pos(833, 595), 10222335));
        colorPallets.add(new ColorPallet(new Pos(846, 595), 3329415));
        colorPallets.add(new ColorPallet(new Pos(862, 595), 6599935));
        colorPallets.add(new ColorPallet(new Pos(876, 595), 8874465));

        System.out.println(colorPallets.size() + "個のカラーが登録されました");
    }

    public static ColorPallet getColorByColorCode(int colornum) {


        ColorPallet color = null;
        int min = 255;
        for (ColorPallet c : colorPallets) {
            int minn = ColorUtil.difference(c.getColor(), colornum);
            if (minn <= min) {
                min = minn;
                color = c;
            }
        }
        return color;
    }

}
