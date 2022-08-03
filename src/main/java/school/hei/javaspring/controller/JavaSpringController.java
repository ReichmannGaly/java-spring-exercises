package school.hei.javaspring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
public class JavaSpringController {
    @PostMapping(value="/post",produces = {MediaType.IMAGE_JPEG_VALUE})
    public  @ResponseBody byte[] post(@RequestBody byte[] file){
        try {
            InputStream inputStream = new ByteArrayInputStream(file);

            BufferedImage bufferedImage = ImageIO.read(inputStream);

            BufferedImage result = new BufferedImage(
                    bufferedImage.getWidth(),
                    bufferedImage.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            graphic.dispose();

            ByteArrayOutputStream BAOUS = new ByteArrayOutputStream();
            ImageIO.write(result, "jpg", BAOUS);

            byte[] byteImage = BAOUS.toByteArray();
            return byteImage;

        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
