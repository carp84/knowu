package knowu.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Tool for converting GPS coordinates to Baidu coordinates
 */
public class Gps2BaiDu {

  private static Log LOG = LogFactory.getLog(Gps2BaiDu.class);

  public static void main(String[] args) throws IOException {
    double lng = 116.4677880782428;
    double lat = 39.90193009190567;
    System.out.println(convertGpsToBaidu(lng, lat));
  }

  /**
   * Convert Gps coordinates to Baidu coordinates
   * @param lng
   * @param lat
   * @return GPSPoint object containing the converted coordinates, or NULL if error in http response
   * @throws IOException
   */
  public static GPSPoint convertGpsToBaidu(double lng, double lat)
      throws IOException {
    GPSPoint result = null;
    // Baidu's http api for coordinate convertion
    // http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x=116.32715863448607&y=39.990912172420714&callback=BMap.Convertor.cbk_3694
    // gps type is 0
    // google type is 2
    // baidu type is 4
    String path =
        "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x=" + lng
            + "+&y=" + lat + "&callback=BMap.Convertor.cbk_7594";
    try {
      // Get convert result through http request
      URL url = new URL(path);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(5 * 1000);
      InputStream inStream = conn.getInputStream();

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len = 0;
      while ((len = inStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, len);
      }
      // Get the result
      String res = outStream.toString();
      LOG.debug("Result get from baidu api: " + res);
      // Parse the result
      if (res.indexOf("(") > 0 && res.indexOf(")") > 0) {
        String str = res.substring(res.indexOf("(") + 1, res.indexOf(")"));
        String err =
            res.substring(res.indexOf("error") + 7, res.indexOf("error") + 8);
        if ("0".equals(err)) {
          JSONObject js = JSONObject.fromObject(str);
          // Decoding
          String x1 = new String(Base64.decodeBase64(js.getString("x")));
          String y1 = new String(Base64.decodeBase64(js.getString("y")));
          LOG.debug("Converted coordinates: [" + x1 + "  " + y1 + "]");
          result = new GPSPoint();
          result.setLng(Double.parseDouble(x1));
          result.setLat(Double.parseDouble(y1));
        }
      }
    } catch (IOException e) {
      LOG.error("Failed to convert GPS coordinates", e);
      throw e;
    }
    return result;
  }

  public static class GPSPoint {
    private double lng;
    private double lat;

    public double getLng() {
      return lng;
    }

    public void setLng(double lng) {
      this.lng = lng;
    }

    public double getLat() {
      return lat;
    }

    public void setLat(double lat) {
      this.lat = lat;
    }

    @Override
    public String toString() {
      return lng + "," + lat;
    }
  }
}