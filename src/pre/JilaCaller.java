package pre;

import pre.JilaListener;
import pre.JilaParser;
import pre.JilaUpdater;

import java.util.ArrayList;
import java.util.Map;

public class JilaCaller
{

    private final String name;
    private final JilaParser parser;

    public JilaCaller(String name)
    {
        this.name = name;

        parser = new JilaParser(name);
        parser.parse();
    }

    public void call(String key)
    {
        Map<String, ArrayList<String>> tags = parser.tagger(key);
    }

    public String get(String key)
    {
        Map<String, ArrayList<String>> tags = parser.tagger(key);
        return tags.get("val").get(0);
    }

    public void set(String key, String value)
    {
        Map<String, ArrayList<String>> tags = parser.tagger(key);

        if (tags.get("type").get(0).equals("val")) JilaUpdater.valUpdate(name, key, value);

    }


}
