/**
 * <p>Title: 飞鱼论坛</p>
 * <p>Description: 飞鱼论坛－－格式化图片共用类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 飞鱼工作室</p>
 * @author 飞鱼
 * @version 2.0
 */

package com.wondersgroup.falcon.Util;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.awt.image.*;
import javax.swing.ImageIcon;
import com.sun.image.codec.jpeg.*;
import org.apache.log4j.Logger;

public final class ImageUtil {

	private static Logger log = Logger.getLogger(ImageUtil.class);

    private ImageUtil() {// prevent instantiation
    }

    /**
     * @todo: xem lai ham nay, neu kich thuoc nho hon max thi ta luu truc tiep
     *          inputStream xuong thumbnailFile luon
     *
     * This method create a thumbnail and reserve the ratio of the output image
     * NOTE: This method closes the inputStream after it have done its work.
     *
     * @param : inputStream     the stream of a jpeg file
     * @param : thumbnailFile   the output file, must have the ".jpg" extension
     * @param : maxWidth        the maximun width of the image
     * @param : maxHeight       the maximun height of the image
     */
    public static void createThumbnail(InputStream inputStream, String thumbnailFile, int maxWidth, int maxHeight)
        throws IOException, Exception {
        //boolean useSun = false;
        String lowerName = thumbnailFile.toLowerCase();
        if (!lowerName.endsWith(".jpg")) throw new Exception("不支持 '.jpg'以外的图片格式");

        OutputStream outputStream = null;
        try {
            //JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(inputStream);
            //BufferedImage srcImage = decoder.decodeAsBufferedImage();
            byte[] srcByte = FileUtil.getBytes(inputStream);
            ImageIcon imageIcon = new ImageIcon(srcByte);
            Image srcImage = imageIcon.getImage();

            int imgWidth  = srcImage.getWidth(null);
            int imgHeight = srcImage.getHeight(null);
            //log.trace("width = " + imgWidth + " height = " + imgHeight);
            if ( (imgWidth <= 0) || (imgHeight <= 0) ) {
                // imgWidth or imgHeight could be -1, which is considered as an assertion
                throw new Exception("Assertion: ImageUtil: 不能得到图片的大小.");
            }

            // Set the scale.
            AffineTransform tx = new AffineTransform();
            if ((imgWidth > maxWidth) || (imgHeight > maxHeight)) {
                double scaleX = (double)maxWidth/imgWidth;
                double scaleY = (double)maxHeight/imgHeight;
                double scaleRatio = (scaleX < scaleY) ? scaleX : scaleY;
                imgWidth  = (int)(imgWidth  * scaleRatio);
                imgHeight = (int)(imgHeight * scaleRatio);
                // scale as needed
                tx.scale(scaleRatio, scaleRatio);
            } else {// we dont need any transform here, just save it to file and return
                outputStream = new FileOutputStream(thumbnailFile);
                outputStream.write(srcByte);
                return;
            }

            // create thumbnail image
            BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = bufferedImage.createGraphics();
            boolean useTransform = false;
            if (useTransform) {// use transfrom to draw
                //log.trace("use transform");
                g.drawImage(srcImage, tx, null);
            } else {// use java filter
                //log.trace("use filter");
                Image scaleImage = getScaledInstance(srcImage, imgWidth, imgHeight);
                g.drawImage(scaleImage, 0, 0, null);
            }
            g.dispose();// free resource

            // write it to disk
            outputStream = new FileOutputStream(thumbnailFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
            encoder.encode(bufferedImage);
        } catch (IOException e) {
            log.error("Error", e);
            throw e;
        } finally {// this finally is very important
            inputStream.close();
            if (outputStream != null) outputStream.close();
        }
    }

    /**
     * This method returns a fit-sized image for a source image,
     * this method retains the ratio of the source image
     */
    public static Image getFitSizeImage(Image srcImage, int fitWidth, int fitHeight)
        throws IOException {
        if ((fitWidth < 100) || (fitHeight < 100)) throw new IllegalArgumentException("长和宽必须大于100");

        int srcWidth  = srcImage.getWidth(null);//xem lai cho nay vi neu dung BufferedImage thi khong can null
        int srcHeight = srcImage.getHeight(null);
        //log.trace("src w = " + srcWidth + " h = " + srcHeight);

        // dont need any transforms
        if ((srcWidth == fitWidth) && (srcHeight == fitHeight)) return srcImage;

        int newWidth  = srcWidth;
        int newHeight = srcHeight;

        double fitRatio = (double)fitWidth / fitHeight;
        double srcRatio = (double)srcWidth / srcHeight;
        if (srcRatio > fitRatio) {// must cut the width of the source image
            newWidth = (int)(srcHeight * fitRatio);
        } else {// must cut the height of the source image
            newHeight = (int)(srcWidth / fitRatio);
        }
        //log.trace("new w = " + newWidth + " h = " + newHeight);

        ImageFilter cropFilter = new CropImageFilter((srcWidth-newWidth)/2, (srcHeight-newHeight)/2, newWidth, newHeight);
        ImageProducer cropProd = new FilteredImageSource(srcImage.getSource(), cropFilter);
        Image cropImage = Toolkit.getDefaultToolkit().createImage(cropProd);

        Image retImage = new ImageIcon(getScaledInstance(cropImage, fitWidth, fitHeight)).getImage();

        return retImage;
    }

    /**
     * This method returns a fit-sized image for a source image,
     * this method retains the ratio of the source image
     */
    public static Image getFitSizeImage(InputStream inputStream, int fitWidth, int fitHeight)
        throws IOException {
        try {
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(inputStream);
            BufferedImage srcImage = decoder.decodeAsBufferedImage();

            return getFitSizeImage(srcImage, fitWidth, fitHeight);
        } catch (IOException e) {
            log.error(" getFitSizeImage 运行错误：", e);
            throw e;
        } finally {// this finally is very important
            inputStream.close();
        }
    }

    public static void writeJpegImage_Sun(Image image, OutputStream outputStream) throws IOException {

        BufferedImage bufferedImage = null;
        if (image instanceof BufferedImage) {
            bufferedImage = (BufferedImage)image;
        } else {
            // 30% cpu resource
            bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 7.5 cpu
            Graphics2D g = bufferedImage.createGraphics();

            // 50% cpu
            g.drawImage(image, 0, 0, null);
            g.dispose();// free resource
        }

        // write it to disk
        // 12% cpu
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
        encoder.encode(bufferedImage);
    }

    public static void writeJpegImage_Sun(Image image, String fileName) throws IOException {
        OutputStream outputStream = new FileOutputStream(fileName);
        writeJpegImage_Sun(image, outputStream);
    }

    /*
     * 
     public static void writeJpegImage_nonSun(Image image, OutputStream outputStream) throws IOException {
        JpegEncoder encoder = new JpegEncoder(image, 80, outputStream);
        encoder.Compress();
    }
    

    public static void writeJpegImage_nonSun(Image image, String fileName) throws IOException {
        OutputStream outputStream = new FileOutputStream(fileName);
        writeJpegImage_nonSun(image, outputStream);
    }
    */

    public static Image getScaledInstance(Image srcImage, int width, int height) {
        boolean useSun = false;
        ImageFilter filter;
        if (useSun){
            //log.trace("use sun scalefilter");
            filter = new java.awt.image.AreaAveragingScaleFilter(width, height);
        } else {
            //log.trace("use nguoimau scalefilter");
            filter = new AreaAveragingScaleFilter(width, height);
        }
        ImageProducer prod = new FilteredImageSource(srcImage.getSource(), filter);
        Image newImage = Toolkit.getDefaultToolkit().createImage(prod);
        ImageIcon imageIcon = new ImageIcon(newImage);
        return imageIcon.getImage();
    }
/*
    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("c:\\PUTTY.RND");
            createThumbnail(is, "c:\\out.jpg", 120, 120);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.debug("done");
    }
*/
}





