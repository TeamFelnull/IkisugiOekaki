package net.morimori.ikisugioekaki.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColorUtil {
    public static int getColor(int x, int y) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("py colorgetter.py " + x + " " + y);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                return Integer.parseInt(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static String convertIntegerFromColorCode(int n) {
        String ahex = Integer.toHexString(n);
        String hex = "";
        for (int i = 0; i < 6 - ahex.length(); i++) {
            hex += "0";
        }
        hex += ahex;
        return hex;
    }


    public static int[] convertRGBFromColorCode(String hex) {
        char[] ch = hex.toCharArray();
        char[] r = {ch[0], ch[1]};
        char[] g = {ch[2], ch[3]};
        char[] b = {ch[4], ch[5]};
        int[] rgb = new int[3];
        rgb[0] = Integer.parseInt(new String(r), 16);
        rgb[1] = Integer.parseInt(new String(g), 16);
        rgb[2] = Integer.parseInt(new String(b), 16);
        return rgb;
    }

    public static int difference(int num1, int num2) {
        return difference(convertIntegerFromColorCode(num1), convertIntegerFromColorCode(num2));
    }

    public static int difference(String color1, String color2) {
        int[] rgb1 = convertRGBFromColorCode(color1);
        int[] rgb2 = convertRGBFromColorCode(color2);
        int rd = IKSGMath.differenceInt(rgb1[0], rgb2[0]);
        int gd = IKSGMath.differenceInt(rgb1[1], rgb2[1]);
        int bd = IKSGMath.differenceInt(rgb1[2], rgb2[2]);
        return IKSGMath.averageInt(rd, gd, bd);
    }
}
