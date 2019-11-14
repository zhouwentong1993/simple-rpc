package com.wentong.transport.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@Data
public class ResponseHeader extends Header {

    private int code;
    private String response;

    public ResponseHeader(int type, int version, int requestId,  Throwable throwable) {
        this(type, version, requestId, Code.UNKNOWN_ERROR.getCode(), throwable.getMessage());
    }

    public ResponseHeader(int type, int version, int requestId) {
        this(type, version, requestId, Code.SUCCESS.getCode(), null);
    }

    public ResponseHeader( int type, int version, int requestId , int code, String response) {
        super(type, version, requestId);
        this.code = code;
        this.response = response;
    }

    @Override
    public int length() {
        return 3 * Integer.BYTES + Integer.BYTES + (StringUtils.isBlank(response) ? 0 : response.getBytes(StandardCharsets.UTF_8).length);
    }
}
