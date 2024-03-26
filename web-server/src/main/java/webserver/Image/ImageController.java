package webserver.Image;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {
    final private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{memberId}")
    public String showModifyImage(@PathVariable long memberId, Model model){
        model.addAttribute(memberId);
        return "mypage/modify-image";
    }
    @PostMapping("/{memberId}")
    public String modifyImage(Model model, @RequestParam MultipartFile imgFile, RedirectAttributes rttr, @RequestParam long memberId) throws IOException {
        imageService.postImage(memberId,imgFile);
        return "redirect:/mypage/" + memberId;
    }
}
