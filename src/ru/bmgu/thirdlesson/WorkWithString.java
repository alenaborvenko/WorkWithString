/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bmgu.thirdlesson;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author alenk
 */
public class WorkWithString {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String txt = "\t\tРоссия перебросила на восток Ливии несколько сотен сирийских наемников,подготовкой которых занимались российские специалисты. В этом ее обвинило турецкое агентство Anadolu.\n"
                + "\n"
                + "\tКак сообщило агентство со ссылкой на местные источники,в Ливию были отправлены около 400 сирийцев.18 сентября их перебросили из района неподалеку от города Камышлы, где они проходили обучение под руководством россиян, в сирийскую провинцию Латакия, где располагается российская авиабаза Хмеймим, а оттуда — в Ливию.\n"
                + "\n"
                + "\t«Москва пытается тем самым переломить ситуацию в Ливии,где в последние месяцы Хафтар теряет позиции перед натиском правительственных войск Ливии», — заявило Anadolu.\n"
                + "\n"
                + "\tПо данным агентства, каждому из сирийских наемников российские военные выплачивают порядка 1,5-2 тысячи долларов в месяц.\n"
                + "\n"
                + "\tВ Ливии продолжается противостояние между правительством национального согласия (ПНС),которое контролирует Триполи и территории на западе страны и поддерживается Турцией, и Ливийской национальной армией под командованием фельдмаршала Халифы Хафтара, поддержку которому оказывают Египет и Саудовская Аравия.\t\tЕе неофициальными союзниками являются Франция и ОАЭ — последние, по некоторым данным, поставляли армии технику, в том числе приобретенную у России. Кроме того, Россию неоднократно обвиняли в том, что в Ливии находятся ее наемники. В частности, утверждалось, что на стороне Хафтара сражаются около 2 тысяч бойцов из частной военной компании {(ЧВК) Вагнера}.";
// ************-----StringBuilder и паттерн
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я0-9]+", Pattern.UNICODE_CHARACTER_CLASS
                | Pattern.CASE_INSENSITIVE);
        long start = System.currentTimeMillis();
        String txtWithWords = printByWord1(txt, pattern);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;

        try {
            FileWriter txtStringBuilder = new FileWriter("BuielderWithPatterns.txt");
            txtStringBuilder.write("StringBuilder with pattern\n");
            txtStringBuilder.write(txtWithWords);
            txtStringBuilder.write("\n\n");
            txtStringBuilder.close();
            System.out.println("StringBuilder\nTime: " + elapsed);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithString.class.getName()).log(Level.SEVERE, null, ex);
        }
        // ************-----Pattern matches
        start = System.currentTimeMillis();
        txtWithWords = printByWord3(txt, pattern);
        finish = System.currentTimeMillis();
        elapsed = finish - start;

        try {
            FileWriter txtStringBuilder = new FileWriter("WithPatternConcat.txt");
            txtStringBuilder.write("WithPatternConcat\n");
            txtStringBuilder.write(txtWithWords);
            txtStringBuilder.write("\n\n");
            txtStringBuilder.close();
            System.out.println("WithPatternConcat\nTime: " + elapsed);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithString.class.getName()).log(Level.SEVERE, null, ex);
        }
//**************----------concat и сплит
        start = System.currentTimeMillis();
        txtWithWords = printByWord2(txt);
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        try {
            FileWriter txtStringBuilder = new FileWriter("WithSplit.txt");
            txtStringBuilder.write("Split\n");
            txtStringBuilder.write(txtWithWords);
            txtStringBuilder.write("\n\n");
            txtStringBuilder.close();
            System.out.println("concat split\nTime: " + elapsed);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithString.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        //**************----------StringTokenizer
        start = System.currentTimeMillis();
        txtWithWords = printByWord4(txt);
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        try {
            FileWriter txtStringBuilder = new FileWriter("Tokenizer.txt");
            txtStringBuilder.write("Tokenizer\n");
            txtStringBuilder.write(txtWithWords);
            txtStringBuilder.write("\n\n");
            txtStringBuilder.close();
            System.out.println("Tokenizer\nTime: " + elapsed);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithString.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
//**************------- метод с использованием Билдера и паттерна

    private static String printByWord1(String txt, Pattern pattern) {
        StringBuilder txtRes = new StringBuilder("");
        Matcher matcher = pattern.matcher(txt);
        int count = 0;
        while (matcher.find()) {
            txtRes.append(matcher.group()).append("\n");
            count++;
        }
        txtRes.append("Total words: ").append(count);
        return txtRes.toString();
    }
// *************--------methods concat split

    private static String printByWord2(String txt) {
        String[] txtTemp = txt.split("[^а-яА-Яa-zA-z_0-9]+");
        String txtResult = "";
        for (String string : txtTemp) {
            txtResult += string + "\n";
        }
        txtResult += "Total words: " + txtTemp.length;
        return txtResult;
    }
//*************** pattern and matcher concat

    private static String printByWord3(String txt, Pattern pattern) {
        String txtResult = "";
        Matcher matcher = pattern.matcher(txt);
        int count = 0;
        while (matcher.find()) {
            txtResult += matcher.group() + "\n";
            count++;
        }

        txtResult += "\nTotal words: " + count;
        return txtResult;
    }
//***************** Tokenizer

    private static String printByWord4(String txt) {
        StringTokenizer st = new StringTokenizer(txt, "\t\n\s .,{}()—«»-");
        String txtResult = "";
        int count = 0;
        while (st.hasMoreTokens()) {
            txtResult += st.nextToken() + "\n";
            count++;
        }
        txtResult += "\nTotal words: " + count;
        return txtResult;
    }
}
