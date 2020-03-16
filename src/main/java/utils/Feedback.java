package utils;

import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONObject;
public class Feedback {

    public static final String STATUS_SUCCESS           = "200"; // 成功
    public static final String STATUS_NO_CONTENT        = "204"; // 请求处理成功, 无回复
    public static final String STATUS_PARTIAL_CONTENT   = "206"; // 获取部分资源
    public static final String STATUS_BAD_REQUEST       = "400"; // 客户端无法理解请求
    public static final String STATUS_PERMISSION_DENIED = "401"; // 未经授权
    public static final String STATUS_ACCESS_FORBID     = "403"; // 资源禁止访问
    public static final String STATUS_PAGE_NOT_FOND     = "404"; // 请求资源不存在
    public static final String STATUS_UNKNOWN_ERROR     = "500"; // 服务器出现故障
    public static final String STATUS_ERROR             = "501"; // 可控的错误 (自定义)
    public static final String STATUS_NOT_LOGGED_IN     = "502"; // 未登录 (自定义)
    public static final String STATUS_SERVER_BUSY       = "503"; // 服务器忙碌

    private static String getInfoFromStatus(String status) {
        String info;
        switch (status) {
            case STATUS_SUCCESS:            info = "操作成功";   break;
            case STATUS_PERMISSION_DENIED:  info = "权限不足";   break;
            case STATUS_PAGE_NOT_FOND:      info = "找不到页面"; break;
            case STATUS_UNKNOWN_ERROR:      info = "未知错误";   break;
            case STATUS_ERROR:              info = "操作失败";   break;
            case STATUS_NOT_LOGGED_IN:      info = "未登录";     break;
            default: info = "系统繁忙";
        }
        return info;
    }

    public static JSONObject status(String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", getInfoFromStatus(status));
        jsonObject.put("data", "");
        jsonObject.put("status", status);
        return jsonObject;
    }

    public static JSONObject info(String info, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", info);
        jsonObject.put("data", "");
        jsonObject.put("status", status);
        return jsonObject;
    }

    public static JSONObject object(Object object, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", "");
        jsonObject.put("data", JSON.toJSON(object));
        jsonObject.put("status", status);
        return jsonObject;
    }

    public static JSONObject jsonArray(JSONArray jsonArray, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", "");
        jsonObject.put("data", jsonArray);
        jsonObject.put("status", status);
        return jsonObject;
    }

    public static JSONObject jsonObject(JSONObject feedbackJsonObject, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", "");
        jsonObject.put("data", feedbackJsonObject);
        jsonObject.put("status", status);
        return jsonObject;
    }

}
