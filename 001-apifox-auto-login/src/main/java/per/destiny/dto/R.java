package per.destiny.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    private int code;    // 状态码（如200成功，500失败）
    private String msg;  // 提示信息
    private T data;      // 返回的数据（泛型）

    // 成功响应（无数据）
    public static <T> R<T> ok() {
        return ok(null);
    }

    // 成功响应（带数据）
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMsg("success");
        r.setData(data);
        return r;
    }

    // 失败响应
    public static <T> R<T> fail(int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}