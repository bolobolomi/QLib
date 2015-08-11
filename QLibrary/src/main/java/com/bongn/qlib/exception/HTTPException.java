package com.bongn.qlib.exception;

/**
 * Created by Qiang on 15/7/23.
 */
public class HTTPException extends Exception {
    private static final long serialVersionUID = 1L;
    private int respCode = -1;

    public HTTPException(Throwable cause) {
        super(cause);
    }

    public HTTPException(int respCode, String respBody) {
        super(respBody);
        this.respCode = respCode;
    }

    public int getResponseCode() {
        return this.respCode;
    }

    public String toString() {
        if(this.respCode != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Response code: ");
            sb.append(this.respCode);
            sb.append(", body: ");
            sb.append(this.getMessage());
            return sb.toString();
        } else {
            return super.toString();
        }
    }
}

