package com.example.hello.DumpanLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
    private static final Logger logger = LoggerFactory.getLogger(Utility.class);

    public static void dumpAndLog(String message) {
        logger.info(message);  // Ghi thông tin vào log
    }
}