    /**
     * This class is taken from Pure Java AWT project
     * and modified to exclude PJAGraphicsManager class
     */
  // From java.awt.image.AreaAveragingScaleFilter
  // but without use of float type
  class AreaAveragingScaleFilter extends ImageFilter
  {
    private ColorModel rgbModel;
    private long []    alphas;
    private long []    reds;
    private long []    greens;
    private long []    blues;

    protected int      srcWidth;
    protected int      srcHeight;
    private   int []   srcPixels;
    protected int      destWidth;
    protected int      destHeight;
    protected int []   destPixels;

    {
      // Test if the class java.awt.image.ColorModel can be loaded
      //boolean classColorModelAccessible = PJAGraphicsManager.getDefaultGraphicsManager ().isClassAccessible ("java.awt.image.ColorModel");
      // modified by minhnn
      boolean classColorModelAccessible = isClassAccessible ("java.awt.image.ColorModel");
      if (classColorModelAccessible)
        rgbModel = ColorModel.getRGBdefault ();
    }

    /**
     * Constructs an AreaAveragingScaleFilter that scales the pixels from
     * its source Image as specified by the width and height parameters.
     * @param width  the target width to scale the image
     * @param height the target height to scale the image
     */
    public AreaAveragingScaleFilter (int width, int height)
    {
      destWidth = width;
      destHeight = height;
    }

