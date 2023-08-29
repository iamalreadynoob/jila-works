package pre;

import fileReading.TextReading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JilaParser
{

    private String path;
    private ArrayList<String> names;
    private ArrayList<ArrayList<String>> blocks;
    protected JilaParser(String name)
    {
        path = "src/jila/" + name + ".jl";

        names = new ArrayList<>();
        blocks = new ArrayList<>();
    }

    protected void parse()
    {
        ArrayList<String> lines = TextReading.read(path);

        int loc = 0;
        while (loc < lines.size())
        {
            if (lines.get(loc).isEmpty() || lines.get(loc).isBlank()) lines.remove(loc);
            else loc++;
        }

        loc = 0;

        while (loc < lines.size())
        {
            if (lines.get(loc).trim().startsWith("!"))
            {
                names.add(lines.get(loc).trim());
                loc++;

                ArrayList<String> temp = new ArrayList<>();
                while (loc < lines.size() && !lines.get(loc).trim().startsWith("!"))
                {
                    temp.add(lines.get(loc).trim());
                    loc++;
                }
                blocks.add(temp);
            }
            else loc++;
        }
    }

    protected Map<String, ArrayList<String>> tagger(String name)
    {
        Map<String, ArrayList<String>> tags = new HashMap<>();

        int index = names.indexOf(name);

        int loc = 0;

        while (loc < blocks.get(index).size())
        {
            if (blocks.get(index).get(loc).contains("="))
            {
                ArrayList<String> temp = new ArrayList<>();

                int subIndex = blocks.get(index).get(loc).indexOf("=");

                String tag = blocks.get(index).get(loc).substring(0, subIndex).trim();
                String val = blocks.get(index).get(loc).substring(subIndex+1).trim();
                temp.add(val);

                tags.put(tag, temp);

                loc++;
            }

            else if (blocks.get(index).get(loc).contains(":"))
            {
                ArrayList<String> temp = new ArrayList<>();

                int subIndex = blocks.get(index).get(loc).indexOf(":");

                String tag = blocks.get(index).get(loc).substring(0, subIndex);

                while (loc < blocks.get(index).size() && !blocks.get(index).get(loc).trim().equals("END"))
                {
                    temp.add(blocks.get(index).get(loc));
                    loc++;
                }

                tags.put(tag, temp);
            }

            else loc++;
        }


        return tags;
    }

}
