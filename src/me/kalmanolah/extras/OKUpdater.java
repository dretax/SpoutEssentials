package me.kalmanolah.extras;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

public class OKUpdater
{
  public static void update(String name, String ver, String checkloc, String dlloc, Logger log, String prefix)
  {
    try
    {
      String latestver = null;

      log.info(prefix + "Initiating auto-update...");

      latestver = OKReader.main(checkloc + "?id=" + name);

      if (latestver != null)
      {
        String[] halve = latestver.split("\\&");

        String[] newver = halve[0].split("\\.");

        String[] oldver = ver.split("\\.");

        int g = 0;

        int h = 0;

        for (String s : newver)
        {
          g++;
        }

        for (String r : oldver)
        {
          h++;
        }

        String isnew = null;

        if (g > h)
        {
          isnew = "yes";
        }
        else if ((g == h) || (g < h))
        {
          int z = 0;

          while ((z < g - 1) || ((z == g - 1) && (isnew == null)))
          {
            if (Integer.parseInt(newver[z]) > Integer.parseInt(oldver[z]))
            {
              isnew = "yes";
            }

            if (Integer.parseInt(newver[z]) < Integer.parseInt(oldver[z]))
            {
              isnew = "no";
            }

            z++;
          }

        }

        if (isnew == null)
        {
          isnew = "no";
        }

        if (isnew.equals("yes"))
        {
          log.info(prefix + "A new version of " + name + ", v" + halve[0] + " is available.");

          new File("plugins" + File.separator + name).mkdir();

          new File("plugins" + File.separator + name + File.separator + "update").mkdir();

          File file = new File("plugins" + File.separator + name + File.separator + "update" + File.separator + name + "-" + halve[0] + "-" + halve[1]);

          if (!file.exists())
          {
            log.info(prefix + "Starting download of " + name + " v" + halve[0] + "...");

            URL url = new URL(dlloc + "?id=" + name + "&ver=" + halve[0] + "&mc=1");

            File f = new File("plugins/spoutEssentials.jar");
            f.delete();

            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("plugins/spoutEssentials.jar");
            fos.getChannel().transferFrom(rbc, 0L, 16777216L);
            fos.close();
          }
          else
          {
            log.info(prefix + "You already have the latest version of " + name + " in your /plugins/" + name + "/update/ folder.");
          }

        }
        else
        {
          log.info(prefix + "You already have the latest version of " + name + ".");
        }
      }

    }
    catch (Exception e)
    {
      log.info(prefix + "Error while checking for latest version.");
    }
  }
}