    public void setDimensions (int w, int h)
    {
      srcWidth = w;
      srcHeight = h;
      if (destWidth < 0)
      {
        if (destHeight < 0)
        {
          destWidth = srcWidth;
          destHeight = srcHeight;
        }
        else
          destWidth = srcWidth * destHeight / srcHeight;
      }
      else if (destHeight < 0)
        destHeight = srcHeight * destWidth / srcWidth;

      consumer.setDimensions (destWidth, destHeight);
    }

    public void setHints (int hints)
    {
      // Images are sent entire frame by entire frame
      consumer.setHints (  (hints & (SINGLEPASS | SINGLEFRAME))
                         | TOPDOWNLEFTRIGHT);
    }

    public void imageComplete (int status)
    {
      if (   status == STATICIMAGEDONE
          || status == SINGLEFRAMEDONE)
        accumPixels (0, 0, srcWidth, srcHeight, rgbModel, srcPixels, 0, srcWidth);
      consumer.imageComplete (status);
    }

    public void setPixels (int x, int y, int width, int height,
                           ColorModel model, byte pixels [], int offset, int scansize)
    {
      // Store pixels in srcPixels array
      if (srcPixels == null)
        srcPixels = new int [srcWidth * srcHeight];
      for (int row = 0, destRow = y * srcWidth;
           row < height;
           row++, destRow += srcWidth)
      {
        int rowOff = offset + row * scansize;
        for (int col = 0; col < width; col++)
          // v1.2 : Added & 0xFF to disable sign bit
          srcPixels [destRow + x + col] = model.getRGB (pixels [rowOff + col] & 0xFF);
      }
    }

    public void setPixels (int x, int y, int width, int height,
                   ColorModel model, int pixels[], int offset, int scansize)
    {
      // Store pixels in srcPixels array
      if (srcPixels == null)
        srcPixels = new int [srcWidth * srcHeight];
      for (int row = 0, destRow = y * srcWidth;
           row < height;
           row++, destRow += srcWidth)
      {
        int rowOff = offset + row * scansize;
        for (int col = 0; col < width; col++)
          // If model == null, consider it's the default RGB model
          srcPixels [destRow + x + col] = model == null
                                                     ? pixels [rowOff + col]
                                                     : model.getRGB (pixels [rowOff + col]);
      }
    }

