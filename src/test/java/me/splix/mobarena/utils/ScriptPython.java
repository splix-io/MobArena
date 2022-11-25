package me.splix.mobarena.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ScriptPython {
    Process mProcess;

    public void runScript(String args){
        System.out.println("Stage 1");
        Process process;
        try{
            process = Runtime.getRuntime().exec("python src/test/java/me/splix/mobarena/utils/3DGraphicalDrawer.py " + args);
            mProcess = process;
        }catch(Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        System.out.println("Stage 2");
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        String line;
        System.out.println("Stage 3");
        try{
            while((line = reader.readLine()) != null){
                System.out.println("stdout: "+ line);
            }
        }catch(IOException e){
            System.out.println("Exception in reading output"+ e.toString());
        }
        System.out.println("Stage 4");
    }
}
