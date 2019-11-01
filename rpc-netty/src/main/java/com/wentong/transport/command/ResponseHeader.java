package com.wentong.transport.command;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@Data
public class ResponseHeader extends Header {

    private int code;
    private String response;

    @Override
    public int length() {
        return 3 * Integer.BYTES + Integer.BYTES + (StringUtils.isBlank(response) ? 0 : response.getBytes(StandardCharsets.UTF_8).length);
    }
}
