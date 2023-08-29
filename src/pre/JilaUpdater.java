package pre;

import fileReading.TextReading;
import fileWriting.TextWriting;

import java.util.ArrayList;

public class JilaUpdater
{

    protected static void valUpdate(String name, String key, String value)
    {
        ArrayList<String> lines = TextReading.read("src/jila/" + name + ".jl");

        int loc = 0;

        while (loc < lines.size())
        {
            if (lines.get(loc).trim().equals("!" + key))
            {
                while (loc < lines.size())
                {
                    if (lines.get(loc).trim().startsWith("val") && lines.get(loc).trim().contains("="))
                    {
                        lines.set(loc, "val = " + value);
                        break;
                    }
                    else loc++;
                }

                break;
            }
        }

        TextWriting.write("src/jila/" + name + ".jl", lines);
    }

}
