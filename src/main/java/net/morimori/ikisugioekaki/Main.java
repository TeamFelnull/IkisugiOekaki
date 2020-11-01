package net.morimori.ikisugioekaki;

import net.morimori.ikisugioekaki.pallet.ColorPallet;
import net.morimori.ikisugioekaki.pallet.PalletTool;
import net.morimori.ikisugioekaki.util.Pos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static boolean stopd;

    public static int windowx;
    public static int windowy;
    public static int windoww = 1000;
    public static int windowh = 700;

    public static int campusx = 3;
    public static int campusy = 32;
    public static int campusw = 775;
    public static int campush = 480;

    public static final Map<ColorPallet, List<Pos>> colors = new HashMap<>();

    public static void main(String[] args) throws InterruptedException, AWTException, MalformedURLException {
        //     int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
        //     int sh = Toolkit.getDefaultToolkit().getScreenSize().height;


        StopCheckThread sct = new StopCheckThread();
        sct.start();


        System.out.println("ウィンドウの左上をクリック");
        int[] pos = getClickdMousePosition();
        windowx = pos[0];
        windowy = pos[1];
        System.out.println("ウィンドウのクリックを確認");
        System.out.println("座標 x:" + windowx + " y:" + windowy);
/*
        while (true) {
            int[] pos2 = getClickdMousePosition();
            System.out.println("x=" + (pos2[0] - windowx) + " y=" + (pos2[1] - windowy));
            System.out.println(ColorUtil.getColor((pos2[0]), (pos2[1])));
            if (false)
                break;
        }
*/
        ColorPallet.init();


        Thread.sleep(100);
/*
        try {
            PalletTool.selectSize(3);
            Robot r = new Robot();
            r.delay(10);
            r.mouseMove(windowx + campusx, windowy + campusy);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            boolean re = false;
            for (int i = 0; i < campush; i++) {
                if (re) {
                    r.mouseMove(windowx + campusx + campusw, windowy + campusy + i);
                    r.delay(3);
                    r.mouseMove(windowx + campusx + campusw, windowy + campusy + i + 1);
                } else {
                    r.mouseMove(windowx + campusx, windowy + campusy + i);
                    r.delay(3);
                    r.mouseMove(windowx + campusx, windowy + campusy + i + 1);
                }
                r.delay(15);
                re = !re;
                if (stopd)
                    Thread.currentThread().stop();
            }
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
*/

        File pictuer = new File("D:\\Pictures\\DropBox\\avatar.jpg");
        URL url = new URL("https://cdn.discordapp.com/attachments/608914022883917824/772354417344774184/unknown.png");
        if (pictuer.exists()) {
            try {
                BufferedImage image = ImageIO.read(url);
                int size = campush;
                float w = image.getWidth();
                float h = image.getHeight();
                int aw = size;
                int ah = size;
                if (w == h) {
                    aw = size;
                    ah = size;
                } else if (w > h) {
                    aw = size;
                    ah = (int) ((float) size * (h / w));
                } else if (w > h) {
                    aw = (int) ((float) size * (w / h));
                    ah = size;
                }
                BufferedImage reImage = new BufferedImage(aw, ah, image.getType());
                reImage.createGraphics().drawImage(image.getScaledInstance(aw, ah, Image.SCALE_AREA_AVERAGING), 0, 0, aw, ah, null);
                for (int y = 0; y < reImage.getHeight(); y++) {
                    for (int x = 0; x < reImage.getWidth(); x++) {
                        int c = reImage.getRGB(x, y);
                        ColorPallet pallet = ColorPallet.getColorByColorCode(c);
                        if (!colors.containsKey(pallet))
                            colors.put(pallet, new ArrayList<>());
                        colors.get(pallet).add(new Pos(x, y));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("描画開始");
        try {
            Robot r = new Robot();
            r.delay(10);

            colors.forEach((n, m) -> {
                PalletTool.select(n.getPosition());

                for (int i = 0; i < campush; i++) {
                    int finalI = i;
                    Map<Pos, Pos> lipos = new HashMap<>();
                    m.stream().filter(pn -> pn.getY() == finalI).forEach(pn -> {
                        if (lipos.entrySet().stream().anyMatch(en -> en.getValue().getX() + 1 == pn.getX())) {
                            lipos.entrySet().stream().filter(en -> en.getValue().getX() + 1 == pn.getX()).forEach(en -> {
                                lipos.put(en.getKey(), pn);
                            });
                        } else {
                            lipos.put(pn, pn);
                        }
                    });
                    lipos.forEach((n2, m2) -> {
                        r.mouseMove(windowx + campusx + n2.getX(), windowy + campusy + finalI);
                        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        r.mouseMove(windowx + campusx + m2.getX(), windowy + campusy + finalI);
                        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        if (stopd)
                            Thread.currentThread().stop();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        } catch (AWTException e) {
            e.printStackTrace();
        }


        System.out.println("終了");
        sct.stop();
    }

    public static void clearCampus() {

        try {
            Robot r = new Robot();
            PalletTool.selectEraser();
            r.delay(10);
            for (int i = 0; i < campush; i++) {
                r.mouseMove(windowx + campusx, windowy + campusy + i);
                r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                r.mouseMove(windowx + campusx + campusw, windowy + campusy + i);
                r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    public static int[] getClickdMousePosition() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("py clickposgetter.py");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                int[] pos = {Integer.parseInt(s.split(":")[0]), Integer.parseInt(s.split(":")[1])};
                return pos;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static class StopCheckThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    Process process = runtime.exec("py stopclickgetter.py");
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String s = null;
                    while ((s = stdInput.readLine()) != null) {
                        if (s.equals("cricked")) {
                            stopd = true;
                        }
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (stopd)
                    break;
            }
        }
    }

}
