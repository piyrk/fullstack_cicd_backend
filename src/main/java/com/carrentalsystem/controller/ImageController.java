@RestController
@RequestMapping("/api/images")
public class ImageController {

private final S3Service s3Service;

public ImageController(S3Service s3Service) {
this.s3Service = s3Service;
}

@PostMapping("/variant/upload")
public ResponseEntity<?> uploadVariant(@RequestParam("file") MultipartFile file) {
try {
String url = s3Service.upload(file, "variant-image");
return ResponseEntity.ok(Map.of("url", url));
} catch (Exception e) {
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body(Map.of("error", e.getMessage()));
}
}

@PostMapping("/license/upload")
public ResponseEntity<?> uploadLicense(@RequestParam("file") MultipartFile file) {
try {
String url = s3Service.upload(file, "license-image");
return ResponseEntity.ok(Map.of("url", url));
} catch (Exception e) {
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body(Map.of("error", e.getMessage()));
}
}
}
