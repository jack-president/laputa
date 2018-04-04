package com.laputa.foundation.web.zookeeper.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZookeeperService {

    @Value("${zookeeper.address:127.0.0.1:2181}")
    private String zookeeperAddress;

    public List<Object> demo() {
        return null;
    }
}
