package pre;

import fileReading.TextReading;
import stringHandling.Parsing;

import java.io.File;
import java.util.ArrayList;

public class JilaListener
{

    public static void listen()
    {
        String[] paths = getFilePaths();
        for (String p: paths) reshape(p);
    }

    private static String[] getFilePaths()
    {
        String[] paths = null;

        File dir = new File("src");

        if (dir.exists() && dir.isDirectory())
        {
            File[] files = dir.listFiles();

            if (files != null)
            {
                paths = new String[files.length];
                for (int i = 0; i < files.length; i++) paths[i] = files[i].getAbsolutePath();
            }
        }

        return paths;
    }

    private static void reshape(String path)
    {
        ArrayList<String> lines = TextReading.read(path);

        for (int i = 0; i < lines.size(); i++)
        {
            if (lines.get(i).trim().contains("new JilaCaller") &&
                    Parsing.isString(lines.get(i).trim(), lines.get(i).trim().indexOf("new JilaCaller"), lines.get(i).trim().indexOf("new JilaCaller") + 12))
            {

            }
        }
    }


}
