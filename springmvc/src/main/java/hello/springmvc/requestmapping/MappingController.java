package hello.springmvc.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping-get-v1")
    public String mappingV1() {
        log.info("mapping-get-v1");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable Long userId) {
        log.info("mappingPath userId = {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑 조건에 맞는 파라미터가 들어올때만 호출됨. params = "mode" params = "!mode" params = "mode = debug" params = "mode !=
     * debug" params = {"mode != debug", "data=good"}
     */
    @GetMapping(path = "/mapping-param", params = "mode=debug")
    public String mappingPath() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 헤더로 추가 매핑 headers = "mode" headers = "!mode" headers = "mode = debug" headers = "mode != debug"
     */
    @GetMapping(path = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // request content-type으로 매핑. 맞지않으면 415 MediaType Not Supported 예외 발생
    @GetMapping(path = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }

    // request accept로 매핑. 맞지않으면 406 NotAcceptable 예외 발생.
    // String -> text/plain. map -> application/json (HttpMessageConverter?)
    @GetMapping(path = "/mapping-produces", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
}
