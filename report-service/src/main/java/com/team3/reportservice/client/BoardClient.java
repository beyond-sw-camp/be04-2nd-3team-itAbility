package com.team3.reportservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="itability-board-service", url="localhost:8000")
public interface BoardClient {

}
