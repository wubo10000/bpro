package com.bms.system.filter;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class CaptchaSingleton extends ListImageCaptchaEngine
{
    private static CaptchaSingleton instance = new CaptchaSingleton();

    private static Logger log = Logger.getLogger(CaptchaSingleton.class);

    private static final String NUMERIC_CHARS = "23456789";// No numeric 0

    // private static final String LOWER_ASCII_CHARS = "abcdefghjkmnpqrstxyz";//
    // No

    private static final String UPPER_ASCII_CHARS = "ABCDEFGHJKLMNPQRSTXYZ";// No

    public static CaptchaSingleton getInstance()
    {
        return instance;
    }

    int fontSize = 50;

    ImageCaptcha imageCaptcha = null;

    int imageHeight = 100;

    int imageWidth = 250;

    int maxWordLength = 5;

    int minWordLength = 4;

    private CaptchaSingleton()
    {
    }

    protected void buildInitialFactories()
    {
        int minWordLength = 4;
        int maxWordLength = 5;
        int fontSize = 50;
        int imageWidth = 250;
        int imageHeight = 100;

        WordGenerator words = new RandomWordGenerator(NUMERIC_CHARS
                + UPPER_ASCII_CHARS);

        // word2image components
        TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength,
                maxWordLength, new RandomListColorGenerator(new Color[]{
                        new Color(23, 170, 27), new Color(220, 34, 11),
                        new Color(23, 67, 172)}), new TextDecorator[]{});
        BackgroundGenerator background = new UniColorBackgroundGenerator(
                imageWidth, imageHeight, Color.white);
        FontGenerator font = new RandomFontGenerator(fontSize, fontSize,
                new Font[]{new Font("nyala", Font.BOLD, fontSize),
                        new Font("Bell MT", Font.PLAIN, fontSize),
                        new Font("Credit valley", Font.BOLD, fontSize)});

        ImageDeformation postDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation backDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation textDef = new ImageDeformationByFilters(
                new ImageFilter[]{});

        WordToImage word2image = new DeformedComposedWordToImage(font,
                background, randomPaster, backDef, textDef, postDef);
        addFactory(new GimpyFactory(words, word2image));
    }

    public boolean validateCaptchaResponse(String validateCode,
            HttpSession session)
    {
        boolean flag = true;
        try
        {
            imageCaptcha = (ImageCaptcha) session.getAttribute("imageCaptcha");
            if (imageCaptcha == null || validateCode == null
                    || "".equals(validateCode))
            {
                // //log.info("validateCaptchaResponse returned false due to
                // imageCaptcha is null");
                flag = false;
            }
            else
            {
                // validateCode = validateCode.toLowerCase();// use upper case
                // for
                validateCode = validateCode.toUpperCase();// use upper case for
                // easier usage
                flag = (imageCaptcha.validateResponse(validateCode))
                        .booleanValue();
            }
            session.removeAttribute("imageCaptcha");
            return flag;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            log.error("校验码校验异常 ＝＝ " + ex);
            return false;
        }
    }

    /**
     * Write the captcha image of current user to the servlet response
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @throws IOException
     */
    public void writeCaptchaImage(HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {

        imageCaptcha = getNextImageCaptcha();
        HttpSession session = request.getSession();
        session.setAttribute("imageCaptcha", imageCaptcha);
        BufferedImage image = (BufferedImage) imageCaptcha.getChallenge();

        OutputStream outputStream = null;
        try
        {
            outputStream = response.getOutputStream();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setContentType("image/jpeg");

            JPEGImageEncoder encoder = JPEGCodec
                    .createJPEGEncoder(outputStream);
            encoder.encode(image);

            outputStream.flush();
            outputStream.close();
            outputStream = null;// no close twice
        }
        catch (IOException ex)
        {
            log.error("产生图片异常 ＝＝ " + ex);
            throw ex;
        }
        finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException ex)
                {
                    log.error(ex.getMessage(), ex);
                }
            }
            imageCaptcha.disposeChallenge();
        }
    }

}
