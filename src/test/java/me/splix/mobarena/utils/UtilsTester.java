package me.splix.mobarena.utils;

import org.bukkit.Location;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UtilsTester {


    @Test
    void whenTestCase1() {
        Location location1 = new Location(null, -0, -0, 0);
        Location location2 = new Location(null, 63, 27, 46);
        long currentTime = System.nanoTime();
        List<Location> locations = Utils.getOutlineBoarder(location1, location2, 1);
        StringBuilder args = new StringBuilder();
        long elapsedTime = System.nanoTime();
        for (Location locCal: locations)
            args.append(locCal.getX()).append(",").append(locCal.getY()).append(",").append(locCal.getZ()).append(":");
        System.out.println("Elapsed Time: " + TimeUnit.NANOSECONDS.toMillis(elapsedTime - currentTime));

        //Run Graphical Display
        ScriptPython scriptPython = new ScriptPython();
        scriptPython.runScript(args.toString());

    }



}
