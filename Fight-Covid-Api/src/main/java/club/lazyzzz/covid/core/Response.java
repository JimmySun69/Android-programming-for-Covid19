package club.lazyzzz.covid.core;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Data
public class Response {
    private Integer code;

    private String msg;

    private Object data;

    public Response() {
    }

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response ok(@Nullable Object o) {
        return ok("", o);
    }

    public static Response ok(@NonNull String msg, @Nullable Object o) {
        return new Response(HttpStatus.OK.value(), msg, o);
    }
}
