package de.b4ckf1sh.mwowarhornreplacer;

import java.io.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Main {

    private static final File mwoBase = new File("C:\\MechWarrior Online\\Game");

    public static void main(String[] args) throws IOException {
        //read from PAK
        File gameData = new File(mwoBase.getPath() + "\\GameData.pak");
        ZipFile zipFile = new ZipFile(gameData);
        ZipEntry entry = zipFile.getEntry("Libs/Items/Modules/CockpitItems.xml");
        InputStream stream = zipFile.getInputStream(entry);
        String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));


        //replace warhorns
        String newXml = replaceSolarisWarhorns(result);

        //write to PAK
        FileOutputStream fos = new FileOutputStream(mwoBase.getPath() + "\\zzWarhornMod.pak");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        ZipEntry zipEntry = new ZipEntry("Libs/Items/Modules/CockpitItems.xml");

        zipOut.putNextEntry(zipEntry);
        int buffer = newXml.getBytes().length;
        zipOut.write(newXml.getBytes(),0,buffer);

        zipOut.close();
        fos.close();


    }

    //replaces S4-10 warhorns with S2 warhorns.
    private static String replaceSolarisWarhorns(String xml) {
        WarhornStats s2part = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s_oct2018_generic_warhorn:s_oct2018_generic_warhorn:s_oct2018_generic_warhorn",
                "s7s2_warhorn_d.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/solaris_warhorn_october_2018_d\"");
        WarhornStats s2gold = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s_oct2018_gold_warhorn:s_oct2018_gold_warhorn:s_oct2018_gold_warhorn",
                "s7s2_warhorn_a.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/solaris_warhorn_october_2018_a\"");
        WarhornStats s2silver = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s_oct2018_silver_warhorn:s_oct2018_silver_warhorn:s_oct2018_silver_warhorn",
                "s7s2_warhorn_b.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/solaris_warhorn_october_2018_b\"");
        WarhornStats s2bronze = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s_oct2018_br_warhorn:s_oct2018_br_warhorn:s_oct2018_br_warhorn",
                "s7s2_warhorn_c.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/solaris_warhorn_october_2018_c\"");


        WarhornStats s4part = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s7s4_generic_warhorn:s7s4_generic_warhorn:s7s4_generic_warhorn",
                "s7s4_warhorn_d.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_d\"");
        WarhornStats s4gold = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s7s4_gold_warhorn:s7s4_gold_warhorn:s7s4_gold_warhorn",
                "s7s4_warhorn_a.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a\"");
        WarhornStats s4silver = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s7s4_silver_warhorn:s7s4_silver_warhorn:s7s4_silver_warhorn",
                "s7s4_warhorn_b.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b\"");
        WarhornStats s4bronze = new WarhornStats(
                "objects/purchasable/cockpit_mounted/speaker/s7s2_warhorn/s7s2_warhorn_generic.cga",
                "Sounds/warhorns/s7s4_bronze_warhorn:s7s4_bronze_warhorn:s7s4_bronze_warhorn",
                "s7s4_warhorn_c.png",
                "objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c\"");

        xml = WarhornStats.replaceWarhornStats(xml, s4part, s2part);
        xml = WarhornStats.replaceWarhornStats(xml, s4gold, s2gold);
        xml = WarhornStats.replaceWarhornStats(xml, s4silver, s2silver);
        xml = WarhornStats.replaceWarhornStats(xml, s4bronze, s2bronze);

        //S4 models
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_d.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div1.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div1.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div1.cga", s2part.getModel());

        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div2.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div2.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div2.cga", s2part.getModel());

        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div3.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div3.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div3.cga", s2part.getModel());

        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div4.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div4.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div4.cga", s2part.getModel());

        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div5.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div5.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div5.cga", s2part.getModel());

        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div6.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div6.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div6.cga", s2part.getModel());

        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_a_div7.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_b_div7.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s4_warhorn_c_div7.cga", s2part.getModel());

        //S5-10 models
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s5_warhorn_a.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s6_warhorn_a.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s7_warhorn_a.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s8_warhorn_a.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s9_warhorn_a.cga", s2part.getModel());
        xml = xml.replaceAll("objects/purchasable/cockpit_mounted/speaker/s7s4_warhorn/s7s10_warhorn_a.cga", s2part.getModel());

        return xml;
    }


}
