package com.kisan.dto;

import java.time.LocalDateTime;

public record ApiResponse(LocalDateTime timeStamp, String message){}
