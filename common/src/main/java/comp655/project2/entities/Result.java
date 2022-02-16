package comp655.project2.entities;


import java.io.Serializable;
public class Result<T> implements Serializable {
    String code;
    String msg;
    T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Builder<T> {

        public Result<T> success() {
            Result<T> result = new Result<T>();
            result.setCode("0");
            result.setMsg("Successful operation");
            result.setData(null);
            return result;
        }

        public Result<T> success(T t) {
            Result<T> result = new Result<T>();
            result.setCode("0");
            result.setMsg("Successful operation");
            result.setData(t);
            return result;
        }
        public Result<T> success(String msg,T t) {
            Result<T> result = new Result<T>();
            result.setCode("0");
            result.setMsg(msg);
            result.setData(t);
            return result;
        }

        public Result<T> error() {
            Result<T> result = new Result<T>();
            result.setCode("-1");
            result.setMsg("Operation failed");
            result.setData(null);
            return result;
        }
        public Result<T> error(String meg) {
            Result<T> result = new Result<T>();
            result.setCode("-1");
            result.setMsg(meg);
            result.setData(null);
            return result;
        }
        public Result<T> error(T t) {
            Result<T> result = new Result<T>();
            result.setCode("-1");
            result.setMsg("Operation failed");
            result.setData(t);
            return result;
        }

        public Result<T> error(String msg, T t) {
            Result<T> result = new Result<T>();
            result.setCode("-1");
            result.setMsg(msg);
            result.setData(t);
            return result;
        }
    }
}