package com.vgrigoriev.algorithms.sorts;



public class SortTest {

    public static void main(String[] args) {
        String r = "Device.Services.VoiceService.1.ExtensionProfile.1.Extension.723";
        String r1 = "Device.Services.VoiceService.1.ExtensionProfile.1.Extension.81";
        String r2 = "Device.Services.VoiceService.1.ExtensionProfile.1.Extension.913.";




        r2 = r2.substring(0, r2.length() - 1);
        if (r1.charAt(r1.length() - 1) == '.') {
            r1 = r1.substring(0, r1.length() - 1);
        }
        System.out.println(r1.substring(r1.lastIndexOf(".")));
    }
}