    private int [] calcRow ()
    {
      long mult = (srcWidth * srcHeight) << 32;
      if (destPixels == null)
        destPixels = new int [destWidth];

      for (int x = 0; x < destWidth; x++)
      {
        int a = (int)roundDiv (alphas [x], mult);
        int r = (int)roundDiv (reds   [x], mult);
        int g = (int)roundDiv (greens [x], mult);
        int b = (int)roundDiv (blues  [x], mult);
        a = Math.max (Math.min (a, 255), 0);
        r = Math.max (Math.min (r, 255), 0);
        g = Math.max (Math.min (g, 255), 0);
        b = Math.max (Math.min (b, 255), 0);
        destPixels [x] = (a << 24 | r << 16 | g << 8 | b);
      }

      return destPixels;
    }

    private void accumPixels (int x, int y, int w, int h,
                              ColorModel model, int [] pixels, int off,
                              int scansize)
    {
      reds   = new long [destWidth];
      greens = new long [destWidth];
      blues  = new long [destWidth];
      alphas = new long [destWidth];

      int sy = y;
      int syrem = destHeight;
      int dy = 0;
      int dyrem = 0;
      while (sy < y + h)
      {
        if (dyrem == 0)
        {
          for (int i = 0; i < destWidth; i++)
            alphas [i] =
            reds   [i] =
            greens [i] =
            blues  [i] = 0;

          dyrem = srcHeight;
        }

        int amty = Math.min (syrem, dyrem);
        int sx = 0;
        int dx = 0;
        int sxrem = 0;
        int dxrem = srcWidth;
        int a = 0,
            r = 0,
            g = 0,
            b = 0;
        while (sx < w)
        {
          if (sxrem == 0)
          {
            sxrem = destWidth;
            int rgb = pixels [off + sx];
            a = rgb >>> 24;
            r = (rgb >> 16) & 0xFF;
            g = (rgb >>  8) & 0xFF;
            b = rgb & 0xFF;
          }

          int  amtx = Math.min (sxrem, dxrem);
          long mult = (amtx * amty) << 32;
          alphas [dx] += mult * a;
          reds   [dx] += mult * r;
          greens [dx] += mult * g;
          blues  [dx] += mult * b;

          if ((sxrem -= amtx) == 0)
            sx++;

          if ((dxrem -= amtx) == 0)
          {
            dx++;
            dxrem = srcWidth;
          }
        }

        if ((dyrem -= amty) == 0)
        {
          int outpix [] = calcRow ();
          do
          {
            consumer.setPixels (0, dy, destWidth, 1,
                                rgbModel, outpix, 0, destWidth);
            dy++;
          }
          while ((syrem -= amty) >= amty && amty == srcHeight);
        }
        else
          syrem -= amty;

        if (syrem == 0)
        {
          syrem = destHeight;
          sy++;
          off += scansize;
        }
      }
    }

    public static int roundDiv (int dividend, int divisor)
    {
        final int remainder = dividend % divisor;
        if (Math.abs (remainder) * 2 <= Math.abs (divisor))
          return dividend / divisor;
        else
          if (dividend * divisor < 0)
            return dividend / divisor - 1;
          else
            return dividend / divisor + 1;
    }


    public static long roundDiv (long dividend, long divisor) {
        final long remainder = dividend % divisor;
        if (Math.abs (remainder) * 2 <= Math.abs (divisor))
          return dividend / divisor;
        else
          if (dividend * divisor < 0)
            return dividend / divisor - 1;
          else
            return dividend / divisor + 1;
    }

    public static boolean isClassAccessible (String className) {
        // Test if the class className can be loaded
        try {
          Class.forName (className);
          // Class can be loaded
          return true;
        }
        catch (ClassNotFoundException e)
        { }
        catch (LinkageError error)
        { }  // Thrown by some AWT classes which require awt library in static initializer.

        return false;
    }
  }
